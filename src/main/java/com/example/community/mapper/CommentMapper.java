package com.example.community.mapper;

import com.example.community.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment (parent_id,type,commentator,gmt_create,gmt_modify,content) values (#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModify},#{content})")
    void create(Comment comment);

    @Select("select * from comment where id = #{Id}")
    Comment getById(@Param(value = "Id") Long Id);

    @Select("select * from comment where parent_id = #{parentId} and type = #{type} order by gmt_create desc")
    List<Comment> getByParentIdAndType(@Param(value = "parentId") Long id,@Param(value = "type") Integer type);

    @Update("update comment set comment_count = comment_count+1 where id = #{id}")
    void updateCommentCount(@Param(value = "id") Long id);
}
