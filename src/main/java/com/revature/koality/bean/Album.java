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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.LazyInitializationException;

@Entity
@Table(name = "ALBUM")
public class Album implements Serializable {

	private static final long serialVersionUID = 1L;

	private int albumId;
	private String albumName;
	private String genre;
	private float unitPrice;
	private Image image;
	private Publisher publisher;
	private List<Track> trackList;

	public Album() {
		super();
	}

	public Album(String albumName, String genre, float unitPrice) {
		super();
		this.albumName = albumName;
		this.genre = genre;
		this.unitPrice = unitPrice;
	}

	public Album(String albumName, String genre, float unitPrice, Image image) {
		super();
		this.albumName = albumName;
		this.genre = genre;
		this.unitPrice = unitPrice;
		this.image = image;
	}

	public Album(String albumName, String genre, float unitPrice, Image image, Publisher publisher) {
		super();
		this.albumName = albumName;
		this.genre = genre;
		this.unitPrice = unitPrice;
		this.image = image;
		this.publisher = publisher;
	}

	public Album(String albumName, String genre, float unitPrice, Image image, Publisher publisher,
			List<Track> trackList) {
		super();
		this.albumName = albumName;
		this.genre = genre;
		this.unitPrice = unitPrice;
		this.image = image;
		this.publisher = publisher;
		this.trackList = trackList;
	}

	public Album(int albumId, String albumName, String genre, float unitPrice, Image image, Publisher publisher,
			List<Track> trackList) {
		super();
		this.albumId = albumId;
		this.albumName = albumName;
		this.genre = genre;
		this.unitPrice = unitPrice;
		this.image = image;
		this.publisher = publisher;
		this.trackList = trackList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "albumIdGen")
	@SequenceGenerator(name = "albumIdGen", sequenceName = "ALBUM_ID_SEQ", allocationSize = 1)
	@Column(name = "ALBUM_ID")
	public int getAlbumId() {
		return albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	@Column(name = "ALBUM_NAME")
	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	@Column(name = "GENRE")
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Column(name = "UNIT_PRICE")
	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "IMAGE_ID")
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PUBLISHER_ID")
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "TRACK_ALBUM", joinColumns = { @JoinColumn(name = "ALBUM_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "TRACK_ID") })
	public List<Track> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<Track> trackList) {
		this.trackList = trackList;
	}

	@Override
	public String toString() {
		return "Album [albumId=" + albumId + ", albumName=" + albumName + "]";
	}

	public void truncate(boolean all) {
		if (this.publisher != null) {
			try {
				this.publisher.truncate(true);
			} catch (LazyInitializationException e) {
				this.publisher = null;
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
