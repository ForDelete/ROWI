package com.example.club.controllers;

import com.example.club.models.ChatRoom;
import com.example.club.models.Message;
import com.example.club.models.User;
import com.example.club.repositories.ChatRoomRepos;
import com.example.club.services.ChatRoomService;
import com.example.club.services.MessageService;
import com.example.club.services.UserService;
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
    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    UserService userService;

//    @GetMapping("/messages")
//    public ResponseEntity<List<Message>> FindAll(){
//        return ResponseEntity.ok(messageService.FindAll());
//    }
    @GetMapping("/addMessage")
    public void AddMessage(@RequestParam("chat") Integer chatRoom_id, @RequestParam("sender") Integer sender_id, @RequestParam("message")String message){

        ChatRoom chatRoom = chatRoomService.FindChatroomById(chatRoom_id);
        User user = userService.FindUserById(sender_id);
        //long currentMoment = System.currentTimeMillis();
        java.util.Date date = new java.util.Date();
        java.sql.Time sqlDate = new java.sql.Time( date.getTime() );

        messageService.AddMessage(chatRoom,user, message, sqlDate);
    }


}
