package com.yunrer.entity;

/**
 * AbstractSysStringmap entity provides the base persistence definition of the
 * SysStringmap entity. @author MyEclipse Persistence Tools
 */
/**
 * stringmap 实体类
 * @author wangpeng
 */
public abstract class AbstractSysStringmap implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Integer value;
	private String objectTypeName;
	private String filedName;
	private String pinyin;

	// Constructors

	/** default constructor */
	public AbstractSysStringmap() {
	}

	/** full constructor */
	public AbstractSysStringmap(String name, Integer value,
			String objectTypeName, String filedName) {
		this.name = name;
		this.value = value;
		this.objectTypeName = objectTypeName;
		this.filedName = filedName;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return this.value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getObjectTypeName() {
		return this.objectTypeName;
	}

	public void setObjectTypeName(String objectTypeName) {
		this.objectTypeName = objectTypeName;
	}

	public String getFiledName() {
		return this.filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}