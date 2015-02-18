package com.i94living.dbtools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
* @ClassName: PropertiesUtil 
* @Description: 获取properties的值
* @author hhy
* @date 2015年2月5日 下午2:22:02 
*
 */
public class PropertiesUtil {
	
	private static Properties pro;
	private static InputStream in;
	private static String savePath;
	
	static {
		try {
			initProperties();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void initProperties()throws Exception{
		pro = new Properties();
		savePath = PropertiesUtil.class.getResource("/db.properties").getPath();
		in=new BufferedInputStream(new FileInputStream(savePath));
		pro.load(in);
	}
	
	/**
	 * 根据key获取value
	 * @param key
	 * @return
	 */
	public static String getValue(String key){
		return pro.getProperty(key);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean setValue(Map<String,String> valueMap){
		boolean returnParam = false;
		Iterator iter = valueMap.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, String> entry = (Entry<String, String>) iter.next();
			pro.setProperty(entry.getKey(), entry.getValue());
			System.out.println("key:"+entry.getKey());
			System.out.println("value:"+entry.getValue());
		}
		try {
			FileOutputStream fos = new FileOutputStream(savePath);
			pro.store(fos, "modify");
			returnParam = true;
			fos.close();
			fos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnParam;
		
	}
}
