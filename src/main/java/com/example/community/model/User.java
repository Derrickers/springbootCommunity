package com.example.community.model;

import lombok.Data;

/**
 * 这一对象是本网站用户对象，用于存储本网站用户信息
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModify;
    private String avatarUrl;
}
