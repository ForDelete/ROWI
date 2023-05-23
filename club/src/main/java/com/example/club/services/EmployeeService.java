package com.example.club.services;

import com.example.club.models.Employee;
import com.example.club.models.User;
import com.example.club.repositories.EmployeeRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepos employeeRepos;

    public List<Employee> FindAll(){
        return employeeRepos.findAll();
    }
    public void AddEmployee(@Param("employee_id") User employee_id, @Param("product") Integer product){
        employeeRepos.save(new Employee(employee_id, product));
    }
}
