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
@Table(name = "AUDIO_STOCK")
public class Audio implements Serializable {

	private static final long serialVersionUID = 1L;

	private int audioId;
	private String audioType;
	private byte[] audioData;

	public Audio() {
		super();
	}

	public Audio(String audioType, byte[] audioData) {
		super();
		this.audioType = audioType;
		this.audioData = audioData;
	}

	public Audio(int audioId, String audioType, byte[] audioData) {
		super();
		this.audioId = audioId;
		this.audioType = audioType;
		this.audioData = audioData;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audioIdGen")
	@SequenceGenerator(name = "audioIdGen", sequenceName = "AUDIO_ID_SEQ", allocationSize = 1)
	@Column(name = "AUDIO_ID")
	public int getAudioId() {
		return audioId;
	}

	public void setAudioId(int audioId) {
		this.audioId = audioId;
	}

	@Column(name = "AUDIO_TYPE")
	public String getAudioType() {
		return audioType.toUpperCase();
	}

	public void setAudioType(String audioType) {
		this.audioType = audioType.toUpperCase();
	}

	@Lob
	@Column(name = "AUDIO_DATA", columnDefinition = "BLOB")
	public byte[] getAudioData() {
		return audioData;
	}

	public void setAudioData(byte[] audioData) {
		this.audioData = audioData;
	}

	@Override
	public String toString() {
		return "Audio [audioId=" + audioId + ", audioType=" + audioType + "]";
	}

}
