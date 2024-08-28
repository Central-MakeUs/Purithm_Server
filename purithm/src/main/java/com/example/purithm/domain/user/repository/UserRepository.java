package com.example.purithm.domain.user.repository;

import java.time.LocalDateTime;

import com.example.purithm.domain.user.entity.Provider;
import com.example.purithm.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
  @Query("SELECT u FROM User u WHERE u.provider = :provider AND u.providerId = :providerId AND u.deletedAt IS NULL")
  User findByProviderAndProviderId(Provider provider, String providerId);

  @Query("SELECT COUNT(r) FROM Review r WHERE r.user.id = :userId")
  int countReviewsByUserId(@Param("userId") Long userId);

  @Query("SELECT COUNT(fl) FROM FilterLike fl WHERE fl.user.id = :userId")
  int countLikesByUserId(@Param("userId") Long userId);

  @Query("SELECT COUNT(ufl) FROM UserFilterLog ufl WHERE ufl.userId = :userId")
  int countLogsByUserId(@Param("userId") Long userId);

  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u "
      + "WHERE u.provider = :provider AND u.providerId = :providerId AND u.deletedAt IS NOT NULL AND u.deletedAt >= :sevenDaysAgo")
  boolean existsWithdrawnUser(Provider provider, String providerId, LocalDateTime sevenDaysAgo);

  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u "
      + "WHERE u.id = :userId AND u.deletedAt IS NOT NULL")
  boolean existsWithdrawnUserById(Long userId);
}
