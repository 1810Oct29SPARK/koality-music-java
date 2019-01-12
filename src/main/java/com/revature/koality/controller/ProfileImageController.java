package com.revature.koality.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.utility.CommonUtility;

@RestController
public class ProfileImageController {

	// SERVICE DECLARATION

	public ProfileImageController() {
		super();
	}

	// SERVICE SETTER

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

	@PostMapping("/image-customer")
	public void updateCustomerProfileImage(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int publisherId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				String imageType = jo.getString("imageType");
				byte[] imageData = CommonUtility.decodeBlobUrl(jo.getString("imageData").toString());

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
