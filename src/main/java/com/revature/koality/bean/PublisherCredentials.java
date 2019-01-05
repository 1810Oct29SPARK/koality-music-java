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
@Table(name = "PUBLISHER_CREDENTIALS")
public class PublisherCredentials implements Serializable {

	private static final long serialVersionUID = 1L;

	private int publisherCredentialsId;
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

	public PublisherCredentials(int publisherCredentialsId, String username, String hashSalt, String passwordHash) {
		super();
		this.publisherCredentialsId = publisherCredentialsId;
		this.username = username;
		this.hashSalt = hashSalt;
		this.passwordHash = passwordHash;
	}

	public PublisherCredentials(int publisherCredentialsId, String username, String hashSalt, String passwordHash,
			Publisher publisher) {
		super();
		this.publisherCredentialsId = publisherCredentialsId;
		this.username = username;
		this.hashSalt = hashSalt;
		this.passwordHash = passwordHash;
		this.publisher = publisher;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisherCredIdGen")
	@SequenceGenerator(name = "publisherCredIdGen", sequenceName = "PUBLISHER_CREDENTIALS_ID_SEQ", allocationSize = 1)
	@Column(name = "PUBLISHER_CREDENTIALS_ID")
	public int getPublisherCredentialsId() {
		return publisherCredentialsId;
	}

	public void setPublisherCredentialsId(int publisherCredentialsId) {
		this.publisherCredentialsId = publisherCredentialsId;
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
	@JoinColumn(name = "PUBLISHER_CREDENTIALS_ID")
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
