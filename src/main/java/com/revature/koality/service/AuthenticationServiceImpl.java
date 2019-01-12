package com.revature.koality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.CustomerCredentials;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.PublisherCredentials;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.PublisherDAO;
import com.revature.koality.utility.CommonUtility;

@Service("authenticationServiceImpl")
public class AuthenticationServiceImpl implements AuthenticationService {

	private PublisherDAO pd;
	private CustomerDAO cd;

	public AuthenticationServiceImpl() {
		super();
	}

	public AuthenticationServiceImpl(PublisherDAO publisherDAOMock) {
		super();
		this.pd = publisherDAOMock;
	}

	public AuthenticationServiceImpl(CustomerDAO customerDAOMock) {
		super();
		this.cd = customerDAOMock;
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
	public Publisher isValidPublisher(String username, String password) {

		PublisherCredentials credentials = pd.getPublisherCredentialsByUsername(username);

		if (credentials != null) {
			String createHash = password + credentials.getHashSalt();

			String hash = CommonUtility.digestSHA256(createHash);

			if (credentials.getPasswordHash().equals(hash)) {

				Publisher publisher = credentials.getPublisher();

				publisher.truncate(true);

				return publisher;
			}
		}

		return null;

	}

	@Override
	public Customer isValidCustomer(String username, String password) {

		CustomerCredentials credentials = cd.getCustomerCredentialsByUsername(username);

		if (credentials != null) {

			String createHash = password + credentials.getHashSalt();

			String hash = CommonUtility.digestSHA256(createHash);

			if (credentials.getPasswordHash().equals(hash)) {

				Customer customer = credentials.getCustomer();

				customer.truncate(true);

				return customer;
			}
		}

		return null;

	}

}
