package com.revature.koality.service;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Publisher;

public interface ProfileService {

	Publisher getPublisherProfile(int publisherId);

	Customer getCustomerProfile(int customerId);

	boolean updatePublisherDetails(int publisherId, String firstName, String lastName, String email,
			String companyName);

	boolean updateCustomerDetails(int customerId, String firstName, String lastName, String email,
			String favoriteGenre);

	boolean updatePublisherCredentials(int publisherId, String oldUsername, String newUsername, String oldPassword,
			String newPassword);

	boolean updateCustomerCredentials(int customerId, String oldUsername, String newUsername, String oldPassword,
			String newPassword);

	boolean updatePublisherImage(int publisherId, String imageType, byte[] imageData);

	boolean updateCustomerImage(int customerId, String imageType, byte[] imageData);

}
