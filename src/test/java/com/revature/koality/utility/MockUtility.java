package com.revature.koality.utility;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.Audio;
import com.revature.koality.bean.Customer;
import com.revature.koality.bean.CustomerCredentials;
import com.revature.koality.bean.CustomerDetail;
import com.revature.koality.bean.Image;
import com.revature.koality.bean.Playlist;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.PublisherCredentials;
import com.revature.koality.bean.PublisherDetail;
import com.revature.koality.bean.ReviewContent;
import com.revature.koality.bean.Track;
import com.revature.koality.bean.TrackReview;



/**
 * 
 * Mock object factory class for generating mock beans filled with random data
 * 
 * @author Eddy Soma
 *
 */
public class MockUtility {

	public static Image getMockImage() {

		Image image = new Image();
		image.setImageType(CommonUtility.generateRandomString(3));
		image.setImageData(CommonUtility.generateRandomString(100).getBytes());

		return image;

	}

	public static Audio getMockAudio() {

		Audio audio = new Audio();
		audio.setAudioType(CommonUtility.generateRandomString(3));
		audio.setAudioData(CommonUtility.generateRandomString(100).getBytes());

		return audio;

	}

	public static PublisherDetail getMockPublisherDetail() {

		PublisherDetail publisherDetail = new PublisherDetail();
		publisherDetail.setFirstName(CommonUtility.generateRandomString(6));
		publisherDetail.setLastName(CommonUtility.generateRandomString(6));
		publisherDetail.setEmail(CommonUtility.generateRandomString(16));
		publisherDetail.setCompanyName(CommonUtility.generateRandomString(8));

		return publisherDetail;

	}

	public static PublisherCredentials getMockPublisherCredentials() {

		PublisherCredentials publisherCredentials = new PublisherCredentials();
		publisherCredentials.setUsername(CommonUtility.generateRandomString(6));
		publisherCredentials.setHashSalt(CommonUtility.generateRandomString(4));
		publisherCredentials.setPasswordHash(CommonUtility.digestSHA256(CommonUtility.generateRandomString(8)));

		return publisherCredentials;

	}

	public static Publisher getMockPublisher() {

		Publisher publisher = new Publisher();
		publisher.setPublisherDetail(getMockPublisherDetail());
		publisher.setImage(getMockImage());
		publisher.setPublisherCredentials(getMockPublisherCredentials());

		return publisher;

	}

	public static CustomerDetail getMockCustomerDetail() {

		CustomerDetail customerDetail = new CustomerDetail();
		customerDetail.setFirstName(CommonUtility.generateRandomString(6));
		customerDetail.setLastName(CommonUtility.generateRandomString(6));
		customerDetail.setEmail(CommonUtility.generateRandomString(16));
		customerDetail.setFavoriteGenre(CommonUtility.generateRandomString(6));

		return customerDetail;

	}

	public static CustomerCredentials getMockCustomerCredentials() {

		CustomerCredentials customerCredentials = new CustomerCredentials();
		customerCredentials.setUsername(CommonUtility.generateRandomString(6));
		customerCredentials.setHashSalt(CommonUtility.generateRandomString(4));
		customerCredentials.setPasswordHash(CommonUtility.digestSHA256(CommonUtility.generateRandomString(8)));

		return customerCredentials;

	}

	public static Customer getMockCustomer() {

		Customer customer = new Customer();
		customer.setCustomerDetail(getMockCustomerDetail());
		customer.setImage(getMockImage());
		customer.setCustomerCredentials(getMockCustomerCredentials());

		return customer;

	}

	public static Track getMockTrack() {

		Track track = new Track();
		track.setTrackName(CommonUtility.generateRandomString(6));
		track.setGenre(CommonUtility.generateRandomString(6));
		track.setComposer(CommonUtility.generateRandomString(10));
		track.setArtist(CommonUtility.generateRandomString(10));
		track.setTrackLength(CommonUtility.generateRandomInteger(1000));
		track.setUnitPrice(CommonUtility.generateRandomInteger(10));
		track.setAudio(getMockAudio());

		return track;

	}

	public static Album getMockAlbum() {

		Album album = new Album();
		album.setAlbumName(CommonUtility.generateRandomString(6));
		album.setGenre(CommonUtility.generateRandomString(6));
		album.setUnitPrice(CommonUtility.generateRandomInteger(200));
		album.setImage(getMockImage());

		return album;

	}

	public static ReviewContent getMockReviewContent() {

		ReviewContent reviewContent = new ReviewContent();
		reviewContent.setRating(CommonUtility.generateRandomInteger(10));
		reviewContent.setReviewComment(CommonUtility.generateRandomString(50));

		return reviewContent;

	}

	public static TrackReview getMockTrackReview() {

		TrackReview trackReview = new TrackReview();
		trackReview.setReviewContent(getMockReviewContent());

		return trackReview;

	}

	public static AlbumReview getMockAlbumReview() {

		AlbumReview albumReview = new AlbumReview();
		albumReview.setReviewContent(getMockReviewContent());

		return albumReview;

	}

	public static Playlist getMockPlaylist() {

		Playlist playlist = new Playlist();
		playlist.setPlaylistName(CommonUtility.generateRandomString(6));

		return playlist;

	}

}
