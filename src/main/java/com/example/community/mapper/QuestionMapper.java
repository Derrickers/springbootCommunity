package com.example.community.mapper;

import com.example.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modify,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModify},#{creator},#{tag})")
    public void create(Question question);
}
