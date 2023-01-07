package com.diegochueri.people.validations.dto;

public class IdNotFoundExceptionDto {

	private String message;

	public IdNotFoundExceptionDto() {
	}

	public IdNotFoundExceptionDto(String entity) {
		this.message = ("ID de " + entity + " informado não encontrado");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
