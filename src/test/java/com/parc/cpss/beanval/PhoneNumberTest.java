package com.parc.cpss.beanval;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PhoneNumberTest {
	private static ValidatorFactory validatorFactory;
	private static Validator validator;
	private static SimpleDateFormat dateFormat;

	@BeforeAll
	public static void createValidator() {
		System.out.println("Running BeforeClass");
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	@AfterAll
	public static void close() {
		validatorFactory.close();
	}

	private static void printViolations(String testvector, Set<ConstraintViolation<PhoneNumber>> violations) {
		for (ConstraintViolation<PhoneNumber> v : violations) {
			System.out.println(String.format("%s: %s", testvector, v.getMessage()));
		}
	}

	@Test
	void valid_numbers() throws ParseException {
		String[] vectors = { "415-555-1212", "831 555.1212" };
		for (String testvector : vectors) {
			PhoneNumber number = new PhoneNumber(testvector);

			Set<ConstraintViolation<PhoneNumber>> violations = validator.validate(number);
			printViolations(testvector, violations);
			assertEquals(0, violations.size());
		}
	}
}
