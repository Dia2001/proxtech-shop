package com.proxtechshop.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

	public static String convertDateToyyyyMMddHHmmss(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}
	
	public static String convertDateToddMMyyyy(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}
	
	public static Date createDateFromYMD(int year, int month, int day) {
		return new Date(year - 1900, month - 1, day);
	}
	
	public static String getFileType(String contentType) {
		String fileType = "";
		if (contentType.contains("image/jpeg")) {
			fileType = ".jpg";
		}
		if (contentType.contains("image/webp")) {
			fileType = ".webp";
		}
		if (contentType.contains("image/png")) {
			fileType = ".png";
		}
		return fileType;
	}
}
