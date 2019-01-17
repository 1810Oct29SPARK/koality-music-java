package com.revature.koality.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("logoutController")
public class LogoutController {

	public LogoutController() {
		super();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/logout")
	public void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.getSession().invalidate();

	}

}
