package essentials;

import java.security.MessageDigest;

import com.google.gson.GsonBuilder;

//Hashing the Block with SHA-256
//Block includes PreviousHash, Nonce, Data, TimeStamp

public class Hash {
	
	//Hashing function
	public static String hashfun(String input) {
		try {
			//Algorithm to Hash the Block "SHA-256", "SHA-128", "MD5" etc can be used
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			
			//Converting the hash in hexString
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Short hand helper to turn Object into a json string
	public static String getJson(Object o) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}

	// Returns difficulty string target, to compare to hash. eg difficulty of 5 will
	// return "00000"
	public static String getDifficultyString(int difficulty) {
		return new String(new char[difficulty]).replace('\0', '0');
	}
}
