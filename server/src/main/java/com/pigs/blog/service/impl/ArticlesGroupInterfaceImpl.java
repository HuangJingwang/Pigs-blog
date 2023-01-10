package com.pigs.blog.service.impl;

import com.pigs.blog.contract.request.ArticlesGroupCreateRequest;
import com.pigs.blog.contract.request.ArticlesGroupListRequest;
import com.pigs.blog.contract.request.ArticlesGroupUpdateRequest;
import com.pigs.blog.contract.response.ArticlesGroupListResponse;
import com.pigs.blog.mapper.ArticlesGroupMapper;
import com.pigs.blog.mapper.ext.ArticlesGroupMapperExt;
import com.pigs.blog.model.ArticlesGroup;
import com.pigs.blog.model.ArticlesGroupExample;
import com.pigs.blog.service.ArticlesGroupInterface;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticlesGroupInterfaceImpl implements ArticlesGroupInterface {
    @Autowired
    ArticlesGroupMapper groupMapper;

    @Autowired
    ArticlesGroupMapperExt groupMapperExt;

    @Override
    public List<ArticlesGroupListResponse> list(ArticlesGroupListRequest request) {

        ArticlesGroupExample example = new ArticlesGroupExample();
        ArticlesGroupExample.Criteria criteria = example.createCriteria();
        if (Strings.isNotBlank(request.getAuthor())) {
            criteria.andAuthorEqualTo(request.getAuthor());
        }
        example.setOrderByClause("id desc");
        List<ArticlesGroup> articlesGroups = groupMapper.selectByExample(example);
        List<ArticlesGroupListResponse> result = copyList(articlesGroups);
        return result;
    }

    @Override
    public void create(ArticlesGroupCreateRequest request) {
        ArticlesGroup group = new ArticlesGroup();
        BeanUtils.copyProperties(request, group);
        groupMapper.insertSelective(group);
    }

    public void update(ArticlesGroupUpdateRequest request) {
        ArticlesGroup group = new ArticlesGroup();
        BeanUtils.copyProperties(request, group);
        groupMapper.updateByPrimaryKeySelective(group);
    }

    /**
     * 逻辑删除
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        List<Integer> ids = selectSubId(id);

        ids.forEach(s -> {
            ArticlesGroup articlesGroup = new ArticlesGroup();
            articlesGroup.setId(s);
            articlesGroup.setIsDelete((short) 1);
            groupMapper.updateByPrimaryKeySelective(articlesGroup);
        });
    }


    /**
     * 根据id找到所有子组id
     *
     * @param id
     * @return
     */
    private List<Integer> selectSubId(Integer id) {
        List<Integer> parentIds = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        parentIds.add(id);
        while (!parentIds.isEmpty()) {
            result.addAll(parentIds);
            ArticlesGroupExample example = new ArticlesGroupExample();
            ArticlesGroupExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdIn(parentIds);
            List<ArticlesGroup> articlesGroups = groupMapper.selectByExample(example);
            List<Integer> subIds = articlesGroups.stream().map(ArticlesGroup::getId).collect(Collectors.toList());

            parentIds.removeAll(result);
            parentIds.addAll(subIds);
        }
        return result;
    }

    private List<ArticlesGroupListResponse> copyList(List<ArticlesGroup> list) {
        List<ArticlesGroupListResponse> responses = new ArrayList<>();
        list.forEach(s -> {
            ArticlesGroupListResponse response = new ArticlesGroupListResponse();
            BeanUtils.copyProperties(s, response);
            responses.add(response);
        });
        return responses;
    }
}

