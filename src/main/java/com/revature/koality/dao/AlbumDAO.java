package com.revature.koality.dao;

import java.util.List;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.Track;

public interface AlbumDAO {

	int addAlbum(Album album, int publisherId, List<Integer> trackIdList);

	Album getAlbumById(int albumId);

	List<Album> getAllAlbums();

	float updateAlbumPrice(int albumId, float unitPrice);

	List<Track> getAllTracksByAlbumId(int albumId);

	List<AlbumReview> getAllAlbumReviewsByAlbumId(int albumId);

	int getAlbumPurchaseCount(int albumId);

	boolean hasAccessAsPublisher(int albumId, int publisherId);

	boolean hasAccessAsCustomer(int albumId, int customerId);

}
