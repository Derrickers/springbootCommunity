package com.example.community.service;

import com.example.community.dto.NotificationDTO;
import com.example.community.dto.PaginationDTO;
import com.example.community.enums.NotificationStatusEnum;
import com.example.community.enums.NotificationTypeEnum;
import com.example.community.exception.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import com.example.community.mapper.NotificationMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Notification;
import com.example.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO<NotificationDTO> list(String receiver, Integer page, Integer size) {
        Integer totalCount = notificationMapper.countByReceiver(receiver);
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();
        paginationDTO.setPagination(totalCount,page,size);

        Integer offset = size*(paginationDTO.getPage()-1);
        List<Notification> notifications = notificationMapper.listByReceiver(receiver,offset,size);
        List<NotificationDTO> notificationDTOS;

        //转换成为notificationDTO
        notificationDTOS = notifications.stream().map(notify -> {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notify,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notify.getType()));
            return notificationDTO;
        }).collect(Collectors.toList());
        paginationDTO.setData(notificationDTOS);

        return paginationDTO;
    }

    public Long unreadCount(String accountId) {
        return notificationMapper.countUnread(accountId);
    }

    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.getById(id);
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(!notification.getReceiver().equals(user.getAccountId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        //更新为已读
        notificationMapper.read(notification.getId(),notification.getStatus());

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
        return notificationDTO;
    }
}
