package com.revature.koality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.revature.koality.bean.Playlist;
import com.revature.koality.bean.Track;
import com.revature.koality.dao.CustomerDAO;
import com.revature.koality.dao.CustomerDAOImpl;
import com.revature.koality.dao.PlaylistDAO;
import com.revature.koality.dao.PlaylistDAOImpl;

@Service("playlistServiceImpl")
public class PlaylistServiceImpl implements PlaylistService {

	private CustomerDAO cd;
	private PlaylistDAO pld;

	public PlaylistServiceImpl() {
		super();

	}

	public PlaylistServiceImpl(CustomerDAOImpl cd) {
		super();
		this.cd = cd;
	}

	public PlaylistServiceImpl(PlaylistDAOImpl pld) {
		super();
		this.pld = pld;
	}

	public PlaylistServiceImpl(PlaylistDAOImpl pld, CustomerDAOImpl cd) {
		super();
		this.cd = cd;
		this.pld = pld;
	}

	public CustomerDAO getCd() {
		return cd;
	}

	@Autowired
	@Qualifier("customerDAOImpl")
	public void setCd(CustomerDAO cd) {
		this.cd = cd;
	}

	public PlaylistDAO getPld() {
		return pld;
	}

	@Autowired
	@Qualifier("playlistDAOImpl")
	public void setPld(PlaylistDAO pld) {
		this.pld = pld;
	}

	@Override
	public List<Track> getPurchasedTracks(int customerId) {
		return cd.getAllTracksByCustomerId(customerId);
	}

	@Override
	public int createPlaylist(String playlistName, int customerId) {
		return pld.addPlaylist(playlistName, customerId);
	}

	@Override
	public boolean deletePlaylist(int playlistId) {
		return pld.deletePlaylist(playlistId);
	}

	@Override
	public int addTracksToPlaylist(int playlistId, List<Integer> trackIdList) {
		return pld.addTracksToPlaylist(playlistId, trackIdList);
	}

	@Override
	public int removeTracksFromPlaylist(int playlistId, List<Integer> trackIdList) {
		return pld.removeTracksFromPlaylist(playlistId, trackIdList);
	}

	@Override
	public List<Playlist> getAllPlaylists(int customerId) {
		return pld.getAllPlaylistsByCustomerId(customerId);
	}

	@Override
	public Playlist selectPlaylist(int playlistId) {
		return pld.getPlaylistById(playlistId);
	}

}
