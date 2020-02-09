package com.example.community.mapper;

import com.example.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * 这一部分主要是负责服务器和数据库之间的数据传输
 * 使用Mapper注释，指明功能
 */
@Mapper
public interface UserMapper {
    //插入数据，自动读取User对象中对应属性的内容，填写到sql语句中
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modify,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModify},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where account_id = ${id}")
    User findById(@Param("id") String id);

    @Select("select * from user where account_id = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update user set name = #{name},token = #{token},gmt_modify = #{gmtModify},avatar_url = #{avatarUrl} where account_id = #{accountId}")
    void update(User user);
}
