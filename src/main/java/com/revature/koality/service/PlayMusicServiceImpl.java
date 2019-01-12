package com.revature.koality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Track;
import com.revature.koality.dao.AlbumDAO;
import com.revature.koality.dao.AlbumDAOImpl;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.TrackDAO;
import com.revature.koality.dao.TrackDAOImpl;

@Service("playMusicServiceImpl")
public class PlayMusicServiceImpl implements PlayMusicService {

	private CustomerDAO cd;
	private TrackDAO td;
	private AlbumDAO ad;

	public PlayMusicServiceImpl() {
		super();
	}

	public PlayMusicServiceImpl(CustomerDAOImpl cd) {
		super();
		this.cd = cd;
	}

	public PlayMusicServiceImpl(TrackDAOImpl td) {
		super();
		this.td = td;
	}

	public PlayMusicServiceImpl(AlbumDAOImpl ad) {
		super();
		this.ad = ad;
	}

	public CustomerDAO getCd() {
		return cd;
	}

	@Autowired
	@Qualifier("customerDAOImpl")
	public void setCd(CustomerDAO cd) {
		this.cd = cd;
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
	public List<Track> getPurchasedTracks(int customerId) {

		return cd.getAllTracksByCustomerId(customerId);

	}

	@Override
	public Track getTrack(int customerId, int trackId) {

		Track track = td.getTrackById(trackId);

		if (track != null && td.hasAccessAsCustomer(trackId, customerId)) {
			return track;
		}

		return null;
	}

	@Override
	public List<Album> getPurchasedAlbums(int customerId) {

		return cd.getAllAlbumsByCustomerId(customerId);

	}

	@Override
	public Album getAlbum(int customerId, int albumId) {

		Album album = ad.getAlbumById(albumId);

		if (album != null && ad.hasAccessAsCustomer(albumId, customerId)) {
			return album;
		}

		return null;
	}

	@Override
	public List<Track> getAlbumTracks(int albumId) {

		List<Track> tracks = ad.getAllTracksByAlbumId(albumId);

		if (tracks != null) {
			return tracks;
		}

		return null;
	}

}
