package com.example.purithm.domain.user.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.purithm.domain.filter.entity.Membership;
import com.example.purithm.domain.user.dto.request.UserInfoRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Provider provider;

  private String providerId;

  private String username;

  private String profile;

  private boolean terms;

  private Membership membership;

  private String email;

  @Column(nullable = true)
  private String password;

  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  @Column(updatable = false)
  private Date createdAt;

  @Column(updatable = false)
  private LocalDateTime deletedAt;

  public void agreeToTerms() {
    this.terms = true;
  }
  public void upgradeToPremium() {
    this.membership = Membership.PREMIUM;
  }
  public void upgradeToPremiumPlus() {
    this.membership = Membership.PREMIUM_PLUS;
  }
  public void updateProfile(UserInfoRequestDto userInfo) {
    this.profile = userInfo.profile();
    this.username = userInfo.name();
  }

  public void withdraw() {
    this.deletedAt = LocalDateTime.now();
  }

}
