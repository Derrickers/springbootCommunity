package com.example.community.mapper;

import com.example.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("select * from user where id = ${id}")
    User findById(@Param("id") Integer id);
}
