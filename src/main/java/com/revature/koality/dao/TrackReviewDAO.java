package com.revature.koality.dao;

import com.revature.koality.bean.ReviewContent;
import com.revature.koality.bean.TrackReview;

public interface TrackReviewDAO {

	boolean addTrackReview(ReviewContent reviewContent, int trackId, int customerId);

	TrackReview getTrackReviewById(int trackReviewId);

	TrackReview getTrackReviewByTrackIdAndCustomerId(int trackId, int customerId);

	boolean deleteTrackReview(int trackReviewId);

}
