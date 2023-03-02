package com.pigs.blog.model;

public class ArticlesWithBLOBs extends Articles {
    private String articleText;

    private String articlePictureUrl;

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText == null ? null : articleText.trim();
    }

    public String getArticlePictureUrl() {
        return articlePictureUrl;
    }

    public void setArticlePictureUrl(String articlePictureUrl) {
        this.articlePictureUrl = articlePictureUrl == null ? null : articlePictureUrl.trim();
    }
}