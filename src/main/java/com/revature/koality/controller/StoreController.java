package com.revature.koality.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Track;
import com.revature.koality.service.PurchaseService;
import com.revature.koality.utility.CommonUtility;

@RestController("storeController")
public class StoreController {

	private PurchaseService purchaseService;

	public StoreController() {
		super();
	}

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	@Autowired
	@Qualifier("purchaseServiceImpl")
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/store-tracks-all")
	public void displayAllTracks(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Track> trackList = null;

		trackList = purchaseService.viewAllTracks();

		if (trackList != null && !trackList.isEmpty()) {
			status = 200;
		} else {
			status = 404;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(trackList));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/store-albums-all")
	public void displayAllAlbums(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Album> albumList = null;

		albumList = purchaseService.viewAllAlbums();

		if (albumList != null && !albumList.isEmpty()) {
			status = 200;
		} else {
			status = 404;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(albumList));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/purchase-track")
	public void purchaseTrack(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				int trackId = jo.getInt("trackId");

				if (purchaseService.purchaseTrack(customerId, trackId)) {
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

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/purchase-album")
	public void purchaseAlbum(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				int albumId = jo.getInt("albumId");

				if (purchaseService.purchaseAlbum(customerId, albumId)) {
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

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/tracks-recommended/{genre}")
	public void displayRecommendedTracks(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Track> trackList = null;

		try {
			String uri = request.getRequestURI();
			String genre = uri.substring(uri.lastIndexOf('/') + 1);

			trackList = purchaseService.viewRecommendedTracks(genre);
			if (trackList == null) {
				status = 400;
			} else if (trackList.isEmpty()) {
				status = 404;
			} else {
				status = 200;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(trackList));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/albums-recommended/{genre}")
	public void displayRecommendedAlbums(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Album> albumList = null;

		try {
			String uri = request.getRequestURI();
			String genre = uri.substring(uri.lastIndexOf('/') + 1);

			albumList = purchaseService.viewRecommendedAlbums(genre);
			if (albumList == null) {
				status = 400;
			} else if (albumList.isEmpty()) {
				status = 404;
			} else {
				status = 200;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = 400;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(albumList));

	}

}
