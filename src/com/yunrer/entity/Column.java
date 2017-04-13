package com.yunrer.entity;
/**
 * 字段信息  实体类
 * @author wangpeng
 *
 */
public class Column {

	private String COLUMN_NAME , COLUMN_COMMENT ,COLUMN_TYPE,COLUMN_KEY,IS_NULLABLE;

	public String getCOLUMN_NAME() {
		return COLUMN_NAME;
	}

	public void setCOLUMN_NAME(String cOLUMN_NAME) {
		COLUMN_NAME = cOLUMN_NAME;
	}

	public String getCOLUMN_COMMENT() {
		return COLUMN_COMMENT;
	}

	public void setCOLUMN_COMMENT(String cOLUMN_COMMENT) {
		COLUMN_COMMENT = cOLUMN_COMMENT;
	}

	public String getCOLUMN_TYPE() {
		return COLUMN_TYPE;
	}

	public void setCOLUMN_TYPE(String cOLUMN_TYPE) {
		COLUMN_TYPE = cOLUMN_TYPE;
	}

	public String getCOLUMN_KEY() {
		return COLUMN_KEY;
	}

	public void setCOLUMN_KEY(String cOLUMN_KEY) {
		COLUMN_KEY = cOLUMN_KEY;
	}

	public String getIS_NULLABLE() {
		return IS_NULLABLE;
	}

	public void setIS_NULLABLE(String iS_NULLABLE) {
		IS_NULLABLE = iS_NULLABLE;
	}


}
