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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.revature.koality.utility.CommonUtility;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private int customerId;
	private CustomerDetail customerDetail;
	private Image image;
	private CustomerCredentials customerCredentials;
	private List<Publisher> publisherList;
	private List<Track> trackList;
	private List<Album> albumList;
	private String imageUrl;

	public Customer() {
		super();
	}

	public Customer(CustomerDetail customerDetail) {
		super();
		this.customerDetail = customerDetail;
	}

	public Customer(CustomerDetail customerDetail, Image image) {
		super();
		this.customerDetail = customerDetail;
		this.image = image;
	}

	public Customer(CustomerDetail customerDetail, Image image, CustomerCredentials customerCredentials) {
		super();
		this.customerDetail = customerDetail;
		this.image = image;
		this.customerCredentials = customerCredentials;
	}

	public Customer(CustomerDetail customerDetail, Image image, CustomerCredentials customerCredentials,
			List<Publisher> publisherList, List<Track> trackList, List<Album> albumList) {
		super();
		this.customerDetail = customerDetail;
		this.image = image;
		this.customerCredentials = customerCredentials;
		this.publisherList = publisherList;
		this.trackList = trackList;
		this.albumList = albumList;
	}

	public Customer(int customerId, CustomerDetail customerDetail, Image image, CustomerCredentials customerCredentials,
			List<Publisher> publisherList, List<Track> trackList, List<Album> albumList) {
		super();
		this.customerId = customerId;
		this.customerDetail = customerDetail;
		this.image = image;
		this.customerCredentials = customerCredentials;
		this.publisherList = publisherList;
		this.trackList = trackList;
		this.albumList = albumList;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerIdGen")
	@SequenceGenerator(name = "customerIdGen", sequenceName = "CUSTOMER_ID_SEQ", allocationSize = 1)
	@Column(name = "CUSTOMER_ID")
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Embedded
	public CustomerDetail getCustomerDetail() {
		return customerDetail;
	}

	public void setCustomerDetail(CustomerDetail customerDetail) {
		this.customerDetail = customerDetail;
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
	public CustomerCredentials getCustomerCredentials() {
		return customerCredentials;
	}

	public void setCustomerCredentials(CustomerCredentials customerCredentials) {
		this.customerCredentials = customerCredentials;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "PUBLISHER_CUSTOMER", joinColumns = { @JoinColumn(name = "CUSTOMER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PUBLISHER_ID") })
	public List<Publisher> getPublisherList() {
		return publisherList;
	}

	public void setPublisherList(List<Publisher> publisherList) {
		this.publisherList = publisherList;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "CUSTOMER_TRACK", joinColumns = { @JoinColumn(name = "CUSTOMER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "TRACK_ID") })
	public List<Track> getTrackList() {
		return trackList;
	}

	public void setTrackList(List<Track> trackList) {
		this.trackList = trackList;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "CUSTOMER_ALBUM", joinColumns = { @JoinColumn(name = "CUSTOMER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ALBUM_ID") })
	public List<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
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
		return "Customer [customerId=" + customerId + ", customerDetail=" + customerDetail + "]";
	}

	public void truncate(boolean all) {
		this.publisherList = null;
		this.trackList = null;
		this.albumList = null;
		this.image = null;
		if (all) {
			this.imageUrl = null;
		}
	}

	public void loadImageUrl() {
		if (this.image != null) {
			this.imageUrl = CommonUtility.encodeToBlobUrl(this.image.getImageType(), this.image.getImageData(), false);
			this.image = null;
		}
	}

}
