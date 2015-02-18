package com.i94living.dbtools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;


/**
 * 生产bean文件
* @ClassName: CreateClazz 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author hhy
* @date 2015年2月18日 下午7:52:59 
*
 */
@Service
public class CreateClazzUtil {
	
	/**
	 * 获取clazz
	 * @param tableName
	 * @return
	 */
	public String crateClazzByTables(String tableName){
		StringBuffer sb = new StringBuffer();
		List<Column> columns = new ArrayList<Column>();
		columns = DBUtil.getColumn(tableName);
		for (Column column : columns) {
			sb.append("\n/**\n*");
			sb.append(column.getRemark());
			sb.append("\n*/\n");
			sb.append("private ");
			sb.append(getJavaType(column.getType()));
			sb.append(" ");
			sb.append(getJavaName(column.getName()));
			sb.append("; \n");
			
		}
		for (Column column : columns) {
			sb.append(getSetFunctionName(column.getName(), column.getType(), column.getRemark()));
			sb.append(getGetFunctionName(column.getName(), column.getType(), column.getRemark()));
		}
		return sb.toString();
	}
	
	/**
	 * 获取get方法的方法名
	 * @param javaName
	 * @return
	 */
	public String getGetFunctionName(String sqlName,String sqlType,String remark){
		String javaName = getJavaName(sqlName);
		String javaType = getJavaType(sqlType);
		String firstChar = javaName.substring(0,1);
		String JavaName = javaName.replaceFirst(firstChar, firstChar.toUpperCase());
		StringBuffer sb = new StringBuffer();
		sb.append("\n/**\n*");
		sb.append(remark);
		sb.append("\n*/\n");
		sb.append("public ");
		sb.append(javaType);
		sb.append(" get");
		sb.append(JavaName);
		sb.append("(){\n");
		sb.append("    return ");
		sb.append(javaName);
		sb.append(";\n}");
		return sb.toString();
	}
	
	/**
	 * 获取set方法的方法名
	 * @param javaName
	 * @return
	 */
	public String getSetFunctionName(String sqlName,String sqlType,String remark){
        
		String javaName = getJavaName(sqlName);
		String javaType = getJavaType(sqlType);
		String firstChar = javaName.substring(0,1);
		String JavaName = javaName.replaceFirst(firstChar, firstChar.toUpperCase());
		StringBuffer sb = new StringBuffer();
		sb.append("\n/**\n*");
		sb.append(remark);
		sb.append("\n*/\n");
		sb.append("public void set");
		sb.append(JavaName);
		sb.append("(");
		sb.append(javaType);
		sb.append(" ");
		sb.append(javaName);
		sb.append("){ \n");
		sb.append("    this.");
		sb.append(javaName);
		sb.append(" = ");
		sb.append(javaName);
		sb.append(";\n}");
		return sb.toString();
	}
	
	/**
	 * 获取javaType
	 * @param sqlType
	 * @return
	 */
	public String getJavaType(String sqlType){
		return new I94livingSqlTypes(sqlType.toUpperCase()).getJavaType();   
	}
	/**
	 * 获取JavaName
	 * @param sqlName
	 * @return
	 */
	public String getJavaName(String sqlName){
		List<Integer> indexs = new ArrayList<Integer>();
		List<String>oldStrs = new ArrayList<String>();
		List<String>newStrs = new ArrayList<String>();
		//去掉（f_）
		String str=(sqlName.toLowerCase()).replaceFirst("f_", "");
		
		String patternString="_";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			//System.out.println(matcher.start());
			indexs.add(matcher.start());
		}
		//暂时不替换
		//String tmp = matcher.replaceAll("");
		for (Integer integer : indexs) {
			oldStrs.add(str.substring(integer, integer+2));
			newStrs.add(str.substring(integer+1,integer+2).toUpperCase());
		}
		for(int i =0;i<oldStrs.size();i++){
			str = str.replaceAll(oldStrs.get(i), newStrs.get(i));
		}
		return str;
	}
	
	public static void main(String[] args) {
		//System.out.println(new CreateClazzUtil().getJavaName("f_li_name"));
		System.out.println(new CreateClazzUtil().getGetFunctionName("f_li_name", "String","注释"));
		System.out.println(new CreateClazzUtil().getSetFunctionName("f_li_name", "String","注释"));
	}
}
