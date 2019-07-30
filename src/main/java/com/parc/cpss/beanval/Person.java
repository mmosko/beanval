package com.parc.cpss.beanval;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import net.andreinc.jbvext.annotations.date.*;

public class Person {
	
	@NotNull(message = "birthday must not be null")
	@After(value = "1900-01-01", format = "yyyy-MM-dd", message="Birthday must be after 1900-01-01")
	@Past(message = "birthday must be in the past")
	private Date m_birthday;
	
	@NotNull(message = "gender must not be null")
	private String m_gender;
	
	@Positive(message="height must be positive")
	private int m_height;
	
	public Person(Date birthday, String gender, int height) {
		m_birthday = birthday;
		m_gender = gender;
		m_height = height;
	}
	
	public Date birthday() {
		return m_birthday;
	}
	
}
