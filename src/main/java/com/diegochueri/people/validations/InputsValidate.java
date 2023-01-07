package com.diegochueri.people.validations;

import com.diegochueri.people.controllers.dto.AddressUpdateDto;
import com.diegochueri.people.controllers.dto.PersonUpdateDto;
import com.diegochueri.people.validations.dto.UpdateDataNotInformedException;

public class InputsValidate {

	public static void validateIfPersonUpdateInputIsNull(PersonUpdateDto personInfoToUpdate) {
		if (personInfoToUpdate.getName() == null && personInfoToUpdate.getBirthDate() == null) {
			throw new UpdateDataNotInformedException();
		}
	}

	public static void validateIfAddressUpdateInputIsNull(AddressUpdateDto addressInfoToUpdate) {
		if (addressInfoToUpdate.getCep() == null && addressInfoToUpdate.getNumber() == null
				&& addressInfoToUpdate.getStreet() == null && addressInfoToUpdate.getTown() == null) {
			throw new UpdateDataNotInformedException();
		}
	}
}
