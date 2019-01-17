package com.revature.koality.dao;

import java.util.List;

import com.revature.koality.bean.Playlist;
import com.revature.koality.bean.Track;

public interface PlaylistDAO {

	int addPlaylist(String playlistName, int customerId);

	Playlist getPlaylistById(int playlistId);

	List<Track> getTracksFromPlaylist(int playlistId);

	List<Playlist> getAllPlaylistsByCustomerId(int customerId);

	int addTracksToPlaylist(int playlistId, List<Integer> trackIdList);

	int removeTracksFromPlaylist(int playlistId, List<Integer> trackIdList);

	boolean deletePlaylist(int playlistId);

	boolean isOwner(int playlistId, int customerId);

}
