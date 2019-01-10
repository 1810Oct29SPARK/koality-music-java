package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Track;

public interface PurchaseService {
	
	List<Track> viewAllTracks(); 

	boolean purchaseTrack(int customerId, int trackId);

	boolean purchaseAlbum(int customerId, int albumId);

}
