package com.revature.koality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.revature.koality.bean.CustomerData;
import com.revature.koality.bean.PublisherData;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.PublisherDAO;

@Service("dashboardServiceImpl")
public class DashboardServiceImpl implements DashboardService {

	private PublisherDAO pd;
	private CustomerDAO cd;

	public DashboardServiceImpl() {
		super();
	}

	public DashboardServiceImpl(PublisherDAO pd) {
		super();
		this.pd = pd;
	}

	public DashboardServiceImpl(CustomerDAO cd) {
		super();
		this.cd = cd;
	}

	public PublisherDAO getPd() {
		return pd;
	}

	@Autowired
	@Qualifier("publisherDAOImpl")
	public void setPd(PublisherDAO pd) {
		this.pd = pd;
	}

	public CustomerDAO getCd() {
		return cd;
	}

	@Autowired
	@Qualifier("customerDAOImpl")
	public void setCd(CustomerDAO cd) {
		this.cd = cd;
	}

	@Override
	public PublisherData getPublisherStats(int publisherId) {
		return pd.getPublisherDataById(publisherId);
	}

	@Override
	public CustomerData getCustomerStats(int customerId) {
		return cd.getCustomerDataById(customerId);
	}

}
