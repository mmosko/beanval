package com.parc.cpss.beanval;

import javax.validation.constraints.Pattern;

public abstract class PhoneNumberBase {

	@Pattern(regexp = "^\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$")
	protected String m_phoneNumber;

	public PhoneNumberBase(String number) {
		m_phoneNumber = number;
	}
}
