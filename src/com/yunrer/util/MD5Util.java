package com.yunrer.util;

import java.security.MessageDigest;

/**
 * MD5工具类
 * @ClassName MD5Util
 * @Description 
 * @author rujun
 * @date 2016-12-21
 */

public abstract class MD5Util {
	public final static String MD5(String s) {  
        //用于加密的字符  
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
                'A', 'B', 'C', 'D', 'E', 'F' };  
        try {  
            byte[] btInput = s.getBytes();  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
            mdInst.update(btInput);  
            byte[] md = mdInst.digest();  
            int j = md.length;  
            char str[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {     
                byte byte0 = md[i];    
                str[k++] = md5String[byte0 >>> 4 & 0xf];  
                str[k++] = md5String[byte0 & 0xf];    
            }  
            return new String(str);  
               
        } catch (Exception e) {  
            return null;  
        }  
        }  
}
