package com.revature.koality.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.koality.bean.Publisher;
import com.revature.koality.bean.Track;
import com.revature.koality.bean.TrackReview;
import com.revature.koality.utility.HibernateUtility;

public class TrackDAOImpl implements TrackDAO {

	private SessionFactory sessionFactory;

	public TrackDAOImpl() {
		this.sessionFactory = HibernateUtility.getMainSessionFactory();
	}

	public TrackDAOImpl(boolean isTest) {
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
	public int addTrack(Track track, int publisherId) {

		int id = -1;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Publisher publisher = session.load(Publisher.class, publisherId);
				track.setPublisher(publisher);
				id = (int) session.save(track);
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
	public Track getTrackById(int trackId) {

		Track track = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				track = session.get(Track.class, trackId);
				track.loadAudioUrl();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return track;

	}

	@Override
	public List<Track> getAllTracks() {

		List<Track> trackList = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM Track t ORDER BY t.trackId ASC";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				trackList = session.createQuery(hql, Track.class).getResultList();
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
	public float updateTrackPrice(int trackId, float unitPrice) {

		float price = -1F;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Track track = session.get(Track.class, trackId);
				track.setUnitPrice(unitPrice);
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
	public List<TrackReview> getAllTrackReviewsByTrackId(int trackId) {

		List<TrackReview> trackReviewList = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM TrackReview tr WHERE tr.track.trackId = :trackId";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				trackReviewList = session.createQuery(hql, TrackReview.class).setParameter("trackId", trackId)
						.getResultList();
				trackReviewList.forEach(tr -> Hibernate.initialize(tr.getCustomer()));
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return trackReviewList;

	}

	@Override
	public int getTrackPurchaseCount(int trackId) {

		int count = -1;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String sql = "SELECT COUNT(1) FROM CUSTOMER_TRACK\r\n" + "WHERE TRACK_ID = " + trackId;
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

}
