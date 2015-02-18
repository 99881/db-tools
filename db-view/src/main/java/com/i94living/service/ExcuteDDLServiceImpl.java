package com.i94living.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i94living.dbtools.Column;
import com.i94living.dbtools.DBUtil;
import com.i94living.dbtools.PropertiesUtil;
import com.i94living.dbtools.Table;
import com.i94living.dbtools.ddl.CreateDDLPGSQL;

/**
 * 
* @ClassName: ExcuteDDLServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author hhy
* @date 2015年2月5日 下午2:15:14 
*
 */
@Service("excuteDDLService")
public class ExcuteDDLServiceImpl implements IExcuteDDlService{
	@Autowired
	private DBUtil dBUtil;
	/**
	 * 数据库执行方法
	 */
	public void excuteSql(String filePath)throws Exception{
		/**
		 * postgresql 
		 */
		if(PropertiesUtil.getValue("i94living.dbType").equals("pgsql")){
			CreateDDLPGSQL createDDL = new CreateDDLPGSQL();
			createDDL.createDDL(filePath);
			return;
		}
		/**
		 * mysql
		 */
		if(PropertiesUtil.getValue("i94living.dbType").equals("mysql")){
			
		}
	}

	/**
	 * 获取数据库中表以及表结构
	 */
	public List<Table> getTables() {
		// TODO Auto-generated method stub
		return dBUtil.getTables();
	}

	/**
	 * 获取表结构
	 * @return
	 */
	public List<Column> getColumns(String tableName) {
		// TODO Auto-generated method stub
		return dBUtil.getColumn(tableName);
	}
	

	
}
