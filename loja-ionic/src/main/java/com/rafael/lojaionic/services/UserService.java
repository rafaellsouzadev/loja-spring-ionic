package com.rafael.lojaionic.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.rafael.lojaionic.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
