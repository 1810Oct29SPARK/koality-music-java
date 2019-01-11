package com.revature.koality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.service.AuthenticationService;

@RestController("loginController")
public class LoginController {

	private AuthenticationService as;

	public LoginController() {
		super();
	}

	public AuthenticationService getAs() {
		return as;
	}

	@Autowired
	@Qualifier("authenticationServiceImpl")
	public void setAs(AuthenticationService as) {
		this.as = as;
	}

}
