package com.example.club.controllers;

import com.example.club.models.ChatRoom;
import com.example.club.models.Message;
import com.example.club.models.User;
import com.example.club.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@CrossOrigin
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> FindAll(){
        return ResponseEntity.ok(messageService.FindAll());
    }
    @GetMapping("/addMessage")
    public void AddMessage(@RequestParam("chatRoom") ChatRoom chatRoom_id, @RequestParam("sender") User sender_id, @RequestParam("message")String message){

        //long currentMoment = System.currentTimeMillis();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date( date.getTime() );

        messageService.AddMessage(chatRoom_id,sender_id, message, sqlDate);
    }


}
