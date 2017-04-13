package com.yunrer.entity;

/**
 * SysStringmap entity. @author MyEclipse Persistence Tools
 */
/**
 * stringmap 实体类
 * @author zgf
 *
 */
public class StringMap extends AbstractSysStringmap implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public StringMap() {
	}

	/** full constructor */
	public StringMap(String name, Integer value, String objectTypeName,
			String filedName) {
		super(name, value, objectTypeName, filedName);
	}

}
