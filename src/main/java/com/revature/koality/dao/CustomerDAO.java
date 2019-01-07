package com.revature.koality.dao;

import java.util.List;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Customer;
import com.revature.koality.bean.CustomerCredentials;
import com.revature.koality.bean.CustomerDetail;
import com.revature.koality.bean.Image;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.Track;

public interface CustomerDAO {

	int addCustomer(Customer customer);

	CustomerCredentials getCustomerCredentialsByUsername(String username);

	Customer getCustomerById(int customerId);

	List<Customer> getAllCustomers();

	boolean updateCustomerDetail(int customerId, CustomerDetail customerDetail);

	boolean updateCustomerImage(int customerId, Image image);

	boolean updateCustomerCredentials(int customerId, CustomerCredentials customerCredentials);

	List<Publisher> getAllSubscribeeByCustomerId(int customerId);

	List<Track> getAllTracksByCustomerId(int customerId);

	List<Album> getAllAlbumsByCustomerId(int customerId);

	boolean subscribeToPublisher(int customerId, int publisherId);

	boolean unsubscribeFromPublisher(int customerId, int publisherId);

	boolean purchaseTrack(int customerId, int trackId);

	boolean purchaseAlbum(int customerId, int albumId);

}
