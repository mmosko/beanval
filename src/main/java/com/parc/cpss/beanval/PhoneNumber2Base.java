package com.parc.cpss.beanval;

public abstract class PhoneNumber2Base {

	@CheckPhoneNumber
	protected String m_phoneNumber;

	public PhoneNumber2Base(String number) {
		m_phoneNumber = number;
	}
}
