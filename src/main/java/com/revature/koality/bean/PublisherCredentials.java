package com.revature.koality.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PUBLISHER_CREDENTIALS")
public class PublisherCredentials implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String hashSalt;
	private String passwordHash;
	private Publisher publisher;

	public PublisherCredentials() {
		super();
	}

	public PublisherCredentials(String username) {
		super();
		this.username = username;
	}

	public PublisherCredentials(String username, String passwordHash) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
	}

	public PublisherCredentials(String username, String hashSalt, String passwordHash) {
		super();
		this.username = username;
		this.hashSalt = hashSalt;
		this.passwordHash = passwordHash;
	}

	public PublisherCredentials(String username, String hashSalt, String passwordHash, Publisher publisher) {
		super();
		this.username = username;
		this.hashSalt = hashSalt;
		this.passwordHash = passwordHash;
		this.publisher = publisher;
	}

	@Id
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
	@JoinColumn(name = "USERNAME")
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "PublisherCredentials [username=" + username + ", passwordHash=" + passwordHash + "]";
	}

}
