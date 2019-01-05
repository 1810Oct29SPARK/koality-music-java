package com.revature.koality.dao;

import java.util.List;

import com.revature.koality.bean.Playlist;

public interface PlaylistDAO {

	boolean addPlaylist(String playlistName, int customerId);

	Playlist getPlaylistById(int playlistId);

	List<Playlist> getAllPlaylistsByCustomerId(int customerId);

	boolean addTracksToPlaylist(int playlistId, List<Integer> trackIdList);

	boolean removeTracksFromPlaylist(int playlistId, List<Integer> trackIdList);

	boolean deletePlaylist(int playlistId);

}
