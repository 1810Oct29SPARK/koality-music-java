package com.revature.koality.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.service.RegisterService;
import com.revature.koality.utility.CommonUtility;

@RestController("registerController")
public class RegisterController {

	RegisterService registerService;

	public RegisterController() {
		super();
	}

	public RegisterService getRegisterService() {
		return registerService;
	}

	@Autowired
	@Qualifier("registerServiceImpl")
	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	@PostMapping("/register-publisher")
	public ResponseEntity<Integer> registerPublisher(HttpServletRequest request) {

		HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
		Integer id = -1;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			String firstName = jo.getString("firstName");
			String lastName = jo.getString("lastName");
			String email = jo.getString("email");
			String companyName = jo.getString("companyName");
			String username = jo.getString("username");
			String password = jo.getString("password");

			id = registerService.registerPublisher(firstName, lastName, email, companyName, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.BAD_REQUEST;
		}

		if (id > 0) {
			status = HttpStatus.OK;
			HttpSession session = request.getSession(true);
			session.setAttribute("publisherId", id);
			session.setAttribute("customerId", null);
			session.setMaxInactiveInterval(600);
		}

		return new ResponseEntity<Integer>(id, status);

	}

	@PostMapping("/register-customer")
	public ResponseEntity<Integer> registerCustomer(HttpServletRequest request) {

		HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
		Integer id = -1;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			String firstName = jo.getString("firstName");
			String lastName = jo.getString("lastName");
			String email = jo.getString("email");
			String favoriteGenre = jo.getString("favoriteGenre");
			String username = jo.getString("username");
			String password = jo.getString("password");

			id = registerService.registerCustomer(firstName, lastName, email, favoriteGenre, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.BAD_REQUEST;
		}

		if (id > 0) {
			status = HttpStatus.OK;
			HttpSession session = request.getSession(true);
			session.setAttribute("customerId", id);
			session.setAttribute("publisherId", null);
			session.setMaxInactiveInterval(600);
		}

		return new ResponseEntity<Integer>(id, status);

	}

}
