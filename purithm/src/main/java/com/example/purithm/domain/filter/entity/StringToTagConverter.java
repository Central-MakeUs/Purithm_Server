package com.example.purithm.domain.filter.entity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToTagConverter implements Converter<String, Tag> {
	@Override
	public Tag convert(String source) {
		return Tag.fromValue(source);
	}
}