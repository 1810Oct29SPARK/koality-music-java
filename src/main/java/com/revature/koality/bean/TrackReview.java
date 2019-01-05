package com.revature.koality.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.LazyInitializationException;

@Entity
@Table(name = "TRACK_REVIEW")
public class TrackReview implements Serializable {

	private static final long serialVersionUID = 1L;

	private int trackReviewId;
	private ReviewContent reviewContent;
	private Track track;
	private Customer customer;

	public TrackReview() {
		super();
	}

	public TrackReview(ReviewContent reviewContent) {
		super();
		this.reviewContent = reviewContent;
	}

	public TrackReview(ReviewContent reviewContent, Track track, Customer customer) {
		super();
		this.reviewContent = reviewContent;
		this.track = track;
		this.customer = customer;
	}

	public TrackReview(int trackReviewId, ReviewContent reviewContent, Track track, Customer customer) {
		super();
		this.trackReviewId = trackReviewId;
		this.reviewContent = reviewContent;
		this.track = track;
		this.customer = customer;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trackReviewIdGen")
	@SequenceGenerator(name = "trackReviewIdGen", sequenceName = "TRACK_REVIEW_ID_SEQ", allocationSize = 1)
	@Column(name = "TRACK_REVIEW_ID")
	public int getTrackReviewId() {
		return trackReviewId;
	}

	public void setTrackReviewId(int trackReviewId) {
		this.trackReviewId = trackReviewId;
	}

	@Embedded
	public ReviewContent getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(ReviewContent reviewContent) {
		this.reviewContent = reviewContent;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "TRACK_ID")
	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CUSTOMER_ID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "TrackReview [trackReviewId=" + trackReviewId + ", reviewContent=" + reviewContent + "]";
	}

	public void truncate() {
		if (this.track != null) {
			try {
				this.track.truncate(true);
			} catch (LazyInitializationException e) {
				this.track = null;
			}
		}
		if (this.customer != null) {
			try {
				this.customer.truncate(true);
			} catch (LazyInitializationException e) {
				this.customer = null;
			}
		}
	}

}
