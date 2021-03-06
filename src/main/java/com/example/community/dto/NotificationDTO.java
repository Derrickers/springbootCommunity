package com.example.community.dto;

import com.example.community.model.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long outerId;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private String typeName;
    private Integer type;
}
