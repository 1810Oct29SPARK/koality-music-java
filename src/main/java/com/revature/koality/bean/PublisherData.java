package com.revature.koality.bean;

public class PublisherData {

	private int numberOfTracksSold;
	private int numberOfAlbumsSold;
	private int numberOfSubscribers;

	public PublisherData() {
		super();
	}

	public PublisherData(int numberOfTracksSold, int numberOfAlbumsSold, int numberOfSubscribers) {
		super();
		this.numberOfTracksSold = numberOfTracksSold;
		this.numberOfAlbumsSold = numberOfAlbumsSold;
		this.numberOfSubscribers = numberOfSubscribers;
	}

	public int getNumberOfTracksSold() {
		return numberOfTracksSold;
	}

	public void setNumberOfTracksSold(int numberOfTracksSold) {
		this.numberOfTracksSold = numberOfTracksSold;
	}

	public int getNumberOfAlbumsSold() {
		return numberOfAlbumsSold;
	}

	public void setNumberOfAlbumsSold(int numberOfAlbumsSold) {
		this.numberOfAlbumsSold = numberOfAlbumsSold;
	}

	public int getNumberOfSubscribers() {
		return numberOfSubscribers;
	}

	public void setNumberOfSubscribers(int numberOfSubscribers) {
		this.numberOfSubscribers = numberOfSubscribers;
	}

	@Override
	public String toString() {
		return "PublisherData [numberOfTracksSold=" + numberOfTracksSold + ", numberOfAlbumsSold=" + numberOfAlbumsSold
				+ ", numberOfSubscribers=" + numberOfSubscribers + "]";
	}

}
