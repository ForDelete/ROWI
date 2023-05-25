package com.example.club.controllers;

import com.example.club.models.User;
import com.example.club.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@CrossOrigin("*")//Для обращения к методам для сторонних людей
public class UserController {

    @Autowired
    UserService userService;
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> FindAll(){
//
//        return ResponseEntity.ok(userService.FindAll());
//    }
    @GetMapping("/addUser")
    public void  AddUser(@RequestParam("name") String name, @RequestParam("login") String login, @RequestParam("password") String password){
        userService.AddUser(name,login,password);
        //return ResponseEntity.ok(userService.FindUserByLP(login, password));
    }
    @GetMapping("/hasUser/login={login}")//Есть ли юзер по логину
    public ResponseEntity<Boolean> CheckIsUserExists(@PathVariable("login") String login){
        return ResponseEntity.ok(userService.CheckIsUserExists(login)!=null);
    }
    @GetMapping("/user")//Найти юзера по логину/паролю
    public ResponseEntity<User> FindUserByLP(@RequestParam("login") String login,@RequestParam("password") String password){
        return ResponseEntity.ok(userService.FindUserByLP(login, password));
    }
    //Hidden method
    public User FindUserById(@RequestParam("id") Integer id){
        return userService.FindUserById(id);
    }

    @GetMapping("/isManager/id={id}")//Узнать, менеджер ли я
    public ResponseEntity<Boolean> CheckIsUserManager(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.CheckIsUserManager(id)!=null);
    }
}
