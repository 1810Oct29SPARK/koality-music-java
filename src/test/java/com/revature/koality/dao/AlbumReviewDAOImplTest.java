package com.revature.koality.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.koality.utility.HibernateUtility;
import com.revature.koality.utility.MockUtility;

public class AlbumReviewDAOImplTest {

	private static AlbumReviewDAO ard;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ard = new AlbumReviewDAOImpl(true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtility.destroySessionFactories();
	}

	@Ignore
	@Test
	public void testAddAlbumReview() {
		assertNotEquals(-1, ard.addAlbumReview(MockUtility.getMockReviewContent(), 7, 1));
	}

	@Ignore
	@Test
	public void testAddAlbumReview2() {
		assertNotEquals(-1, ard.addAlbumReview(MockUtility.getMockReviewContent(), 5, 5));
	}

	@Ignore
	@Test
	public void testAddAlbumReview3() {
		assertNotEquals(-1, ard.addAlbumReview(MockUtility.getMockReviewContent(), 1, 6));
	}

	@Test
	public void testGetAlbumReviewById() {
		assertEquals("W1g3D0hhe2c2IuEYJjM5hyRMC1VWzSaka9i8ehbsS4rYCzxRYc",
				ard.getAlbumReviewById(6).getReviewContent().getReviewComment());
	}

	@Test
	public void testGetAlbumReviewByAlbumIdAndCustomerId() {
		assertEquals(7, ard.getAlbumReviewByAlbumIdAndCustomerId(2, 3).getReviewContent().getRating());
	}

	@Ignore
	@Test
	public void testDeleteAlbumReview() {
		assertTrue(ard.deleteAlbumReview(10));
	}

	@Test
	public void testIsOwnerTrue() {
		assertTrue(ard.isOwner(4, 2));
	}

	@Test
	public void testIsOwnerFalse() {
		assertFalse(ard.isOwner(11, 2));
	}

}
