package com.revature.koality.dao;

import java.util.List;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.Track;

public interface AlbumDAO {

	boolean addAlbum(Album album, int publisherId, List<Integer> trackIdList);

	Album getAlbumById(int albumId);

	List<Album> getAllAlbums();

	boolean updateAlbumPrice(int albumId, float unitPrice);

	List<Track> getAllTracksByAlbumId(int albumId);

	List<AlbumReview> getAllAlbumReviewsByAlbumId(int albumId);

	int getAlbumPurchaseCount(int albumId);

}
