package com.example.community.model;

import lombok.Data;

@Data
public class Question {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModify;
    private String creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
