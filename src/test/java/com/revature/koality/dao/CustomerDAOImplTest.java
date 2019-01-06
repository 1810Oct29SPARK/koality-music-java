package com.revature.koality.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.koality.utility.HibernateUtility;
import com.revature.koality.utility.MockUtility;

public class CustomerDAOImplTest {

	private static CustomerDAO cd;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cd = new CustomerDAOImpl(true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtility.destroySessionFactories();
	}

	@Test
	public void testAddCustomer() {
		assertNotEquals(-1, cd.addCustomer(MockUtility.getMockCustomer()));
	}

	@Test
	public void testGetCustomerCredentialsByUsername() {
		assertEquals("pMJtk3",
				cd.getCustomerCredentialsByUsername("ytLTgU").getCustomer().getCustomerDetail().getLastName());
	}

	@Test
	public void testGetCustomerById() {
		assertEquals(
				"data:image/yik;base64,QWJINjVQVnZHdHdzTFVyVnN3YTVqNHdUWGE4d0FaWXJsdDRTcW90NnYzSk9nS2NpYTlpRkFMY0hHMHlVSGp4UkliUk5KVHBBWXgzNGoxN1k1NnprcEdvV21uVkI4OHFaR0hpOA==",
				cd.getCustomerById(1).getImageUrl());
	}

	@Test
	public void testGetAllCustomers() {
		assertEquals(1, cd.getAllCustomers().get(0).getCustomerId());
	}

	@Test
	public void testUpdateCustomerDetail() {
		assertTrue(cd.updateCustomerDetail(4, MockUtility.getMockCustomerDetail()));
	}

	@Test
	public void testUpdateCustomerImage() {
		assertTrue(cd.updateCustomerImage(4, MockUtility.getMockImage()));
	}

	@Test
	public void testUpdateCustomerCredentials() {
		assertTrue(cd.updateCustomerCredentials(4, MockUtility.getMockCustomerCredentials()));
	}

	@Ignore
	@Test
	public void testGetAllSubscribeeByCustomerId() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetAllTracksByCustomerId() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetAllAlbumsByCustomerId() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testSubscribeToPublisher() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testUnsubscribeFromPublisher() {
		fail("Not yet implemented");
	}

}
