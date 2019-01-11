package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Track;

public interface PlayMusicService {

	List<Track> getPurchasedTracks(int customerId);

	Track getTrack(int trackId);

}
