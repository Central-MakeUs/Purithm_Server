package com.example.purithm.domain.user.repository;

import com.example.purithm.domain.user.entity.Provider;
import com.example.purithm.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByProviderAndProviderId(Provider provider, String providerId);

  @Query("SELECT COUNT(r) FROM Review r WHERE r.user.id = :userId")
  int countReviewsByUserId(@Param("userId") Long userId);

  @Query("SELECT COUNT(fl) FROM FilterLike fl WHERE fl.user.id = :userId")
  int countLikesByUserId(@Param("userId") Long userId);

  @Query("SELECT COUNT(ufl) FROM UserFilterLog ufl WHERE ufl.userId = :userId")
  int countLogsByUserId(@Param("userId") Long userId);

  boolean existsByProviderId(String id);
}
