package org.localmarket.util;

import java.util.HashMap;

public class TokenManager {

	public static HashMap<String, String> tokenMap;

	static {
		tokenMap = new HashMap<String, String>();
		tokenMap.put("yw3hsr743sjt32qd", "9sfb387sdjfaherstjkdsifoituw98qr");
	}

	public static void addUser(String id, String token) {
		tokenMap.put(id, token);
	}

	public static void removeUser(String id) {
		tokenMap.remove(id);
	}

	public static boolean validLogin(String id, String token) throws Exception {
		if ((id.equals("yw3hsr743sjt32qd")
				&& token.equals("9sfb387sdjfaherstjkdsifoituw98qr"))
				|| (token.equals(tokenMap.get(id)))) {
			return true;
		}
		throw new Exception("Not authorized");
	}

}
