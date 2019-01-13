package com.revature.koality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

@Service("profileServiceImpl")
public class ProfileServiceImpl implements ProfileService {

	private PublisherDAO pd;
	private CustomerDAO cd;

	public ProfileServiceImpl() {
		super();
	}

	public ProfileServiceImpl(PublisherDAO publisherDAOMock) {
		super();
		this.pd = publisherDAOMock;
	}

	public ProfileServiceImpl(CustomerDAO customerDAOMock) {
		super();
		this.cd = customerDAOMock;
	}

	public ProfileServiceImpl(PublisherDAOImpl pd) {
		super();
		this.pd = pd;
	}

	public ProfileServiceImpl(CustomerDAOImpl cd) {
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
	public Publisher getPublisherProfile(int publisherId) {
		return pd.getPublisherById(publisherId);
	}

	@Override
	public Customer getCustomerProfile(int customerId) {
		return cd.getCustomerById(customerId);
	}

	@Override
	public boolean updatePublisherDetails(int publisherId, String firstName, String lastName, String email,
			String companyName) {

		Publisher publisher = pd.getPublisherById(publisherId);

		if (publisher != null) {

			PublisherDetail publisherDetail = publisher.getPublisherDetail();

			publisherDetail.setCompanyName(companyName);
			publisherDetail.setEmail(email);
			publisherDetail.setFirstName(firstName);
			publisherDetail.setLastName(lastName);

			return pd.updatePublisherDetail(publisherId, publisherDetail);
		}

		return false;

	}

	@Override
	public boolean updateCustomerDetails(int customerId, String firstName, String lastName, String email,
			String favoriteGenre) {

		Customer customer = cd.getCustomerById(customerId);

		if (customer != null) {

			CustomerDetail customerDetail = customer.getCustomerDetail();

			customerDetail.setFavoriteGenre(favoriteGenre);
			customerDetail.setEmail(email);
			customerDetail.setFirstName(firstName);
			customerDetail.setLastName(lastName);

			return cd.updateCustomerDetail(customerId, customerDetail);
		}

		return false;
	}

	@Override
	public boolean updatePublisherCredentials(int publisherId, String oldUsername, String newUsername,
			String oldPassword, String newPassword) {

		PublisherCredentials credentials = pd.getPublisherCredentialsByUsername(oldUsername);

		String createHash = oldPassword + credentials.getHashSalt();

		String passwordHash = CommonUtility.digestSHA256(createHash);

		if (credentials.getPasswordHash().equals(passwordHash)) {

			createHash = newPassword + credentials.getHashSalt();

			passwordHash = CommonUtility.digestSHA256(createHash);

			credentials.setPasswordHash(passwordHash);

			credentials.setUsername(newUsername);

			return pd.updatePublisherCredentials(publisherId, credentials);
		}

		return false;
	}

	@Override
	public boolean updateCustomerCredentials(int customerId, String oldUsername, String newUsername, String oldPassword,
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

			return cd.updateCustomerCredentials(customerId, credentials);
		}

		return false;
	}

	@Override
	public boolean updatePublisherImage(int publisherId, String imageType, byte[] imageData) {
		
		Publisher publisher = pd.getPublisherById(publisherId); 

		Image image = publisher.getImage(); 
		
		image.setImageData(imageData);
		
		image.setImageType(imageType);

		return pd.updatePublisherImage(publisherId, image);
	}

	@Override
	public boolean updateCustomerImage(int customerId, String imageType, byte[] imageData) {
		
		Customer customer = cd.getCustomerById(customerId); 

		Image image = customer.getImage(); 
		
		image.setImageData(imageData);
		
		image.setImageType(imageType);

		return cd.updateCustomerImage(customerId, image);
	}

}
