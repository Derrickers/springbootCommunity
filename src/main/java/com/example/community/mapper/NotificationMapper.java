package com.example.community.mapper;

import com.example.community.model.Notification;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotificationMapper {

    @Select("select * from notification where receiver = #{receiver} order by gmt_create desc limit #{offset},#{size}")
    List<Notification> listByReceiver(@Param(value = "receiver") String receiver,@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Insert("insert into notification (notifier,receiver,outer_id,type,gmt_create,status,outer_title,notifier_name) values (#{notifier},#{receiver},#{outerId},#{type},#{gmtCreate},#{status},#{outerTitle},#{notifierName})")
    void insert(Notification notification);

    @Select("select count(1) from notification where receiver = #{receiver}")
    Integer countByReceiver(@Param(value = "receiver") String receiver);

    @Select("select count(1) from notification where receiver = #{receiver} and status = 0")
    Long countUnread(@Param(value = "receiver") String receiver);

    @Select("select * from notification where id = #{id}")
    Notification getById(@Param(value = "id") Long id);

    @Update("update notification set status = #{status} where id = #{id}")
    void read(@Param(value = "id") Long id,@Param(value = "status") Integer status);
}
