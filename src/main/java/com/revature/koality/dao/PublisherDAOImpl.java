package com.revature.koality.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
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
	public int addPublisher(Publisher publisher) {

		int id = -1;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				id = (int) session.save(publisher);
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				id = -1;
			} finally {
				session.close();
			}
		}

		return id;

	}

	@Override
	public PublisherCredentials getPublisherCredentialsByUsername(String username) {

		PublisherCredentials publisherCredentials = null;
		Session session = null;

		if (this.sessionFactory != null) {
			String hql = "FROM PublisherCredentials pc WHERE pc.username = :username";
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				publisherCredentials = session.createQuery(hql, PublisherCredentials.class)
						.setParameter("username", username).getSingleResult();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return publisherCredentials;

	}

	@Override
	public Publisher getPublisherById(int publisherId) {

		Publisher publisher = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				publisher = session.get(Publisher.class, publisherId);
				publisher.loadImageUrl();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return publisher;

	}

	@Override
	public List<Publisher> getAllPublishers() {

		List<Publisher> publisherList = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM Publisher p ORDER BY p.publisherId ASC";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				publisherList = session.createQuery(hql, Publisher.class).getResultList();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return publisherList;

	}

	@Override
	public boolean updatePublisherDetail(int publisherId, PublisherDetail publisherDetail) {

		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Publisher publisher = session.get(Publisher.class, publisherId);
				publisher.setPublisherDetail(publisherDetail);
				session.getTransaction().commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return false;

	}

	@Override
	public boolean updatePublisherImage(int publisherId, Image image) {

		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Publisher publisher = session.get(Publisher.class, publisherId);
				publisher.getImage().setImageType(image.getImageType());
				publisher.getImage().setImageData(image.getImageData());
				session.getTransaction().commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return false;

	}

	@Override
	public boolean updatePublisherCredentials(int publisherId, PublisherCredentials publisherCredentials) {

		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Publisher publisher = session.get(Publisher.class, publisherId);
				publisher.getPublisherCredentials().setUsername(publisherCredentials.getUsername());
				publisher.getPublisherCredentials().setHashSalt(publisherCredentials.getHashSalt());
				publisher.getPublisherCredentials().setPasswordHash(publisherCredentials.getPasswordHash());
				session.getTransaction().commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return false;

	}

	@Override
	public List<Customer> getAllSubscribersByPublisherId(int publisherId) {

		List<Customer> customerList = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Publisher publisher = session.get(Publisher.class, publisherId);
				customerList = publisher.getCustomerList();
				Hibernate.initialize(customerList);
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return customerList;

	}

	@Override
	public List<Track> getAllTracksByPublisherId(int publisherId) {

		List<Track> trackList = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM Track t WHERE t.publisher.publisherId = :publisherId";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				trackList = session.createQuery(hql, Track.class).setParameter("publisherId", publisherId)
						.getResultList();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return trackList;

	}

	@Override
	public List<Album> getAllAlbumsByPublisherId(int publisherId) {

		List<Album> albumList = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM Album a WHERE a.publisher.publisherId = :publisherId";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				albumList = session.createQuery(hql, Album.class).setParameter("publisherId", publisherId)
						.getResultList();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return albumList;

	}

}
