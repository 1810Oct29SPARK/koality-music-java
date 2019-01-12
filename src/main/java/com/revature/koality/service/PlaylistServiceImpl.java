package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Playlist;
import com.revature.koality.bean.Track;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.PlaylistDAO;
import com.revature.koality.dao.PlaylistDAOImpl;

public class PlaylistServiceImpl implements PlaylistService {
	
	private CustomerDAO cd; 
	private PlaylistDAO pd; 

	public PlaylistServiceImpl() {
		super(); 
		cd = new CustomerDAOImpl(); 
		pd = new PlaylistDAOImpl(); 
		
	}
	
	public PlaylistServiceImpl(CustomerDAOImpl cd) {
		super(); 
		this.cd = cd;
	}
	
	public PlaylistServiceImpl(PlaylistDAOImpl pd) {
		super(); 
		this.pd = pd;
	}
	
	public PlaylistServiceImpl(PlaylistDAOImpl pd, CustomerDAOImpl cd) {
		super(); 
		this.cd = cd;
		this.pd = pd; 
	}

	@Override
	public List<Track> getPurchasedSongs(int customerId) {
		return cd.getAllTracksByCustomerId(customerId); 
	}

	@Override
	public int createPlaylist(String playlistName, int customerId) {
		return pd.addPlaylist(playlistName, customerId); 
	}

	@Override
	public boolean deletePlaylist(int playlistId) {
		return pd.deletePlaylist(playlistId); 
	}

	@Override
	public int addTracksToPlaylist(int playlistId, List<Integer> trackIdList) {
		return pd.addTracksToPlaylist(playlistId, trackIdList); 
	}

	@Override
	public int removeTracksFromPlaylist(int playlistId, List<Integer> trackIdList) {
		return pd.removeTracksFromPlaylist(playlistId, trackIdList); 
	}

	@Override
	public List<Playlist> getAllPlaylists(int customerId) {
		return pd.getAllPlaylistsByCustomerId(customerId); 
	}

	@Override
	public Playlist selectPlaylist(int playlistId) {
		return pd.getPlaylistById(playlistId); 
	}

}
