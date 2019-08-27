package com.parc.cpss.beanval;

import java.util.Date;

public class PersonNoChecks extends PersonBase {

	public PersonNoChecks(Date birthday, String gender, int height) {
		super(birthday, gender, height);
	}

	@Override
	public Date birthday() {
		return m_birthday;
	}

	@Override
	public int grow(int addedHeight) {
		m_height += addedHeight;
		return m_height;
	}
}
