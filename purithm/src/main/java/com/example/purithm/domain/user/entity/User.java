package com.example.purithm.domain.user.entity;

import com.example.purithm.domain.filter.entity.Membership;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String providerId;
  private String username;
  private String profile;
  private boolean terms;
  private Membership membership;

  public void agreeToTerms() {
    this.terms = true;
  }
  public void upgradeToPremium() {
    this.membership = Membership.PREMIUM;
  }
  public void upgradeToPremiumPlus() {
    this.membership = Membership.PREMIUM_PLUS;
  }
}
