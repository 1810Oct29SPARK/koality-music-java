package com.revature.koality.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Image;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.PublisherCredentials;
import com.revature.koality.bean.PublisherDetail;
import com.revature.koality.bean.Track;
import com.revature.koality.utility.HibernateUtility;

public class PublisherDAOImpl implements PublisherDAO {

	private SessionFactory sessionFactory;

	public PublisherDAOImpl() {
		this.sessionFactory = HibernateUtility.getMainSessionFactory();
	}

	public PublisherDAOImpl(boolean isTest) {
		if (isTest) {
			this.sessionFactory = HibernateUtility.getTestSessionFactory();
		} else {
			this.sessionFactory = HibernateUtility.getMainSessionFactory();
		}
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addPublisher(Publisher publisher) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PublisherCredentials getPublisherCredentialsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Publisher getPublisherById(int publisherId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Publisher> getAllPublishers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePublisherDetail(int publisherId, PublisherDetail publisherDetail) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePublisherImage(int publisherId, Image image) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePublisherCredentials(int publisherId, PublisherCredentials publisherCredentials) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Customer> getAllSubscribersByPublisherId(int publisherId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Track> getAllTracksByPublisherId(int publisherId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Album> getAllAlbumsByPublisherId(int publisherId) {
		// TODO Auto-generated method stub
		return null;
	}

}
