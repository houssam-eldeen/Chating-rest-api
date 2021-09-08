package com.abolkog.springboot.tut.chatingController;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatAppController
{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @RequestMapping("/start")
    public String start()
    {
        return "start";
    }

    @MessageMapping("/chat")
    @SendTo("/topic/output")
    public ChatOutput chat(ChatInput input)
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ChatOutput output = new ChatOutput();
        output.setUser(input.getUser());
        output.setMessage(input.getMessage());
        output.setDateTime(formatter.format(ZonedDateTime.now()));
        return output;
    }
}
