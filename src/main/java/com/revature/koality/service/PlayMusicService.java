package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Track;

public interface PlayMusicService {

	List<Track> getPurchasedTracks(int customerId);

	List<Album> getPurchasedAlbums(int customerId);

	Track getTrack(int customerId, int trackId);

	Album getAlbum(int customerId, int albumId);

	List<Track> getAlbumTracks(int albumId);

}
