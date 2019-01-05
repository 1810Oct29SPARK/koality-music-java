package com.revature.koality.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.LazyInitializationException;

@Entity
@Table(name = "PLAYLIST")
public class Playlist implements Serializable {

	private static final long serialVersionUID = 1L;

	private int playlistId;
	private String playlistName;
	private Customer customer;
	private List<Track> trackList;

	public Playlist() {
		super();
	}

	public Playlist(String playlistName) {
		super();
		this.playlistName = playlistName;
	}

	public Playlist(String playlistName, Customer customer) {
		super();
		this.playlistName = playlistName;
		this.customer = customer;
	}

	public Playlist(String playlistName, Customer customer, List<Track> trackList) {
		super();
		this.playlistName = playlistName;
		this.customer = customer;
		this.trackList = trackList;
	}

	public Playlist(int playlistId, String playlistName, Customer customer, List<Track> trackList) {
		super();
		this.playlistId = playlistId;
		this.playlistName = playlistName;
		this.customer = customer;
		this.trackList = trackList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "playlistIdGen")
	@SequenceGenerator(name = "playlistIdGen", sequenceName = "PLAYLIST_ID_SEQ", allocationSize = 1)
	@Column(name = "PLAYLIST_ID")
	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	@Column(name = "PLAYLIST_NAME")
	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "TRACK_PLAYLIST", joinColumns = { @JoinColumn(name = "PLAYLIST_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "TRACK_ID") })
	public List<Track> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<Track> trackList) {
		this.trackList = trackList;
	}

	@Override
	public String toString() {
		return "Playlist [playlistId=" + playlistId + ", playlistName=" + playlistName + "]";
	}

	public void truncate(boolean all) {
		if (this.customer != null) {
			try {
				this.customer.truncate(true);
			} catch (LazyInitializationException e) {
				this.customer = null;
			}
		}
		try {
			this.trackList.forEach(t -> t.truncate(all));
		} catch (LazyInitializationException e) {
			this.trackList = null;
		}
	}

	public void loadTrackUrls() {
		if (this.trackList != null) {
			this.trackList.forEach(t -> {
				t.loadAudioUrl();
				t.truncate(false);
			});
		}
	}

}
