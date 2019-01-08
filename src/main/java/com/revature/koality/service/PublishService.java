package com.revature.koality.service;

import java.util.List;

public interface PublishService {

	int publishTrack(int publisherId, String trackName, String genre, String composer, String artist, int trackLength,
			float unitPrice, String audioType, byte[] audioData);

	int publishAlbum(int publisherId, String albumName, String genre, float unitPrice, String imageType,
			byte[] imageData, List<Integer> trackIdList);

}
