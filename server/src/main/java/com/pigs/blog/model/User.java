package com.pigs.blog.model;

import java.util.Date;

public class User {
    private Long id;

    private String account;

    private String password;

    private String role;

    private Long githubId;

    private String questionAnswer1;

    private String questionAnswer2;

    private Date createAt;

    private Date updateAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Long getGithubId() {
        return githubId;
    }

    public void setGithubId(Long githubId) {
        this.githubId = githubId;
    }

    public String getQuestionAnswer1() {
        return questionAnswer1;
    }

    public void setQuestionAnswer1(String questionAnswer1) {
        this.questionAnswer1 = questionAnswer1 == null ? null : questionAnswer1.trim();
    }

    public String getQuestionAnswer2() {
        return questionAnswer2;
    }

    public void setQuestionAnswer2(String questionAnswer2) {
        this.questionAnswer2 = questionAnswer2 == null ? null : questionAnswer2.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}