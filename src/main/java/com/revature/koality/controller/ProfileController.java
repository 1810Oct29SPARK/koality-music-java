package com.revature.koality.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Publisher;
import com.revature.koality.utility.CommonUtility;

@RestController("profileController")
public class ProfileController {

	// SERVICES DECLARATION

	public ProfileController() {
		super();
	}

	// SERVICE SETTER

	@GetMapping("/profile-publisher")
	public void getPublisherProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Publisher publisher = new Publisher();

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int publisherId = Integer.parseInt(session.getAttribute("publisherId").toString());
				// SERVICE
				if (publisher.getPublisherId() != 0) {
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

	@GetMapping("/profile-customer")
	public void getCustomerProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Customer customer = new Customer();

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());
				// SERVICE
				if (customer.getCustomerId() != 0) {
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

	@PutMapping("/profile-publisher")
	public void updatePublisherDetail(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int publisherId = Integer.parseInt(session.getAttribute("publisherId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				String firstName = jo.getString("firstName");
				String lastName = jo.getString("lastName");
				String email = jo.getString("email");
				String companyName = jo.getString("companyName");

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

	@PutMapping("/profile-customer")
	public void updateCustomerDetail(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				String firstName = jo.getString("firstName");
				String lastName = jo.getString("lastName");
				String email = jo.getString("email");
				String favoriteGenre = jo.getString("favoriteGenre");

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