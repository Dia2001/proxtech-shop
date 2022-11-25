package com.proxtechshop.utils;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {
	
	public static BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	public static String randomOrderId() {
		String myString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";

		int targetStringLength = 10;
		Random randomNumber = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);

		for (int i = 0; i < targetStringLength; i++) {
			if (i == 0) {
				int randomNo = randomNumber.nextInt(myString.length());
				// 5
				Character character = myString.charAt(randomNo);
				buffer.append(Character.toUpperCase(character));
			} else {
				StringBuilder sb = new StringBuilder(10);
				int index = (int) (AlphaNumericString.length() * Math.random());
				buffer.append(AlphaNumericString.charAt(index));
			}
		}
		String generatedString = buffer.toString();
	    return generatedString;
	}

}
