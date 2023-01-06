package com.pigs.blog.service.impl;

import com.pigs.blog.common.PageData;
import com.pigs.blog.contract.request.ArticlesCreateRequest;
import com.pigs.blog.contract.request.ArticlesUpdateRequest;
import com.pigs.blog.contract.response.ArticlesDetailResponse;
import com.pigs.blog.mapper.ArticlesMapper;
import com.pigs.blog.model.Articles;
import com.pigs.blog.model.criteria.ArticlesListCriteria;
import com.pigs.blog.contract.request.ArticlesListRequest;
import com.pigs.blog.contract.response.ArticlesListResponse;
import com.pigs.blog.mapper.ext.ArticlesMapperExt;
import com.pigs.blog.service.ArticlesInterface;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Service
public class ArticlesInterfaceImpl implements ArticlesInterface {
    @Autowired
    private ArticlesMapperExt mapperExt;
    @Autowired
    private ArticlesMapper mapper;

    @Override
    public PageData<ArticlesListResponse> getPageData(ArticlesListRequest request) {
        PageData<ArticlesListResponse> result = new PageData<>();
        ArticlesListCriteria criteria = createCriteria(request.getPageNo(), request.getPageSize(), request);
        Long count = mapperExt.countArticlesList(criteria);
        if (count == 0) {
            result.setHasNext(false);
            result.setResultList(Collections.emptyList());
            result.setTotalResult(0L);
        }

        List<Articles> list = mapperExt.selectArticlesList(criteria);
        List<ArticlesListResponse> resultList = copyList(list);
        result.setTotalResult(count);
        result.setResultList(resultList);
        result.setHasNext(((count + request.getPageSize() - 1) / request.getPageSize()) > request.getPageNo());
        return result;
    }

    @Override
    public void updateArticles(ArticlesUpdateRequest request) {
        Articles articles = new Articles();
        BeanUtils.copyProperties(request, articles);
        mapper.updateByPrimaryKeySelective(articles);
    }

    @Override
    public void deleteArticles(Integer id) {
        Articles articles = new Articles();
        articles.setStatus("deleted");
        mapper.updateByPrimaryKeySelective(articles);
    }

    @Override
    public ArticlesDetailResponse getDetailArticles(Integer id) {
        Articles articles = mapper.selectByPrimaryKey(id);
        ArticlesDetailResponse result = new ArticlesDetailResponse();
        BeanUtils.copyProperties(articles,result);
        return result;
    }

    @Override
    public void saveArticles(ArticlesCreateRequest request) {
        Articles articles = new Articles();
        BeanUtils.copyProperties(request, articles);
        mapper.insertSelective(articles);
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

    private ArticlesListCriteria createCriteria(Integer pageNo, Integer pageSize, ArticlesListRequest request) {
        ArticlesListCriteria result = new ArticlesListCriteria();
        if (Strings.isNotBlank(request.getAuthor())) {
            result.setAuthor(request.getAuthor());
        }

        result.setStart(pageNo * pageSize);
        result.setOffset(pageSize);
        return result;
    }
}
