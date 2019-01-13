package com.revature.koality.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Audio;
import com.revature.koality.bean.Image;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.Track;
import com.revature.koality.dao.AlbumDAOImpl;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.PublisherDAOImpl;
import com.revature.koality.dao.TrackDAOImpl;
import com.revature.koality.utility.MockUtility;

public class PublishServiceImplTest {

	@Mock
	TrackDAOImpl trackDAOMock;

	@Mock
	AlbumDAOImpl albumDAOMock;

	@InjectMocks
	private PublishService as;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testPublishTrack() {

		Publisher publisher = MockUtility.getMockPublisher();

		Track track = MockUtility.getMockTrack();

		Audio audio = track.getAudio();

		int publisherId = publisher.getPublisherId();
		String trackName = track.getTrackName();
		String genre = track.getGenre();
		String composer = track.getComposer();
		String artist = track.getArtist();
		int trackLength = track.getTrackLength();
		float unitPrice = track.getUnitPrice();
		String audioType = audio.getAudioType();
		byte[] audioData = audio.getAudioData();

		trackDAOMock = mock(TrackDAOImpl.class);

		as = new PublishServiceImpl(trackDAOMock);

		when(trackDAOMock.addTrack(track, publisherId)).thenReturn(1);

		int actual = as.publishTrack(publisherId, trackName, genre, composer, artist, trackLength, unitPrice, audioType,
				audioData);

		assertEquals(1, actual);
	}

	@Test
	public void testInvalidPublishTrack() {

		Publisher publisher = MockUtility.getMockPublisher();

		Track track = MockUtility.getMockTrack();

		track.setUnitPrice(-12);

		Audio audio = track.getAudio();

		int publisherId = publisher.getPublisherId();
		String trackName = track.getTrackName();
		String genre = track.getGenre();
		String composer = track.getComposer();
		String artist = track.getArtist();
		int trackLength = track.getTrackLength();
		float unitPrice = track.getUnitPrice();
		String audioType = audio.getAudioType();
		byte[] audioData = audio.getAudioData();

		trackDAOMock = mock(TrackDAOImpl.class);

		as = new PublishServiceImpl(trackDAOMock);

		when(trackDAOMock.addTrack(track, publisherId)).thenReturn(1);

		int actual = as.publishTrack(publisherId, trackName, genre, composer, artist, trackLength, unitPrice, audioType,
				audioData);

		assertEquals(0, actual);
	}

	@Test
	public void testPublishAlbum() {

		Publisher publisher = MockUtility.getMockPublisher();

		Album album = MockUtility.getMockAlbum();

		Integer[] trackIds = { 1, 2, 3, 4, 5 };

		List<Integer> trackIdList = new ArrayList<Integer>(Arrays.asList(trackIds));

		int publisherId = publisher.getPublisherId();
		String albumName = album.getAlbumName();
		String genre = album.getGenre();
		float unitPrice = album.getUnitPrice();
		Image image = album.getImage();
		String imageType = image.getImageType();
		byte[] imageData = image.getImageData();

		albumDAOMock = mock(AlbumDAOImpl.class);

		as = new PublishServiceImpl(albumDAOMock);

		when(albumDAOMock.addAlbum(album, publisherId, trackIdList)).thenReturn(1);

		int actual = as.publishAlbum(publisherId, albumName, genre, unitPrice, imageType, imageData, trackIdList);

		assertEquals(1, actual);
	}

	@Test
	public void testNoTrackPublishAlbum() {

		Publisher publisher = MockUtility.getMockPublisher();

		Album album = MockUtility.getMockAlbum();

		Integer[] trackIds = { 1, 2, 3, 4, 5 };

		List<Integer> trackIdList = null;

		int publisherId = publisher.getPublisherId();
		String albumName = album.getAlbumName();
		String genre = album.getGenre();
		float unitPrice = album.getUnitPrice();
		Image image = album.getImage();
		String imageType = image.getImageType();
		byte[] imageData = image.getImageData();

		albumDAOMock = mock(AlbumDAOImpl.class);

		as = new PublishServiceImpl(albumDAOMock);

		when(albumDAOMock.addAlbum(album, publisherId, trackIdList)).thenReturn(1);

		int actual = as.publishAlbum(publisherId, albumName, genre, unitPrice, imageType, imageData, null);

		assertEquals(0, actual);
	}

	@Test
	public void testNegativePricePublishAlbum() {

		Publisher publisher = MockUtility.getMockPublisher();

		Album album = MockUtility.getMockAlbum();

		List<Integer> trackIdList = null;

		album.setUnitPrice(-12);
		int publisherId = publisher.getPublisherId();
		String albumName = album.getAlbumName();
		String genre = album.getGenre();
		float unitPrice = album.getUnitPrice();
		Image image = album.getImage();
		String imageType = image.getImageType();
		byte[] imageData = image.getImageData();

		albumDAOMock = mock(AlbumDAOImpl.class);

		as = new PublishServiceImpl(albumDAOMock);

		when(albumDAOMock.addAlbum(album, publisherId, trackIdList)).thenReturn(1);

		int actual = as.publishAlbum(publisherId, albumName, genre, unitPrice, imageType, imageData, null);

		assertEquals(0, actual);
	}

}
