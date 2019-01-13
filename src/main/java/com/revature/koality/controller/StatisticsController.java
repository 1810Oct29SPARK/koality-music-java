package com.revature.koality.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/publisher-stats")
	public void getPublisherStats(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		PublisherData publisherData = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int publisherId = Integer.parseInt(session.getAttribute("publisherId").toString());

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

	@GetMapping("/customer-stats")
	public void getCustomerStats(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		CustomerData customerData = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

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
