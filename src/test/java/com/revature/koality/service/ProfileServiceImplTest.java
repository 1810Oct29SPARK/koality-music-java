package com.revature.koality.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.CustomerDetail;
import com.revature.koality.bean.Image;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.PublisherDetail;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.PublisherDAOImpl;
import com.revature.koality.utility.MockUtility;

public class ProfileServiceImplTest {
	
	@Mock
	PublisherDAOImpl publisherDAOMock; 
	
	@Mock
	CustomerDAOImpl	customerDAOMock; 
	
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
		
		assertEquals(publisher,actual); 
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
		
		assertEquals(null,actual); 
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
		
		assertEquals(customer,actual); 
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
		
		assertEquals(null,actual); 
	}
	

	@Test
	public void testUpdatePublisherDetails() {
		
		Publisher publisher = MockUtility.getMockPublisher(); 
		PublisherDetail publisherDetail = publisher.getPublisherDetail(); 

		int publisherId = publisher.getPublisherId(); 
		String firstName = publisherDetail.getFirstName();
		String lastName = publisherDetail.getLastName(); 
		String email = publisherDetail.getEmail(); 
		String companyName = publisherDetail.getCompanyName();  
		
		PublisherDAOImpl publisherDAOMock; 
		
		publisherDAOMock = mock(PublisherDAOImpl.class); 
		
		ProfileService profileService = new ProfileServiceImpl(publisherDAOMock); 
		
		when(publisherDAOMock.updatePublisherDetail(publisherId, publisherDetail)).thenReturn(true); 
		
		boolean actual = profileService.updatePublisherDetails(publisherId, firstName, lastName, email, companyName); 
		
		assertTrue(actual) ; 
		
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
		
		boolean actual = profileService.updatePublisherDetails(publisherId, firstName, lastName, email, companyName); 
		
		assertTrue(actual) ; 
		
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
		
		assertTrue(actual) ; 
	}

	@Test
	public void testUpdatePublisherCredentials() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateCustomerCredentials() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePublisherImage() {
		
		
		Image imageMock = MockUtility.getMockImage(); 
		
		String imageType = imageMock.getImageType(); 
		
		byte[] imageData = imageMock.getImageData(); 
		
		int publisherId = 12; 
		
		Image image = new Image(imageType, imageData);
		
		System.out.println(publisherId);
		
		publisherDAOMock = mock(PublisherDAOImpl.class); 
		
		ProfileService profileService = new ProfileServiceImpl(publisherDAOMock); 
		
		when(publisherDAOMock.updatePublisherImage(publisherId, image)).thenReturn(true); 
		
		boolean actual = profileService.updatePublisherImage(publisherId, imageType, imageData); 
		
		assertTrue(actual); 
	}

	@Test
	public void testUpdateCustomerImage() {
		
		Image imageMock = MockUtility.getMockImage(); 
		
		String imageType = imageMock.getImageType(); 
		
		byte[] imageData = imageMock.getImageData(); 
		
		int customerId = 11; 
		
		Image image = new Image(imageType, imageData);
		
		System.out.println(customerId);
		
		customerDAOMock = mock(CustomerDAOImpl.class); 
		
		ProfileService profileService = new ProfileServiceImpl(customerDAOMock); 
		
		when(customerDAOMock.updateCustomerImage(customerId, image)).thenReturn(true); 
		
		boolean actual = profileService.updateCustomerImage(customerId, imageType, imageData); 
		
		assertTrue(actual); 
	}

}
