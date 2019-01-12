package com.revature.koality.service;

import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.ReviewContent;
import com.revature.koality.bean.TrackReview;
import com.revature.koality.dao.AlbumReviewDAO;
import com.revature.koality.dao.AlbumReviewDAOImpl;
import com.revature.koality.dao.TrackReviewDAO;
import com.revature.koality.dao.TrackReviewDAOImpl;

public class PostReviewServiceImpl implements PostReviewService {
	
	private TrackReviewDAO td; 
	private AlbumReviewDAO ad; 

	public PostReviewServiceImpl() {
		super(); 
		td = new TrackReviewDAOImpl(); 
		ad = new AlbumReviewDAOImpl(); 
	}
	
	public PostReviewServiceImpl(TrackReviewDAOImpl td) {
		super(); 
		this.td = td; 
	}
	
	public PostReviewServiceImpl(AlbumReviewDAOImpl ad) {
		super(); 
		this.ad = ad; 
	}

	@Override
	public int postTrackReview(int rating, String reviewComment, int trackId, int customerId) {
		
		ReviewContent reviewContent = new ReviewContent(rating, reviewComment); 
		
		return td.addTrackReview(reviewContent, trackId, customerId); 
	}

	@Override
	public int postAlbumReview(int rating, String reviewComment, int albumId, int customerId) {
		
		ReviewContent reviewContent = new ReviewContent(rating, reviewComment); 
		
		return ad.addAlbumReview(reviewContent, albumId, customerId); 
	}

	@Override
	public TrackReview viewPostedTrackReview(int trackId, int customerId) {
		return td.getTrackReviewByTrackIdAndCustomerId(trackId, customerId); 
	}

	@Override
	public AlbumReview viewPostedAlbumReview(int albumId, int customerId) {
		return ad.getAlbumReviewByAlbumIdAndCustomerId(albumId, customerId); 
	}

	@Override
	public boolean deleteTrackReview(int trackReviewId) {
		return td.deleteTrackReview(trackReviewId); 
	}

	@Override
	public boolean deleteAlbumReview(int albumReviewId) {
		return ad.deleteAlbumReview(albumReviewId); 
	}

}
