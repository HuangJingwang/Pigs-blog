package com.pigs.blog.service.impl;

import com.pigs.blog.common.ErrorCodeEnum;
import com.pigs.blog.common.ResultResponse;
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
        if (Strings.isNotBlank(request.getAccount())) {
            criteria.andAccountEqualTo(request.getAccount());
        }
        example.setOrderByClause("id desc");
        List<ArticlesGroup> articlesGroups = groupMapper.selectByExample(example);
        List<ArticlesGroupListResponse> result = copyList(articlesGroups);
        return result;
    }

    @Override
    public ResultResponse create(ArticlesGroupCreateRequest request) {
        ArticlesGroup group = new ArticlesGroup();
        BeanUtils.copyProperties(request, group);

        //检测parentId是否存在
        if (request.getParentId() != null) {
            ArticlesGroup articlesGroup = groupMapper.selectByPrimaryKey(request.getParentId());
            if (articlesGroup == null) {
                return ResultResponse.fail(ErrorCodeEnum.PARENT_ID_IS_NOT_EXIST.getCode(), ErrorCodeEnum.PARENT_ID_IS_NOT_EXIST.getMsg());
            }
        }

        groupMapper.insertSelective(group);
        return ResultResponse.success(null);
    }

    public ResultResponse update(ArticlesGroupUpdateRequest request, Long id) {
        ArticlesGroup group = new ArticlesGroup();
        BeanUtils.copyProperties(request, group);

        //1. 检测parentId是否存在
        if (request.getParentId() != null) {
            ArticlesGroup articlesGroup = groupMapper.selectByPrimaryKey(request.getParentId());
            if (articlesGroup == null) {
                return ResultResponse.fail(ErrorCodeEnum.PARENT_ID_IS_NOT_EXIST.getCode(), ErrorCodeEnum.PARENT_ID_IS_NOT_EXIST.getMsg());
            }
        }

        //2. 检测是否id和parentId一样
        if (request.getParentId() != null && id.equals(request.getParentId())) {
            return ResultResponse.fail(ErrorCodeEnum.PARENT_ID_IS_CONFLICT_TO_CURRENT_ID.getCode(), ErrorCodeEnum.PARENT_ID_IS_CONFLICT_TO_CURRENT_ID.getMsg());
        }

        groupMapper.updateByPrimaryKeySelective(group);
        return ResultResponse.success(null);
    }

    /**
     * 逻辑删除
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        List<Long> ids = selectSubId(id);

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
    private List<Long> selectSubId(Long id) {
        List<Long> parentIds = new ArrayList<>();
        List<Long> result = new ArrayList<>();
        parentIds.add(id);
        while (!parentIds.isEmpty()) {
            result.addAll(parentIds);
            ArticlesGroupExample example = new ArticlesGroupExample();
            ArticlesGroupExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdIn(parentIds);
            List<ArticlesGroup> articlesGroups = groupMapper.selectByExample(example);
            List<Long> subIds = articlesGroups.stream().map(ArticlesGroup::getId).collect(Collectors.toList());

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

