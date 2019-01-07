package com.revature.koality.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE_STOCK")
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	private int imageId;
	private String imageType;
	private byte[] imageData;

	public Image() {
		super();
	}

	public Image(String imageType, byte[] imageData) {
		super();
		this.imageType = imageType;
		this.imageData = imageData;
	}

	public Image(int imageId, String imageType, byte[] imageData) {
		super();
		this.imageId = imageId;
		this.imageType = imageType;
		this.imageData = imageData;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageIdGen")
	@SequenceGenerator(name = "imageIdGen", sequenceName = "IMAGE_ID_SEQ", allocationSize = 1)
	@Column(name = "IMAGE_ID")
	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	@Column(name = "IMAGE_TYPE")
	public String getImageType() {
		return imageType.toUpperCase();
	}

	public void setImageType(String imageType) {
		this.imageType = imageType.toUpperCase();
	}

	@Lob
	@Column(name = "IMAGE_DATA", columnDefinition = "BLOB")
	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", imageType=" + imageType + "]";
	}

}
