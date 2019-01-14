package com.revature.koality.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.bean.CustomerData;
import com.revature.koality.bean.PublisherData;
import com.revature.koality.service.DashboardService;
import com.revature.koality.utility.CommonUtility;

@RestController("statisticsController")
public class StatisticsController {

	private DashboardService dashboardService;

	public StatisticsController() {
		super();
	}

	public DashboardService getDashboardService() {
		return dashboardService;
	}

	@Autowired
	@Qualifier("dashboardServiceImpl")
	public void setDashboardService(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/publisher-stats")
	public void getPublisherStats(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		PublisherData publisherData = null;

		HttpSession session = request.getSession(false);
		int publisherId = CommonUtility.getUserIdFromSessionOrBody(session, request, "publisherId");

		if (publisherId != 0) {
			try {
				publisherData = dashboardService.getPublisherStats(publisherId);
				if (publisherData == null) {
					status = 401;
				} else {
					status = 200;
				}
			} catch (Exception e) {
				e.printStackTrace();
				status = 400;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(publisherData));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/customer-stats")
	public void getCustomerStats(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		CustomerData customerData = null;

		HttpSession session = request.getSession(false);
		int customerId = CommonUtility.getUserIdFromSessionOrBody(session, request, "customerId");

		if (customerId != 0) {
			try {
				customerData = dashboardService.getCustomerStats(customerId);
				if (customerData == null) {
					status = 401;
				} else {
					status = 200;
				}
			} catch (Exception e) {
				e.printStackTrace();
				status = 400;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(customerData));

	}

}
