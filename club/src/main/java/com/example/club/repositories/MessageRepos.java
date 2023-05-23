package com.example.club.repositories;

import com.example.club.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepos extends JpaRepository<Message, Integer> {
}
