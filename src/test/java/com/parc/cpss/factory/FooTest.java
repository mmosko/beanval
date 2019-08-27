package com.parc.cpss.factory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FooTest {

	@Test
	void test() {
		String s = "{Foo: null}";
		Foo f = Foo.fromJson(s);
		assertNotNull(f);
	}

}
