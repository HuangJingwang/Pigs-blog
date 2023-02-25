package com.pigs.blog.service.impl;

import com.pigs.blog.common.ArticlesStatusEnum;
import com.pigs.blog.exception.ErrorCodeEnum;
import com.pigs.blog.common.PageData;
import com.pigs.blog.common.ResultResponse;
import com.pigs.blog.contract.request.ArticlesCreateRequest;
import com.pigs.blog.contract.request.ArticlesListRequest;
import com.pigs.blog.contract.request.ArticlesPageDataRequest;
import com.pigs.blog.contract.request.ArticlesUpdateRequest;
import com.pigs.blog.contract.response.ArticlesDetailResponse;
import com.pigs.blog.contract.response.ArticlesPreOrNextResponse;
import com.pigs.blog.contract.response.ArticlesSaveResponse;
import com.pigs.blog.exception.PigsBlogException;
import com.pigs.blog.mapper.ArticlesGroupMapper;
import com.pigs.blog.mapper.ArticlesMapper;
import com.pigs.blog.mapper.UserInfoMapper;
import com.pigs.blog.model.*;
import com.pigs.blog.model.criteria.ArticlesListCriteria;
import com.pigs.blog.contract.response.ArticlesListResponse;
import com.pigs.blog.mapper.ext.ArticlesMapperExt;
import com.pigs.blog.model.criteria.ArticlesPageCriteria;
import com.pigs.blog.service.ArticlesInterface;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Service
public class ArticlesInterfaceImpl implements ArticlesInterface {
    private static final Logger logger = LoggerFactory.getLogger(ArticlesInterfaceImpl.class);
    @Autowired
    private ArticlesMapperExt mapperExt;
    @Autowired
    private ArticlesMapper mapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private QiniuService qiniuService;
    @Autowired
    private ArticlesGroupMapper articlesGroupMapper;

    @Override
    public PageData<ArticlesListResponse> getPageData(ArticlesPageDataRequest request) {
        PageData<ArticlesListResponse> result = new PageData<>();
        ArticlesPageCriteria criteria = createCriteria(request.getPageNo(), request.getPageSize(), request);
        Long count = mapperExt.countArticlesList(criteria);
        if (count == 0) {
            result.setHasNext(false);
            result.setResultList(Collections.emptyList());
            result.setTotalResult(0L);
            return result;
        }

        List<Articles> list = mapperExt.selectArticlesList(criteria);
        List<ArticlesListResponse> resultList = copyList(list);
        result.setTotalResult(count);
        result.setResultList(resultList);
        result.setHasNext(((count + request.getPageSize() - 1) / request.getPageSize()) > request.getPageNo());
        return result;
    }

    /**
     * 更新文章
     *
     * @param id
     * @param request
     */
    @Override
    public void updateArticles(Long id, ArticlesUpdateRequest request) {
        ArticlesWithBLOBs articles = new ArticlesWithBLOBs();
        articles.setId(id);
        String pictureUrls = String.join(",", request.getArticlePictureUrl());
        articles.setArticlePictureUrl(pictureUrls);
        BeanUtils.copyProperties(request, articles);
        mapper.updateByPrimaryKeySelective(articles);
    }

    /**
     * 逻辑删除:
     * @param id
     */
    @Override
    public void deleteArticles(Long id) {
        ArticlesWithBLOBs articles = mapper.selectByPrimaryKey(id);
        ArticlesPageCriteria criteria = new ArticlesPageCriteria();
        criteria.setAccount(articles.getAccount());
        Long count = mapperExt.countArticlesList(criteria);
        if(count >= 20){
            throw new PigsBlogException(500,"回收箱最多只能回收20篇文章，你首先得彻底删除一些文章");
        }

        articles.setStatus(ArticlesStatusEnum.DELETED.getStatus());
        mapper.updateByPrimaryKeySelective(articles);
    }

    /**
     * 查找文章详情
     * 1. 查找文章的详细数据
     * 2. 将阅读量++
     *
     * @param id
     * @return
     */
    @Override
    public ArticlesDetailResponse getDetailArticles(Long id) {
        // 1
        ArticlesWithBLOBs articles = mapper.selectByPrimaryKey(id);
        ArticlesDetailResponse result = new ArticlesDetailResponse();

        ArticlesGroup articlesGroup = articlesGroupMapper.selectByPrimaryKey(articles.getGroupId());
        result.setGroupName(articlesGroup.getGroupName());

        result.setArticlePictureUrl(Arrays.asList(articles.getArticlePictureUrl().split(",")));
        BeanUtils.copyProperties(articles, result);

        // 2
        articles.setPageView(articles.getPageView() + 1);
        mapper.updateByPrimaryKeySelective(articles);

        //3 查询nickname
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andAccountEqualTo(articles.getAccount());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);

        if(CollectionUtils.isEmpty(userInfos)){
            throw new PigsBlogException(ErrorCodeEnum.NICKNAME_CANNOT_BE_FOUND_BY_ACCOUNT.getCode(),ErrorCodeEnum.NICKNAME_CANNOT_BE_FOUND_BY_ACCOUNT.getMsg());
        }

