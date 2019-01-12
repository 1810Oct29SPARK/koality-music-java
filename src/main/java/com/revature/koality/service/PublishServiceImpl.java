package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Audio;
import com.revature.koality.bean.Image;
import com.revature.koality.bean.Track;
import com.revature.koality.dao.AlbumDAO;
import com.revature.koality.dao.AlbumDAOImpl;
import com.revature.koality.dao.TrackDAO;
import com.revature.koality.dao.TrackDAOImpl;

public class PublishServiceImpl implements PublishService {

	private TrackDAO td;
	private AlbumDAO ad;

	public PublishServiceImpl() {
		super();
		td = new TrackDAOImpl();
		ad = new AlbumDAOImpl();
	}

	public PublishServiceImpl(TrackDAO trackDAOMock) {
		super();
		this.td = trackDAOMock;
	}

	public PublishServiceImpl(AlbumDAO albumDAOMock) {
		super();
		this.ad = albumDAOMock;
	}

	public TrackDAO getTd() {
		return td;
	}

	public void setTd(TrackDAO td) {
		this.td = td;
	}

	public AlbumDAO getAd() {
		return ad;
	}

	public void setAd(AlbumDAO ad) {
		this.ad = ad;
	}

	@Override
	public int publishTrack(int publisherId, String trackName, String genre, String composer, String artist,
			int trackLength, float unitPrice, String audioType, byte[] audioData) {

		Audio audio = new Audio(audioType, audioData);

		if (unitPrice > 0) {

			Track track = new Track(trackName, genre, composer, artist, trackLength, unitPrice, audio, null);

			return td.addTrack(track, publisherId);
		}

		return 0;
	}

	@Override
	public int publishAlbum(int publisherId, String albumName, String genre, float unitPrice, String imageType,
			byte[] imageData, List<Integer> trackIdList) {

		Image image = new Image(imageType, imageData);

		if (unitPrice > 0) {

			Album album = new Album(albumName, genre, unitPrice, image);

			return ad.addAlbum(album, publisherId, trackIdList);
		}

		return 0;
	}

}
