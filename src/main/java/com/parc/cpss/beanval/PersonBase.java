package com.parc.cpss.beanval;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import net.andreinc.jbvext.annotations.date.After;

public abstract class PersonBase implements PersonInterface {
	@NotNull(message = "birthday must not be null")
	@After(value = "1900-01-01", format = "yyyy-MM-dd", message = "Birthday must be after {value}", payload = ViolationSeverity.Info.class)
	@Past(message = "birthday must be in the past")
	protected Date m_birthday;

	@NotNull(message = "gender must not be null")
	protected String m_gender;

	@Positive(message = "height must be positive")
	protected int m_height;

	public PersonBase(Date birthday, String gender, int height) {
		m_birthday = birthday;
		m_gender = gender;
		m_height = height;
	}
}
