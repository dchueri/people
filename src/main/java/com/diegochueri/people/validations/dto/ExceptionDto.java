package com.diegochueri.people.validations.dto;

public class ExceptionDto {

	private String message;

	public ExceptionDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
