package com.revature.koality.service;

public interface RegisterService {

	int registerCustomer(String firstName, String lastName, String email, String favoriteGenre, String username,
			String password);

	int registerPublisher(String firstName, String lastName, String email, String companyName, String username,
			String password);

}
