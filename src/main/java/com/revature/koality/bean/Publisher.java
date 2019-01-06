package com.revature.koality.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.revature.koality.utility.CommonUtility;

@Entity
@Table(name = "PUBLISHER")
public class Publisher implements Serializable {

	private int publisherId;
	private PublisherDetail publisherDetail;
	private Image image;
	private PublisherCredentials publisherCredentials;
	private List<Customer> customerList;
	private String imageUrl;

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

	public Publisher(int publisherId, PublisherDetail publisherDetail, Image image,
			PublisherCredentials publisherCredentials, List<Customer> customerList) {
		super();
		this.publisherId = publisherId;
		this.publisherDetail = publisherDetail;
		this.image = image;
		this.publisherCredentials = publisherCredentials;
		this.customerList = customerList;
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

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "PUBLISHER_CREDENTIALS_ID")
	public PublisherCredentials getPublisherCredentials() {
		return publisherCredentials;
	}

	public void setPublisherCredentials(PublisherCredentials publisherCredentials) {
		this.publisherCredentials = publisherCredentials;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "publisherList")
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	@Transient
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Publisher [publisherId=" + publisherId + ", publisherDetail=" + publisherDetail + "]";
	}

	public void truncate(boolean all) {
		this.customerList = null;
		this.image = null;
		this.publisherCredentials = null;
		if (all) {
			this.imageUrl = null;
		}
	}

	public void loadImageUrl() {
		if (this.image != null) {
			this.imageUrl = CommonUtility.encodeToBlobUrl(this.image.getImageType(), this.image.getImageData(), false);
		}
	}

}
