package com.zarak.spring_security_test.dto.user;

public enum UserRole {
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	String authority;

	UserRole(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}
}
