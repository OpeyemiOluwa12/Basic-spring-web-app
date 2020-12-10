package com.opeyemi.spring.basicwebapp.service;

import com.opeyemi.spring.basicwebapp.model.ChatForm;
import com.opeyemi.spring.basicwebapp.model.ChatMessage;
import com.opeyemi.spring.basicwebapp.model.MessageType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<ChatMessage> chatMessageList;

    public MessageService() {

    }

    @PostConstruct
    public void postConstruct() {
        this.chatMessageList = new ArrayList<>();
    }

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public void addMessage(ChatForm chatForm) {
        ChatMessage chatMessage = new ChatMessage();
        switch (MessageType.messageType(chatForm.getMessageType())) {
            case SAY:
                chatMessage.setMessage(chatForm.getMessage());
                break;
            case SHOUT:
                chatMessage.setMessage(chatForm.getMessage().toUpperCase());
                break;
            case WHISPER:
                chatMessage.setMessage(chatForm.getMessage().toLowerCase());
                break;
        }
        chatMessage.setUsername(chatForm.getUsername());
        chatMessageList.add(chatMessage);
    }

    public void clearMessage() {
        chatMessageList.clear();
    }
}
