package com.parc.cpss.factory;

public class FooImpl extends FooBase {

//	protected FooImpl() {}

	public static Foo fromJson(String json) {
		return new FooImpl();
	}
	
	@Override
	public String toJson() {
		return "{Foo: null}";
	}

}

