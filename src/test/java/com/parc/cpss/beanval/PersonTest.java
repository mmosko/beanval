package com.parc.cpss.beanval;

//import com.parc.cpss.beanval.Person;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
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
	
	private static String violationSeverity(ConstraintViolation<Person> violation) {
		Set<Class<? extends Payload>> payloads = violation.getConstraintDescriptor().getPayload();
		String severity = "Error";
		
		for (Class<? extends Payload> payload : payloads) {
			switch(payload.getTypeName()) {
			case "com.parc.cpss.beanval.ViolationSeverity$Info":
				severity = "Info";
				break;
			default:
				break;
			}
		}
			
		return severity;
	}
		
	private 
	static void printViolations(Set<ConstraintViolation<Person>> violations) {
		for (ConstraintViolation<Person> v : violations) {
			System.out.println(String.format("%s: %s", violationSeverity(v), v.getMessage()));
		}		
	}
	
	@Test
	void good_person() throws ParseException {
		Date birthday = dateFormat.parse("1901-01-01");
		Person person = new Person(birthday, "Male", 127);
		assertNotNull(person);
		
		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		printViolations(violations);
		assertTrue(violations.isEmpty());
	}


	@Test
	void null_birthday() {
		Person person = new Person(null, "Female", 127);
		assertNotNull(person);
		
		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		printViolations(violations);
		// violates NotNull and After
		assertEquals(2, violations.size());
	}

	@Test
	void null_gender() throws ParseException {
		Person person = new Person(dateFormat.parse("1901-01-01"), null, 127);
		assertNotNull(person);
		
		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		printViolations(violations);
		// violates NotNull
		assertEquals(1, violations.size());
	}
	
	@Test
	void birthday_too_old() throws ParseException {
		Person person = new Person(dateFormat.parse("1900-01-01"), "Female", 127);
		assertNotNull(person);
		
		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		printViolations(violations);

		// violates After
		assertEquals(1, violations.size());
	}
	
	@Test
	void birthday_in_future() throws ParseException {
		Person person = new Person(dateFormat.parse("2900-01-01"), "Female", 127);
		assertNotNull(person);
		
		Set<ConstraintViolation<Person>> violations = validator.validate(person);
		printViolations(violations);

		// violates Past
		assertEquals(1, violations.size());
	}
	
	@Test
	void negative_grow_result() throws ParseException {
		int height = 127;
		Person person = new Person(dateFormat.parse("1901-01-01"), "Male", height);
		
		ExecutableValidator executableValidator = Validation
		        .buildDefaultValidatorFactory()
		        .getValidator()
		        .forExecutables();
		
		try {
			int bad_height = -5;
		    Method grow = person.getClass().getMethod("grow", int.class);
		    
		    // TODO: This calls System.out.println for each violation.  We need to
		    // pass it through our printViolations() to get the severity.
		    executableValidator
		            .validateReturnValue(person, grow, bad_height).stream()
		            .map(ConstraintViolation::getMessage)
		            .forEach(System.out::println);
		} catch (NoSuchMethodException e) {
		    e.printStackTrace();
		}
	}

}
