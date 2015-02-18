package com.i94living.dbtools;

import java.util.List;

/**
 * 
* @ClassName: Table 
* @Description: 表结构 
* @author hhy
* @date 2015年2月9日 上午9:24:35 
*
 */
public class Table {
	//表名称
	private String name;
	//表描述
	private String remark;
	//表属性
	private List<Column> culumns;
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
	 * @return the culumns
	 */
	public List<Column> getCulumns() {
		return culumns;
	}
	/**
	 * @param culumns the culumns to set
	 */
	public void setCulumns(List<Column> culumns) {
		this.culumns = culumns;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Table [name=" + name + ", remark=" + remark + ", culumns="
				+ culumns + "]";
	}
	

	
	
}
