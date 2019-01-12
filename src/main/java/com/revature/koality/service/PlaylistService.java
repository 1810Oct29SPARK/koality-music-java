package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Playlist;
import com.revature.koality.bean.Track;

public interface PlaylistService {
	
	List<Track> getPurchasedSongs(int customerId); 
	
	int createPlaylist(String playlistName, int customerId); 
	
	boolean deletePlaylist(int playlistId); 
	
	int addTracksToPlaylist(int playlistId, List<Integer> trackIdList); 
	
	int removeTracksFromPlaylist(int playlistId, List<Integer> trackIdList); 
	
	List<Playlist> getAllPlaylists(int customerId);
	
	Playlist selectPlaylist(int playlistId); 
	
}
