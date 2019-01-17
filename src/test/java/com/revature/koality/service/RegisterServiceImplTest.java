package com.revature.koality.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.koality.bean.Image;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.PublisherCredentials;
import com.revature.koality.bean.PublisherDetail;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.PublisherDAOImpl;
import com.revature.koality.utility.CommonUtility;

public class RegisterServiceImplTest {
	
	@Mock
	PublisherDAOImpl publisherDAOMock;

	@Mock
	CustomerDAOImpl customerDAOMock;
	 

	@InjectMocks
	private RegisterService registerService;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testRegisterCustomer() {
		
		
		
	}

	@Test
	public void testRegisterPublisher() { 
		
		
		String firstName = "firstName";  
		String lastName = "lastName"; 
		String email = "email@gmail"; 
		String companyName = "company Name"; 
		String username = "userName"; 
		String password = "password";
		String hashSalt = "1234"; 
		String createHash = password + hashSalt; 
		String passwordHash = CommonUtility.digestSHA256(createHash);
		
		PublisherDetail publisherDetail = new PublisherDetail(firstName, lastName, email, companyName);
		
		PublisherCredentials publisherCredentials = new PublisherCredentials(username, hashSalt, passwordHash);

		Publisher publisher = new Publisher();
		Image image = null; 
		publisher.setImage(image);
		publisher.setPublisherCredentials(publisherCredentials);
		publisher.setPublisherDetail(publisherDetail);
		
		publisherDAOMock = mock(PublisherDAOImpl.class);

		registerService = new RegisterServiceImpl(publisherDAOMock);
		
		when(publisherDAOMock.addPublisher(publisher)).thenReturn(1); 

		int actual = registerService.registerPublisher(firstName, lastName, email, companyName, username, password); 
		
		assertEquals(1,actual); 
	}

}
