package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Track;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.TrackDAO;

public class PurchaseServiceImpl implements PurchaseService {

	private CustomerDAO cd;
	private TrackDAO td;

	public PurchaseServiceImpl() {
		super();
		cd = new CustomerDAOImpl();
	}

	@Override
	public List<Track> viewAllTracks() {

		return td.getAllTracks();
	}

	@Override
	public boolean purchaseTrack(int customerId, int trackId) {

		return cd.purchaseTrack(customerId, trackId);
	}

	@Override
	public boolean purchaseAlbum(int customerId, int albumId) {

		return cd.purchaseAlbum(customerId, albumId);
	}

}
