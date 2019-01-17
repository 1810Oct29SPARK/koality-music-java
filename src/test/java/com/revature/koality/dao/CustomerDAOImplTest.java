package com.revature.koality.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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

	@Ignore
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

	@Test
	public void testGetAllSubscribeeByCustomerId() {
		assertEquals(2, cd.getAllSubscribeeByCustomerId(2).size());
	}

	@Test
	public void testGetAllTracksByCustomerId() {
		assertEquals(2, cd.getAllTracksByCustomerId(1).size());
	}

	@Test
	public void testGetAllAlbumsByCustomerId() {
		assertEquals(1, cd.getAllAlbumsByCustomerId(1).size());
	}

	@Ignore
	@Test
	public void testSubscribeToPublisher() {
		assertTrue(cd.subscribeToPublisher(5, 2));
	}

	@Ignore
	@Test
	public void testSubscribeToPublisher2() {
		assertTrue(cd.subscribeToPublisher(5, 4));
	}

	@Ignore
	@Test
	public void testSubscribeToPublisher3() {
		assertTrue(cd.subscribeToPublisher(6, 5));
	}

	@Ignore
	@Test
	public void testUnsubscribeFromPublisher() {
		assertTrue(cd.unsubscribeFromPublisher(2, 4));
	}

	@Ignore
	@Test
	public void testPurchaseTrack() {
		assertTrue(cd.purchaseTrack(3, 9));
	}

	@Ignore
	@Test
	public void testPurchaseTrack2() {
		assertTrue(cd.purchaseTrack(6, 5));
	}

	@Ignore
	@Test
	public void testPurchaseAlbum() {
		assertTrue(cd.purchaseAlbum(2, 7));
	}

	public void testGetCustomerDataById() {
		assertEquals(4, cd.getCustomerDataById(2).getNumberOfTracksBought());
		assertEquals(2, cd.getCustomerDataById(2).getNumberOfAlbumsBought());
		assertEquals(2, cd.getCustomerDataById(2).getNumberOfSubscribees());
	}

}
