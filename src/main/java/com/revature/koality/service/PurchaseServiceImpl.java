package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Track;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.TrackDAO;
import com.revature.koality.dao.TrackDAOImpl;

public class PurchaseServiceImpl implements PurchaseService {

	private CustomerDAO cd;
	private TrackDAO td;

	public PurchaseServiceImpl() {
		super();
		cd = new CustomerDAOImpl();
		td = new TrackDAOImpl();
	}

	public PurchaseServiceImpl(CustomerDAO customerDAOMock) {
		super();
		this.cd = customerDAOMock;
	}

	public PurchaseServiceImpl(TrackDAO trackDAOMock) {
		super();
		this.td = trackDAOMock;
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
