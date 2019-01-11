package com.revature.koality.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.revature.koality.bean.Customer;
import com.revature.koality.bean.Playlist;
import com.revature.koality.bean.Track;
import com.revature.koality.utility.HibernateUtility;

@Repository("playlistDAOImpl")
public class PlaylistDAOImpl implements PlaylistDAO {

	private SessionFactory sessionFactory;

	public PlaylistDAOImpl() {
		this.sessionFactory = HibernateUtility.getMainSessionFactory();
	}

	public PlaylistDAOImpl(boolean isTest) {
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
	public int addPlaylist(String playlistName, int customerId) {

		int id = -1;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Playlist playlist = new Playlist(playlistName);
				playlist.setCustomer(session.load(Customer.class, customerId));
				id = (int) session.save(playlist);
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
	public Playlist getPlaylistById(int playlistId) {

		Playlist playlist = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				playlist = session.get(Playlist.class, playlistId);
				playlist.loadTrackUrls();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return playlist;

	}

	@Override
	public List<Playlist> getAllPlaylistsByCustomerId(int customerId) {

		List<Playlist> playlistList = null;
		Session session = null;

		if (this.sessionFactory != null) {
			try {
				String hql = "FROM Playlist pl WHERE pl.customer.customerId = :customerId";
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				playlistList = session.createQuery(hql, Playlist.class).setParameter("customerId", customerId)
						.getResultList();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return playlistList;

	}

	@Override
	public int addTracksToPlaylist(int playlistId, List<Integer> trackIdList) {

		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Playlist playlist = session.get(Playlist.class, playlistId);

				for (int ti : trackIdList) {
					playlist.getTrackList().add(session.load(Track.class, ti));
				}
				session.getTransaction().commit();
				return trackIdList.size();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return -1;

	}

	@Override
	public int removeTracksFromPlaylist(int playlistId, List<Integer> trackIdList) {

		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Playlist playlist = session.get(Playlist.class, playlistId);
				playlist.getTrackList().removeIf(t -> trackIdList.indexOf(t.getTrackId()) != -1);
				session.getTransaction().commit();
				return trackIdList.size();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			} finally {
				session.close();
			}
		}

		return -1;

	}

	@Override
	public boolean deletePlaylist(int playlistId) {

		Session session = null;

		if (this.sessionFactory != null) {
			try {
				session = this.sessionFactory.getCurrentSession();
				session.beginTransaction();
				Playlist playlist = session.load(Playlist.class, playlistId);
				session.delete(playlist);
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
