package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Audio;
import com.revature.koality.bean.Track;
import com.revature.koality.dao.TrackDAO;
import com.revature.koality.dao.TrackDAOImpl;

public class PublishServiceImpl implements PublishService {

	private TrackDAO td;

	public PublishServiceImpl() {
		super();
		td = new TrackDAOImpl();
	}

	public TrackDAO getTd() {
		return td;
	}

	public void setTd(TrackDAO td) {
		this.td = td;
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
		// TODO Auto-generated method stub
		return 0;
	}

}
