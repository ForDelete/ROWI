package com.example.club.services;

import com.example.club.controllers.UserController;
import com.example.club.models.ChatRoom;
import com.example.club.models.Employee;
import com.example.club.models.Message;
import com.example.club.models.User;
import com.example.club.repositories.ChatRoomRepos;
import com.example.club.repositories.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ChatRoomService {

    @Autowired
    ChatRoomRepos chatRoomRepos;
    @Autowired
    UserController userController;

//    public List<ChatRoom> ShowMyChats(@Param("user_id") Integer user_id){
//        return chatRoomRepos.findAll();
//    }
//    public void AddChatRoom(@Param("chatName") String chatName, @Param("user") User user, @Param("employee") Employee employee, @Param("speciality") Integer speciality){
//        chatRoomRepos.save(new ChatRoom(chatName, user, employee, speciality));
//    }
    //Создать чат(Без указания менеджера)
    public void AddChatRoom(@Param("chatName") String chatName, @Param("user_id") Integer user_id){
        User user = userController.FindUserById(user_id);
        chatRoomRepos.save(new ChatRoom(chatName, user));
    }

    public void SetEmployee(@Param("id") Integer id, @Param("chatName") String chatName, @Param("user") User user, @Param("employee") Employee newEmployee, @Param("speciality") Integer speciality, @Param("laci") Integer laci){
        chatRoomRepos.save(new ChatRoom(id, chatName, user, newEmployee, speciality, laci));
    }
    //Hidden
    public ChatRoom FindChatroomById(@Param("id") Integer id){
        return chatRoomRepos.findAll().stream()
                .filter(user-> Objects.equals(user.getID(), id))
                .findAny().orElse(null);
    }

    public void SetSpeciality(@Param("id") Integer id, @Param("chatName") String chatName, @Param("user") User user, @Param("employee") Employee employee, @Param("newSpeciality") Integer newSpeciality, @Param("laci") Integer laci){
        chatRoomRepos.save(new ChatRoom(id, chatName, user, employee, newSpeciality, laci));
    }

    public List<ChatRoom> GetChatRoomsByID(Integer id){//Получить свои чаты
        List<ChatRoom> x = chatRoomRepos.findAll();
        List<ChatRoom> y = x.stream().filter(z->z.getUser_id().getID()==id).toList();
        //List<Integer> w = y.stream().map(z->z.getID()).toList();
        return y;
    }
    public List<ChatRoom> GetEmployeeChatRoomsByID(Integer id){//Получить свои чаты
        List<ChatRoom> x = chatRoomRepos.findAll();
        List<ChatRoom> y = x.stream().filter(z->z.getCurrent_employee()!=null&&z.getCurrent_employee().getID()==id).toList();
        //List<Integer> w = y.stream().map(z->z.getID()).toList();
        return y;
    }
    public List<ChatRoom> GetFreeChats(Integer spec, Integer laci){//Получить свободные чаты
        List<ChatRoom> x = chatRoomRepos.findAll();
        List<ChatRoom> y = x.stream().filter(z->z.getCurrent_employee()==null&& Objects.equals(z.getSpeciality(), spec) &&z.getComplexity()<=laci).toList();
        return y;
    }

//    public List<Message> OpenChat(@Param("id") Integer id){
//        return chatRoomRepos.findAllById();
//    }
}
