package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Playlist;
import com.revature.koality.bean.Track;

public interface PlaylistService {

	int createPlaylist(String playlistName, int customerId);

	boolean deletePlaylist(int playlistId, int customerId);

	int addTracksToPlaylist(int playlistId, List<Integer> trackIdList, int customerId);

	int removeTracksFromPlaylist(int playlistId, List<Integer> trackIdList, int customerId);

	List<Playlist> getAllPlaylists(int customerId);

	Playlist selectPlaylist(int playlistId, int customerId);

	List<Track> getPlaylistTracks(int playlistId, int customerId);

}
