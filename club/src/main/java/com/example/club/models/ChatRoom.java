package com.example.club.models;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer speciality;

//    public ChatRoom(String chatName, User user_id, Employee current_employee, Integer speciality) {
//        this.chatName = chatName;
//        this.user_id = user_id;
//        this.current_employee = current_employee;
//        this.speciality = speciality;
//    }
    public ChatRoom(String chatName, User user_id, Integer speciality) {
        this.chatName = chatName;
        this.user_id = user_id;
        this.current_employee = null;
        this.speciality = speciality;
    }
    //SetEmployee
    public void SetEmployee(Integer id, String chatName, User user_id, Employee newEmployee, Integer speciality) {
//        this.chatName = chatName;
//        this.user_id = user_id;
        this.current_employee = newEmployee;
//        this.speciality = speciality;
    }
    //SetSpeciality
    public void SetSpeciality(Integer id, String chatName, User user_id, Employee employee, Integer newSpeciality) {
//        this.chatName = chatName;
//        this.user_id = user_id;'
        this.current_employee = null;
        this.speciality = newSpeciality;
    }

}
