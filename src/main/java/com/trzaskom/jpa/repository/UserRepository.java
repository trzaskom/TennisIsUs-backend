package com.trzaskom.jpa.repository;

import com.trzaskom.jpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
    public boolean existsByUsername(String username);
}
