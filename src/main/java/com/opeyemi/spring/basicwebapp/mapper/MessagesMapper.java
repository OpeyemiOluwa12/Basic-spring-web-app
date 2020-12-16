package com.opeyemi.spring.basicwebapp.mapper;

import com.opeyemi.spring.basicwebapp.model.Messages;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessagesMapper {

    @Select("SELECT * FROM MESSAGES")
    List<Messages> getAllMessages();

    @Insert("INSERT INTO MESSAGES (username, messagetext) VALUES(#{username}, #{messageText})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insert(Messages messages);
}
