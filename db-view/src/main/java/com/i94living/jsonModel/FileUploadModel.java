package com.i94living.jsonModel;

/**
 * 文件上传的model
* @ClassName: FileUploadModel 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author hhy
* @date 2015年2月15日 下午8:07:37 
*
 */
public class FileUploadModel {
	private String fileName;
	private String fileSavePath;
	private String statue;
	private String msg;
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the fileSavePath
	 */
	public String getFileSavePath() {
		return fileSavePath;
	}
	/**
	 * @param fileSavePath the fileSavePath to set
	 */
	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}
	/**
	 * @return the statue
	 */
	public String getStatue() {
		return statue;
	}
	/**
	 * @param statue the statue to set
	 */
	public void setStatue(String statue) {
		this.statue = statue;
	}
	
}
