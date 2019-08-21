package com.parc.cpss.beanval;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Person extends PersonBase {

	public Person(Date birthday, String gender, int height) {
		super(birthday, gender, height);
	}

	@Override
	@NotNull
	public Date birthday() {
		return m_birthday;
	}

	@Override
	@Positive(message = "grow() return must be positive")
	public int grow(@Positive(message = "addedHeight must be positive") int addedHeight) {
		m_height += addedHeight;
		return m_height;
	}
}
