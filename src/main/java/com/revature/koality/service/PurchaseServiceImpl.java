package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Track;
import com.revature.koality.dao.AlbumDAO;
import com.revature.koality.dao.AlbumDAOImpl;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.TrackDAO;
import com.revature.koality.dao.TrackDAOImpl;

public class PurchaseServiceImpl implements PurchaseService {

	private CustomerDAO cd;
	private TrackDAO td;
	private AlbumDAO ad; 

	public PurchaseServiceImpl() {
		super();
		cd = new CustomerDAOImpl();
		td = new TrackDAOImpl();
		ad = new AlbumDAOImpl(); 
	}

	public PurchaseServiceImpl(CustomerDAOImpl cd) {
		super();
		this.cd = cd;
	}

	public PurchaseServiceImpl(TrackDAOImpl td) {
		super();
		this.td = td;
	}
	
	public PurchaseServiceImpl(AlbumDAOImpl ad) {
		super();
		this.ad = ad;
	}

	@Override
	public List<Track> viewAllTracks() {
		return td.getAllTracks();
	}
	
	@Override
	public List<Album> viewAllAlbums() {
		return ad.getAllAlbums();
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
