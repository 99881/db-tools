package com.i94living.dbtools;


/**
 * 
* @ClassName: Column 
* @Description: 数据库的列
* @author hhy
* @date 2015年2月6日 上午11:06:58 
*
 */
public class Column {
	//列名
	private String name;
	//是否为空
	private String isNum;
	//数据类型
	private String type;
	//字段描述
	private String remark;
	//是否主键
	private String isPK;
	//长度
	private int size;
	
	public void setSize(int size) {
		this.size = size;
	}
	public int getSize() {
		return size;
	}
	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the isNum
	 */
	public String getIsNum() {
		return isNum;
	}
	/**
	 * @param isNum the isNum to set
	 */
	public void setIsNum(String isNum) {
		this.isNum = isNum;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the isPK
	 */
	public String getIsPK() {
		return isPK;
	}
	/**
	 * @param isPK the isPK to set
	 */
	public void setIsPK(String isPK) {
		this.isPK = isPK;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Column [name=" + name + ", isNum=" + isNum + ", type=" + type
				+ ", remark=" + remark + ", isPK=" + isPK + "]";
	}
	
	
}
