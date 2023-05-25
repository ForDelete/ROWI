package com.example.club.controllers;

import com.example.club.models.ChatRoom;
import com.example.club.models.Employee;
import com.example.club.models.Message;
import com.example.club.models.User;
import com.example.club.services.ChatRoomService;
import com.example.club.services.EmployeeService;
import com.example.club.services.MessageService;
import com.example.club.services.UserService;
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
    @Autowired
    EmployeeService employeeService;
    @Autowired
    UserService userService;



    @GetMapping("/addChatroom")//Создать чат(Создаёт юзер)
    public void AddChatRoom(@RequestParam("chatName") String chatName, @RequestParam("user_id") Integer user_id){
        chatRoomService.AddChatRoom(chatName, user_id);
    }

    @GetMapping("/setEmployee")//Установить ответственного за этот чат менеджера
    public void SetEmployee(@RequestParam("chat_id") Integer id,@RequestParam("employee") Integer newEmployee){
        ChatRoom chatRoom = chatRoomService.FindChatroomById(id);
        Employee employee = employeeService.FindEmployeeById(newEmployee);
        chatRoomService.SetEmployee(id,chatRoom.getChatName(),chatRoom.getUser_id(), employee,chatRoom.getSpeciality(),chatRoom.getComplexity());
    }
    @GetMapping("/setSpeciality")//Установить новую "тему" вопроса (Перенаправит впрос компетентным специалистам)
    public void SetSpeciality(@RequestParam("chat_id") Integer id, @RequestParam("speciality") Integer newSpeciality, @RequestParam("laci") Integer laci){
        ChatRoom chatRoom = chatRoomService.FindChatroomById(id);
        chatRoomService.SetSpeciality(id,chatRoom.getChatName(),chatRoom.getUser_id(), null, newSpeciality,laci);
    }




    @GetMapping("/chatrooms/user={id}")//Вернуть все чаты пользователя(Активные)
    public ResponseEntity<List<ChatRoom>> ShowMyChats(@PathVariable("id") Integer id){
        User user = userService.FindUserById(id);
        return ResponseEntity.ok(chatRoomService.GetChatRoomsByID(user.getID()));
    }
    @GetMapping("/chatrooms/employee={id}")//Вернуть все чаты менеджера(Активные)
    public ResponseEntity<List<ChatRoom>> ShowEmployeeChats(@PathVariable("id") Integer id){
        Employee employee = employeeService.FindEmployeeById(id);
        return ResponseEntity.ok(chatRoomService.GetEmployeeChatRoomsByID(employee.getID()));
    }
    @GetMapping("/freechats/employee={id}")//Вернуть все "свободные чаты" (доступные нам)
    public ResponseEntity<List<ChatRoom>> ShowFreeChats(@PathVariable("id") Integer id){
        User user = userService.FindUserById(id);
        Employee employee = employeeService.FindEmployeeById(user.getID());
        return ResponseEntity.ok(chatRoomService.GetFreeChats(employee.getSpecialization(),employee.getLaci()));
    }
    @GetMapping("/chat/id={id}")//Получить все сообщения чата (по его id)
    public ResponseEntity<List<Message>> OpenChat(@PathVariable("id") Integer id){
        return ResponseEntity.ok(messageService.GetMessagesByChatID(id));
    }
}
