package com.pigs.blog.model.criteria;

import lombok.Data;

@Data
public class UserInfoPageCriteria {
    private Integer start;
    private Integer offset;
    private Integer articlesCountMoreThan;
}
