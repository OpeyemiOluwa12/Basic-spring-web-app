package com.opeyemi.spring.basicwebapp.controller;

import com.opeyemi.spring.basicwebapp.model.ChatForm;
import com.opeyemi.spring.basicwebapp.model.MessageType;
import com.opeyemi.spring.basicwebapp.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String showChats(ChatForm chatForm, Model model) {
        model.addAttribute("chatMessages", messageService.getChatMessageList());
        return "chat";
    }

    @PostMapping
    public String sendChat(ChatForm chatForm, Model model) {

        messageService.addMessage(chatForm);
        model.addAttribute("chatMessages", messageService.getChatMessageList());
        chatForm.setMessage("");
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public List<String> allMessageTypes() {
        return Arrays.stream(MessageType.values()).map(MessageType::getLabel).collect(Collectors.toList());
    }
}
