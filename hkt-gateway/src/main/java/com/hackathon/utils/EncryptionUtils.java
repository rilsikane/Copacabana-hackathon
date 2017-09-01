package com.hackathon.utils;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class EncryptionUtils {
	private static final Logger LOG = Logger.getLogger(EncryptionUtils.class);
	private static final String ALGORITHM = "DESede";

	public static String encrypt(String message, String secretKey) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			byte[] digestOfPassword = messageDigest.digest(secretKey.getBytes(Charsets.UTF_8));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainTextBytes = message.getBytes(Charsets.UTF_8);
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			String base64EncryptedString = new String(base64Bytes);
			return base64EncryptedString;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public static String decrypt(String encryptedText, String secretKey) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
			byte[] message = Base64.decodeBase64(encryptedText.getBytes(Charsets.UTF_8));
			byte[] digestOfPassword = messageDigest.digest(secretKey.getBytes(Charsets.UTF_8));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
			Cipher decipher = Cipher.getInstance(ALGORITHM);
			decipher.init(Cipher.DECRYPT_MODE, key);
			byte[] plainText = decipher.doFinal(message);
			return new String(plainText, Charsets.UTF_8);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public static String encodeBase64(String encryptedText) {
		try {
			byte[] encodedBytes = Base64.encodeBase64(encryptedText.getBytes());
			return new String(encodedBytes);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return StringUtils.EMPTY;
		}
	}

	public static String decodeBase64(String encryptedText) {
		try {
			byte[] decodedBytes = Base64.decodeBase64(encryptedText.getBytes());
			return new String(decodedBytes);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return StringUtils.EMPTY;
		}
	}

}
