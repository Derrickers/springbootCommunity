package com.example.community.mapper;

import com.example.community.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment (parent_id,type,commentator,gmt_create,gmt_modify,content) values (#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModify},#{content})")
    void create(Comment comment);

    @Select("select * from comment where id = #{Id}")
    Comment getById(@Param(value = "Id") Long Id);

    @Select("select * from comment where parent_id = #{parentId} and type = #{type}")
    List<Comment> getByParentIdAndType(@Param(value = "parentId") Long id,@Param(value = "type") Integer type);
}
