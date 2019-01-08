package com.revature.koality.service;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Publisher;

public interface ProfileService {

	Publisher getPublisherProfile(int id);

	Customer getCustomerProfile(int id);

	boolean updatePublisherDetails(int id, String firstName, String lastName, String email, String companyName);

	boolean updateCustomerDetails(int id, String firstName, String lastName, String email, String favoriteGenre);

	boolean updatePublisherCredentials(int id, String oldUsername, String newUsername, String oldPassword,
			String newPassword);

	boolean updateCustomerCredentials(int id, String oldUsername, String newUsername, String oldPassword,
			String newPassword);

	boolean updatePublisherImage(int id, String imageType, byte[] imageData);

	boolean updateCustomerImage(int id, String imageType, byte[] imageData);

}
