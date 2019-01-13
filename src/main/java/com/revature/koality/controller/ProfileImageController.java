package com.revature.koality.controller;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@PostMapping("/image-publisher")
	public void updatePublisherProfileImage(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int publisherId = Integer.parseInt(session.getAttribute("publisherId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

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
		} else {
			status = 440;
		}

		response.setStatus(status);

	}

	@PostMapping("/image-customer")
	public void updateCustomerProfileImage(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

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
		} else {
			status = 440;
		}

		response.setStatus(status);

	}

}
