package com.parc.cpss.factory;

public interface Foo {
	String toJson();
	static Foo fromJson(String json) {
		return FooImpl.fromJson(json);
	}
}

