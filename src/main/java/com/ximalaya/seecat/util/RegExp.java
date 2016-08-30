package com.ximalaya.seecat.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
	public static String test(String str, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if (m.find()) {
			return m.group();
		}
		return Constants.EMPTY_STRING;
	}
}
