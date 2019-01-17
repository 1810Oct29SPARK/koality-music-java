package com.revature.koality.service;

import java.util.List;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.Track;
import com.revature.koality.bean.TrackReview;

public interface DisplayService {

	List<Track> getPublisherTracks(int publisherId);

	List<Album> getPublisherAlbums(int publisherId);

	List<TrackReview> viewTrackReviews(int trackId, int publisherId);

	List<AlbumReview> viewAlbumReviews(int albumId, int publisherId);

}
