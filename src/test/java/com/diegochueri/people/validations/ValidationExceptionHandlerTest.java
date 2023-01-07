package com.diegochueri.people.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diegochueri.people.validations.dto.ExceptionDto;
import com.diegochueri.people.validations.dto.UpdateDataNotInformedException;

@SpringBootTest
class ValidationExceptionHandlerTest {

	@InjectMocks
	private ValidationExceptionHandler exceptionHandler;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void whenUpdateInputIsNullThenThrowsUpdateDataNotInformedException() {
		ResponseEntity<ExceptionDto> response = exceptionHandler.handle(new UpdateDataNotInformedException());

		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		Assertions.assertEquals(ExceptionDto.class, response.getBody().getClass());
		Assertions.assertEquals("não foram enviados dados para atualizar", response.getBody().getMessage());
	}

}
