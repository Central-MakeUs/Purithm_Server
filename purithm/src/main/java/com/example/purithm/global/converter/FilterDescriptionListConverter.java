package com.example.purithm.global.converter;

import java.util.List;

import com.example.purithm.domain.filter.entity.FilterDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class FilterDescriptionListConverter implements AttributeConverter<List<FilterDetail>, String> {

	private final ObjectMapper mapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(List<FilterDetail> attribute) {
		try {
			return mapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FilterDetail> convertToEntityAttribute(String dbData) {
		try {
			return mapper.readValue(dbData, new TypeReference<List<FilterDetail>>() {});
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
