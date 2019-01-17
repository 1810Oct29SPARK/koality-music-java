package com.revature.koality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

@Service("displayServiceImpl")
public class DisplayServiceImpl implements DisplayService {

	private TrackDAO td;
	private AlbumDAO ad;
	private PublisherDAO pd;

	public DisplayServiceImpl() {
		super();
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

	public PublisherDAO getPd() {
		return pd;
	}

	@Autowired
	@Qualifier("publisherDAOImpl")
	public void setPd(PublisherDAO pd) {
		this.pd = pd;
	}

	@Override
	public List<TrackReview> viewTrackReviews(int trackId, int publisherId) {

		List<TrackReview> trackReviewList = null;

		if (td.hasAccessAsPublisher(trackId, publisherId)) {
			trackReviewList = td.getAllTrackReviewsByTrackId(trackId);
			trackReviewList.forEach(tr -> tr.truncate());
		}

		return trackReviewList;

	}

	@Override
	public List<AlbumReview> viewAlbumReviews(int albumId, int publisherId) {

		List<AlbumReview> albumReviewList = null;

		if (ad.hasAccessAsPublisher(albumId, publisherId)) {
			albumReviewList = ad.getAllAlbumReviewsByAlbumId(albumId);
			albumReviewList.forEach(ar -> ar.truncate());
		}

		return albumReviewList;

	}

	@Override
	public List<Track> getPublisherTracks(int publisherId) {

		List<Track> trackList = pd.getAllTracksByPublisherId(publisherId);
		trackList.forEach(t -> t.truncate(true));
		return trackList;

	}

	@Override
	public List<Album> getPublisherAlbums(int publisherId) {

		List<Album> albumList = pd.getAllAlbumsByPublisherId(publisherId);
		albumList.forEach(a -> a.truncate(false));
		return albumList;

	}

}
