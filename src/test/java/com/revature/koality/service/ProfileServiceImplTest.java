package com.revature.koality.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.CustomerCredentials;
import com.revature.koality.bean.CustomerDetail;
import com.revature.koality.bean.Image;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.PublisherCredentials;
import com.revature.koality.bean.PublisherDetail;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.PublisherDAOImpl;
import com.revature.koality.utility.CommonUtility;
import com.revature.koality.utility.MockUtility;

public class ProfileServiceImplTest {

	@Mock
	PublisherDAOImpl publisherDAOMock;

	@Mock
	CustomerDAOImpl customerDAOMock;

	@InjectMocks
	private ProfileService profileService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testGetPublisherProfile() {

		Publisher publisher = MockUtility.getMockPublisher();

		int publisherId = 10;

		publisher.setPublisherId(publisherId);

		publisherDAOMock = mock(PublisherDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(publisherDAOMock);

		when(publisherDAOMock.getPublisherById(publisherId)).thenReturn(publisher);

		Publisher actual = profileService.getPublisherProfile(publisherId);

		assertEquals(publisher, actual);
	}

	@Test
	public void testGetInvalidPublisherProfile() {

		Publisher publisher = MockUtility.getMockPublisher();

		int publisherId = 10;

		publisher.setPublisherId(publisherId);

		publisherDAOMock = mock(PublisherDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(publisherDAOMock);

		when(publisherDAOMock.getPublisherById(publisherId)).thenReturn(publisher);

		Publisher actual = profileService.getPublisherProfile(5);

		assertEquals(null, actual);
	}

	@Test
	public void testGetCustomerProfile() {

		Customer customer = MockUtility.getMockCustomer();

		int customerId = 10;

		customer.setCustomerId(customerId);

		customerDAOMock = mock(CustomerDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(customerDAOMock);

		when(customerDAOMock.getCustomerById(customerId)).thenReturn(customer);

		Customer actual = profileService.getCustomerProfile(customerId);

		assertEquals(customer, actual);
	}

	@Test
	public void testGetInvalidCustomerProfile() {

		Customer customer = MockUtility.getMockCustomer();

		int customerId = 10;

		customer.setCustomerId(customerId);

		customerDAOMock = mock(CustomerDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(customerDAOMock);

		when(customerDAOMock.getCustomerById(customerId)).thenReturn(customer);

		Customer actual = profileService.getCustomerProfile(21);

		assertEquals(null, actual);
	}

	@Test
	public void testUpdatePublisherDetails() {

		int publisherId = 12;
		String firstName = "Jane";
		String lastName = "Jill";
		String email = "Janeljsdlkfj";
		String companyName = "The jane jill company";

		Publisher publisher = MockUtility.getMockPublisher();

		PublisherDetail publisherDetail = publisher.getPublisherDetail();

		publisherDetail.setCompanyName(companyName);
		publisherDetail.setEmail(email);
		publisherDetail.setFirstName(firstName);
		publisherDetail.setLastName(lastName);

		publisher.setPublisherDetail(publisherDetail);

		publisherDAOMock = mock(PublisherDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(publisherDAOMock);

		when(publisherDAOMock.getPublisherById(publisherId)).thenReturn(publisher);

		when(publisherDAOMock.updatePublisherDetail(publisherId, publisherDetail)).thenReturn(true);

		boolean actual = profileService.updatePublisherDetails(12, firstName, lastName, email, companyName);

		assertTrue(actual);

	}

	@Test
	public void testInvalidUpdatePublisherDetails() {

		int publisherId = 12;
		String firstName = "Jane";
		String lastName = "Jill";
		String email = "Janeljsdlkfj";
		String companyName = "The jane jill company";

		Publisher publisher = MockUtility.getMockPublisher();

		PublisherDetail publisherDetail = publisher.getPublisherDetail();

		publisherDetail.setCompanyName(companyName);
		publisherDetail.setEmail(email);
		publisherDetail.setFirstName(firstName);
		publisherDetail.setLastName(lastName);

		publisher.setPublisherDetail(publisherDetail);

		publisherDAOMock = mock(PublisherDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(publisherDAOMock);

		when(publisherDAOMock.getPublisherById(publisherId)).thenReturn(publisher);

		when(publisherDAOMock.updatePublisherDetail(publisherId, publisherDetail)).thenReturn(true);

		boolean actual = profileService.updatePublisherDetails(35, firstName, lastName, email, companyName);

		assertFalse(actual);

	}

	@Test
	public void testUpdateCustomerDetails() {

		int customerId = 10;
		String firstName = "John";
		String lastName = "Doe";
		String email = "JohnDoe@email.com";
		String favoriteGenre = "Rock";

		Customer customer = MockUtility.getMockCustomer();

		CustomerDetail customerDetail = customer.getCustomerDetail();

		customerDetail.setFavoriteGenre(favoriteGenre);
		customerDetail.setEmail(email);
		customerDetail.setFirstName(firstName);
		customerDetail.setLastName(lastName);

		customerDAOMock = mock(CustomerDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(customerDAOMock);

		when(customerDAOMock.getCustomerById(customerId)).thenReturn(customer);

		when(customerDAOMock.updateCustomerDetail(customerId, customerDetail)).thenReturn(true);

		boolean actual = profileService.updateCustomerDetails(customerId, firstName, lastName, email, favoriteGenre);

		assertTrue(actual);
	}
	
	@Test
	public void testInvalidUpdateCustomerDetails() {

		int customerId = 10;
		String firstName = "John";
		String lastName = "Doe";
		String email = "JohnDoe@email.com";
		String favoriteGenre = "Rock";

		Customer customer = MockUtility.getMockCustomer();

		CustomerDetail customerDetail = customer.getCustomerDetail();

		customerDetail.setFavoriteGenre(favoriteGenre);
		customerDetail.setEmail(email);
		customerDetail.setFirstName(firstName);
		customerDetail.setLastName(lastName);

		customerDAOMock = mock(CustomerDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(customerDAOMock);

		when(customerDAOMock.getCustomerById(customerId)).thenReturn(customer);

		when(customerDAOMock.updateCustomerDetail(customerId, customerDetail)).thenReturn(true);

		boolean actual = profileService.updateCustomerDetails(0, firstName, lastName, email, favoriteGenre);

		assertFalse(actual);
	}

	@Test
	public void testUpdatePublisherCredentials() {

		PublisherCredentials credentials = MockUtility.getMockPublisherCredentials();

		String oldUsername = credentials.getUsername();
		String newUsername = "newUser";
		String oldPassword = "password";
		String newPassword = oldPassword;
		int publisherId = 12;

		// This creates password hash for old credentials
		String createHash = oldPassword + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		credentials.setPasswordHash(hash);

		// This creates new updated credentials for mocking
		PublisherCredentials newCredentials = credentials;

		String createNewHash = newPassword + credentials.getHashSalt();

		String newPasswordHash = CommonUtility.digestSHA256(createNewHash);

		newCredentials.setPasswordHash(newPasswordHash);

		newCredentials.setUsername(newUsername);

		publisherDAOMock = mock(PublisherDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(publisherDAOMock);

		when(publisherDAOMock.getPublisherCredentialsByUsername(oldUsername)).thenReturn(credentials);

		when(publisherDAOMock.updatePublisherCredentials(publisherId, newCredentials)).thenReturn(true);

		Boolean actual = profileService.updatePublisherCredentials(publisherId, oldUsername, newUsername, oldPassword,
				newPassword);

		assertTrue(actual);
	}

	
	@Test
	public void testUpdateCustomerCredentials() {
		
		CustomerCredentials credentials = MockUtility.getMockCustomerCredentials();

		String oldUsername = credentials.getUsername();
		String newUsername = "newUser";
		String oldPassword = "password";
		String newPassword = oldPassword;
		int customerId = 12;

		// This creates password hash for old credentials
		String createHash = oldPassword + credentials.getHashSalt();

		String hash = CommonUtility.digestSHA256(createHash);

		credentials.setPasswordHash(hash);

		// This creates new updated credentials for mocking
		CustomerCredentials newCredentials = credentials;

		String createNewHash = newPassword + credentials.getHashSalt();

		String newPasswordHash = CommonUtility.digestSHA256(createNewHash);

		newCredentials.setPasswordHash(newPasswordHash);

		newCredentials.setUsername(newUsername);

		customerDAOMock = mock(CustomerDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(customerDAOMock);

		when(customerDAOMock.getCustomerCredentialsByUsername(oldUsername)).thenReturn(credentials);

		when(customerDAOMock.updateCustomerCredentials(customerId, newCredentials)).thenReturn(true);

		Boolean actual = profileService.updateCustomerCredentials(customerId, oldUsername, newUsername, oldPassword,
				newPassword);

		assertTrue(actual);
	}

	@Test
	public void testUpdatePublisherImage() {

		Publisher publisher = MockUtility.getMockPublisher(); 

		Image image = publisher.getImage(); 

		String imageType = image.getImageType();

		byte[] imageData = image.getImageData();

		int publisherId = 11;

		publisherDAOMock = mock(PublisherDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(publisherDAOMock);
		
		when(publisherDAOMock.getPublisherById(publisherId)).thenReturn(publisher); 

		when(publisherDAOMock.updatePublisherImage(publisherId, image)).thenReturn(true);

		boolean actual = profileService.updatePublisherImage(publisherId, imageType, imageData);

		assertTrue(actual);
	}
	
	@Test
	public void testInvalidPublisherImage() {

		Publisher publisher = MockUtility.getMockPublisher(); 

		Image image = publisher.getImage(); 

		String imageType = image.getImageType();

		byte[] imageData = image.getImageData();

		int publisherId = 11;

		publisherDAOMock = mock(PublisherDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(publisherDAOMock);
		
		when(publisherDAOMock.getPublisherById(publisherId)).thenReturn(publisher); 

		when(publisherDAOMock.updatePublisherImage(publisherId, image)).thenReturn(true);

		boolean actual = profileService.updatePublisherImage(publisherId, imageType, imageData);

		assertTrue(actual);
	}
	

	@Test
	public void testUpdateCustomerImage() {
		
		Customer customer = MockUtility.getMockCustomer(); 

		Image image = customer.getImage(); 

		String imageType = image.getImageType();

		byte[] imageData = image.getImageData();

		int customerId = 11;

		System.out.println(customerId);

		customerDAOMock = mock(CustomerDAOImpl.class);

		ProfileService profileService = new ProfileServiceImpl(customerDAOMock);
		
		when(customerDAOMock.getCustomerById(customerId)).thenReturn(customer); 

		when(customerDAOMock.updateCustomerImage(customerId, image)).thenReturn(true);

		boolean actual = profileService.updateCustomerImage(customerId, imageType, imageData);

		assertTrue(actual);
	}

}
