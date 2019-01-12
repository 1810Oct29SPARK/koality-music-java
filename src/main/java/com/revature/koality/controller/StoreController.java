package com.revature.koality.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Track;
import com.revature.koality.utility.CommonUtility;

@RestController
public class StoreController {

	// SERVICE DECLARATION

	public StoreController() {
		super();
	}

	// SERVICE SETTER

	@GetMapping("store-tracks-all")
	public void displayAllTracks(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Track> trackList = null;

		// SERVICE

		if (trackList != null && !trackList.isEmpty()) {
			status = 200;
		} else {
			status = 404;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(trackList));

	}

	@GetMapping("store-albums-all")
	public void displayAllAlbums(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Album> albumList = null;

		// SERVICE

		if (albumList != null && !albumList.isEmpty()) {
			status = 200;
		} else {
			status = 404;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(albumList));

	}

	@PostMapping("purchase-track")
	public void purchaseTrack(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				int trackId = jo.getInt("trackId");

				// SERVICE
			} catch (Exception e) {
				e.printStackTrace();
				status = 400;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);

	}

	@PostMapping("purchase-album")
	public void purchaseAlbum(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				int albumId = jo.getInt("albumId");

				// SERVICE
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
