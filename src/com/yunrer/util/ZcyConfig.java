package com.yunrer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ZcyConfig {

		public static String API_GATEWAY ="";// http://118.178.149.133:9002
		public static String SECRET="";//qa7mVz1DDN
		public static String APP_KEY  = "";// 909669
		public static String DEPOSIT_RATE= "";//0.5
		public static String NAME_SPACE = "";//  /open/

	public ZcyConfig() {
		super();

			try {
				InputStream is = ZcyConfig.class.getResourceAsStream(
						"/resources/zcyconfig.properties");
				Properties p = new Properties();
				p.load(is);
				API_GATEWAY = p.get("ZCY.API_GATEWAY").toString();
				SECRET = p.get("ZCY.SECRET").toString();
				APP_KEY = p.get("ZCY.APP_KEY").toString();
				DEPOSIT_RATE = p.get("ZCY.DEPOSIT_RATE").toString();
				NAME_SPACE = p.get("ZCY.NAME_SPACE").toString();
			} catch (Exception e) {}

	}

	public static ZcyConfig instance(){
		
		return ZcyinnerClass.getinstance();
	}
	
	
	static class ZcyinnerClass {

		public static ZcyConfig getinstance() {
			return new ZcyConfig();
		
		}
	}

	
}
