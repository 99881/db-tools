<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="${contextPath}resources/jquery/jquery-1.11.2.min.js" ></script>
<script type="text/javascript" src="${contextPath}resources/bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="${contextPath}resources/AjaxFileUploaderV2.1/ajaxfileupload.js" charset="utf-8"></script>
<link rel="stylesheet"  href="${contextPath}resources/bootstrap/css/bootstrap.min.css"></link>
<link rel="stylesheet"  href="${contextPath}resources/awesome-3.2.1/css/font-awesome.min.css"></link>

<title>欢迎来到 ${projectName}</title>
<script type="text/javascript">
function ajaxFileUpload(){
	$.ajaxFileUpload({
		url:'sqlFileUpload.do',
		secureuri:false, //是否启用安全提交,默认为false
		fileElementId:'file',
		dataType:'JSON',
		success:function(data,status){
			if(data.statue=="SUCCESS"){
				$("#showMsg").html(data.fileSavePath);
				loadTables();
			}
		},
		error:function(data,status,e){
			alert("ERROR");
		}
	});
}

function loadTables(){
	$.ajax({
		type:"POST",
		url:"getTables.do",
		dataType:"JSON",
		success:function(data){
			var obj = eval(data);
			var str ="<tbody>";
			$(obj).each(function(index){
				var tmp = obj[index];
				str=str+"<tr><td>"+tmp.name+"</td><td>"+tmp.remark+"</td><td>"+tmp.toDo+"</td></tr>"
			});
			str=str+"</tbody>"
			$("#tables").html(
				"<table class='table table-bordered'><tbhead><tr><th>名称</th><th>描述</th><th>操作</th></tr></tbhead>"+str+"</table>"		
			);
		}
	});
}
function getCloums(v){
	var tableName = v;
	//columns
	$.ajax({
		type:"POST",
		url:"getcolumns.do",
		dataType:"JSON",
		data:{"tableName":tableName},
		success:function(data){
			var obj = eval(data);
			var str ="<tbody>";
			$(obj).each(function(index){
				var tmp = obj[index];
				str=str+"<tr><td>"+tmp.name+"</td><td>"+tmp.remark+"</td><td>"+tmp.type+"</td><td>"+tmp.size+"</td><td>"+tmp.isNum+"</td></tr>"
			});
			str=str+"</tbody>"
			$("#columns").html(
				"<table class='table table-bordered'><tbhead><tr><th>列名称</th><th>列描述</th><th>列类型</th><th>列长度</th><th>是否允许空</th></tr></tbhead>"+str+"</table>"		
			);
			location.hash="#anchor";
		}
		
	});
}

function createClazz(v){
	var tableName = v;
	$.ajax({
		type:"POST",
		url:"getClazz.do",
		dataType:"JSON",
		data:{"tableName":tableName},
		success:function(data){
			$("#columns").html("<pre>"+data.objStr+"</pre>");
		}
	});
}

function modifyDbParam(){
	$('#saveChanges').popover('hide');
	$('#saveChanges').popover({content:"修改数据库参数失败",placement:"top",trigger:"click"});
	var dbType = $("#dbType").val();
	var dbDriver=$("#dbDriver").val();
	var dbUrl=$("#dbUrl").val();
	var dbUsername=$("#dbUsername").val();
	var dbPassword=$("#dbPassword").val();
	var data={"dbType":dbType,"dbDriver":dbDriver,"dbUrl":dbUrl,"dbUsername":dbUsername,"dbPassword":dbPassword};
	$.ajax({
		type:"POST",
		data:data,
		dataType:"JSON",
		url:"modifyDB.do",
		success:function(data){
			if(data.status="SUCCESS"){
				//修改成功之后执行隐藏功能$('#dbModal').modal('hide');
				$('#dbModal').modal('hide');
			}
		},
		error:function(data){
			$('#saveChanges').popover('show');
			
		}
	});
	
}

function reloadTables(){
	loadTables();
}

</script>
<style type="text/css">
	#center{
		margin-top: 55px;
	}
</style>
</head>
<body onload="loadTables();" >
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">DB-VIEWS</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <form class="navbar-form navbar-right">
            <button type="button" class="btn btn-danger navbar-right" data-toggle="modal" data-target="#dbModal">修改数据库配置文件</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
	<a id="anchor"></a>
    <div class="container" id="center">
    <!-- 文件上传并导入sql -->
    <div class="row">
    	<div class="input-group">
    	 	<span class="input-group-addon" id="basic-addon1">格式化的EXCEL文档</span>
    	 	<input type="file" id="file" name="file"  class="form-control" aria-describedby="basic-addon1">
    	 	<span id="showMsg" class="input-group-addon"></span>
    	</div>
    	<br/>
    	<div class="button-group">
    		<input type="button" class="btn btn-info" value="上传EXCEL文档并执行SQL语句" onclick="ajaxFileUpload();"/>
    		<a class="btn btn-success" href="javascript:reloadTables();">
			<i class="icon-repeat"></i> Reload</a>
    	</div>
    </div>
    <hr>
    <!-- 获取数据库表的数据库 -->
	<div class="row">
		<div class="col-md-4" id="tables">
		
        </div>
        <div class="col-md-8" id="columns">
		
        </div>
	</div>
    </div> 
    <!-- 数据库配置信息 -->
	<div class="modal fade" id="dbModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">数据库配置信息</h4>
				</div>
				<div class="modal-body">
				<form>
		          <div class="form-group">
		            <label for="recipient-name" class="control-label">数据库类型（例如：mysql，pgsql……）</label>
		            <input type="text" class="form-control" id="dbType">
		          </div>
		          <div class="form-group">
		           	<label for="recipient-name" class="control-label">数据库驱动（例如：com.mysql.jdbc.Driver）</label>
		            <input type="text" class="form-control" id="dbDriver">
		          </div>
		          <div class="form-group">
		           	<label for="recipient-name" class="control-label">数据库连接url（例如：jdbc:mysql://localhost:3306/dbview?useUnicode=true&characterEncoding=utf-8）</label>
		            <input type="text" class="form-control" id="dbUrl">
		          </div>
		          <div class="form-group">
		           	<label for="recipient-name" class="control-label">数据库账号（例如：admin）</label>
		            <input type="text" class="form-control" id="dbUsername">
		          </div>
		          <div class="form-group">
		           	<label for="recipient-name" class="control-label">数据库密码（例如:123456）</label>
		            <input type="text" class="form-control" id="dbPassword">
		          </div>
		        </form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" id="saveChanges" class="btn btn-primary" onclick="modifyDbParam();" >Save changes</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container" id="footer">
		<hr>
        <footer>
        <p>&copy; Company 2014</p>
        </footer>
    </div>
</body>
</html>