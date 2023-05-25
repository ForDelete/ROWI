package com.example.club.services;

import com.example.club.models.Employee;
import com.example.club.models.User;
import com.example.club.repositories.EmployeeRepos;
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
    @Autowired
    EmployeeRepos employeeRepos;

    public List<User> FindAll(){
        return userRepos.findAll();
    }

    public User FindUserByLP(@Param("login") String login, @Param("password") String password){
        //User x = userRepos.findById(id).orElseThrow();
        return userRepos.findAll().stream()
                .filter(user-> Objects.equals(user.getLogin(), login) && Objects.equals(user.getPassword(), password))
                .findAny().orElse(null);
    }
    //Hidden
    public User FindUserById(@Param("id") Integer id){
        return userRepos.findAll().stream()
                .filter(user-> Objects.equals(user.getID(), id))
                .findAny().orElse(null);
    }
    public User CheckIsUserExists(@Param("login") String login){
        return userRepos.findAll().stream()
                .filter(user-> Objects.equals(user.getLogin(), login))
                .findAny().orElse(null);
    }
    public Employee CheckIsUserManager(@Param("id") Integer id){
        return employeeRepos.findAll().stream()
                .filter(employee-> Objects.equals(employee.getID(), id))
                .findAny().orElse(null);
    }

    public void AddUser(@Param("name") String name, @Param("login") String login, @Param("password") String password){
        userRepos.save(new User(name, login, password));
    }
}
