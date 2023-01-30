package com.pigs.blog.model.criteria;

import lombok.Data;

@Data
public class ArticlesPageCriteria {
    private String account;
    private Integer start;
    private Integer offset;
    private String status;
    private int orderByPV;

    public void setOrderByPV(int orderByPV) {
        this.orderByPV = orderByPV;
    }

    public int getOrderByPV() {
        return orderByPV;
    }
}
