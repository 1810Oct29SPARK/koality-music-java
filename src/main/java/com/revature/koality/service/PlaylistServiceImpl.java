package com.revature.koality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.revature.koality.bean.Playlist;
import com.revature.koality.bean.Track;
import com.revature.koality.dao.PlaylistDAO;
import com.revature.koality.dao.PlaylistDAOImpl;

@Service("playlistServiceImpl")
public class PlaylistServiceImpl implements PlaylistService {

	private PlaylistDAO pld;

	public PlaylistServiceImpl() {
		super();

	}

	public PlaylistServiceImpl(PlaylistDAOImpl pld) {
		super();
		this.pld = pld;
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
	public int createPlaylist(String playlistName, int customerId) {
		return pld.addPlaylist(playlistName, customerId);
	}

	@Override
	public boolean deletePlaylist(int playlistId, int customerId) {

		if (pld.isOwner(playlistId, customerId)) {
			return pld.deletePlaylist(playlistId);
		} else {
			return false;
		}

	}

	@Override
	public int addTracksToPlaylist(int playlistId, List<Integer> trackIdList, int customerId) {

		if (pld.isOwner(playlistId, customerId)) {
			return pld.addTracksToPlaylist(playlistId, trackIdList);
		} else {
			return -1;
		}

	}

	@Override
	public int removeTracksFromPlaylist(int playlistId, List<Integer> trackIdList, int customerId) {

		if (pld.isOwner(playlistId, customerId)) {
			return pld.removeTracksFromPlaylist(playlistId, trackIdList);
		} else {
			return -1;
		}

	}

	@Override
	public List<Playlist> getAllPlaylists(int customerId) {

		List<Playlist> playlistList = pld.getAllPlaylistsByCustomerId(customerId);
		playlistList.forEach(pl -> pl.truncate(true));
		return playlistList;

	}

	@Override
	public Playlist selectPlaylist(int playlistId, int customerId) {

		if (pld.isOwner(playlistId, customerId)) {
			return pld.getPlaylistById(playlistId);
		} else {
			return null;
		}

	}

	@Override
	public List<Track> getPlaylistTracks(int playlistId, int customerId) {

		if (pld.isOwner(playlistId, customerId)) {
			List<Track> trackList = pld.getTracksFromPlaylist(playlistId);
			trackList.forEach(t -> t.truncate(false));
			return trackList;
		} else {
			return null;
		}

	}

}
