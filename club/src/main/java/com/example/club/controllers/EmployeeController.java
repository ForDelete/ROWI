package com.example.club.controllers;

import com.example.club.models.Employee;
import com.example.club.models.User;
import com.example.club.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

//    @GetMapping("/employees")
//    public ResponseEntity<List<Employee>>FindAll(){
//        return ResponseEntity.ok(employeeService.FindAll());
//    }
    @GetMapping("/addEmployee")
    public void AddEmployee(@RequestParam("user_id") User user_id, @RequestParam("specialization") Integer specialization, @RequestParam("laci") Integer laci){
        employeeService.AddEmployee(user_id,specialization, laci);
    }
}
