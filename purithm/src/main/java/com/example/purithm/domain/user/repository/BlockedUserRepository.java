package com.example.purithm.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.purithm.domain.user.entity.BlockedUser;

public interface BlockedUserRepository extends JpaRepository<BlockedUser, Long> {
}
