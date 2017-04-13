package com.yunrer.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.jstl.core.Config;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析xml工具类
 * @ClassName XmlUtil
 * @Description 
 * @author zgf
 */
public class XmlUtil {

    //利用dom4j解析xml文件内容，并返回map数据形式  
    @SuppressWarnings("rawtypes")
	public static Map paserXmlByDOM4J(String path) throws Exception{   
        SAXReader reader = new SAXReader();  
        Document doc = reader.read(Config.class.getClassLoader().getResourceAsStream(path));  
        Map xml=paserXml(doc);  
        return xml;  
    }  
      
    //传入xml格式的string，转化为xml类型，然后解析其内容，返回map数据形式  
    public static Map<String,String> strToXmlAndPaserXml(String strXml) {  
        SAXReader reader = new SAXReader();  
        Document doc = null;
		try {
			doc = reader.read(new ByteArrayInputStream(strXml.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        Map<String,String> xml=paserXml(doc);  
        return xml;  
    }  
      
      
    /**
     * 解析遍历xml的数据 
     * @Title: paserXml 
     * @Description:
     * @param doc
     * @return Map<String,String>         
     * @throws
     */
    public static Map<String,String> paserXml(Document doc) {  
        Map<String,String> xml=new HashMap<String,String>();  
        Element root = doc.getRootElement();  
        Iterator it = root.elementIterator();  
        Element element;  
        while (it.hasNext()) {  
            element = (Element) it.next();  
            xml.put(element.attributeValue("key"),element.attributeValue("value"));  
        }  
        return xml;  
    }  

}
