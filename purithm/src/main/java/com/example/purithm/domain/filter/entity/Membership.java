package com.example.purithm.domain.filter.entity;

public enum Membership {
  BASIC,
  PREMIUM,
  PREMIUM_PLUS;

  public static Membership checkMembership(int numOfReviews) {
    if (numOfReviews >= 8) {
      return PREMIUM_PLUS;
    }
    if (numOfReviews >= 4) {
      return PREMIUM;
    }
    return BASIC;
  }
}
