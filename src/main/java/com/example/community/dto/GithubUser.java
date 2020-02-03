package com.example.community.dto;

/**
 * 用于接受用户信息，一些GitHub返回用户信息JSON中需要的信息
 */
public class GithubUser {
    private String name;
    private Long id;
    private String bio;


    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }
}
