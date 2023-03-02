package com.pigs.blog.model.criteria;

import lombok.Data;

@Data
public class ArticlesPageCriteria {
    private String author;
    private Integer start;
    private Integer offset;
    private String status;
}
