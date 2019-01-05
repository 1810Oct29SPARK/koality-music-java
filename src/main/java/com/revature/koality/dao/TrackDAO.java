package com.revature.koality.dao;

import java.util.List;

import com.revature.koality.bean.Track;
import com.revature.koality.bean.TrackReview;

public interface TrackDAO {

	boolean addTrack(Track track, int publisherId);

	Track getTrackById(int trackId);

	List<Track> getAllTracks();

	boolean updateTrackPrice(int trackId, float unitPrice);

	List<TrackReview> getAllTrackReviewsByTrackId(int trackId);

	int getTrackPurchaseCount(int trackId);

}
