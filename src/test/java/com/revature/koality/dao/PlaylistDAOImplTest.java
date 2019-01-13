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

import com.revature.koality.bean.Track;
import com.revature.koality.utility.CommonUtility;
import com.revature.koality.utility.HibernateUtility;

public class PlaylistDAOImplTest {

	private static PlaylistDAO pld;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pld = new PlaylistDAOImpl(true);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		HibernateUtility.destroySessionFactories();
	}

	@Ignore
	@Test
	public void testAddPlaylist() {
		assertNotEquals(-1,
				pld.addPlaylist(CommonUtility.generateRandomString(8), CommonUtility.generateRandomInteger(6)));
	}

	@Ignore
	@Test
	public void testAddPlaylist2() {
		assertNotEquals(-1,
				pld.addPlaylist(CommonUtility.generateRandomString(8), CommonUtility.generateRandomInteger(6)));
	}

	@Test
	public void testGetPlaylistById() {
		List<Track> trackList = pld.getPlaylistById(3).getTrackList();
		trackList.removeIf(t -> t.getTrackId() != 4);
		assertEquals(
				"data:audio/rmf;base64,dVIzWXFTN3VPN0dFZ2pSbzhTV2JGbkhiTmd6NkFHTmtOOVV5SlFUYnYxb1ZpRWJQMHV1QWd0RGNQbzlsdHF4akpmcTZmSWZ6cWdVTUlwUGhzWkNKNko5RjlidUJwTHRmaVFvMQ==",
				trackList.get(0).getAudioUrl());
	}

	@Test
	public void testGetAllPlaylistsByCustomerId() {
		assertTrue(pld.getAllPlaylistsByCustomerId(1).size() > 0);
	}

	@Ignore
	@Test
	public void testAddTracksToPlaylist() {
		List<Integer> trackIdList = new ArrayList<>();
		trackIdList.add(5);
		trackIdList.add(2);
		assertEquals(2, pld.addTracksToPlaylist(4, trackIdList));
	}

	@Ignore
	@Test
	public void testAddTracksToPlaylist2() {
		List<Integer> trackIdList = new ArrayList<>();
		trackIdList.add(3);
		trackIdList.add(5);
		assertEquals(2, pld.addTracksToPlaylist(8, trackIdList));
	}

	@Ignore
	@Test
	public void testAddTracksToPlaylist3() {
		List<Integer> trackIdList = new ArrayList<>();
		trackIdList.add(1);
		trackIdList.add(9);
		assertEquals(2, pld.addTracksToPlaylist(5, trackIdList));
	}

	@Ignore
	@Test
	public void testRemoveTracksFromPlaylist() {
		List<Integer> trackIdList = new ArrayList<>();
		trackIdList.add(1);
		assertEquals(1, pld.removeTracksFromPlaylist(5, trackIdList));
	}

	@Ignore
	@Test
	public void testDeletePlaylist() {
		assertTrue(pld.deletePlaylist(10));
	}

	@Test
	public void testIsOwnerTrue() {
		assertTrue(pld.isOwner(3, 4));
	}

	@Test
	public void testIsOwnerFalse() {
		assertFalse(pld.isOwner(3, 5));
	}

}
