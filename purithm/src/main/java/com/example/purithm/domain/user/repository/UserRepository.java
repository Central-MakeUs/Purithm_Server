package com.example.purithm.domain.user.repository;

import com.example.purithm.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}