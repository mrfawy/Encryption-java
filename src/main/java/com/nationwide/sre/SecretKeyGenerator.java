package com.nationwide.sre;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecretKeyGenerator {
	/**
	 * Using a pass phrase , hash it using SHA-256 , and generate the
	 * symmetric(Secret) key of length 128
	 * 
	 * @param passPhrase
	 *            a string shared between parties , preferably containing mixed
	 *            case and special characters
	 * @return
	 */

	public static SecretKey generateSecretKey(String passPhrase) {

		try {
			byte[] key = passPhrase.getBytes("UTF-8");
			MessageDigest sha = MessageDigest.getInstance("SHA-256");
			key = sha.digest(key);
			// comment this line if Java security policy accepts key size of
			// more than 128 , not the default settings per JCE
			key = Arrays.copyOf(key, 16); // use only first 128 bit
			SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
			return secretKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns the max key length allowed by JVM , JCE policies can be used change the limit , by default 128 is returned for AES
	 * @return
	 */
	public static int getMAxAllowedKeyLength() {
		try {
			int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
			return maxKeyLen;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;

	}

}
