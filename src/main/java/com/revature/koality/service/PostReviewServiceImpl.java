package com.revature.koality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.ReviewContent;
import com.revature.koality.bean.TrackReview;
import com.revature.koality.dao.AlbumDAO;
import com.revature.koality.dao.AlbumReviewDAO;
import com.revature.koality.dao.AlbumReviewDAOImpl;
import com.revature.koality.dao.TrackDAO;
import com.revature.koality.dao.TrackReviewDAO;
import com.revature.koality.dao.TrackReviewDAOImpl;

@Service("postReviewServiceImpl")
public class PostReviewServiceImpl implements PostReviewService {

	private TrackDAO td;
	private AlbumDAO ad;
	private TrackReviewDAO trd;
	private AlbumReviewDAO ard;

	public PostReviewServiceImpl() {
		super();
	}

	public PostReviewServiceImpl(TrackReviewDAOImpl trd) {
		super();
		this.trd = trd;
	}

	public PostReviewServiceImpl(AlbumReviewDAOImpl ard) {
		super();
		this.ard = ard;
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

	public TrackReviewDAO getTrd() {
		return trd;
	}

	@Autowired
	@Qualifier("trackReviewDAOImpl")
	public void setTrd(TrackReviewDAO trd) {
		this.trd = trd;
	}

	public AlbumReviewDAO getArd() {
		return ard;
	}

	@Autowired
	@Qualifier("albumReviewDAOImpl")
	public void setArd(AlbumReviewDAO ard) {
		this.ard = ard;
	}

	@Override
	public int postTrackReview(int rating, String reviewComment, int trackId, int customerId) {

		if (!td.hasAccessAsCustomer(trackId, customerId)) {
			return 0;
		} else {
			ReviewContent reviewContent = new ReviewContent(rating, reviewComment);
			return trd.addTrackReview(reviewContent, trackId, customerId);
		}

	}

	@Override
	public int postAlbumReview(int rating, String reviewComment, int albumId, int customerId) {

		if (!ad.hasAccessAsCustomer(albumId, customerId)) {
			return 0;
		} else {
			ReviewContent reviewContent = new ReviewContent(rating, reviewComment);
			return ard.addAlbumReview(reviewContent, albumId, customerId);
		}

	}

	@Override
	public TrackReview viewPostedTrackReview(int trackId, int customerId) {

		TrackReview trackReview = trd.getTrackReviewByTrackIdAndCustomerId(trackId, customerId);
		trackReview.setTrack(null);
		trackReview.setCustomer(null);

		return trackReview;
	}

	@Override
	public AlbumReview viewPostedAlbumReview(int albumId, int customerId) {

		AlbumReview albumReview = ard.getAlbumReviewByAlbumIdAndCustomerId(albumId, customerId);
		albumReview.setAlbum(null);
		albumReview.setCustomer(null);

		return albumReview;
	}

	@Override
	public boolean deleteTrackReview(int trackReviewId, int customerId) {

		if (trd.isOwner(trackReviewId, customerId)) {
			return trd.deleteTrackReview(trackReviewId);
		} else {
			return false;
		}

	}

	@Override
	public boolean deleteAlbumReview(int albumReviewId, int customerId) {

		if (ard.isOwner(albumReviewId, customerId)) {
			return ard.deleteAlbumReview(albumReviewId);
		} else {
			return false;
		}

	}

}
