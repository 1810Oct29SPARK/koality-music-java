package com.revature.koality.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.RandomStringUtils;

public class CommonUtility {

	/**
	 * 
	 * Transform a raw string into a SHA-256 hash string
	 * 
	 * @param message
	 * @return sha256Digest
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
	 * @return
	 */
	public static String generateRandomString(int length) {
		
		return RandomStringUtils.randomAlphanumeric(length);
		
	}

}
