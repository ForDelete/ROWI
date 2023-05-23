//package com.example.club.websocket;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//
//import java.sql.Date;
//import java.text.SimpleDateFormat;
//
//public class MessageHandlingController {
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public OutputMessage send(Message message) throws Exception {
//        String time = new SimpleDateFormat("HH:mm").format(new Date());
//        return new OutputMessage(message.getFrom(), message.getText(), time);
//    }
//}
