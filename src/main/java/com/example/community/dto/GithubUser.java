package com.example.community.dto;

import lombok.Data;

/**
 * 用于接受用户信息，一些GitHub返回用户信息JSON中需要的信息
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
