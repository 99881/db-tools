package com.i94living.dbtools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
* @ClassName: DateUtil 
* @Description: ʱ�䴦������ 
* @author hhy
* @date 2015��1��24�� ����10:13:13 
*
 */
public class DateUtil {
	
	/**
	 * ��ȡ��ʽ��������
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date);
	}

}
