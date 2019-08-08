package com.parc.cpss.beanval;

import java.util.Date;

import javax.validation.ConstraintTarget;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import com.parc.cpss.beanval.ViolationSeverity;

import net.andreinc.jbvext.annotations.date.*;

public class Person extends PersonBase  {
	
	public Person(Date birthday, String gender, int height) {
		super(birthday, gender, height);
	}
	
	@NotNull
	public Date birthday() {
		return m_birthday;
	}
	
	@Positive(message="grow() return must be positive")
	public int grow(
			@Positive(message="addedHeight must be positive") int addedHeight
			) {
		m_height += addedHeight;
		return m_height;
	}
}
