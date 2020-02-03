package com.example.community.provider;

import com.alibaba.fastjson.JSON;
import com.example.community.dto.AccessTokenDTO;
import com.example.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Create by Derricker on 2020/2/2
 * 这里采用okHttp3模拟访问请求，以获取信息
 */

//对象自动实例化到一个IOC对象池
@Component
public class GithubProvider {
    //本方法是获取access_token的方法，通过传递必要信息，如回调地址，具体app的id，secert等，来申请accessToken，
    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        //这里是okHTTP的具体用法
        //声明MediaType
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        //创建类
        OkHttpClient client = new OkHttpClient();

        //使用fastJson创建accessToken请求，请求中包含必要信息
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        //建立请求
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();//接收GitHub返回值
            //现在获取accessToken的十六进制码
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //本方法使用accessToken，访问api来获取具体的用户信息，获得的是一个用户的JSON文件，需要用fastJSON来解析
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
