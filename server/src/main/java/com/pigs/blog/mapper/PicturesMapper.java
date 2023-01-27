package com.pigs.blog.mapper;

import com.pigs.blog.model.Pictures;
import com.pigs.blog.model.PicturesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PicturesMapper {
    long countByExample(PicturesExample example);

    int deleteByExample(PicturesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Pictures row);

    int insertSelective(Pictures row);

    List<Pictures> selectByExample(PicturesExample example);

    Pictures selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Pictures row, @Param("example") PicturesExample example);

    int updateByExample(@Param("row") Pictures row, @Param("example") PicturesExample example);

    int updateByPrimaryKeySelective(Pictures row);

    int updateByPrimaryKey(Pictures row);
}