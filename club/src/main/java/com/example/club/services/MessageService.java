package com.example.club.services;

import com.example.club.models.ChatRoom;
import com.example.club.models.Message;
import com.example.club.models.User;
import com.example.club.repositories.MessageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepos messageRepos;

    public List<Message> FindAll(){

        return messageRepos.findAll();
    }
    public void AddMessage(@Param("chatRoom_id") ChatRoom chatRoom_id, @Param("sender_id") User sender_id, @Param("message") String message, @Param("time") Time time){
        messageRepos.save(new Message(chatRoom_id, sender_id, message, time));
    }

    public List<Message> GetMessagesByChatID(Integer id){
        List<Message> x = messageRepos.findAll();
        List<Message> y = x.stream().filter(z->z.getChatRoom().getID()==id).toList();
        return y;
    }



}
