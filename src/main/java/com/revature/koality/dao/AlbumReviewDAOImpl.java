package com.revature.koality.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.revature.koality.bean.Album;
import com.revature.koality.bean.AlbumReview;
import com.revature.koality.bean.Customer;
import com.revature.koality.bean.ReviewContent;
import com.revature.koality.utility.HibernateUtility;

@Repository("albumReviewDAOImpl")
public class AlbumReviewDAOImpl implements AlbumReviewDAO {

	private SessionFactory sessionFactory;

	public AlbumReviewDAOImpl() {
		this.sessionFactory = HibernateUtility.getMainSessionFactory();
	}

	public AlbumReviewDAOImpl(boolean isTest) {
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
	public int addAlbumReview(ReviewContent reviewContent, int albumId, int customerId) {

		int id = -1;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				AlbumReview albumReview = new AlbumReview(reviewContent);
				albumReview.setAlbum(session.load(Album.class, albumId));
				albumReview.setCustomer(session.load(Customer.class, customerId));
				id = (int) session.save(albumReview);
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
	public AlbumReview getAlbumReviewById(int albumReviewId) {

		AlbumReview albumReview = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				albumReview = session.get(AlbumReview.class, albumReviewId);
				Hibernate.initialize(albumReview.getAlbum());
				Hibernate.initialize(albumReview.getCustomer());
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return albumReview;

	}

	@Override
	public AlbumReview getAlbumReviewByAlbumIdAndCustomerId(int albumId, int customerId) {

		AlbumReview albumReview = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM AlbumReview ar WHERE ar.album.albumId = :albumId AND ar.customer.customerId = :customerId";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				albumReview = session.createQuery(hql, AlbumReview.class).setParameter("albumId", albumId)
						.setParameter("customerId", customerId).getSingleResult();
				Hibernate.initialize(albumReview.getAlbum());
				Hibernate.initialize(albumReview.getCustomer());
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return albumReview;

	}

	@Override
	public boolean deleteAlbumReview(int albumReviewId) {

		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				AlbumReview albumReview = session.load(AlbumReview.class, albumReviewId);
				session.delete(albumReview);
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
	public boolean isOwner(int albumReviewId, int customerId) {

		Session session = null;
		boolean owner = false;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				AlbumReview albumReview = session.get(AlbumReview.class, albumReviewId);
				if (albumReview.getCustomer().getCustomerId() == customerId) {
					owner = true;
				}
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return owner;

	}

}
