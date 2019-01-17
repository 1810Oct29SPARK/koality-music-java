package com.revature.koality.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.service.ProfileService;
import com.revature.koality.utility.CommonUtility;

@RestController("profileImageController")
public class ProfileImageController {

	private ProfileService profileService;

	public ProfileImageController() {
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
	@PostMapping("/image-publisher")
	public void updatePublisherProfileImage(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int publisherId = jo.getInt("publisherId");
			String imageType = jo.getString("imageType");
			byte[] imageData = CommonUtility.decodeBlobUrl(jo.getString("imageData").toString());

			if (profileService.updatePublisherImage(publisherId, imageType, imageData)) {
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
	@PostMapping("/image-customer")
	public void updateCustomerProfileImage(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int customerId = jo.getInt("customerId");
			String imageType = jo.getString("imageType");
			byte[] imageData = CommonUtility.decodeBlobUrl(jo.getString("imageData").toString());

			if (profileService.updateCustomerImage(customerId, imageType, imageData)) {
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
