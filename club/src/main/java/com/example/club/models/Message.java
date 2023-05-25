package com.example.club.models;

import jakarta.persistence.*;
import lombok.*;

//import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Table(name = "messages", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@ToString
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @ManyToOne
    @JoinColumn(name ="FK_Message_ChatRoom")
    private ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn(name ="FK_Message_User")
    private User sender_id;
    private String message;
    private Time time;

    public Message(ChatRoom chatRoom, User sender_id, String message, Time time) {
        this.chatRoom = chatRoom;
        this.sender_id = sender_id;
        this.message = message;
        this.time = time;
    }
}
