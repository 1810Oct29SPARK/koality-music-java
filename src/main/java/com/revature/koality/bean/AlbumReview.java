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
@Table(name = "ALBUM_REVIEW")
public class AlbumReview implements Serializable {

	private static final long serialVersionUID = 1L;

	private int albumReviewId;
	private ReviewContent reviewContent;
	private Album album;
	private Customer customer;

	public AlbumReview() {
		super();
	}

	public AlbumReview(ReviewContent reviewContent) {
		super();
		this.reviewContent = reviewContent;
	}

	public AlbumReview(ReviewContent reviewContent, Album album, Customer customer) {
		super();
		this.reviewContent = reviewContent;
		this.album = album;
		this.customer = customer;
	}

	public AlbumReview(int albumReviewId, ReviewContent reviewContent, Album album, Customer customer) {
		super();
		this.albumReviewId = albumReviewId;
		this.reviewContent = reviewContent;
		this.album = album;
		this.customer = customer;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "albumReviewIdGen")
	@SequenceGenerator(name = "albumReviewIdGen", sequenceName = "ALBUM_REVIEW_ID_SEQ", allocationSize = 1)
	@Column(name = "ALBUM_REVIEW_ID")
	public int getAlbumReviewId() {
		return albumReviewId;
	}

	public void setAlbumReviewId(int albumReviewId) {
		this.albumReviewId = albumReviewId;
	}

	@Embedded
	public ReviewContent getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(ReviewContent reviewContent) {
		this.reviewContent = reviewContent;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ALBUM_ID")
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
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
		return "AlbumReview [albumReviewId=" + albumReviewId + ", reviewContent=" + reviewContent + "]";
	}

	public void truncate() {
		if (this.album != null) {
			try {
				this.album.truncate(true);
			} catch (LazyInitializationException e) {
				this.album = null;
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
