package com.i94living.service;

import java.util.List;

import com.i94living.dbtools.Column;
import com.i94living.dbtools.Table;

/**
 * 
* @ClassName: IExcuteDDlService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author hhy
* @date 2015年2月9日 上午9:37:30 
*
 */
public interface IExcuteDDlService {
	/**
	 * 
	 * @throws Exception
	 */
	public void excuteSql(String filePath)throws Exception;
	
	/**
	 * 获取数据库中的表以及表结构
	 * @return
	 */
	public List<Table> getTables();
	
	/**
	 * 获取表结构
	 * @return
	 */
	public List<Column> getColumns(String tableName);
}
