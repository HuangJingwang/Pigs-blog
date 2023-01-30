package com.pigs.blog.model.criteria;

import lombok.Data;

import java.util.List;

@Data
public class ArticlesListCriteria {
    private String[] tags;
    private Integer groupId;
    private String account;
}
