package com.revature.koality.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Track;
import com.revature.koality.service.DisplayService;
import com.revature.koality.service.PlayMusicService;
import com.revature.koality.utility.CommonUtility;

@RestController("inventoryController")
public class InventoryController {

	private DisplayService displayService;
	private PlayMusicService playMusicService;

	public InventoryController() {
		super();
	}

	public DisplayService getDisplayService() {
		return displayService;
	}

	@Autowired
	@Qualifier("displayServiceImpl")
	public void setDisplayService(DisplayService displayService) {
		this.displayService = displayService;
	}

	public PlayMusicService getPlayMusicService() {
		return playMusicService;
	}

	@Autowired
	@Qualifier("playMusicServiceImpl")
	public void setPlayMusicService(PlayMusicService playMusicService) {
		this.playMusicService = playMusicService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/published-tracks")
	public void getPublishedTracks(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Track> trackList = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int publisherId = Integer.parseInt(session.getAttribute("publisherId").toString());

				trackList = displayService.getPublisherTracks(publisherId);
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
	@GetMapping("/published-albums")
	public void getPublishedAlbums(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Album> albumList = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int publisherId = Integer.parseInt(session.getAttribute("publisherId").toString());

				albumList = displayService.getPublisherAlbums(publisherId);
				if (albumList == null) {
					status = 401;
				} else if (albumList.isEmpty()) {
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
		response.getWriter().write(CommonUtility.toJsonStringJackson(albumList));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/purchased-tracks")
	public void getPurchasedTracks(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Track> trackList = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				trackList = playMusicService.getPurchasedTracks(customerId);
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
	@GetMapping("/purchased-albums")
	public void getPurchasedAlbums(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Album> albumList = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				albumList = playMusicService.getPurchasedAlbums(customerId);
				if (albumList == null) {
					status = 401;
				} else if (albumList.isEmpty()) {
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
		response.getWriter().write(CommonUtility.toJsonStringJackson(albumList));

	}

}
