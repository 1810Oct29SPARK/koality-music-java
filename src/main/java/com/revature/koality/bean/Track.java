package com.revature.koality.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.LazyInitializationException;

import com.revature.koality.utility.CommonUtility;

@Entity
@Table(name = "TRACK")
public class Track implements Serializable {

	private static final long serialVersionUID = 1L;

	private int trackId;
	private String trackName;
	private String genre;
	private String composer;
	private String artist;
	private int trackLength;
	private float unitPrice;
	private Audio audio;
	private Publisher publisher;
	private String audioUrl;

	public Track() {
		super();
	}

	public Track(String trackName, String genre, float unitPrice) {
		super();
		this.trackName = trackName;
		this.genre = genre;
		this.unitPrice = unitPrice;
	}

	public Track(String trackName, String genre, String composer, String artist, int trackLength, float unitPrice) {
		super();
		this.trackName = trackName;
		this.genre = genre;
		this.composer = composer;
		this.artist = artist;
		this.trackLength = trackLength;
		this.unitPrice = unitPrice;
	}

	public Track(String trackName, String genre, String composer, String artist, int trackLength, float unitPrice,
			Audio audio, Publisher publisher) {
		super();
		this.trackName = trackName;
		this.genre = genre;
		this.composer = composer;
		this.artist = artist;
		this.trackLength = trackLength;
		this.unitPrice = unitPrice;
		this.audio = audio;
		this.publisher = publisher;
	}

	public Track(int trackId, String trackName, String genre, String composer, String artist, int trackLength,
			float unitPrice, Audio audio, Publisher publisher) {
		super();
		this.trackId = trackId;
		this.trackName = trackName;
		this.genre = genre;
		this.composer = composer;
		this.artist = artist;
		this.trackLength = trackLength;
		this.unitPrice = unitPrice;
		this.audio = audio;
		this.publisher = publisher;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trackIdGen")
	@SequenceGenerator(name = "trackIdGen", sequenceName = "TRACK_ID_SEQ", allocationSize = 1)
	@Column(name = "TRACK_ID")
	public int getTrackId() {
		return trackId;
	}

	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

	@Column(name = "TRACK_NAME")
	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	@Column(name = "GENRE")
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Column(name = "COMPOSER")
	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	@Column(name = "ARTIST")
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Column(name = "TRACK_LENGTH")
	public int getTrackLength() {
		return trackLength;
	}

	public void setTrackLength(int trackLength) {
		this.trackLength = trackLength;
	}

	@Column(name = "UNIT_PRICE")
	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "AUDIO_ID")
	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PUBLISHER_ID")
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Transient
	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", trackName=" + trackName + "]";
	}

	public void truncate(boolean all) {
		this.audio = null;
		if (this.publisher != null) {
			try {
				this.publisher.truncate(true);
			} catch (LazyInitializationException e) {
				this.publisher = null;
			}
		}
		if (all) {
			this.audioUrl = null;
		}
	}

	public void loadAudioUrl() {
		if (this.audio != null) {
			this.audioUrl = CommonUtility.encodeToBlobUrl(this.audio.getAudioType(), this.audio.getAudioData(), true);
			this.audio = null;
		}
	}

}
