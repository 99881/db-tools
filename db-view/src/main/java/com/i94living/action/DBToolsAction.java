package com.i94living.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.i94living.dbtools.Column;
import com.i94living.dbtools.CreateClazzUtil;
import com.i94living.dbtools.PropertiesUtil;
import com.i94living.dbtools.Table;
import com.i94living.jsonModel.FileUploadModel;
import com.i94living.jsonModel.TableModel;
import com.i94living.service.IExcuteDDlService;



/**
 * 
* @ClassName: DBToolsAction 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author hhy
* @date 2015年2月9日 上午9:37:18 
*
 */
@Controller
public class DBToolsAction {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Value("${project.filePath}")
	private String basePath;
	
	@Value(value="${project.name}")
	private String projectName;
	
	@Autowired
	private IExcuteDDlService excuteDDLService;
	
	@Autowired
	private CreateClazzUtil createClazzUtil;
	
	
	@RequestMapping("/index.do")
	public String index(Model model,HttpSession session){ 
		session.setAttribute("projectName", projectName);
		return "index";
	}
	
	/**
	 * 修改数据库连接参数
	 * @param dbType
	 * @param dbDriver
	 * @param dbUrl
	 * @param dbUsername
	 * @param dbPassword
	 * @return
	 */
	@RequestMapping(value = "modifyDB.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> modifyDBParam(@RequestParam("dbType")String dbType,
			@RequestParam("dbDriver")String dbDriver,
			@RequestParam("dbUrl")String dbUrl,
			@RequestParam("dbUsername")String dbUsername,
			@RequestParam("dbPassword")String dbPassword){
		Map<String,String>returnMap = new HashMap<String,String>();
		Map<String,String> dbMap = new HashMap<String,String>();
		dbMap.put("i94living.dbType", dbType);
		dbMap.put("i94living.dbDriver", dbDriver);
		dbMap.put("i94living.dbUrl", dbUrl);
		dbMap.put("i94living.dbUsername", dbUsername);
		dbMap.put("i94living.dbPassword", dbPassword);
		logger.info("dbType:"+dbType+",dbDriver:"+dbDriver+",dbUrl:"+dbUrl+",dbUsername:"+dbUsername+",dbPassword:"+dbPassword);
		if(PropertiesUtil.setValue(dbMap)){
			returnMap.put("status", "SUCCESS");
		}else{
			returnMap.put("status", "ERROR");
		}
		return returnMap;
	}
	
	
	/**
	 * 获取数据库表
	 * @return
	 */
	@RequestMapping(value="getTables.do",method = RequestMethod.POST)
	@ResponseBody
	public List<TableModel> getTables(){
		List<TableModel> tables = new ArrayList<TableModel>();
		List<Table> t =  new ArrayList<Table>();
		t = excuteDDLService.getTables();
		for (Table table : t) {
			TableModel tm = new TableModel();
			tm.setName(table.getName());
			tm.setRemark(table.getRemark());
			tm.setToDo("<a href=javascript:getCloums('"+table.getName()+"');>表结构</a>&nbsp;"
					+ "<a href=javascript:createClazz('"+table.getName()+"');>实体</a>");
			tables.add(tm);
		}
		return tables;
	}
	
	@RequestMapping(value="getcolumns.do",method = RequestMethod.POST)
	@ResponseBody
	public List<Column> getColumns(@RequestParam("tableName") String tableName){
		List<Column> columns = new ArrayList<Column>();
		columns = excuteDDLService.getColumns(tableName);
		return columns;
	}
	
	
	/**
	 * 实现文件的异步上传
	 * @param file
	 * @param request
	 * @param response
	 * @param fileUploadModel
	 * @return
	 */
	@RequestMapping(value="sqlFileUpload.do")
	@ResponseBody
	public FileUploadModel fileUpload(@RequestParam MultipartFile[] file,
			 HttpServletRequest request, HttpServletResponse response,FileUploadModel fileUploadModel){
		if(fileUploadModel==null){
			fileUploadModel = new FileUploadModel();
		}
		for (MultipartFile multipartFile : file) {
			if(multipartFile==null){
				fileUploadModel.setStatue("ERROR");
				fileUploadModel.setMsg("上传文件不能为空");
				return fileUploadModel;
			}else{
				String realFileName = multipartFile.getOriginalFilename();
				String fileSavePath=basePath+new Date().getTime()+realFileName;
				fileUploadModel.setFileName(realFileName);
				fileUploadModel.setFileSavePath(fileSavePath);
				try {
					FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(fileSavePath));
					try {
						excuteDDLService.excuteSql(fileSavePath);
						fileUploadModel.setStatue("SUCCESS");
						fileUploadModel.setMsg("文件导入成功");
					} catch (Exception e) {
						fileUploadModel.setStatue("ERROR");
						fileUploadModel.setMsg("文件导入失败");
						e.printStackTrace();
					}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					fileUploadModel.setStatue("ERROR");
					fileUploadModel.setMsg("上传文件失败"+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return fileUploadModel;
	}
	
	@RequestMapping(value="getClazz.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> crateClazzByTables(@RequestParam("tableName") String tableName){
		Map<String,String> returnMap = new HashMap<String,String>();
		String objStr = "";
		objStr = createClazzUtil.crateClazzByTables(tableName);
		returnMap.put("objStr", objStr);
		return returnMap;
	}
}
