package com.pigs.blog.model.criteria;

import lombok.Data;

@Data
public class ArticlesListCriteria {
    private String author;
    private Integer start;
    private Integer offset;
}
