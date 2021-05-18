package com.example.demo.util;


/**
 * @author: yanchengzhe
 * @Date: 2021/3/8 14:47
 * @Description: 字符串处理常用工具包
 */
public final class Strings {

	/**
	 * 如果字符串是null,返回空串 ,否则返回原字符串
	 * @param string
	 * @return
	 */
	public static String nullToEmpty(String string) {
		return string == null ? "" : string;
	}

	/**
	 * 如果字符串是空串,返回null ,否则返回原字符串
	 * @param string
	 * @return
	 */
	public static String emptyToNull(String string) {
		return isNullOrEmpty(string) ? null : string;
	}

	/**
	 * 判断字符串是不是null
	 * @param string
	 * @return {@code true} 如果是空串 , {@code false}不是空串
	 */
	public static boolean isNullOrEmpty(String string) {
		return string == null || string.isEmpty();
	}

	/**
	 *
	 * @param string
	 * @return {@code true} 如果不是空串 , {@code false}是空串
	 */
	public static boolean isNotNullOrEmpty(String string) {
		return string != null && !string.isEmpty();
	}

}
