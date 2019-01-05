package com.revature.koality.dao;

import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.ReviewContent;

public interface AlbumReviewDAO {

	boolean addAlbumReview(ReviewContent reviewContent, int albumId, int customerId);

	AlbumReview getAlbumReviewById(int albumReviewId);

	AlbumReview getAlbumReviewByAlbumIdAndCustomerId(int albumId, int customerId);

	boolean deleteAlbumReview(int albumReviewId);

}
