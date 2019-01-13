package com.revature.koality.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.bean.Track;
import com.revature.koality.service.PlayMusicService;
import com.revature.koality.utility.CommonUtility;

@RestController("musicController")
public class MusicController {

	private PlayMusicService playMusicService;

	public MusicController() {
		super();
	}

	public PlayMusicService getPlayMusicService() {
		return playMusicService;
	}

	@Autowired
	@Qualifier("playMusicServiceImpl")
	public void setPlayMusicService(PlayMusicService playMusicService) {
		this.playMusicService = playMusicService;
	}

	@GetMapping("/album/{albumId}")
	public void getAlbumTracks(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<Track> trackList = null;

		try {
			String uri = request.getRequestURI();
			int albumId = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));

			trackList = playMusicService.getAlbumTracks(albumId);
			if (trackList == null) {
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

	@GetMapping("/track/{trackId}")
	public void playMusic(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Track track = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());
				String uri = request.getRequestURI();
				int trackId = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));

				track = playMusicService.getTrack(customerId, trackId);
				if (track == null) {
					status = 404;
				} else {
					status = 200;
				}
			} catch (Exception e) {
				status = 400;
			}
		} else {
			status = 440;
		}

		response.setStatus(status);
		response.getWriter().write(CommonUtility.toJsonStringJackson(track));

	}

}
