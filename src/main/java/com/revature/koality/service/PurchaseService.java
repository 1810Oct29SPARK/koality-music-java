package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Track;

public interface PurchaseService {

	List<Track> viewAllTracks();

	List<Album> viewAllAlbums();

	boolean purchaseTrack(int customerId, int trackId);

	boolean purchaseAlbum(int customerId, int albumId);

	List<Track> viewRecommendedTracks(String genre);

	List<Album> viewRecommendedAlbums(String genre);

}
