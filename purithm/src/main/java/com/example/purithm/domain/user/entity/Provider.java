package com.example.purithm.domain.user.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Provider {
	KAKAO("KAKAO"),
	APPLE("APPLE");
	private final String provider;
}
