package com.revature.koality.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.service.ProfileService;
import com.revature.koality.utility.CommonUtility;

@RestController("credentialsController")
public class CredentialsController {

	private ProfileService profileService;

	public CredentialsController() {
		super();
	}

	public ProfileService getProfileService() {
		return profileService;
	}

	@Autowired
	@Qualifier("profileServiceImpl")
	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/credentials-publisher")
	public void updatePublisherCredentials(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int publisherId = jo.getInt("publisherId");
			String oldUsername = jo.getString("oldUsername");
			String newUsername = jo.getString("newUsername");
			String oldPassword = jo.getString("oldPassword");
			String newPassword = jo.getString("newPassword");

			if (profileService.updatePublisherCredentials(publisherId, oldUsername, newUsername, oldPassword,
					newPassword)) {
				status = 200;
			} else {
				status = 400;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		response.setStatus(status);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/credentials-customer")
	public void updateCustomerCredentials(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int customerId = jo.getInt("customerId");
			String oldUsername = jo.getString("oldUsername");
			String newUsername = jo.getString("newUsername");
			String oldPassword = jo.getString("oldPassword");
			String newPassword = jo.getString("newPassword");

			if (profileService.updateCustomerCredentials(customerId, oldUsername, newUsername, oldPassword,
					newPassword)) {
				status = 200;
			} else {
				status = 400;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		response.setStatus(status);

	}

}
