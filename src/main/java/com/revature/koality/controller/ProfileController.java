package com.revature.koality.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Publisher;
import com.revature.koality.service.ProfileService;
import com.revature.koality.utility.CommonUtility;

@RestController("profileController")
public class ProfileController {

	private ProfileService profileService;

	public ProfileController() {
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
	@PostMapping("/profile-publisher")
	public void getPublisherProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Publisher publisher = null;

		HttpSession session = request.getSession(false);
		int publisherId = CommonUtility.getUserIdFromSessionOrBody(session, request, "publisherId");

		if (publisherId != 0) {
			try {
				publisher = profileService.getPublisherProfile(publisherId);
				if (publisher != null && publisher.getPublisherId() != 0) {
					status = 200;
				} else {
					status = 400;
				}
			} catch (Exception e) {
				e.printStackTrace();
				status = 401;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(publisher));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/profile-customer")
	public void getCustomerProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Customer customer = null;

		HttpSession session = request.getSession(false);
		int customerId = CommonUtility.getUserIdFromSessionOrBody(session, request, "customerId");

		if (customerId != 0) {
			try {
				customer = profileService.getCustomerProfile(customerId);
				if (customer != null && customer.getCustomerId() != 0) {
					status = 200;
				} else {
					status = 400;
				}
			} catch (Exception e) {
				e.printStackTrace();
				status = 401;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(customer));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/profile-publisher")
	public void updatePublisherDetail(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int publisherId = jo.getInt("publisherId");
			String firstName = jo.getString("firstName");
			String lastName = jo.getString("lastName");
			String email = jo.getString("email");
			String companyName = jo.getString("companyName");

			if (profileService.updatePublisherDetails(publisherId, firstName, lastName, email, companyName)) {
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
	@PutMapping("/profile-customer")
	public void updateCustomerDetail(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int customerId = jo.getInt("customerId");
			String firstName = jo.getString("firstName");
			String lastName = jo.getString("lastName");
			String email = jo.getString("email");
			String favoriteGenre = jo.getString("favoriteGenre");

			if (profileService.updateCustomerDetails(customerId, firstName, lastName, email, favoriteGenre)) {
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
