package com.revature.koality.dao;

import com.revature.koality.bean.ReviewContent;
import com.revature.koality.bean.TrackReview;

public interface TrackReviewDAO {

	int addTrackReview(ReviewContent reviewContent, int trackId, int customerId);

	TrackReview getTrackReviewById(int trackReviewId);

	TrackReview getTrackReviewByTrackIdAndCustomerId(int trackId, int customerId);

	boolean deleteTrackReview(int trackReviewId);

	boolean isOwner(int trackReviewId, int customerId);

}
