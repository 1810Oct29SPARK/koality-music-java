package com.revature.koality.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.koality.bean.AlbumReview;
import com.revature.koality.utility.CommonUtility;
import com.revature.koality.utility.HibernateUtility;
import com.revature.koality.utility.MockUtility;

public class AlbumDAOImplTest {

	private static AlbumDAO ad;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ad = new AlbumDAOImpl(true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtility.destroySessionFactories();
	}

	@Ignore
	@Test
	public void testAddAlbum() {
		List<Integer> trackIdList = new ArrayList<>();
		trackIdList.add(CommonUtility.generateRandomInteger(12));
		trackIdList.add(13);
		int publisherId = CommonUtility.generateRandomInteger(5);
		assertNotEquals(-1, ad.addAlbum(MockUtility.getMockAlbum(), publisherId, trackIdList));
	}

	@Test
	public void testGetAlbumById() {
		assertEquals("2AUkLK", ad.getAlbumById(5).getAlbumName());
	}

	@Test
	public void testGetAlbumByIdWithImage() {
		assertEquals(
				"data:image/mzz;base64,cE1RWGcxNUo2TEcwRHVQeDVGa1B1YXlJZFJ5SFlaU2dacFk3b0tOYnFmTVI3aDluWFA1QTVnRkk0QzVITzZ0ZWlFRnJxeWlyaXNhQ1AwbThiM016WVEyeDhOSUhSWUd2S0laQg==",
				ad.getAlbumById(2).getImageUrl());
	}

	@Test
	public void testGetAlbumByIdWithTrack() {
		assertEquals(3, ad.getAlbumById(3).getTrackList().size());
	}

	@Test
	public void testGetAllAlbums() {
		assertEquals("4Avwo3", ad.getAllAlbums().get(0).getGenre());
	}

	@Test
	public void testUpdateAlbumPrice() {
		assertNotEquals(-1F, ad.updateAlbumPrice(6, CommonUtility.generateRandomInteger(200)));
	}

	@Test
	public void testGetAllTracksByAlbumId() {
		assertTrue(ad.getAllTracksByAlbumId(1).size() > 0);
	}

	@Test
	public void testGetAllAlbumReviewsByAlbumId() {
		List<AlbumReview> albumReviewList = ad.getAllAlbumReviewsByAlbumId(1);
		albumReviewList.removeIf(ar -> ar.getCustomer().getCustomerId() != 3);
		assertEquals(1, albumReviewList.get(0).getReviewContent().getRating());
	}

	@Test
	public void testGetAlbumPurchaseCount() {
		assertEquals(1, ad.getAlbumPurchaseCount(8));
	}

	@Test
	public void testHasAccessAsPublisherValid() {
		assertTrue(ad.hasAccessAsPublisher(5, 2));
	}

	@Test
	public void testHasAccessAsPublisherInvalid() {
		assertFalse(ad.hasAccessAsPublisher(6, 1));
	}

	@Test
	public void testHasAccessAsCustomerValid() {
		assertTrue(ad.hasAccessAsCustomer(8, 1));
	}

	@Test
	public void testHasAccessAsCustomerInvalid() {
		assertFalse(ad.hasAccessAsCustomer(8, 2));
	}

}
