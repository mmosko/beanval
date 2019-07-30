package com.parc.cpss.beanval;

//import com.parc.cpss.beanval.Person;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class PersonTest {

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
	
	@Test
	void good_person() throws ParseException {
		Date birthday = dateFormat.parse("1901-01-01");
		Person person = new Person(birthday, "Male", 127);
		assertNotNull(person);
		
		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		assertTrue(violations.isEmpty());
	}


	@Test
	void null_birthday() {
		Person person = new Person(null, "Female", 127);
		assertNotNull(person);
		
		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		// violates NotNull and After
		for (ConstraintViolation<Person> v : violations) {
			System.out.println(v.getMessage());
		}
		assertEquals(2, violations.size());
	}

}
