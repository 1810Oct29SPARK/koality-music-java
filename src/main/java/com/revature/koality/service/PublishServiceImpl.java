package com.revature.koality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Audio;
import com.revature.koality.bean.Image;
import com.revature.koality.bean.Track;
import com.revature.koality.dao.AlbumDAO;
import com.revature.koality.dao.TrackDAO;

@Service("publishServiceImpl")
public class PublishServiceImpl implements PublishService {

	private TrackDAO td;
	private AlbumDAO ad;

	public PublishServiceImpl() {
		super();
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

	@Autowired
	@Qualifier("trackDAOImpl")
	public void setTd(TrackDAO td) {
		this.td = td;
	}

	public AlbumDAO getAd() {
		return ad;
	}

	@Autowired
	@Qualifier("albumDAOImpl")
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

		if (trackIdList != null) {

			Image image = new Image(imageType, imageData);

			if (unitPrice > 0) {

				Album album = new Album(albumName, genre, unitPrice, image);

				return ad.addAlbum(album, publisherId, trackIdList);
			}
		}

		return 0;
	}

}
