package com.nationwide.sre;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

public class Encryptor {

	/**
	 * Encrypt plainText using AES symmetric key algorithm and encode the result into Base64
	 * AES Specs :ECB mode and PKCS5Padding schema 
	 * @param plainText message text 
	 * @param secretKey
	 * @return
	 */
	public static String encryptBase64(String plainText, SecretKey secretKey) {
		try {
			byte[] encryptedBytes = Encryptor.encrypt(plainText, secretKey);
			String encryptedText = Base64.encodeBase64String(encryptedBytes);
			return encryptedText;
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] encrypt(String plainText, SecretKey secretKey)
			throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] plainTextByte = plainText.getBytes();
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		byte[] IVByte=cipher.getIV();
		//result has first 16 bytes as IV + cipherMsg
		byte[] result=combineBytes(IVByte,encryptedByte);		
		
		return result;
	}
	
	private static  byte[] combineBytes(byte[] a,byte[] b){
		
		byte[] combined = new byte[a.length + b.length];

		for (int i = 0; i < combined.length; ++i)
		{
		    combined[i] = i < a.length ? a[i] : b[i - a.length];
		}
		return combined;
	}

}
