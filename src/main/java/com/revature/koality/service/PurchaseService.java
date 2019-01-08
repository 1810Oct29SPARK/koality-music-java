package com.revature.koality.service;

public interface PurchaseService {
	
	boolean purchaseTrack(int customerId, int trackId); 
	
	boolean purchaseAlbum(int customerId, int albumId); 
	
}
