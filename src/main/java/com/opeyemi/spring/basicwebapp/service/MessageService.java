package com.opeyemi.spring.basicwebapp.service;

import com.opeyemi.spring.basicwebapp.mapper.MessagesMapper;
import com.opeyemi.spring.basicwebapp.model.ChatForm;
import com.opeyemi.spring.basicwebapp.model.Messages;
import com.opeyemi.spring.basicwebapp.model.MessageType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<Messages> messagesList;

    private final MessagesMapper messagesMapper;

    public MessageService(MessagesMapper messagesMapper) {

        this.messagesMapper = messagesMapper;
    }

    @PostConstruct
    public void postConstruct() {
        this.messagesList = new ArrayList<>();
    }

    public List<Messages> getMessagesList() {
        return messagesMapper.getAllMessages();
    }

    public void addMessage(ChatForm chatForm) {
        Messages messages = new Messages();
        switch (MessageType.messageType(chatForm.getMessageType())) {
            case SAY:
                messages.setMessageText(chatForm.getMessage());
                break;
            case SHOUT:
                messages.setMessageText(chatForm.getMessage().toUpperCase());
                break;
            case WHISPER:
                messages.setMessageText(chatForm.getMessage().toLowerCase());
                break;
        }
        messages.setUsername(chatForm.getUsername());
        messagesMapper.insert(messages);
    }

    public void clearMessage() {
        messagesList.clear();
    }
}
