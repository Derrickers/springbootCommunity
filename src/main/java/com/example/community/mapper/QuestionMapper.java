package com.example.community.mapper;

import com.example.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modify,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModify},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select count(1) from question where creator = #{userId}")
    Integer countByUserId(@Param(value = "userId") String userId);

    @Select("select * from question where id = #{id}")
    Question getById(@Param(value = "id") Long id);

    @Update("update question set title = #{title}, description = #{description},gmt_modify = #{gmtModify},tag = #{tag} where id = #{id}")
    void update(Question question);

    @Update("update question set view_count = view_count+1 where id = #{id}")
    void updateViewCount(@Param(value = "id") Long id);

    @Update("update question set comment_count = comment_count+1 where id = #{id}")
    void updateCommentCount(@Param(value = "id") Long id);
}
