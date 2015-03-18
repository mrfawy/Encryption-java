package com.nationwide.sre;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public class Decryptor {
	
	/**
	 * Decrypt Base64 encoded cipherText using AES symmetric key algorithm .
	 * AES Specs : ECB mode and PKCS5Padding schema 
	 * @param cipherText a Base64 encoded string
	 * @param secretKey
	 * @return
	 */

	public static String decryptBase64(String cipherText, SecretKey secretKey) {
		try {
			byte[] cipherBytes = Base64.decodeBase64(cipherText);
			byte[] plainByte = Decryptor.decrypt(cipherBytes, secretKey);
			String plainText = new String(plainByte);
			return plainText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] decrypt(byte[] cipherBytes, SecretKey secretKey) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			//split Cipher byte  has first 16 bytes as IV + cipherMsg
			byte[] iv=Arrays.copyOf(cipherBytes, 16);
			byte[] cipherMsg=Arrays.copyOfRange(cipherBytes,16,cipherBytes.length);
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, secretKey,ivspec);			
			byte[] plainBytes = cipher.doFinal(cipherMsg);
			return plainBytes;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
