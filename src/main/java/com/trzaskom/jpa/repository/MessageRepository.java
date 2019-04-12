package com.trzaskom.jpa.repository;

import com.trzaskom.jpa.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM messages WHERE sender_id = ?1 OR receiver_id = ?1", nativeQuery = true)
    List<Message> getAllByLoggedUser(Long userId);
}
