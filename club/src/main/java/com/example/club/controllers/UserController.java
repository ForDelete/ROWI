package com.example.club.controllers;

import com.example.club.models.User;
import com.example.club.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin//Для обращения к методам для сторонних людей
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<User>> FindAll(){

        return ResponseEntity.ok(userService.FindAll());
    }

    @GetMapping("/user")
    public ResponseEntity<User> FindUserByLP(@RequestParam("login") String login,@RequestParam("password") String password){
        return ResponseEntity.ok(userService.FindUserByLP(login, password));
    }
    @GetMapping("/hasUser")
    public ResponseEntity<Boolean> CheckIsUserExists(@RequestParam("login") String login){
        return ResponseEntity.ok(userService.FindUserByLogin(login)!=null);
    }

    @GetMapping("/addUser")
    public void  AddUser(@RequestParam("name") String name, @RequestParam("login") String login, @RequestParam("password") String password){
        userService.AddUser(name,login,password);
        //return ResponseEntity.ok(userService.FindUserByLP(login, password));
    }
}
