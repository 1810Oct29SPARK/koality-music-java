package com.revature.koality.service;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Publisher;

public interface RegisterService {
	
	int RegisterCustomer(String firstName, String lastName, String email, String favoriteGenre, String username, String password); 
	int RegisterPublisher(String firstName, String lastName, String email, String companyName, String username, String password); 

}
