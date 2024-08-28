package com.example.purithm.domain.user.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Provider {
	KAKAO("KAKAO"),
	APPLE("APPLE"),
	PURITHM("자체 가입");

	private final String provider;
}
