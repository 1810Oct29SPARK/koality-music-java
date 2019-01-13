package com.revature.koality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Track;
import com.revature.koality.dao.AlbumDAO;
import com.revature.koality.dao.AlbumDAOImpl;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.TrackDAO;
import com.revature.koality.dao.TrackDAOImpl;

@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {

	private CustomerDAO cd;
	private TrackDAO td;
	private AlbumDAO ad;

	public PurchaseServiceImpl() {
		super();
	}

	public CustomerDAO getCd() {
		return cd;
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

	@Autowired
	@Qualifier("customerDAOImpl")
	public void setCd(CustomerDAO cd) {
		this.cd = cd;
	}

	public TrackDAO getTd() {
		return td;
	}

	@Autowired
	@Qualifier("trackDAOImpl")
	public void setTd(TrackDAO td) {
		this.td = td;
	}

	public AlbumDAO getAd() {
		return ad;
	}

	@Autowired
	@Qualifier("albumDAOImpl")
	public void setAd(AlbumDAO ad) {
		this.ad = ad;
	}

	@Override
	public List<Track> viewAllTracks() {
		List<Track> trackList = td.getAllTracks();
		trackList.forEach(t -> t.truncate(true));
		return trackList;
	}

	@Override
	public List<Album> viewAllAlbums() {
		List<Album> albumList = ad.getAllAlbums();
		albumList.forEach(a -> a.truncate(true));
		return albumList;
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
