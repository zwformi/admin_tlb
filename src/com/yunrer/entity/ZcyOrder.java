package com.yunrer.entity;

import java.lang.reflect.Field;

import com.alibaba.druid.sql.visitor.functions.Char;

import net.sf.ehcache.store.disk.ods.FileAllocationTree;

/**
 * 
 * @author Zhanggf
 *
 */
 public class ZcyOrder
{
	 	public Integer orderType;

	    public Integer supplierId;

	    public String districtCode;

	    public Integer purchaserOrganizationId;

	    public Integer fee;

	    public Integer supplierOrganizationId;

	    public String shopName;

	    public Boolean hasSupplierComment;

	    public String createdAt;

	    public Boolean hasReturn;

	    public String purchaserOrganizationName;

	    public Integer purchaserOrderId;

	    public Integer id;

	    public Integer shopId;

	    public Integer waitReissueQuantity;

	    public Integer originFee;

	    public String updatedAt;

	    public String supplierName;

	    public Integer quantity;

	    public Boolean hasDeliver;

	    public Boolean hasComment;

	    public Boolean hasAcceptance;

	    public String purchaserName;

	    public String supplierOrganizationName;

	    public Integer purchaserId;

	    public Boolean hasReplaceConfirm;

	    public Boolean hasSettle;

	    public Boolean isNeedReissue;

	    public String comment;

	    public String purchaserZoneCode;

	    public Boolean hasRecord;

	    public Integer status;
	    
	    public static void main(String[] args) {
	    	SqlMaker("com.yunrer.entity.ZcyOrder");
//	    	String aaa = "com.xytyzgf.entity.AAA";
//	    	System.out.println(aaa.substring(aaa.lastIndexOf('.')+1, aaa.length()));
	    	
		}

	    
	    public static String SqlMaker(String className){
	    	try{
	    		int index = className.lastIndexOf('.');
	    		String splitedClassName = className.substring(++index, className.length());
	    		String tableName = "tbl_";
	    		tableName += makeLowerCaseName(className);
	    		
	    		String sql = "CREATE TABLE `"+tableName+"` (";
	    		
	    		Class c = Class.forName(className);
	    		Field[] fields = c.getFields();
	    		for (Field field : fields) {
					String fieldName = field.getName();
		    		String splitedFieldName = fieldName.substring((fieldName.lastIndexOf('.')+1), fieldName.length());
		    		String filedType = field.getType().toString();
		    		String splitedFiledType = filedType.substring((filedType.lastIndexOf('.')+1), filedType.length());
		    		sql += splitedFieldName +" " +splitedFiledType+"(10),";
//		    		System.out.println("splitedFieldName:"+splitedFieldName+","+"filedType"+filedType);
		    		
				}
	    		sql.substring(1, sql.length()-1);
	    		sql +=");";
	    		System.out.println(sql);
	    	}catch(Exception e){
	    		
	    	}
	    	return null;
	    }
	    
	    public static String makeLowerCaseName(String name){
    		char[] arr = name.toCharArray();
    		String lowerName = "";
    		for (int i=0;i<arr.length;i++) {
    			char c = arr[i];
    			if(c >= 65 && c <= 90){
    				c -= 32;
    				if(i>0)
    					lowerName += "_"+c;
    			}else
    				lowerName += c;
			}
    		return lowerName;
	    }

}
