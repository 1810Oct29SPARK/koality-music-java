package com.revature.koality.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Repository;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.Track;
import com.revature.koality.utility.HibernateUtility;

@Repository("albumDAOImpl")
public class AlbumDAOImpl implements AlbumDAO {

	private SessionFactory sessionFactory;

	public AlbumDAOImpl() {
		this.sessionFactory = HibernateUtility.getMainSessionFactory();
	}

	public AlbumDAOImpl(boolean isTest) {
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
	public int addAlbum(Album album, int publisherId, List<Integer> trackIdList) {

		int id = -1;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Publisher publisher = session.load(Publisher.class, publisherId);

				List<Track> trackList = new ArrayList<>();
				for (int ti : trackIdList) {
					trackList.add(session.load(Track.class, ti));
				}

				album.setPublisher(publisher);
				album.setTrackList(trackList);
				id = (int) session.save(album);
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
	public Album getAlbumById(int albumId) {

		Album album = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				album = session.get(Album.class, albumId);
				album.loadImageUrl();
				Hibernate.initialize(album.getTrackList());
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return album;

	}

	@Override
	public List<Album> getAllAlbums() {

		List<Album> albumList = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM Album a ORDER BY a.albumId ASC";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				albumList = session.createQuery(hql, Album.class).getResultList();
				albumList.forEach(a -> a.loadImageUrl());
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

	@Override
	public float updateAlbumPrice(int albumId, float unitPrice) {

		float price = -1F;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Album album = session.get(Album.class, albumId);
				album.setUnitPrice(unitPrice);
				price = unitPrice;
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				price = -1F;
			} finally {
				session.close();
			}
		}

		return price;

	}

	@Override
	public List<Track> getAllTracksByAlbumId(int albumId) {

		List<Track> trackList = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Album album = session.get(Album.class, albumId);
				trackList = album.getTrackList();
				Hibernate.initialize(trackList);
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
	public List<AlbumReview> getAllAlbumReviewsByAlbumId(int albumId) {

		List<AlbumReview> albumReviewList = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM AlbumReview ar WHERE ar.album.albumId = :albumId";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				albumReviewList = session.createQuery(hql, AlbumReview.class).setParameter("albumId", albumId)
						.getResultList();
				albumReviewList.forEach(ar -> ar.setCustomer((Customer) ((HibernateProxy) ar.getCustomer())
						.getHibernateLazyInitializer().getImplementation()));
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return albumReviewList;

	}

	@Override
	public int getAlbumPurchaseCount(int albumId) {

		int count = -1;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String sql = "SELECT COUNT(1) FROM CUSTOMER_ALBUM\r\n" + "WHERE ALBUM_ID = " + albumId;
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				count = ((BigDecimal) session.createNativeQuery(sql).getSingleResult()).intValue();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				count = -1;
			} finally {
				session.close();
			}
		}

		return count;

	}

	@Override
	public boolean hasAccessAsPublisher(int albumId, int publisherId) {

		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM Album a WHERE a.albumId = :albumId AND a.publisher.publisherId = :publisherId";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				List<Album> albumList = session.createQuery(hql, Album.class).setParameter("albumId", albumId)
						.setParameter("publisherId", publisherId).getResultList();
				session.getTransaction().commit();
				if (!albumList.isEmpty()) {
					return true;
				}
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
	public boolean hasAccessAsCustomer(int albumId, int customerId) {

		boolean hasAccess = false;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Customer customer = session.get(Customer.class, customerId);
				Album album = session.get(Album.class, albumId);
				if (customer.getAlbumList().contains(album)) {
					hasAccess = true;
				}
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return hasAccess;

	}

}
