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

public class ProfileServiceImpl implements ProfileService {

	public ProfileServiceImpl() {
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
	public Publisher getPublisherProfile(int id) {
		return pd.getPublisherById(id);
	}

	@Override
	public Customer getCustomerProfile(int id) {
		return cd.getCustomerById(id);
	}

	@Override
	public boolean updatePublisherDetails(int id, String firstName, String lastName, String email, String companyName) {

		PublisherDetail publisherDetail = new PublisherDetail(firstName, lastName, email, companyName);

		return pd.updatePublisherDetail(id, publisherDetail);
	}

	@Override
	public boolean updateCustomerDetails(int id, String firstName, String lastName, String email,
			String favoriteGenre) {
		CustomerDetail customerDetail = new CustomerDetail(firstName, lastName, email, favoriteGenre);

		return cd.updateCustomerDetail(id, customerDetail);
	}

	@Override
	public boolean updatePublisherCredentials(int id, String oldUsername, String newUsername, String oldPassword,
			String newPassword) {

		PublisherCredentials credentials = pd.getPublisherCredentialsByUsername(oldUsername);

		String createHash = oldPassword + credentials.getHashSalt();

		String passwordHash = CommonUtility.digestSHA256(createHash);

		if (credentials.getPasswordHash().equals(passwordHash)) {

			credentials = pd.getPublisherCredentialsByUsername(oldUsername);

			createHash = newPassword + credentials.getHashSalt();

			passwordHash = CommonUtility.digestSHA256(createHash);

			credentials.setPasswordHash(passwordHash);

			credentials.setUsername(newUsername);

			return pd.updatePublisherCredentials(id, credentials);
		}

		return false;
	}

	@Override
	public boolean updateCustomerCredentials(int id, String oldUsername, String newUsername, String oldPassword,
			String newPassword) {

		CustomerCredentials credentials = cd.getCustomerCredentialsByUsername(oldUsername);

		String createHash = oldPassword + credentials.getHashSalt();

		String passwordHash = CommonUtility.digestSHA256(createHash);

		if (credentials.getPasswordHash().equals(passwordHash)) {

			credentials = cd.getCustomerCredentialsByUsername(oldUsername);

			createHash = newPassword + credentials.getHashSalt();

			passwordHash = CommonUtility.digestSHA256(createHash);

			credentials.setPasswordHash(passwordHash);

			credentials.setUsername(newUsername);

			return cd.updateCustomerCredentials(id, credentials);
		}

		return false;
	}

	@Override
	public boolean updatePublisherImage(int id, String imageType, byte[] imageData) {

		Image image = new Image(imageType, imageData);

		return pd.updatePublisherImage(id, image);
	}

	@Override
	public boolean updateCustomerImage(int id, String imageType, byte[] imageData) {
		
		Image image = new Image(imageType, imageData);

		return cd.updateCustomerImage(id, image);
	}

}
