package com.revature.koality.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.koality.utility.CommonUtility;
import com.revature.koality.utility.HibernateUtility;
import com.revature.koality.utility.MockUtility;

public class TrackDAOImplTest {

	private static TrackDAO td;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		td = new TrackDAOImpl(true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtility.destroySessionFactories();
	}

	@Ignore
	@Test
	public void testAddTrack() {
		assertNotEquals(-1, td.addTrack(MockUtility.getMockTrack(), 1));
	}

	@Ignore
	@Test
	public void testAddTrackForP2() {
		assertNotEquals(-1, td.addTrack(MockUtility.getMockTrack(), 2));
	}

	@Ignore
	@Test
	public void testAddTrackForP3() {
		assertNotEquals(-1, td.addTrack(MockUtility.getMockTrack(), 3));
	}

	@Test
	public void testGetTrackById() {
		assertEquals("KdXFvYsHnr", td.getTrackById(3).getComposer());
	}

	@Test
	public void testGetTrackByIdWithAudio() {
		assertEquals(
				"data:audio/kp1;base64,UmYxTkhhOHpiSDBoTVU0UHRjWkhUWUhib0NlSEFmV1ZEc2VrNkhYVFFTRzdIdTdzcVI2ektKa0cxajZ4UXdEOU5GSlhMOVQwNVV2ZmZPS0NwaWpLWXRQT0xIdjlMRlhDeWplRA==",
				td.getTrackById(2).getAudioUrl());
	}

	@Test
	public void testGetAllTracks() {
		assertTrue(td.getAllTracks().size() > 1);
	}

	@Test
	public void testGetAllTracksWithId() {
		assertEquals(1, td.getAllTracks().get(0).getTrackId());
	}

	@Test
	public void testUpdateTrackPrice() {
		assertNotEquals(-1F, td.updateTrackPrice(3, CommonUtility.generateRandomInteger(10)));
	}

	@Ignore
	@Test
	public void testGetAllTrackReviewsByTrackId() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetTrackPurchaseCount() {
		fail("Not yet implemented");
	}

}
