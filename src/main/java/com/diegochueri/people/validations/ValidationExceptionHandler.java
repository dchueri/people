package com.diegochueri.people.validations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.diegochueri.people.validations.dto.ExceptionDto;
import com.diegochueri.people.validations.dto.IdNotFoundExceptionDto;
import com.diegochueri.people.validations.dto.PersonCreateErrorDto;
import com.diegochueri.people.validations.dto.UpdateDataNotInformedException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ValidationExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<PersonCreateErrorDto>> handle(MethodArgumentNotValidException exception) {
		List<PersonCreateErrorDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			PersonCreateErrorDto error = new PersonCreateErrorDto(e.getField(), message);
			dto.add(error);
		});

		return ResponseEntity.badRequest().body(dto);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<IdNotFoundExceptionDto> handle(EntityNotFoundException exception) {
		IdNotFoundExceptionDto error = new IdNotFoundExceptionDto();
		if (exception.getMessage().contains("Person")) {
			error = new IdNotFoundExceptionDto("Pessoa");
		}
		if (exception.getMessage().contains("Address")) {
			error = new IdNotFoundExceptionDto("Endereço");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<IdNotFoundExceptionDto> handle(EmptyResultDataAccessException exception) {
		IdNotFoundExceptionDto error = new IdNotFoundExceptionDto("Endereço");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(UpdateDataNotInformedException.class)
	public ResponseEntity<ExceptionDto> handle(UpdateDataNotInformedException exception) {
		ExceptionDto error = new ExceptionDto(exception.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
