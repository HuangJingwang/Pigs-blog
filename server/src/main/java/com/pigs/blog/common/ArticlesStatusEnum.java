package com.pigs.blog.common;

public enum ArticlesStatusEnum {
    PUBLISHED("published", "published"),
    DRAFT("draft", "draft"),
    DELETED("deleted", "deleted");
    private String status;
    private String des;

    ArticlesStatusEnum(String status, String des) {
        this.status = status;
        this.des = des;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
