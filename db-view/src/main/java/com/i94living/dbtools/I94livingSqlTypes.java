package com.i94living.dbtools;

import java.sql.Types;

/**
 * 
* @ClassName: I94livingSqlTypes 
* @Description: 
* @author hhy
* @date 2015年2月16日 下午10:03:53 
*
 */
public class I94livingSqlTypes {
	
	public I94livingSqlTypes(String sqlType) {
		// TODO Auto-generated constructor stub
		switch (sqlType) {
		case "VARCHAR":
			this.sqlType="VARCHAR";
			this.javaType="String";
			break;
		case "CHAR":
			this.sqlType="VARCHAR";
			this.javaType="String";
			break;
		case "LONGVARCHAR":
			this.sqlType="VARCHAR";
			this.javaType="String";
			break;
		case "NUMERIC":
			this.sqlType="NUMERIC";
			this.javaType="java.math.BigDecimal";
			break;
		case "DECIMAL":
			this.sqlType="DECIMAL";
			this.javaType="java.math.BigDecimal";
			break;
		case "BIT":
			this.sqlType="BIT";
			this.javaType="boolean";
			break;
		case "TINYINT":
			this.sqlType="TINYINT";
			this.javaType="byte";
			break;
		case "SMALLINT":
			this.sqlType="SMALLINT";
			this.javaType="short";
			break;
		case "INT":
			this.sqlType=sqlType;
			this.javaType="int";
			//DATETIME
		case "DATETIME":
			this.sqlType=sqlType;
			this.javaType="DATETIME";
		case "INTEGER":
			this.sqlType="INTEGER";
			this.javaType="int";
			break;
		case "BIGINT":
			this.sqlType="BIGINT";
			this.javaType="long";
			break;
		case "REAL":
			this.sqlType="REAL";
			this.javaType="float";
			break;
		case "FLOAT":
			this.sqlType="FLOAT";
			this.javaType="double";
			break;
		case "DOUBLE":
			this.sqlType="DOUBLE";
			this.javaType="double";
			break;
		case "BINARY":
			this.sqlType="BINARY";
			this.javaType="byte[]";
			break;
		case "VARBINARY":
			this.sqlType="VARBINARY";
			this.javaType="byte[]";
			break;
		case "LONGVARBINARY":
			this.sqlType="LONGVARBINARY";
			this.javaType="byte[]";
			break;
		case "DATE":
			this.sqlType="DATE";
			this.javaType="Date";
			break;
		case "TIME":
			this.sqlType="TIME";
			this.javaType="Time";
			break;
		case "TIMESTAMP":
			this.sqlType="TIMESTAMP";
			this.javaType="Timestamp";
			break;
		default:
			this.sqlType=sqlType;
			this.javaType=sqlType;
			break;
			}
	}
	public I94livingSqlTypes(int columnType) {
		switch (columnType) {
		case Types.VARCHAR:
			this.sqlType="VARCHAR";
			this.javaType="String";
			break;
		case Types.CHAR:
			this.sqlType="VARCHAR";
			this.javaType="String";
			break;
		case Types.LONGVARCHAR:
			this.sqlType="VARCHAR";
			this.javaType="String";
			break;
		case Types.NUMERIC:
			this.sqlType="NUMERIC";
			this.javaType="java.math.BigDecimal";
			break;
		case Types.DECIMAL:
			this.sqlType="DECIMAL";
			this.javaType="java.math.BigDecimal";
			break;
		case Types.BIT:
			this.sqlType="BIT";
			this.javaType="boolean";
			break;
		case Types.TINYINT:
			this.sqlType="TINYINT";
			this.javaType="byte";
			break;
		case Types.SMALLINT:
			this.sqlType="SMALLINT";
			this.javaType="short";
			break;
		case Types.INTEGER:
			this.sqlType="INTEGER";
			this.javaType="int";
			break;
		case Types.BIGINT:
			this.sqlType="BIGINT";
			this.javaType="long";
			break;
		case Types.REAL:
			this.sqlType="REAL";
			this.javaType="float";
			break;
		case Types.FLOAT:
			this.sqlType="FLOAT";
			this.javaType="double";
			break;
		case Types.DOUBLE:
			this.sqlType="DOUBLE";
			this.javaType="double";
			break;
		case Types.BINARY:
			this.sqlType="BINARY";
			this.javaType="byte[]";
			break;
		case Types.VARBINARY:
			this.sqlType="VARBINARY";
			this.javaType="byte[]";
			break;
		case Types.LONGVARBINARY:
			this.sqlType="LONGVARBINARY";
			this.javaType="byte[]";
			break;
		case Types.DATE:
			this.sqlType="DATE";
			this.javaType="java.sql.Date";
			break;
		case Types.TIME:
			this.sqlType="TIME";
			this.javaType="java.sql.Time";
			break;
		case Types.TIMESTAMP:
			this.sqlType="TIMESTAMP";
			this.javaType="java.sql.Timestamp";
			break;
		default:
			break;
		}
	}
	//数据库中代码
	private int columnType;
	//sql中的类型
	private String sqlType;
	//对应的java类型
	private String javaType;
	/**
	 * @return the columnType
	 */
	public int getColumnType() {
		return columnType;
	}
	/**
	 * @param columnType the columnType to set
	 */
	public void setColumnType(int columnType) {
		this.columnType = columnType;
	}
	/**
	 * @return the sqlType
	 */
	public String getSqlType() {
		return sqlType;
	}
	/**
	 * @param sqlType the sqlType to set
	 */
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	/**
	 * @return the javaType
	 */
	public String getJavaType() {
		return javaType;
	}
	/**
	 * @param javaType the javaType to set
	 */
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	
	
	
}
