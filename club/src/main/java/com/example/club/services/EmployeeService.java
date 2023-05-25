package com.example.club.services;

import com.example.club.models.Employee;
import com.example.club.models.User;
import com.example.club.repositories.EmployeeRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepos employeeRepos;

//    public List<Employee> FindAll(){
//        return employeeRepos.findAll();
//    }
    public void AddEmployee(@Param("employee_id") User employee_id, @Param("specialization") Integer specialization, @Param("laci") Integer laci){
        employeeRepos.save(new Employee(employee_id, specialization, laci));
    }
    //Hidden
    public Employee FindEmployeeById(@Param("id") Integer id){
        return employeeRepos.findAll().stream()
                .filter(user-> Objects.equals(user.getID(), id))
                .findAny().orElse(null);
    }
}
