package com.example.club.models;


import jakarta.persistence.*;
import lombok.*;

@Table(name = "employees", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@ToString
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @OneToOne
    @JoinColumn(name ="FK_Employee_User")
    private User employee_id;
    private Integer product;

    public Employee(User employee_id, Integer product) {
        this.employee_id = employee_id;
        this.product = product;
    }
}
