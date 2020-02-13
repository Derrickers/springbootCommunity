package com.example.community.model;

import lombok.Data;

@Data
public class Notification {
    private Long id;
    private String notifier;
    private String receiver;
    private Long outerId;
    private int type;
    private Long gmtCreate;
    private int status;
    private String outerTitle;
    private String notifierName;
}
