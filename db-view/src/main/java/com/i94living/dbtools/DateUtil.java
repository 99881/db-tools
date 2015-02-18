package com.i94living.dbtools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
* @ClassName: DateUtil 
* @Description: 时间处理工具类 
* @author hhy
* @date 2015年1月24日 下午10:13:13 
*
 */
public class DateUtil {
	
	/**
	 * 获取格式化的日期
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}

}
