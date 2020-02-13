package com.example.community.dto;

import com.example.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private String commentator;
    private Long gmtCreate;
    private Long gmtModify;
    private Long likeCount;
    private String content;
    private Integer commentCount;

    private User user;
}
