package com.parc.cpss.beanval;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneNumberValidator implements ConstraintValidator<CheckPhoneNumber, String> {
	@Override
	public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
		try {
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			PhoneNumber phoneNumber = phoneUtil.parse(object, null);
			return phoneUtil.isValidNumber(phoneNumber);
		} catch (NumberParseException e) {
			System.err.println("NumberParseException was thrown: " + e.toString());
			return false;
		}
	}
}
