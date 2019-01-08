package com.revature.koality.service;

import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.CustomerDAOImpl;

public class PurchaseServiceImpl implements PurchaseService {

	private CustomerDAO cd;

	public PurchaseServiceImpl() {
		super();
		cd = new CustomerDAOImpl();
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
