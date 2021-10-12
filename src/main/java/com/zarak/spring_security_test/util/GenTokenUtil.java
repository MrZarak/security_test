package com.zarak.spring_security_test.util;

import java.nio.charset.StandardCharsets;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.DirectEncrypter;
import com.nimbusds.jwt.JWTClaimsSet;


public class GenTokenUtil {
	static byte[] secretKey = "52424A327AB914765D0F3DF965A4C653".getBytes(StandardCharsets.UTF_8);

	public static void main(String[] args) throws Exception {
		JWTClaimsSet claims = new JWTClaimsSet.Builder()
				.claim("email", "mrzarak0303@gmail.com")
				.claim("username", "Zarak")
				.build();

		Payload payload = new Payload(claims.toJSONObject());
		JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128CBC_HS256);

		DirectEncrypter encrypter = new DirectEncrypter(secretKey);

		JWEObject jweObject = new JWEObject(header, payload);
		jweObject.encrypt(encrypter);
		String token = jweObject.serialize();
		System.out.println(token);
	}
}
