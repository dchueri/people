package com.diegochueri.people.validations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.diegochueri.people.validations.dto.IdNotFoundExceptionDto;
import com.diegochueri.people.validations.dto.PersonCreateErrorDto;
import com.diegochueri.people.validations.dto.UpdateDataNotInformedException;
import com.diegochueri.people.validations.dto.ExceptionDto;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<PersonCreateErrorDto> handle(MethodArgumentNotValidException exception) {
		List<PersonCreateErrorDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			PersonCreateErrorDto error = new PersonCreateErrorDto(e.getField(), message);
			dto.add(error);
		});

		return dto;
	}

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public IdNotFoundExceptionDto handle(EntityNotFoundException exception) {
		IdNotFoundExceptionDto error = new IdNotFoundExceptionDto();
		if (exception.getMessage().contains("Person")) {
			error = new IdNotFoundExceptionDto("Pessoa");
		}
		if (exception.getMessage().contains("Address")) {
			error = new IdNotFoundExceptionDto("Endereço");
		}
		return error;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UpdateDataNotInformedException.class)
	public ExceptionDto handle(UpdateDataNotInformedException exception) {
		ExceptionDto error = new ExceptionDto(exception.getMessage());
		return error;
	}
}
