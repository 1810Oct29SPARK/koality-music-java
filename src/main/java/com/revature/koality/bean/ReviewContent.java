package com.revature.koality.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReviewContent implements Serializable {

	private static final long serialVersionUID = 1L;

	private int rating;
	private String reviewComment;

	public ReviewContent() {
		super();
	}

	public ReviewContent(int rating, String reviewComment) {
		super();
		this.rating = rating;
		this.reviewComment = reviewComment;
	}

	@Column(name = "RATING")
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Column(name = "REVIEW_COMMENT")
	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	@Override
	public String toString() {
		return "ReviewContent [rating=" + rating + ", reviewComment=" + reviewComment + "]";
	}

}
