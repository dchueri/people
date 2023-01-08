package com.diegochueri.people.validations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.diegochueri.people.controllers.dto.AddressUpdateDto;
import com.diegochueri.people.controllers.dto.PersonUpdateDto;
import com.diegochueri.people.models.Person;
import com.diegochueri.people.utils.PersonMockCreate;
import com.diegochueri.people.validations.dto.UpdateDataNotInformedException;

@SpringBootTest
class InputValidationTest {

	@InjectMocks
	private InputsValidate inputsValidate;

	private Person person;
	private PersonMockCreate personsMock = new PersonMockCreate();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		person = personsMock.personAdd();
	}

	@Test
	void whenUpdatePersonInputIsNotNullThenThrowsUpdateDataNotInformedException() {
		PersonUpdateDto personUpdateInfo = new PersonUpdateDto();
		personUpdateInfo.setName("Updated Name");

		Assertions.assertDoesNotThrow(() -> InputsValidate.validateIfPersonUpdateInputIsNull(personUpdateInfo));
	}

	@Test
	void whenUpdatePersonInputIsNullThenThrowsUpdateDataNotInformedException() {
		PersonUpdateDto nullPersonUpdateInfo = new PersonUpdateDto();

		Exception response = Assertions.assertThrows(UpdateDataNotInformedException.class,
				() -> InputsValidate.validateIfPersonUpdateInputIsNull(nullPersonUpdateInfo));

		Assertions.assertEquals(UpdateDataNotInformedException.class, response.getClass());
	}

	@Test
	void whenUpdateAddressInputIsNotNullThenThrowsUpdateDataNotInformedException() {
		AddressUpdateDto addressUpdateInfo = new AddressUpdateDto();
		addressUpdateInfo.setStreet("Updated Street");

		Assertions.assertDoesNotThrow(() -> InputsValidate.validateIfAddressUpdateInputIsNull(addressUpdateInfo));
	}

	@Test
	void whenUpdateAddressInputIsNullThenThrowsUpdateDataNotInformedException() {
		AddressUpdateDto nullAddressUpdateInfo = new AddressUpdateDto();

		Exception response = Assertions.assertThrows(UpdateDataNotInformedException.class,
				() -> InputsValidate.validateIfAddressUpdateInputIsNull(nullAddressUpdateInfo));

		Assertions.assertEquals(UpdateDataNotInformedException.class, response.getClass());
	}

}
