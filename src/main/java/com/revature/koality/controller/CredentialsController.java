package com.revature.koality.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.utility.CommonUtility;

@RestController
public class CredentialsController {

	// SERVICE DECLARATION

	public CredentialsController() {
		super();
	}

	// SERVICE SETTER

	@PutMapping("/credentials-publisher")
	public void updatePublisherCredentials(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int publisherId = Integer.parseInt(session.getAttribute("publisherId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				String oldUsername = jo.getString("oldUsername");
				String newUsername = jo.getString("newUsername");
				String oldPassword = jo.getString("oldPassword");
				String newPassword = jo.getString("newPassword");

				// SERVICE
			} catch (Exception e) {
				e.printStackTrace();
				status = 400;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);

	}

	@PutMapping("/credentials-customer")
	public void updateCustomerCredentials(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				String oldUsername = jo.getString("oldUsername");
				String newUsername = jo.getString("newUsername");
				String oldPassword = jo.getString("oldPassword");
				String newPassword = jo.getString("newPassword");

				// SERVICE
			} catch (Exception e) {
				e.printStackTrace();
				status = 400;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);

	}

}
