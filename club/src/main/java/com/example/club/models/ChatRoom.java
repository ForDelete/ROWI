package com.example.club.models;

import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;

@Table(name = "chatrooms", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@ToString
@Setter
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String chatName;
    @ManyToOne
    @JoinColumn(name ="FK_ChatRoom_User")
    private User user_id;
    @ManyToOne
    @JoinColumn(name ="FK_ChatRoom_Employee", nullable = true)
    private Employee current_employee;
//    @ManyToOne
//    @JoinColumn(name ="FK_ChatRoom_Speciality")
    private Integer speciality;//Специализация вопроса (Какой специалист нужен)
    private Integer complexity;//Сложность

//    public ChatRoom(String chatName, User user_id, Employee current_employee, Integer speciality) {
//        this.chatName = chatName;
//        this.user_id = user_id;
//        this.current_employee = current_employee;
//        this.speciality = speciality;
//    }
    public ChatRoom(String chatName, User user_id) {
        this.chatName = chatName;
        this.user_id = user_id;
        this.current_employee = null;
        this.speciality = 0;
        this.complexity = 0;
    }
    //SetEmployee
    public void SetEmployee(Integer id, String chatName, User user_id, Employee newEmployee, Integer speciality, Integer complexity) {
        this.chatName = chatName;
        this.user_id = user_id;
        this.current_employee = newEmployee;
        this.speciality = speciality;
        this.complexity = complexity;
    }
    //SetSpeciality
    public void SetSpeciality(Integer id, String chatName, User user_id, Employee employee, Integer newSpeciality, Integer newComplexity) {
        this.chatName = chatName;
        this.user_id = user_id;
        this.current_employee = null;
        this.speciality = newSpeciality;
        this.complexity = newComplexity;
    }

}
