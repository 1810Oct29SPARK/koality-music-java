package com.revature.koality.service;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Publisher;

public interface AuthenticationService {

	Publisher isValidPublisher(String username, String password);

	Customer isValidCustomer(String username, String password);

}
