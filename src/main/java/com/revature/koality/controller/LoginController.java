package com.revature.koality.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Publisher;
import com.revature.koality.service.AuthenticationService;
import com.revature.koality.utility.CommonUtility;

@RestController("loginController")
public class LoginController {

	private AuthenticationService authenticationService;

	public LoginController() {
		super();
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	@Autowired
	@Qualifier("authenticationServiceImpl")
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	@PostMapping("/login-publisher")
	public void loginPublisher(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Publisher publisher = null;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			String username = jo.getString("username");
			String password = jo.getString("password");

			publisher = authenticationService.isValidPublisher(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		if (publisher != null && publisher.getPublisherId() != 0) {
			status = 200;
			HttpSession session = request.getSession(true);
			session.setAttribute("publisherId", publisher.getPublisherId());
			session.setAttribute("customerId", null);
			session.setMaxInactiveInterval(600);
		} else {
			status = 401;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(publisher));

	}

	@PostMapping("/login-customer")
	public void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Customer customer = null;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			String username = jo.getString("username");
			String password = jo.getString("password");

			customer = authenticationService.isValidCustomer(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		if (customer != null && customer.getCustomerId() != 0) {
			status = 200;
			HttpSession session = request.getSession(true);
			session.setAttribute("customerId", customer.getCustomerId());
			session.setAttribute("publisherId", null);
			session.setMaxInactiveInterval(600);
		} else {
			status = 401;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(customer));

	}

}
