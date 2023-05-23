package com.example.club.services;

import com.example.club.models.ChatRoom;
import com.example.club.models.Employee;
import com.example.club.models.Message;
import com.example.club.models.User;
import com.example.club.repositories.ChatRoomRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomService {

    @Autowired
    ChatRoomRepos chatRoomRepos;

    public List<ChatRoom> FindAll(){
        return chatRoomRepos.findAll();
    }
//    public void AddChatRoom(@Param("chatName") String chatName, @Param("user") User user, @Param("employee") Employee employee, @Param("speciality") Integer speciality){
//        chatRoomRepos.save(new ChatRoom(chatName, user, employee, speciality));
//    }
    //Создать чат(Без указания менеджера)
    public void AddChatRoom(@Param("chatName") String chatName, @Param("user") User user, @Param("speciality") Integer speciality){
        chatRoomRepos.save(new ChatRoom(chatName, user, speciality));
    }

    public void SetEmployee(@Param("id") Integer id, @Param("chatName") String chatName, @Param("user") User user, @Param("employee") Employee newEmployee, @Param("speciality") Integer speciality){
        chatRoomRepos.save(new ChatRoom(id, chatName, user, newEmployee, speciality));
    }

    public void SetSpeciality(@Param("id") Integer id, @Param("chatName") String chatName, @Param("user") User user, @Param("employee") Employee employee, @Param("newSpeciality") Integer newSpeciality){
        chatRoomRepos.save(new ChatRoom(id, chatName, user, employee, newSpeciality));
    }

    public ChatRoom GetChatRoomByID(Integer id){
        return chatRoomRepos.findById(id).orElseThrow();
    }

//    public List<Message> OpenChat(@Param("id") Integer id){
//        return chatRoomRepos.findAllById();
//    }
}
