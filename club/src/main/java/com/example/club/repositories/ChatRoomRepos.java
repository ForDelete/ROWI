package com.example.club.repositories;

import com.example.club.models.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomRepos extends JpaRepository<ChatRoom, Integer> {
}
