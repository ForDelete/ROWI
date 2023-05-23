package com.example.club.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

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
    private ChatRoom chatRoom_id;
    @ManyToOne
    @JoinColumn(name ="FK_Message_User")
    private User sender_id;
    private String message;
    private Date time;

    public Message(ChatRoom chatRoom_id, User sender_id, String message, Date time) {
        this.chatRoom_id = chatRoom_id;
        this.sender_id = sender_id;
        this.message = message;
        this.time = time;
    }
}
