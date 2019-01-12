package com.revature.koality.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	 * Generate a random integer between 1 and the specified max value (inclusive)
	 * 
	 * @param maxValue
	 * @return the generated random integer
	 */
	public static int generateRandomInteger(int maxValue) {

		Random rand = new Random();

		return rand.nextInt(maxValue) + 1;

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

	/**
	 * 
	 * Read the servlet request body and produce a string representation
	 * 
	 * @param br
	 * @return the parsed request body in string format
	 */
	public static String readRequest(BufferedReader br) {

		StringBuilder requestBody = new StringBuilder("");
		String buffer = null;

		try {
			while ((buffer = br.readLine()) != null) {
				requestBody.append(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (requestBody.length() == 0) {
			return null;
		} else {
			return new String(requestBody);
		}

	}

	/**
	 * 
	 * Convert a java object into a json string using Jackson databind
	 * 
	 * @param pojo
	 * @return the formatted json string
	 */
	public static String toJsonStringJackson(Object pojo) {

		ObjectMapper jacksonMapper = new ObjectMapper();
		try {
			return jacksonMapper.writeValueAsString(pojo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 
	 * Decode a Base64 data URL string into a byte array after truncating the prefix
	 * 
	 * @param blobUrl
	 * @return the decoded byte data
	 */
	public static byte[] decodeBlobUrl(String blobUrl) {

		String blobData = blobUrl.substring(blobUrl.indexOf("base64,") + 7);
		return Base64.getDecoder().decode(blobData);

	}

}
