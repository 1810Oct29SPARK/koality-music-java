package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.Track;
import com.revature.koality.bean.TrackReview;
import com.revature.koality.dao.AlbumDAO;
import com.revature.koality.dao.AlbumDAOImpl;
import com.revature.koality.dao.PublisherDAO;
import com.revature.koality.dao.PublisherDAOImpl;
import com.revature.koality.dao.TrackDAO;
import com.revature.koality.dao.TrackDAOImpl;

public class DisplayServiceImpl implements DisplayService {
	
	private TrackDAO td;
	private AlbumDAO ad; 
	private PublisherDAO pd; 

	public DisplayServiceImpl() {
		super(); 
		td = new TrackDAOImpl(); 
		ad = new AlbumDAOImpl(); 
		pd = new PublisherDAOImpl(); 
	}
	
	public DisplayServiceImpl(TrackDAOImpl td) {
		super(); 
		this.td = td; 
	}

	
	public DisplayServiceImpl(AlbumDAOImpl ad) {
		super(); 
		this.ad = ad; 
	}
	
	public DisplayServiceImpl(PublisherDAOImpl pd) {
		super(); 
		this.pd = pd; 
	}

	@Override
	public List<TrackReview> viewTrackReviews(int trackId) {
		return td.getAllTrackReviewsByTrackId(trackId); 
	}

	@Override
	public List<AlbumReview> viewAlbumReviews(int albumId) {
		return ad.getAllAlbumReviewsByAlbumId(albumId); 
	}

	@Override
	public List<Track> viewAllTracks() {
		return td.getAllTracks(); 
	}

	@Override
	public List<Album> viewAllAlbums() {
		return ad.getAllAlbums(); 
	}

	@Override
	public List<Track> getPublisherTracks(int publisherId) {
		return pd.getAllTracksByPublisherId(publisherId); 
	}

	@Override
	public List<Album> getPublisherAlbums(int publisherId) {
		return pd.getAllAlbumsByPublisherId(publisherId); 
	}

}