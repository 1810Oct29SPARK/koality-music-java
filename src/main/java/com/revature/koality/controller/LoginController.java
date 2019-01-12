package com.revature.koality.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	@PostMapping("login-publisher")
	public void loginPublisher(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Publisher publisher = null;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			String username = jo.getString("username");
			String password = jo.getString("password");

			publisher = as.isValidPublisher(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		if (publisher != null) {
			status = 200;
			request.getSession(true).setAttribute("publisherId", publisher.getPublisherId());
		} else {
			status = 401;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(publisher));

	}

	@PostMapping("login-customer")
	public void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Customer customer = null;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			String username = jo.getString("username");
			String password = jo.getString("password");

			customer = as.isValidCustomer(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		if (customer != null) {
			status = 200;
			request.getSession(true).setAttribute("customerId", customer.getCustomerId());
		} else {
			status = 401;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(customer));

	}

}
