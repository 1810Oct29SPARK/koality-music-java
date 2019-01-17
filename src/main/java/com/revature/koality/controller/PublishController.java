package com.revature.koality.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.service.PublishService;
import com.revature.koality.utility.CommonUtility;

@RestController("publishController")
public class PublishController {

	private PublishService publishService;

	public PublishController() {
		super();
	}

	public PublishService getPublishService() {
		return publishService;
	}

	@Autowired
	@Qualifier("publishServiceImpl")
	public void setPublishService(PublishService publishService) {
		this.publishService = publishService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/publish-track")
	public void publishTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Integer id = -1;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int publisherId = jo.getInt("publisherId");
			String trackName = jo.getString("trackName");
			String genre = jo.getString("genre");
			String composer = jo.getString("composer");
			String artist = jo.getString("artist");
			int trackLength = jo.getInt("trackLength");
			float unitPrice = jo.getFloat("unitPrice");
			String audioType = jo.getString("audioType");
			byte[] audioData = CommonUtility.decodeBlobUrl(jo.getString("audioData"));

			id = publishService.publishTrack(publisherId, trackName, genre, composer, artist, trackLength, unitPrice,
					audioType, audioData);
			if (id != -1) {
				status = 200;
			} else {
				status = 400;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		response.setStatus(status);
		response.getWriter().write(id.toString());

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/publish-album")
	public void publishAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Integer id = -1;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int publisherId = jo.getInt("publisherId");
			String albumName = jo.getString("albumName");
			String genre = jo.getString("genre");
			float unitPrice = jo.getFloat("unitPrice");
			String imageType = jo.getString("imageType");
			byte[] imageData = CommonUtility.decodeBlobUrl(jo.getString("imageData"));

			List<Integer> trackIdList = new ArrayList<>();
			JSONArray trackIdArray = jo.getJSONArray("trackIdList");
			Iterator<Object> iter = trackIdArray.iterator();
			while (iter.hasNext()) {
				trackIdList.add(Integer.parseInt(iter.next().toString()));
			}

			id = publishService.publishAlbum(publisherId, albumName, genre, unitPrice, imageType, imageData,
					trackIdList);
			if (id != -1) {
				status = 200;
			} else {
				status = 400;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		response.setStatus(status);
		response.getWriter().write(id.toString());

	}

}
