package com.i94living.dbtools.ddl;

import java.util.List;

import com.i94living.dbtools.Column;
import com.i94living.dbtools.DBUtil;
import com.i94living.dbtools.ReadExcelFile;
import com.i94living.dbtools.Table;

/**
 * 
* @ClassName: CreateDDLPGSQL 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author hhy
* @date 2015年2月9日 上午9:39:26 
*
 */
public class CreateDDLPGSQL {
	
	/**
	 * 
	 * @param tables
	 * @return
	 */
	public String getDDL(List<Table> tables){
		StringBuffer sbs = new StringBuffer();
		for (Table table : tables) {
			StringBuffer sb = new StringBuffer();
			sb.append("CREATE TABLE  "+table.getName()+"(  \n");
			for (int i=0;i< table.getCulumns().size();i++) {
				sb.append(table.getCulumns().get(i).getName()+" ");
				sb.append(table.getCulumns().get(i).getType()+" ");
				if(table.getCulumns().get(i).getIsNum()!=null&&table.getCulumns().get(i).getIsNum().endsWith("��")){
					sb.append("NOT NULL ");
				}
				if(table.getCulumns().get(i).getIsPK()!=null&&table.getCulumns().get(i).getIsPK().equals("PK")){
					sb.append("PRIMARY KEY ");
				}
				if(i!=(table.getCulumns().size()-1)){
					sb.append(",");
				}
			}
			sb.append(" ); \n");
			sb.append("COMMENT ON TABLE "+table.getName()+" IS '"+table.getRemark()+"'; \n");
			for (Column column : table.getCulumns()) {
				sb.append("COMMENT ON COLUMN "+table.getName()+"."+column.getName()+" IS '"+column.getRemark()+"'; \n");
			}
			sbs.append(sb.toString()+" \n");
			
		}
		return sbs.toString();
	}
	/**
	 * 
	 * @throws Exception
	 */
	public void createDDL(String filePath)throws Exception{
		ReadExcelFile ref = new ReadExcelFile();
		CreateDDLPGSQL cddl = new CreateDDLPGSQL();
		DBUtil.excute(cddl.getDDL(ref.getTablesForExcel(filePath)));
	}
	
}
