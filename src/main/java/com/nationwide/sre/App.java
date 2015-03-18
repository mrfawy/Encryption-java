package com.nationwide.sre;

import javax.crypto.SecretKey;

public class App {
	public static void main(String[] args) {

		SecretKey secretKey = SecretKeyGenerator
				.generateSecretKey("sR3 CH00$3N pR@$E");

		String msg = "Welcome to SRE, JSON string is here ";

		// Encryption
		String cipherText = Encryptor.encryptBase64(msg, secretKey);
		System.out.println(cipherText);

		// Decryption
		String plainText = Decryptor.decryptBase64(cipherText, secretKey);
		System.out.println(plainText);
	}

}
