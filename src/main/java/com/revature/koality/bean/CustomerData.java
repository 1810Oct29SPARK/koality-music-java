package com.revature.koality.bean;

public class CustomerData {

	private int numberOfTracksBought;
	private int numberOfAlbumsBought;
	private int numberOfSubscribees;

	public CustomerData() {
		super();
	}

	public CustomerData(int numberOfTracksBought, int numberOfAlbumsBought, int numberOfSubscribees) {
		super();
		this.numberOfTracksBought = numberOfTracksBought;
		this.numberOfAlbumsBought = numberOfAlbumsBought;
		this.numberOfSubscribees = numberOfSubscribees;
	}

	public int getNumberOfTracksBought() {
		return numberOfTracksBought;
	}

	public void setNumberOfTracksBought(int numberOfTracksBought) {
		this.numberOfTracksBought = numberOfTracksBought;
	}

	public int getNumberOfAlbumsBought() {
		return numberOfAlbumsBought;
	}

	public void setNumberOfAlbumsBought(int numberOfAlbumsBought) {
		this.numberOfAlbumsBought = numberOfAlbumsBought;
	}

	public int getNumberOfSubscribees() {
		return numberOfSubscribees;
	}

	public void setNumberOfSubscribees(int numberOfSubscribees) {
		this.numberOfSubscribees = numberOfSubscribees;
	}

	@Override
	public String toString() {
		return "CustomerData [numberOfTracksBought=" + numberOfTracksBought + ", numberOfAlbumsBought="
				+ numberOfAlbumsBought + ", numberOfSubscribees=" + numberOfSubscribees + "]";
	}

}
