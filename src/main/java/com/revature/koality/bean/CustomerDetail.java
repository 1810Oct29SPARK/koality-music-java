package com.revature.koality.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CustomerDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String email;
	private String favoriteGenre;

	public CustomerDetail() {
		super();
	}

	public CustomerDetail(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public CustomerDetail(String firstName, String lastName, String email, String favoriteGenre) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.favoriteGenre = favoriteGenre;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FAVORITE_GENRE")
	public String getFavoriteGenre() {
		return favoriteGenre;
	}

	public void setFavoriteGenre(String favoriteGenre) {
		this.favoriteGenre = favoriteGenre;
	}

	@Override
	public String toString() {
		return "CustomerDetail [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", favoriteGenre=" + favoriteGenre + "]";
	}

}
