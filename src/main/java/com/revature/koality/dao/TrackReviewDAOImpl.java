package com.revature.koality.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.ReviewContent;
import com.revature.koality.bean.Track;
import com.revature.koality.bean.TrackReview;
import com.revature.koality.utility.HibernateUtility;

@Repository("trackReviewDAOImpl")
public class TrackReviewDAOImpl implements TrackReviewDAO {

	private SessionFactory sessionFactory;

	public TrackReviewDAOImpl() {
		this.sessionFactory = HibernateUtility.getMainSessionFactory();
	}

	public TrackReviewDAOImpl(boolean isTest) {
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
	public int addTrackReview(ReviewContent reviewContent, int trackId, int customerId) {

		int id = -1;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				TrackReview trackReview = new TrackReview(reviewContent);
				trackReview.setTrack(session.load(Track.class, trackId));
				trackReview.setCustomer(session.load(Customer.class, customerId));
				id = (int) session.save(trackReview);
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
	public TrackReview getTrackReviewById(int trackReviewId) {

		TrackReview trackReview = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				trackReview = session.get(TrackReview.class, trackReviewId);
				Hibernate.initialize(trackReview.getTrack());
				Hibernate.initialize(trackReview.getCustomer());
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return trackReview;

	}

	@Override
	public TrackReview getTrackReviewByTrackIdAndCustomerId(int trackId, int customerId) {

		TrackReview trackReview = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM TrackReview tr WHERE tr.track.trackId = :trackId AND tr.customer.customerId = :customerId";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				trackReview = session.createQuery(hql, TrackReview.class).setParameter("trackId", trackId)
						.setParameter("customerId", customerId).getSingleResult();
				Hibernate.initialize(trackReview.getTrack());
				Hibernate.initialize(trackReview.getCustomer());
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return trackReview;

	}

	@Override
	public boolean deleteTrackReview(int trackReviewId) {

		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				TrackReview trackReview = session.load(TrackReview.class, trackReviewId);
				session.delete(trackReview);
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

}
