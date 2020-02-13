package com.example.community.model;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long parentId;
    private Integer type;
    private String commentator;
    private String content;
    private Long gmtCreate;
    private Long gmtModify;
    private Long likeCount;
    private Integer commentCount;
}
