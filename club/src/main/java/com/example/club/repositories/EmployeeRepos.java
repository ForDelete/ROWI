package com.example.club.repositories;

import com.example.club.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepos extends JpaRepository<Employee, Integer> {

}
