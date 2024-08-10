package com.example.purithm.domain.filter.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Tag {
	SPRING("봄"),
	SUMMER("여름"),
	FALL("가을"),
	WINTER("겨울"),
	BACKLIGHT("역광에서"),
	NIGHT("night"),
	DAILY("daily");

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
