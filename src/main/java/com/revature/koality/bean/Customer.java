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

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private int customerId;
	private CustomerDetail customerDetail;
	private Image image;
	private CustomerCredentials customerCredentials;
	private List<Publisher> publisherList;
	// more

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

	public Customer(int customerId, CustomerDetail customerDetail, Image image,
			CustomerCredentials customerCredentials) {
		super();
		this.customerId = customerId;
		this.customerDetail = customerDetail;
		this.image = image;
		this.customerCredentials = customerCredentials;
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
	@JoinTable(
			name = "PUBLISHER_CUSTOMER",
			joinColumns = {
					@JoinColumn(name = "CUSTOMER_ID")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "PUBLISHER_ID")
			}
			)
	public List<Publisher> getPublisherList() {
		return publisherList;
	}

	public void setPublisherList(List<Publisher> publisherList) {
		this.publisherList = publisherList;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerDetail=" + customerDetail + "]";
	}

}
