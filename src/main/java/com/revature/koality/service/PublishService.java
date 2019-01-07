package com.revature.koality.service;

public interface PublishService {

	int publishTrack(int id, String trackName, String genre, String composer, String artist, int trackLength,
			float unitPrice, String audioType, byte[] audioData);
}
