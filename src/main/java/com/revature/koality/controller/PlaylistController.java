package com.revature.koality.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.bean.Playlist;
import com.revature.koality.bean.Track;
import com.revature.koality.service.PlaylistService;
import com.revature.koality.utility.CommonUtility;

@RestController("playlistController")
public class PlaylistController {

	private PlaylistService playlistService;

	public PlaylistController() {
		super();
	}

	public PlaylistService getPlaylistService() {
		return playlistService;
	}

	@Autowired
	@Qualifier("playlistServiceImpl")
	public void setPlaylistService(PlaylistService playlistService) {
		this.playlistService = playlistService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/playlist-add")
	public void createPlaylist(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Integer id = -1;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int customerId = jo.getInt("customerId");
			String playlistName = jo.getString("playlistName");

			id = playlistService.createPlaylist(playlistName, customerId);
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
	@PostMapping("/playlist-append")
	public void addToPlaylist(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Integer count = 0;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int customerId = jo.getInt("customerId");
			int playlistId = jo.getInt("playlistId");
			List<Integer> trackIdList = new ArrayList<>();
			JSONArray trackIdArray = jo.getJSONArray("trackIdList");
			Iterator<Object> iter = trackIdArray.iterator();
			while (iter.hasNext()) {
				trackIdList.add(Integer.parseInt(iter.next().toString()));
			}

			count = playlistService.addTracksToPlaylist(playlistId, trackIdList, customerId);
			if (count == -1) {
				status = 401;
			} else {
				status = 200;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		response.setStatus(status);
		response.getWriter().write(count.toString());

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/playlist-pop")
	public void removeFromPlaylist(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Integer count = 0;

		try (BufferedReader br = request.getReader()) {
			String requestBody = CommonUtility.readRequest(br);
			JSONObject jo = new JSONObject(requestBody);

			int customerId = jo.getInt("customerId");
			int playlistId = jo.getInt("playlistId");
			List<Integer> trackIdList = new ArrayList<>();
			JSONArray trackIdArray = jo.getJSONArray("trackIdList");
			Iterator<Object> iter = trackIdArray.iterator();
			while (iter.hasNext()) {
				trackIdList.add(Integer.parseInt(iter.next().toString()));
			}

			count = playlistService.removeTracksFromPlaylist(playlistId, trackIdList, customerId);
			if (count == -1) {
				status = 401;
			} else {
				status = 200;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		response.setStatus(status);
		response.getWriter().write(count.toString());

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/playlists-all")
	public void viewAllPlaylists(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Playlist> playlistList = null;

		HttpSession session = request.getSession(false);
		int customerId = CommonUtility.getUserIdFromSessionOrBody(session, request, "customerId");

		if (customerId != 0) {
			try {
				playlistList = playlistService.getAllPlaylists(customerId);
				if (playlistList == null || playlistList.isEmpty()) {
					status = 404;
				} else {
					status = 200;
				}
			} catch (Exception e) {
				e.printStackTrace();
				status = 400;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(playlistList));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/playlist/{playlistId}")
	public void viewPlaylistDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Track> trackList = null;

		HttpSession session = request.getSession(false);
		int customerId = CommonUtility.getUserIdFromSessionOrBody(session, request, "customerId");

		if (customerId != 0) {
			try {
				String uri = request.getRequestURI();
				int playlistId = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));

				trackList = playlistService.getPlaylistTracks(playlistId, customerId);
				if (trackList == null) {
					status = 401;
				} else if (trackList.isEmpty()) {
					status = 404;
				} else {
					status = 200;
				}
			} catch (Exception e) {
				e.printStackTrace();
				status = 400;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(trackList));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/playlist-delete/{playlistId}")
	public void deletePlaylist(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		int customerId = CommonUtility.getUserIdFromSessionOrBody(session, request, "customerId");

		if (customerId != 0) {
			try {
				String uri = request.getRequestURI();
				int playlistId = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));

				if (playlistService.deletePlaylist(playlistId, customerId)) {
					status = 200;
				} else {
					status = 400;
				}
			} catch (Exception e) {
				e.printStackTrace();
				status = 400;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);

	}

}
