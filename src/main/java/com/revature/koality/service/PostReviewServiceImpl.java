package com.revature.koality.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.ReviewContent;
import com.revature.koality.bean.TrackReview;
import com.revature.koality.dao.AlbumReviewDAO;
import com.revature.koality.dao.AlbumReviewDAOImpl;
import com.revature.koality.dao.TrackReviewDAO;
import com.revature.koality.dao.TrackReviewDAOImpl;

@Service("postReviewServiceImpl")
public class PostReviewServiceImpl implements PostReviewService {

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

		ReviewContent reviewContent = new ReviewContent(rating, reviewComment);

		return trd.addTrackReview(reviewContent, trackId, customerId);

	}

	@Override
	public int postAlbumReview(int rating, String reviewComment, int albumId, int customerId) {

		ReviewContent reviewContent = new ReviewContent(rating, reviewComment);

		return ard.addAlbumReview(reviewContent, albumId, customerId);

	}

	@Override
	public TrackReview viewPostedTrackReview(int trackId, int customerId) {
		return trd.getTrackReviewByTrackIdAndCustomerId(trackId, customerId);
	}

	@Override
	public AlbumReview viewPostedAlbumReview(int albumId, int customerId) {
		return ard.getAlbumReviewByAlbumIdAndCustomerId(albumId, customerId);
	}

	@Override
	public boolean deleteTrackReview(int trackReviewId) {
		return trd.deleteTrackReview(trackReviewId);
	}

	@Override
	public boolean deleteAlbumReview(int albumReviewId) {
		return ard.deleteAlbumReview(albumReviewId);
	}

}