        UserInfo userInfo = CollectionUtils.firstElement(userInfos);
        result.setNickName(userInfo.getNickname());
        return result;
    }

    @Override
    public List<ArticlesListResponse> listArticlesByGroupId(Long groupId) {
        ArticlesExample example = new ArticlesExample();
        ArticlesExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        List<Articles> articles = mapper.selectByExample(example);
        return copyList(articles);
    }

    @Override
    public List<ArticlesListResponse> listArticlesByCriteria(ArticlesListRequest request) {
        ArticlesListCriteria criteria = createCriteria(request);
        List<Articles> list = mapperExt.selectArticlesListByCriteria(criteria);
        List<ArticlesListResponse> responseList = copyList(list);
        return responseList;
    }

    @Override
    public ArticlesPreOrNextResponse findPreArticle(Long curId) {
        ArticlesPreOrNextResponse result = new ArticlesPreOrNextResponse();

        ArticlesExample example = new ArticlesExample();
        ArticlesExample.Criteria criteria = example.createCriteria();
        criteria.andIdGreaterThan(curId);
        criteria.andStatusEqualTo(ArticlesStatusEnum.PUBLISHED.getStatus());
        long count = mapper.countByExample(example);
        List<Articles> articles = mapperExt.selectPreArticle(count + 1);
        if (!CollectionUtils.isEmpty(articles)) {
            BeanUtils.copyProperties(CollectionUtils.firstElement(articles), result);
        }
        return result;
    }

    @Override
    public ArticlesPreOrNextResponse findNextArticle(Long id) {
        ArticlesPreOrNextResponse result = new ArticlesPreOrNextResponse();

        ArticlesExample example = new ArticlesExample();
        ArticlesExample.Criteria criteria = example.createCriteria();
        criteria.andIdLessThanOrEqualTo(id);
        criteria.andStatusEqualTo(ArticlesStatusEnum.PUBLISHED.getStatus());
        long count = mapper.countByExample(example);
        List<Articles> articles = mapperExt.selectNextArticle(count);
        if (!CollectionUtils.isEmpty(articles)) {
            BeanUtils.copyProperties(CollectionUtils.firstElement(articles), result);
        }
        return result;
    }

    /**
     * 物理删除
     *
     * @param id
     */
    public void deleteForever(Long id) {
        ArticlesWithBLOBs articles = mapper.selectByPrimaryKey(id);
        String[] split = articles.getArticlePictureUrl().split(",");
        qiniuService.deleteImage(Arrays.asList(split));
        mapper.deleteByPrimaryKey(id);
    }

    public ArticlesListCriteria createCriteria(ArticlesListRequest request) {
        ArticlesListCriteria criteria = new ArticlesListCriteria();
        if (Strings.isNotBlank(request.getTags())) {
            criteria.setTags(request.getTags().split(","));
        }
        if (Strings.isNotBlank(request.getAccount())) {
            criteria.setAccount(request.getAccount());
        }
        if (request.getGroupId() != null) {
            criteria.setGroupId(request.getGroupId());
        }
        if (Strings.isNotBlank(request.getStatus())) {
            criteria.setStatus(request.getStatus());
        }
        return criteria;
    }

    @Override
    public ResultResponse saveArticles(ArticlesCreateRequest request) {
        //1.检测draft的当前用户的文章是否大于10,如果大于10就返回错误
        ArticlesExample articlesExample = new ArticlesExample();
        ArticlesExample.Criteria articlesCriteria = articlesExample.createCriteria();
        articlesCriteria.andAccountEqualTo(request.getAccount());
        articlesCriteria.andStatusEqualTo(ArticlesStatusEnum.DRAFT.getStatus());
        long count = mapper.countByExample(articlesExample);
        if (count >= 10 && request.getStatus().equals(ArticlesStatusEnum.DRAFT.getStatus())) {
            return ResultResponse.fail(ErrorCodeEnum.DRAFT_IS_OVER_TEN.getCode(), ErrorCodeEnum.DRAFT_IS_OVER_TEN.getMsg());
        }

        //2.保存数据
        ArticlesWithBLOBs articles = new ArticlesWithBLOBs();
        BeanUtils.copyProperties(request, articles);
        mapper.insertSelective(articles);

        //3.修改用户的文章数：文章数加1
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(request.getAccount());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(userInfos)) {
            logger.error("add articlesCount fail:because cannot find account: " + request.getAccount());
            return ResultResponse.fail(10004, "account not exist");
        }

        UserInfo userInfo = CollectionUtils.firstElement(userInfos);
        userInfo.setArticlesCount(userInfo.getArticlesCount() + 1);
        userInfoMapper.updateByPrimaryKey(userInfo);

        //4.返回当前的id
        Long id = mapperExt.getMaxId();
        articles.setId(id);
        ArticlesSaveResponse result = new ArticlesSaveResponse();
        BeanUtils.copyProperties(articles, result);

        return ResultResponse.success(result);
    }

    private List<ArticlesListResponse> copyList(List<Articles> list) {
        List<ArticlesListResponse> responses = new ArrayList<>();
        list.forEach(s -> {
            ArticlesListResponse response = new ArticlesListResponse();
            BeanUtils.copyProperties(s, response);
            responses.add(response);
        });
        return responses;
    }

    private ArticlesPageCriteria createCriteria(Integer pageNo, Integer pageSize, ArticlesPageDataRequest request) {
        ArticlesPageCriteria result = new ArticlesPageCriteria();
        if (Strings.isNotBlank(request.getAccount())) {
            result.setAccount(request.getAccount());
        }
        if (Strings.isNotBlank(request.getStatus())) {
            result.setStatus(request.getStatus());
        } else {
            result.setStatus(ArticlesStatusEnum.PUBLISHED.getStatus());
        }
        if (request.getOrderByPV()) {
            result.setOrderByPV(1);
        }
        result.setStart((pageNo - 1) * pageSize);
        result.setOffset(pageSize);
        return result;
    }
}
