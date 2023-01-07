package com.diegochueri.people.validations.dto;

public class PersonCreateErrorDto {
	private String field;
	private String error;

	public PersonCreateErrorDto(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
}
