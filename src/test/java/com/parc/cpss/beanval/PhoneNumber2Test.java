package com.parc.cpss.beanval;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PhoneNumber2Test {
	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	@BeforeAll
	public static void createValidator() {
		System.out.println("Running BeforeClass");
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@AfterAll
	public static void close() {
		validatorFactory.close();
	}

	private static void printViolations(String testvector, Set<ConstraintViolation<PhoneNumber2>> violations) {
		for (ConstraintViolation<PhoneNumber2> v : violations) {
			System.out.println(String.format("%s: %s", testvector, v.getMessage()));
		}
	}

	@Test
	void valid_intl_numbers() throws ParseException {
		String[] vectors = { "+1 415-555-1212", "+44 20 7219-3107" };
		for (String testvector : vectors) {
			PhoneNumber2 number = new PhoneNumber2(testvector);

			Set<ConstraintViolation<PhoneNumber2>> violations = validator.validate(number);
			printViolations(testvector, violations);
			assertEquals(0, violations.size());
		}
	}
}
