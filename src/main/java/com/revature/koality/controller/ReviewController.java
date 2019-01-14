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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.TrackReview;
import com.revature.koality.service.DisplayService;
import com.revature.koality.service.PostReviewService;
import com.revature.koality.utility.CommonUtility;

@RestController("reviewController")
public class ReviewController {

	private DisplayService displayService;
	private PostReviewService postReviewService;

	public ReviewController() {
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

	public PostReviewService getPostReviewService() {
		return postReviewService;
	}

	@Autowired
	@Qualifier("postReviewServiceImpl")
	public void setPostReviewService(PostReviewService postReviewService) {
		this.postReviewService = postReviewService;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/reviews-track/{trackId}")
	public void viewTrackReviews(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<TrackReview> trackReviewList = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int publisherId = Integer.parseInt(session.getAttribute("publisherId").toString());
				String uri = request.getRequestURI();
				int trackId = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));

				trackReviewList = displayService.viewTrackReviews(trackId, publisherId);
				if (trackReviewList == null) {
					status = 401;
				} else if (trackReviewList.isEmpty()) {
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
		response.getWriter().write(CommonUtility.toJsonStringJackson(trackReviewList));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/reviews-album/{albumId}")
	public void viewAlbumReviews(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		List<AlbumReview> albumReviewList = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int publisherId = Integer.parseInt(session.getAttribute("publisherId").toString());
				String uri = request.getRequestURI();
				int albumId = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));

				albumReviewList = displayService.viewAlbumReviews(albumId, publisherId);
				if (albumReviewList == null) {
					status = 401;
				} else if (albumReviewList.isEmpty()) {
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
		response.getWriter().write(CommonUtility.toJsonStringJackson(albumReviewList));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/track-review/{trackId}")
	public void getTrackReview(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		TrackReview trackReview = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());
				String uri = request.getRequestURI();
				int trackId = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));

				trackReview = postReviewService.viewPostedTrackReview(trackId, customerId);
				if (trackReview != null) {
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
		response.getWriter().write(CommonUtility.toJsonStringJackson(trackReview));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/album-review/{albumId}")
	public void getAlbumReview(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		AlbumReview albumReview = null;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());
				String uri = request.getRequestURI();
				int albumId = Integer.parseInt(uri.substring(uri.lastIndexOf('/') + 1));

				albumReview = postReviewService.viewPostedAlbumReview(albumId, customerId);
				if (albumReview != null) {
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
		response.getWriter().write(CommonUtility.toJsonStringJackson(albumReview));

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/track-review")
	public void postTrackReview(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Integer id = -1;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				int trackId = jo.getInt("trackId");
				int rating = jo.getInt("rating");
				String reviewComment = jo.getString("reviewComment");

				id = postReviewService.postTrackReview(rating, reviewComment, trackId, customerId);
				if (id == -1) {
					status = 400;
				} else if (id == 0) {
					status = 401;
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
		response.getWriter().write(id.toString());

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/album-review")
	public void postAlbumReview(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int status = 418;
		Integer id = -1;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				int albumId = jo.getInt("albumId");
				int rating = jo.getInt("rating");
				String reviewComment = jo.getString("reviewComment");

				id = postReviewService.postAlbumReview(rating, reviewComment, albumId, customerId);
				if (id == -1) {
					status = 400;
				} else if (id == 0) {
					status = 401;
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
		response.getWriter().write(id.toString());

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/track-review")
	public void deleteTrackReview(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				int trackReviewId = jo.getInt("trackReviewId");
				if (postReviewService.deleteTrackReview(trackReviewId, customerId)) {
					status = 200;
				} else {
					status = 401;
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
	@DeleteMapping("/album-review")
	public void deleteAlbumReview(HttpServletRequest request, HttpServletResponse response) {

		int status = 418;

		HttpSession session = request.getSession(false);
		if (session != null) {
			try (BufferedReader br = request.getReader()) {
				int customerId = Integer.parseInt(session.getAttribute("customerId").toString());

				String requestBody = CommonUtility.readRequest(br);
				JSONObject jo = new JSONObject(requestBody);

				int albumReviewId = jo.getInt("albumReviewId");
				if (postReviewService.deleteAlbumReview(albumReviewId, customerId)) {
					status = 200;
				} else {
					status = 401;
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
