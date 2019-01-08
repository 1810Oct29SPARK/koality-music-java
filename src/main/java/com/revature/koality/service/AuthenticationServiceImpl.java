package com.revature.koality.service;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.CustomerCredentials;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.PublisherCredentials;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.PublisherDAO;
import com.revature.koality.dao.PublisherDAOImpl;
import com.revature.koality.utility.CommonUtility;

public class AuthenticationServiceImpl implements AuthenticationService {

	public AuthenticationServiceImpl() {
		super();
		pd = new PublisherDAOImpl();
		cd = new CustomerDAOImpl();
	}

	private PublisherDAO pd;
	private CustomerDAO cd;

	public PublisherDAO getPd() {
		return pd;
	}

	public void setPd(PublisherDAO pd) {
		this.pd = pd;
	}

	public CustomerDAO getCd() {
		return cd;
	}

	public void setCd(CustomerDAO cd) {
		this.cd = cd;
	}

	@Override
	public Publisher isValidPublisher(String username, String password) {

		PublisherCredentials credentials = pd.getPublisherCredentialsByUsername(username);

		String createHash = password + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		if (credentials.getPasswordHash().equals(hash)) {
			return credentials.getPublisher();
		}

		return null;
	}

	@Override
	public Customer isValidCustomer(String username, String password) {

		CustomerCredentials credentials = cd.getCustomerCredentialsByUsername(username);

		String createHash = password + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		if (credentials.getPasswordHash().equals(hash)) {
			return credentials.getCustomer();
		}

		return null;
	}

}
