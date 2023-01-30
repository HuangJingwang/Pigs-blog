package com.pigs.blog.service.impl;

import com.pigs.blog.common.ArticlesStatusEnum;
import com.pigs.blog.common.PageData;
import com.pigs.blog.contract.request.ArticlesCreateRequest;
import com.pigs.blog.contract.request.ArticlesListRequest;
import com.pigs.blog.contract.request.ArticlesPageDataRequest;
import com.pigs.blog.contract.request.ArticlesUpdateRequest;
import com.pigs.blog.contract.response.ArticlesDetailResponse;
import com.pigs.blog.contract.response.ArticlesPreOrNextResponse;
import com.pigs.blog.contract.response.ArticlesSaveResponse;
import com.pigs.blog.mapper.ArticlesMapper;
import com.pigs.blog.mapper.UserInfoMapper;
import com.pigs.blog.mapper.UserMapper;
import com.pigs.blog.model.Articles;
import com.pigs.blog.model.ArticlesExample;
import com.pigs.blog.model.UserInfo;
import com.pigs.blog.model.UserInfoExample;
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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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

    @Override
    public void updateArticles(Long id, ArticlesUpdateRequest request) {
        Articles articles = new Articles();
        articles.setId(id);
        BeanUtils.copyProperties(request, articles);
        mapper.updateByPrimaryKeySelective(articles);
    }

    @Override
    public void deleteArticles(Long id) {
        Articles articles = new Articles();
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
        Articles articles = mapper.selectByPrimaryKey(id);
        ArticlesDetailResponse result = new ArticlesDetailResponse();
        BeanUtils.copyProperties(articles, result);

        // 2
        articles.setPageView(articles.getPageView() + 1);
        mapper.updateByPrimaryKey(articles);

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
        criteria.andIdLessThan(curId);
        long count = mapper.countByExample(example);
        if (count == 0) {
            return result;
        }
        long start = count - 1;
        List<Articles> articles = mapperExt.selectPreArticle(start);
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
        long count = mapper.countByExample(example);
        List<Articles> articles = mapperExt.selectNextArticle(count);
        if (!CollectionUtils.isEmpty(articles)) {
            BeanUtils.copyProperties(CollectionUtils.firstElement(articles), result);
        }
        return result;
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
        return criteria;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ArticlesSaveResponse saveArticles(ArticlesCreateRequest request) {
        //保存数据
        Articles articles = new Articles();
        BeanUtils.copyProperties(request, articles);
        mapper.insertSelective(articles);

        // 修改用户的文章数：文章数加1
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(request.getAccount());
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(userInfos)) {
            logger.error("add articlesCount fail:because cannot find account + " + request.getAccount());
            return null;
        }
        UserInfo userInfo = CollectionUtils.firstElement(userInfos);
        userInfo.setArticlesCount(userInfo.getArticlesCount() + 1);
        userInfoMapper.updateByPrimaryKey(userInfo);

        //返回当前的id
        Long id = mapperExt.getMaxId();
        articles.setId(id);
        ArticlesSaveResponse result = new ArticlesSaveResponse();
        BeanUtils.copyProperties(articles, result);

        return result;
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
