package com.proxtechshop.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {
	
	public static BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	public static Page<?> toPage(List<?> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        if(start > list.size())
            return new PageImpl<>(new ArrayList<>(), pageable, list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
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
				Character character = myString.charAt(randomNo);
				buffer.append(Character.toUpperCase(character));
			} else {
				int index = (int) (AlphaNumericString.length() * Math.random());
				buffer.append(AlphaNumericString.charAt(index));
			}
		}
		String generatedString = buffer.toString();
	    return generatedString;
	}

}
