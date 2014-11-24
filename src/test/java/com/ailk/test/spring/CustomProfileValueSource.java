package com.ailk.test.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.test.annotation.ProfileValueSource;
import org.springframework.test.annotation.SystemProfileValueSource;

public class CustomProfileValueSource implements ProfileValueSource {
	static Map<String, String> valuesStore;
	static {
		valuesStore = new HashMap<String, String>();
		valuesStore.put("test-groups", "unit-tests");
	}

	static SystemProfileValueSource systemProfileValueSource = SystemProfileValueSource.getInstance();

	@Override
	public String get(String name) {
		String v = systemProfileValueSource.get(name);
		if (v == null) {
			v = valuesStore.get(name);
		}
		return v;
	}
}
