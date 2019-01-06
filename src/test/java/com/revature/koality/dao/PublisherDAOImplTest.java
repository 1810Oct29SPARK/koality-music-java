package com.revature.koality.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.koality.utility.HibernateUtility;
import com.revature.koality.utility.MockUtility;

public class PublisherDAOImplTest {

	private static PublisherDAO pd;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pd = new PublisherDAOImpl(true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtility.destroySessionFactories();
	}

	@Ignore
	@Test
	public void testAddPublisher() {
		assertNotEquals(-1, pd.addPublisher(MockUtility.getMockPublisher()));
	}

	@Test
	public void testGetPublisherCredentialsByUsername() {
		assertEquals("MhRd", pd.getPublisherCredentialsByUsername("J1k2cH").getHashSalt());
	}

	@Test
	public void testGetPublisherCredentialsByUsernameWithPublisher() {
		assertEquals(1, pd.getPublisherCredentialsByUsername("mYI7a0").getPublisher().getPublisherId());
	}

	@Test
	public void testGetPublisherById() {
		assertEquals("F5DJSK", pd.getPublisherById(3).getPublisherDetail().getFirstName());
	}

	@Test
	public void testGetPublisherByIdWithImage() {
		assertEquals(
				"data:image/yrl;base64,a2lUTTY5WG12bE44MXNISEFBbTI3VmRCVEc0dFU0S0FaOGFvdmdzNHNxanRoUnRrZ0lrbzQxSUpScVlwNUxROEpQQ1VIUGh0MlJLanZSdUZCVktwVklyRmF1aE5xWTFvQUlzNQ==",
				pd.getPublisherById(1).getImageUrl());
	}

	@Test
	public void testGetAllPublishers() {
		assertTrue(pd.getAllPublishers().size() > 1);
	}

	@Test
	public void testGetAllPublishersWithId() {
		assertEquals("ckHOC5RiiDhqqzvH", pd.getAllPublishers().get(1).getPublisherDetail().getEmail());
	}

	@Test
	public void testUpdatePublisherDetail() {
		assertTrue(pd.updatePublisherDetail(6, MockUtility.getMockPublisherDetail()));
	}

	@Test
	public void testUpdatePublisherImage() {
		assertTrue(pd.updatePublisherImage(6, MockUtility.getMockImage()));
	}

	@Test
	public void testUpdatePublisherCredentials() {
		assertTrue(pd.updatePublisherCredentials(6, MockUtility.getMockPublisherCredentials()));
	}

	@Ignore
	@Test
	public void testGetAllSubscribersByPublisherId() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetAllTracksByPublisherId() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetAllAlbumsByPublisherId() {
		fail("Not yet implemented");
	}

}
