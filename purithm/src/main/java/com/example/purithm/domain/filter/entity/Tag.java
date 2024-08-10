package com.example.purithm.domain.filter.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Tag {
	SPRING("spring"),
	SUMMER("summer"),
	FALL("fall"),
	WINTER("winter"),
	BACKLIGHT("backlight"),
	NIGHT("night"),
	DAILY("daily"),
	CAT("cat");

	private final String value;

	public static Tag fromValue(String value) {
		for (Tag tag : Tag.values()) {
			if (tag.value.equals(value)) {
				return tag;
			}
		}

		throw new IllegalArgumentException("Unknown Tag value: " + value);
	}
}
