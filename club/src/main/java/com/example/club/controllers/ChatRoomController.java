package com.example.club.controllers;

import com.example.club.models.ChatRoom;
import com.example.club.models.Employee;
import com.example.club.models.Message;
import com.example.club.models.User;
import com.example.club.services.ChatRoomService;
import com.example.club.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ChatRoomController {
    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    MessageService messageService;

    @GetMapping("/chatrooms")
    public ResponseEntity<List<ChatRoom>> FindAll(){
        return ResponseEntity.ok(chatRoomService.FindAll());
    }
//    @GetMapping("/addChatroom")
//    public void AddChatRoom(@RequestParam("chatName") String chatName, @RequestParam("user") User user, @RequestParam("employee") Employee employee, @RequestParam("speciality") Integer speciality){
//        chatRoomService.AddChatRoom(chatName, user, employee, speciality);
//    }
    @PostMapping("/addChatroom")
    public void AddChatRoom(@RequestParam("chatName") String chatName, @RequestParam("user") User user, @RequestParam("speciality") Integer speciality){
        chatRoomService.AddChatRoom(chatName, user, speciality);
    }
    @PostMapping("/setEmployee")
    public void SetEmployee(@RequestParam("id") Integer id, @RequestParam("chatName") String chatName, @RequestParam("user") User user,@RequestParam("employee") Employee newEmployee, @RequestParam("speciality") Integer speciality){
        chatRoomService.SetEmployee(id,chatName,user, newEmployee,speciality);
    }
    @PostMapping("/setSpeciality")
    public void SetSpeciality(@RequestParam("id") Integer id, @RequestParam("chatName") String chatName, @RequestParam("user") User user,@RequestParam("employee") Employee employee, @RequestParam("speciality") Integer newSpeciality){
        chatRoomService.SetSpeciality(id,chatName,user, employee,newSpeciality);
    }

//    @GetMapping("/Chat")
//    public ResponseEntity<ChatRoom> OpenChat(@RequestParam("id") Integer id){
//        return ResponseEntity.ok(chatRoomService.GetChatRoomByID(id));
//    }
@GetMapping("/Chat")
public ResponseEntity<List<Message>> OpenChat(@RequestParam("id") ChatRoom id){
    //return ResponseEntity.ok(chatRoomService.GetChatRoomByID(id));
    return ResponseEntity.ok(messageService.GetMessagesByChatID(id));
}
}
