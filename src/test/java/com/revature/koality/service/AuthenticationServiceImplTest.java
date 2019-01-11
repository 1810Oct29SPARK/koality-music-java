package com.revature.koality.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.PublisherDAOImpl;
import com.revature.koality.utility.CommonUtility;
import com.revature.koality.utility.MockUtility;
import com.revature.koality.bean.*;

public class AuthenticationServiceImplTest {

	@Mock
	PublisherDAOImpl publisherDAOMock;

	@Mock
	CustomerDAOImpl customerDAOMock;

	@InjectMocks
	private AuthenticationService as;

	@Before
	public void setUpBeforeClass() throws Exception {

	}

	@Test
	public void testIsValidPublisher() {

		PublisherCredentials credentials = MockUtility.getMockPublisherCredentials();

		String username = credentials.getUsername();
		String password = "password";

		String createHash = password + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		credentials.setPasswordHash(hash);

		Publisher publisher = MockUtility.getMockPublisher();

		credentials.setPublisher(publisher);

		publisherDAOMock = mock(PublisherDAOImpl.class);

		AuthenticationService authenticate = new AuthenticationServiceImpl(publisherDAOMock);

		when(publisherDAOMock.getPublisherCredentialsByUsername(username)).thenReturn(credentials);

		Publisher actual = authenticate.isValidPublisher(username, password);

		assertEquals(publisher, actual);

	}

	@Test
	public void testIsInvalidPublisherPassword() {

		PublisherCredentials credentials = MockUtility.getMockPublisherCredentials();

		String username = credentials.getUsername();
		String password = "password";

		String createHash = password + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		credentials.setPasswordHash(hash);

		Publisher publisher = MockUtility.getMockPublisher();

		credentials.setPublisher(publisher);

		publisherDAOMock = mock(PublisherDAOImpl.class);

		AuthenticationService authenticate = new AuthenticationServiceImpl(publisherDAOMock);

		when(publisherDAOMock.getPublisherCredentialsByUsername(username)).thenReturn(credentials);

		Publisher actual = authenticate.isValidPublisher(username, "Invalid");

		assertEquals(null, actual);

	}

	@Test
	public void testIsInvalidPublisherUsername() {

		PublisherCredentials credentials = MockUtility.getMockPublisherCredentials();

		String username = credentials.getUsername();
		String password = "password";

		String createHash = password + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		credentials.setPasswordHash(hash);

		Publisher publisher = MockUtility.getMockPublisher();

		credentials.setPublisher(publisher);

		publisherDAOMock = mock(PublisherDAOImpl.class);

		AuthenticationService authenticate = new AuthenticationServiceImpl(publisherDAOMock);

		when(publisherDAOMock.getPublisherCredentialsByUsername(username)).thenReturn(credentials);

		Publisher actual = authenticate.isValidPublisher("Invalid", password);

		assertEquals(null, actual);

	}

	@Test
	public void testIsValidCustomer() {

		CustomerCredentials credentials = MockUtility.getMockCustomerCredentials();

		String username = credentials.getUsername();
		String password = "password";

		String createHash = password + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		credentials.setPasswordHash(hash);

		Customer customer = MockUtility.getMockCustomer();

		credentials.setCustomer(customer);

		customerDAOMock = mock(CustomerDAOImpl.class);

		AuthenticationService authenticate = new AuthenticationServiceImpl(customerDAOMock);

		when(customerDAOMock.getCustomerCredentialsByUsername(username)).thenReturn(credentials);

		Customer actual = authenticate.isValidCustomer(username, password);

		assertEquals(customer, actual);
	}

	@Test
	public void testIsInvalidCustomerPassword() {

		CustomerCredentials credentials = MockUtility.getMockCustomerCredentials();

		String username = credentials.getUsername();
		String password = "password";

		String createHash = password + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		credentials.setPasswordHash(hash);

		Customer customer = MockUtility.getMockCustomer();

		credentials.setCustomer(customer);

		customerDAOMock = mock(CustomerDAOImpl.class);

		AuthenticationService authenticate = new AuthenticationServiceImpl(customerDAOMock);

		when(customerDAOMock.getCustomerCredentialsByUsername(username)).thenReturn(credentials);

		Customer actual = authenticate.isValidCustomer(username, "Invalid");

		assertEquals(null, actual);

	}

	@Test
	public void testIsInvalidCustomerUsername() {

		CustomerCredentials credentials = MockUtility.getMockCustomerCredentials();

		String username = credentials.getUsername();
		String password = "password";

		String createHash = password + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		credentials.setPasswordHash(hash);

		Customer customer = MockUtility.getMockCustomer();

		credentials.setCustomer(customer);

		customerDAOMock = mock(CustomerDAOImpl.class);

		AuthenticationService authenticate = new AuthenticationServiceImpl(customerDAOMock);

		when(customerDAOMock.getCustomerCredentialsByUsername(username)).thenReturn(credentials);

		Customer actual = authenticate.isValidCustomer("Invalid", password);

		assertEquals(null, actual);

	}

}
