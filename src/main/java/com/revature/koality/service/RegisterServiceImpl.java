package com.revature.koality.service;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.CustomerCredentials;
import com.revature.koality.bean.CustomerDetail;
import com.revature.koality.bean.Image;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.PublisherCredentials;
import com.revature.koality.bean.PublisherDetail;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.PublisherDAO;
import com.revature.koality.dao.PublisherDAOImpl;
import com.revature.koality.utility.CommonUtility;

public class RegisterServiceImpl implements RegisterService {

	private PublisherDAO pd;
	private CustomerDAO cd;

	public RegisterServiceImpl() {
		super();
		pd = new PublisherDAOImpl();
		cd = new CustomerDAOImpl();
	}

	public RegisterServiceImpl(PublisherDAO publisherDAOMock) {
		super();
		this.pd = publisherDAOMock;
	}

	public RegisterServiceImpl(CustomerDAO customerDAOMock) {
		super();
		this.cd = customerDAOMock;
	}

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
	public int registerCustomer(String firstName, String lastName, String email, String favoriteGenre, String username,
			String password) {

		CustomerDetail customerDetail = new CustomerDetail(firstName, lastName, email, favoriteGenre);
		String hashSalt = CommonUtility.generateRandomString(4);

		String createHash = password + hashSalt;

		String passwordHash = CommonUtility.digestSHA256(createHash);

		CustomerCredentials customerCredentials = new CustomerCredentials(username, hashSalt, passwordHash);

		Image image = null;

		Customer customer = new Customer(customerDetail, image, customerCredentials);

		return cd.addCustomer(customer);
	}

	@Override
	public int registerPublisher(String firstName, String lastName, String email, String companyName, String username,
			String password) {

		PublisherDetail publisherDetail = new PublisherDetail(firstName, lastName, email, companyName);
		String hashSalt = CommonUtility.generateRandomString(4);

		String createHash = password + hashSalt;

		String passwordHash = CommonUtility.digestSHA256(createHash);

		PublisherCredentials publisherCredentials = new PublisherCredentials(username, hashSalt, passwordHash);

		Image image = null;

		Publisher publisher = new Publisher(publisherDetail, image, publisherCredentials);

		return pd.addPublisher(publisher);
	}

}
