/**
 * 
 */
package com.music.authorize;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

/**
 * @author jitesh.kumar
 *
 */
public class BasicAuthService {

	public boolean authenticate(String authCredentials) {

		boolean authStatus = false;
		if (null == authCredentials)
			return false;

		final String encodedCredentials = authCredentials.replaceFirst("Basic"
				+ " ", "");

		String usernamePassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder()
					.decode(encodedCredentials);
			usernamePassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernamePassword,
				":");
		final String userName = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		if ("admin".equals(userName) && "adminPass".equals(password))
			authStatus = true;
		else
			authStatus = false;

		return authStatus;
	}

}
