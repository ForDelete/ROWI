package com.example.club.models;


import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;

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
    private Integer specialization;//На чём специализируется работник
    private Integer laci;//(Level of access to complex issues) Уровень доступа к сложным вопросам пользователей

    public Employee(User employee_id, Integer specialization, Integer laci) {
        this.employee_id = employee_id;
        this.specialization = specialization;
        this.laci = laci;
    }
}
