package com.revature.koality.service;

import com.revature.koality.bean.CustomerData;
import com.revature.koality.bean.PublisherData;

public interface DashboardService {

	PublisherData getPublisherStats(int publisherId);

	CustomerData getCustomerStats(int customerId);

}
