package com.i94living.dbtools;

import java.io.File;
import java.util.Date;

/**
 * 
* @ClassName: FolderUtil 
* @Description: 文件夹工具类，新建文件夹 按照时间 
* @author hhy
* @date 2015年2月6日 下午12:02:42 
*
 */
public class FolderUtil {

	/**
	 * 
	 * @param basePath 按照时间创建文件夹
	 */
	public void creteFolderByDate(String basePath){
		String path = basePath+"/"+DateUtil.formatDate(new Date());
		File f = new File(path);
		if(!f.exists()){
			f.mkdirs();
		}
		System.out.println(path);
	}
	
	public static void main(String[] args) {
		FolderUtil fu = new FolderUtil();
		fu.creteFolderByDate("D:/lfq");
	}
}
