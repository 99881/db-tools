<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@include file="jquery.jsp" %>
<%@include file="easyui.jsp" %>
<script type="text/javascript">

function serializeObject(form){
	var o = {};
	$.each(form.serializeArray(),function(index){
		if(o[this['name']]){
			o[this['name']] = o[this['name']]+","+this['value'];
		}else{
			o[this['name']] = this['value'];
		}
	});
	return o;
}
</script>
</head>
