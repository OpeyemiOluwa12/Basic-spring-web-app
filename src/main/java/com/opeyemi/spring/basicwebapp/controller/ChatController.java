package com.opeyemi.spring.basicwebapp.controller;

import com.opeyemi.spring.basicwebapp.model.ChatForm;
import com.opeyemi.spring.basicwebapp.model.MessageType;
import com.opeyemi.spring.basicwebapp.service.MessageService;
import org.springframework.security.core.Authentication;
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
    public String showChats(@ModelAttribute("chatForm") ChatForm chatForm, Model model) {
        model.addAttribute("chatMessages", messageService.getMessagesList());
        return "chat";
    }

    @PostMapping
    public String sendChat(Authentication authentication, ChatForm chatForm, Model model) {
        chatForm.setUsername(authentication.getName());
        messageService.addMessage(chatForm);
        model.addAttribute("chatMessages", messageService.getMessagesList());
        chatForm.setMessage("");
        return "chat";
    }

    @ModelAttribute("allMessageTypes")
    public List<String> allMessageTypes() {
        return Arrays.stream(MessageType.values()).map(MessageType::getLabel).collect(Collectors.toList());
    }
}
