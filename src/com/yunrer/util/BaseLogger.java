package com.yunrer.util;

import org.apache.log4j.Logger;

public class BaseLogger {
	
	public  Logger logger = null;
	public BaseLogger(){
		logger = Logger.getLogger(this.getClass());
		System.out.println(this.getClass());
	}
		
	
}
