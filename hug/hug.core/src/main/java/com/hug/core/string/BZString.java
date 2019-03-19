package com.hug.core.string;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

/**
 * @Description 字符串处理
 * @author chenjian
 */
public class BZString {

	public static final String NULL = "null";

	/**
	 * @Description 是空
	 */
	public static boolean isNullOrEmpty(String string) {
		return (null == string || string.trim().length() == 0 || NULL.equalsIgnoreCase(string));
	}

	/**
	 * @Description 非空
	 */
	public static boolean isNotNullOrEmpty(String string) {
		return !isNullOrEmpty(string);
	}

	/**
	 * @Description 乱码转换，ISO-8859-1 change to UTF-8
	 */
	public static String encoding(String string) throws UnsupportedEncodingException {
		return isNullOrEmpty(string) ? "" : new String(string.getBytes("ISO-8859-1"), "UTF-8");
	}

	/**
	 * @Description a,b,c change to 'a','b','c'
	 * @return a,b,c change to 'a','b','c'
	 */
	public static String arrayToSqlIn(String string) {
		return "'" + string.replace(",", "','") + "'";
	}

	/**
	 * @Description 首字母大写
	 */
	public static String capitalize(String string) {
		char[] cs = string.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}

	/**
	 * 判断字符串中是否含有多个空格
	 * 
	 * @author hhz
	 */
	public static boolean isExsitSpace(String string) {
		return Pattern.compile("\\s+").matcher(string).find();
	}

	/**
	 * @Description 下划线转驼峰
	 * @author chenjian
	 * @date 2018年5月3日 下午2:00:10
	 */
	public static String underlineToHump(String string) {
		String strs[] = string.split("_");
		StringBuilder result = new StringBuilder(strs[0]);
		for (int i = 1; i < strs.length; i++) {
			result.append(capitalize(strs[i]));
		}
		return result.toString();
	}

	public static String getFileSuffix(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public static String getFilePrefix(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	
	public static String subLastOne(String string) {
		return string.substring(0, string.length()-1);
	}
}
