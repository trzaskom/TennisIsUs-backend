package com.trzaskom.jpa.repository;

import com.trzaskom.jpa.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {

    @Query(value = "SELECT COUNT(*) FROM friends WHERE user1_id IN (?1, ?2) AND user2_id IN (?1,?2)", nativeQuery = true)
    Integer existsFriendsByIds(Long userId, Long friendId);

    @Query(value = "SELECT * FROM friends WHERE user1_id = ?1 OR user2_id = ?1", nativeQuery = true)
    List<Friends> findAllUserFriends(Long userId);
}
