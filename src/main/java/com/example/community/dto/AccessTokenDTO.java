package com.example.community.dto;

import lombok.Data;

/**
 * AccessToken接受类，用于存放申请的accessToken的详细信息
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;
}
