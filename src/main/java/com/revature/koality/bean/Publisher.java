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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PUBLISHER")
public class Publisher implements Serializable {

	private int publisherId;
	private PublisherDetail publisherDetail;
	private Image image;
	private PublisherCredentials publisherCredentials;

	private static final long serialVersionUID = 1L;

	public Publisher() {
		super();
	}

	public Publisher(PublisherDetail publisherDetail) {
		super();
		this.publisherDetail = publisherDetail;
	}

	public Publisher(PublisherDetail publisherDetail, Image image) {
		super();
		this.publisherDetail = publisherDetail;
		this.image = image;
	}

	public Publisher(PublisherDetail publisherDetail, Image image, PublisherCredentials publisherCredentials) {
		super();
		this.publisherDetail = publisherDetail;
		this.image = image;
		this.publisherCredentials = publisherCredentials;
	}

	public Publisher(int publisherId, PublisherDetail publisherDetail, Image image,
			PublisherCredentials publisherCredentials) {
		super();
		this.publisherId = publisherId;
		this.publisherDetail = publisherDetail;
		this.image = image;
		this.publisherCredentials = publisherCredentials;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisherIdGen")
	@SequenceGenerator(name = "publisherIdGen", sequenceName = "PUBLISHER_ID_SEQ", allocationSize = 1)
	@Column(name = "PUBLISHER_ID")
	public int getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}

	@Embedded
	public PublisherDetail getPublisherDetail() {
		return publisherDetail;
	}

	public void setPublisherDetail(PublisherDetail publisherDetail) {
		this.publisherDetail = publisherDetail;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "IMAGE_ID")
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USERNAME")
	public PublisherCredentials getPublisherCredentials() {
		return publisherCredentials;
	}

	public void setPublisherCredentials(PublisherCredentials publisherCredentials) {
		this.publisherCredentials = publisherCredentials;
	}

	@Override
	public String toString() {
		return "Publisher [publisherId=" + publisherId + ", publisherDetail=" + publisherDetail + "]";
	}

}
