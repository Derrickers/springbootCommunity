package com.example.community.model;

public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtModify(Long gmtModify) {
        this.gmtModify = gmtModify;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public Long getGmtModify() {
        return gmtModify;
    }

    public Integer getCreator() {
        return creator;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }
}
