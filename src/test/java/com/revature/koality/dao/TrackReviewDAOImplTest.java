package com.revature.koality.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.koality.utility.CommonUtility;
import com.revature.koality.utility.HibernateUtility;
import com.revature.koality.utility.MockUtility;

public class TrackReviewDAOImplTest {

	private static TrackReviewDAO trd;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		trd = new TrackReviewDAOImpl(true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtility.destroySessionFactories();
	}

	@Ignore
	@Test
	public void testAddTrackReview() {
		assertNotEquals(-1, trd.addTrackReview(MockUtility.getMockReviewContent(),
				CommonUtility.generateRandomInteger(13), CommonUtility.generateRandomInteger(6)));
	}

	@Ignore
	@Test
	public void testAddTrackReview2() {
		assertNotEquals(-1, trd.addTrackReview(MockUtility.getMockReviewContent(),
				CommonUtility.generateRandomInteger(13), CommonUtility.generateRandomInteger(6)));
	}

	@Ignore
	@Test
	public void testAddTrackReview3() {
		assertNotEquals(-1, trd.addTrackReview(MockUtility.getMockReviewContent(),
				CommonUtility.generateRandomInteger(13), CommonUtility.generateRandomInteger(6)));
	}

	@Test
	public void testGetTrackReviewById() {
		assertEquals(10, trd.getTrackReviewById(2).getReviewContent().getRating());
	}

	@Test
	public void testGetTrackReviewByIdWithTrack() {
		assertEquals(375, trd.getTrackReviewById(1).getTrack().getTrackLength());
	}

	@Test
	public void testGetTrackReviewByTrackIdAndCustomerId() {
		assertEquals("cBrSMmi9HY7C03W5GwM3si98lfZVrSTsQ5E325qtZC4sU2Gejq",
				trd.getTrackReviewByTrackIdAndCustomerId(3, 4).getReviewContent().getReviewComment());
	}

	@Test
	public void testGetTrackReviewByTrackIdAndCustomerIdWithCustomer() {
		assertEquals("WmQmUg",
				trd.getTrackReviewByTrackIdAndCustomerId(1, 5).getCustomer().getCustomerDetail().getFirstName());
	}

	@Ignore
	@Test
	public void testDeleteTrackReview() {
		assertTrue(trd.deleteTrackReview(7));
	}

	@Test
	public void testIsOwnerTrue() {
		assertTrue(trd.isOwner(5, 5));
	}

	@Test
	public void testIsOwnerFalse() {
		assertFalse(trd.isOwner(8, 3));
	}

}
