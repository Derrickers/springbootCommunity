package com.example.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你找的问题不在啦，要不换个试试～"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题活评论进行回复！"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试！"),
    SYSTEM_ERROR(2004,"服务冒烟了，要不然稍后试试～"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在！"),
    COMMENT_NOT_FOUND(2006,"您选择的评论不存在!"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空!"),
    READ_NOTIFICATION_FAIL(2008,"兄弟你这是在看别人的信息？？!"),
    NOTIFICATION_NOT_FOUND(2009,"消息不翼而飞了!"),
    ;


    private String message;
    private Integer code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }



    CustomizeErrorCode(Integer code,String message){
        this.message = message;
        this.code = code;
    }

}
