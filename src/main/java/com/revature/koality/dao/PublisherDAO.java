package com.revature.koality.dao;

import java.util.List;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Image;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.PublisherCredentials;
import com.revature.koality.bean.PublisherData;
import com.revature.koality.bean.PublisherDetail;
import com.revature.koality.bean.Track;

public interface PublisherDAO {

	int addPublisher(Publisher publisher);

	PublisherCredentials getPublisherCredentialsByUsername(String username);

	Publisher getPublisherById(int publisherId);

	List<Publisher> getAllPublishers();

	boolean updatePublisherDetail(int publisherId, PublisherDetail publisherDetail);

	boolean updatePublisherImage(int publisherId, Image image);

	boolean updatePublisherCredentials(int publisherId, PublisherCredentials publisherCredentials);

	List<Customer> getAllSubscribersByPublisherId(int publisherId);

	List<Track> getAllTracksByPublisherId(int publisherId);

	List<Album> getAllAlbumsByPublisherId(int publisherId);

	PublisherData getPublisherDataById(int publisherId);

}
