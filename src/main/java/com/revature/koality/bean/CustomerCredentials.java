package com.revature.koality.bean;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "CUSTOMER_CREDENTIALS")
public class CustomerCredentials implements Serializable {

	private static final long serialVersionUID = 1L;

	private int customerCredentialsId;
	private String username;
	private String hashSalt;
	private String passwordHash;
	private Customer customer;

	public CustomerCredentials() {
		super();
	}

	public CustomerCredentials(String username) {
		super();
		this.username = username;
	}

	public CustomerCredentials(String username, String hashSalt, String passwordHash) {
		super();
		this.username = username;
		this.hashSalt = hashSalt;
		this.passwordHash = passwordHash;
	}

	public CustomerCredentials(String username, String hashSalt, String passwordHash, Customer customer) {
		super();
		this.username = username;
		this.hashSalt = hashSalt;
		this.passwordHash = passwordHash;
		this.customer = customer;
	}

	public CustomerCredentials(int customerCredentialsId, String username, String hashSalt, String passwordHash) {
		super();
		this.customerCredentialsId = customerCredentialsId;
		this.username = username;
		this.hashSalt = hashSalt;
		this.passwordHash = passwordHash;
	}

	public CustomerCredentials(int customerCredentialsId, String username, String hashSalt, String passwordHash,
			Customer customer) {
		super();
		this.customerCredentialsId = customerCredentialsId;
		this.username = username;
		this.hashSalt = hashSalt;
		this.passwordHash = passwordHash;
		this.customer = customer;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerCredIdGen")
	@SequenceGenerator(name = "customerCredIdGen", sequenceName = "CUSTOMER_CREDENTIALS_ID_SEQ", allocationSize = 1)
	@Column(name = "CUSTOMER_CREDENTIALS_ID")
	public int getCustomerCredentialsId() {
		return customerCredentialsId;
	}

	public void setCustomerCredentialsId(int customerCredentialsId) {
		this.customerCredentialsId = customerCredentialsId;
	}

	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "HASH_SALT")
	public String getHashSalt() {
		return hashSalt;
	}

	public void setHashSalt(String hashSalt) {
		this.hashSalt = hashSalt;
	}

	@Column(name = "PASSWORD_HASH")
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CUSTOMER_CREDENTIALS_ID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "CustomerCredentials [username=" + username + ", passwordHash=" + passwordHash + "]";
	}

}
