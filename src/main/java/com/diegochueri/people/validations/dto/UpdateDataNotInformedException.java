package com.diegochueri.people.validations.dto;

public class UpdateDataNotInformedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UpdateDataNotInformedException() {
		super("não foram enviados dados para atualizar");
	}
}
