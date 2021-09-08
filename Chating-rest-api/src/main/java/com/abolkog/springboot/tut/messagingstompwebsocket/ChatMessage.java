package com.abolkog.springboot.tut.messagingstompwebsocket;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ChatMessage {
    
    private String from;
    private String text;
    private String recipient;
    private String time;
 
    public ChatMessage() {
        
    }
    
    public ChatMessage(String from, String text, String recipient) {
        this.from = from;
        this.text = text;
        this.recipient = recipient;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.time = LocalDateTime.now().format(formatter);
        
//        this.time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getRecipient()
    {
        return recipient;
    }

    public void setRecipient(String recipient)
    {
        this.recipient = recipient;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    
}
