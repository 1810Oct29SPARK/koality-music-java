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
	}

	@Override
	public Publisher isValidPublisher(String username, String password) {

		PublisherDAO pd = new PublisherDAOImpl();

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

		CustomerDAO cd = new CustomerDAOImpl();

		CustomerCredentials credentials = cd.getCustomerCredentialsByUsername(username);

		String createHash = password + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		if (credentials.getPasswordHash().equals(hash)) {
			return credentials.getCustomer();
		}

		return null;
	}

}
