package com.example.club.services;

import com.example.club.models.User;
import com.example.club.repositories.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepos userRepos;

    public List<User> FindAll(){
        return userRepos.findAll();
    }

    public User FindUserByLP(@Param("login") String login, @Param("password") String password){
        //User x = userRepos.findById(id).orElseThrow();
        return userRepos.findAll().stream()
                .filter(user-> Objects.equals(user.getLogin(), login) && Objects.equals(user.getPassword(), password))
                .findAny().orElse(null);
    }
    public User FindUserByLogin(@Param("login") String login){
        return userRepos.findAll().stream()
                .filter(user-> Objects.equals(user.getLogin(), login))
                .findAny().orElse(null);
    }
    public void AddUser(@Param("name") String name, @Param("login") String login, @Param("password") String password){
        userRepos.save(new User(name, login, password));
    }
}
