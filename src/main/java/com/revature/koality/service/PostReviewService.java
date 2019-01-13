package com.revature.koality.service;

import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.TrackReview;

public interface PostReviewService {

	int postTrackReview(int rating, String reviewComment, int trackId, int customerId);

	int postAlbumReview(int rating, String reviewComment, int albumId, int customerId);

	TrackReview viewPostedTrackReview(int trackId, int customerId);

	AlbumReview viewPostedAlbumReview(int albumId, int customerId);

	boolean deleteTrackReview(int trackReviewId, int customerId);

	boolean deleteAlbumReview(int albumReviewId, int customerId);

}
