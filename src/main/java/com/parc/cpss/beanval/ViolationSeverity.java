package com.parc.cpss.beanval;

import javax.validation.Payload;

public class ViolationSeverity {
	public static class Info implements Payload {
	};

	public static class Error implements Payload {
	};
}
