package com.revature.koality.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("inventoryController")
public class InventoryController {

	// SERVICE DECLARATION

	public InventoryController() {
		super();
	}

	// SERVICE SETTER

	@GetMapping("/published-tracks")
	public void getPublishedTracks(HttpServletRequest request, HttpServletResponse response) {

		//

	}

	@GetMapping("/published-albums")
	public void getPublishedAlbums(HttpServletRequest request, HttpServletResponse response) {

		//

	}

	@GetMapping("/purchased-tracks")
	public void getPurchasedTracks(HttpServletRequest request, HttpServletResponse response) {

		//

	}

	@GetMapping("/purchased-tracks")
	public void getPurchasedAlbums(HttpServletRequest request, HttpServletResponse response) {

		//

	}

}
