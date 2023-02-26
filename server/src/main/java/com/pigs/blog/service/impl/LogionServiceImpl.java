package com.pigs.blog.service.impl;

import com.pigs.blog.common.PageData;
import com.pigs.blog.contract.request.LogionCreateRequest;
import com.pigs.blog.mapper.LogionMapper;
import com.pigs.blog.mapper.ext.LogionMapperExt;
import com.pigs.blog.model.Logion;
import com.pigs.blog.model.LogionExample;
import com.pigs.blog.service.LogionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class LogionServiceImpl implements LogionService {
    @Autowired
    private LogionMapperExt logionMapperExt;
    @Autowired
    private LogionMapper logionMapper;

    private List<Integer> getIdList() {
        return logionMapperExt.selectAllIds();
    }

    @Override
    public Logion getLogion() {
        List<Integer> idList = getIdList();
        Random random = new Random();
        int index = random.nextInt(idList.size() + 1);//random.nextInt(max)表示生成[0,max）之间的随机数
        Integer id = idList.get(index);
        Logion logion = logionMapper.selectByPrimaryKey(id);
        return logion;
    }

    @Override
    public PageData list(Integer pageSize, Integer pageNo) {
        PageData<Logion> result = new PageData<>();
        long count = logionMapper.countByExample(new LogionExample());
        if(count == 0){
            result.setHasNext(false);
            result.setResultList(Collections.emptyList());
            result.setTotalResult(0L);
            return result;
        }

        Integer start = pageSize *(pageNo - 1);
        List<Logion> resultList = logionMapperExt.listAll(start, pageSize);

        result.setTotalResult(count);
        result.setResultList(resultList);
        result.setHasNext(count > pageSize*pageNo);
        return result;
    }

    @Override
    public void delete(Integer id) {
        logionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void create(LogionCreateRequest request) {
        Logion l = new Logion();
        l.setLogion(request.getLogion());
        l.setAuthor(request.getAuthor());
        logionMapper.insertSelective(l);
    }
}
