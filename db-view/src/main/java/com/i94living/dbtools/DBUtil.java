package com.i94living.dbtools;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * 
* @ClassName: DBUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author hhy
* @date 2015年2月5日 下午2:49:19 
*
 */
@Repository("dBUtil")
public class DBUtil {
	private static String dbDriver;
	private static String dbUrl;
	private static String dbUsername;
	private static String dbPassword;
	private static String dbName;
	static{
		dbDriver=PropertiesUtil.getValue("i94living.dbDriver");
		dbUrl=PropertiesUtil.getValue("i94livng.dbUrl");
		dbUsername=PropertiesUtil.getValue("i94livng.dbUsername");
		dbPassword=PropertiesUtil.getValue("i94living.dbPassword");
		dbName = PropertiesUtil.getValue("i94living.dbName");
	}
	

	/**
	 * 执行sql
	 * @param sql
	 */
	public static void excute(String sql){
		PreparedStatement stm=null;
		Connection conn =null;
		try {
			Class.forName(dbDriver);
			conn=DriverManager
					.getConnection(dbUrl,dbUsername,dbPassword);
			stm=conn.prepareStatement(sql);
			stm.execute();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				stm.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConn()throws Exception{
		Connection conn = null;
		Class.forName(dbDriver);
		conn=DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		return conn;
	}
	
	/**
	 * 获取数据库中所有的表
	 * @return
	 */
	public static List<Table> getTables(){
		List<Table> tables = new ArrayList<Table>();
		Connection conn = null;
		DatabaseMetaData  dmd = null;
		ResultSet rs =null;
		try {
			conn = getConn();
			dmd = conn.getMetaData();
			rs = dmd.getTables(conn.getCatalog(), null, "%", new String[]{"TABLE"});
			while(rs.next()){
				Table t = new Table();
				t.setName(rs.getString("TABLE_NAME"));
				t.setRemark(rs.getString("REMARKS"));
				//t.setCulumns(getColumn(dmd,rs.getString("TABLE_NAME"),conn));
				tables.add(t);
			}
			if(rs!=null){
				rs.close();
			}
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
	}
	
	/**
	 * 获取表中的属性
	 * @param rs
	 * @return
	 */
	public static List<Column> getColumn(String tableName){
		List<Column>columns  = new ArrayList<Column>();
		Connection conn = null;
		DatabaseMetaData dmd = null;
		try {
			conn = getConn();
			dmd = conn.getMetaData();
			ResultSet rs = dmd.getColumns(null, "%", tableName, "%");
			while(rs.next()){
				Column c = new Column();
				c.setName(rs.getString("COLUMN_NAME"));
				c.setType(rs.getString("TYPE_NAME"));
				c.setIsNum(rs.getInt("NULLABLE")==1?"是":"否");
				c.setRemark(rs.getString("REMARKS"));
				c.setSize(rs.getInt("COLUMN_SIZE"));
				columns.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return columns;
	}
	
	
}
