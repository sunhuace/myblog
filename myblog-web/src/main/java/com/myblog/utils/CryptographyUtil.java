package com.myblog.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * MD5加密
 * @author sun
 *
 */
public class CryptographyUtil {

	
	public static String md5(String str,String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	public static void main(String[] args) {
		String password="admin";
		
		System.out.println("Md5加密"+CryptographyUtil.md5(password, "123"));
	}
}
