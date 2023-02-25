package com.pigs.blog.model;

public class Logion {
    private Integer id;

    private String logion;

    private String author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogion() {
        return logion;
    }

    public void setLogion(String logion) {
        this.logion = logion == null ? null : logion.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }
}