package com.revature.koality.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonUtility {

	/**
	 * 
	 * Transform a raw string into a SHA-256 hash string
	 * 
	 * @param message
	 * @return the SHA-256 digest
	 */
	public static String digestSHA256(String message) {

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		StringBuilder digest = new StringBuilder("");
		md.update(message.getBytes());
		byte[] bytes = md.digest();
		for (byte b : bytes) {
			digest.append(String.format("%02X", b));
		}

		return new String(digest);

	}

	/**
	 * 
	 * Generate a random alphanumeric string of the specified length
	 * 
	 * @param length
	 * @return the generated random string
	 */
	public static String generateRandomString(int length) {

		return RandomStringUtils.randomAlphanumeric(length);

	}

	/**
	 * 
	 * Encode a byte array (binary large object data) into a Base64 URL string
	 * 
	 * @param mimeType
	 * @param blobData
	 * @return a base64 data URL usable by HTML
	 */
	public static String encodeToBlobUrl(String mimeType, byte[] blobData, boolean isAudio) {

		String prefix = null;

		if (isAudio) {
			prefix = "data:audio/" + mimeType.toLowerCase() + ";base64,";
		} else {
			prefix = "data:image/" + mimeType.toLowerCase() + ";base64,";
		}
		String base64Data = Base64.getEncoder().encodeToString(blobData);

		return prefix + base64Data;

	}

}
