package com.nationwide.sre;

import javax.crypto.SecretKey;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {

	@Test
	public void testDuality() {
		SecretKey secretKey = SecretKeyGenerator
				.generateSecretKey("passPhrase");

		String msg = "Message content";

		// Encryption
		String cipherText = Encryptor.encryptBase64(msg, secretKey);

		// Decryption
		String plainText = Decryptor.decryptBase64(cipherText, secretKey);
		
		Assert.assertEquals(plainText, msg);
	}

}
