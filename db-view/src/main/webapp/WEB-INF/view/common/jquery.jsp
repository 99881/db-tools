<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script type="text/javascript" src="${contextPath}resources/jquery/jquery-1.11.2.min.js" charset="utf-8"></script>
<% 
String s=request.getHeader("User-Agent");
if (s!=null && s.indexOf("MSIE")>=0) {
%>
<script type="text/javascript" src="${contextPath}resources/jquery/json2.js" charset="utf-8"></script>
<%}; %>
<script type="text/javascript" src="${contextPath}resources/jquery-validation/1.12.0/jquery.validate.js"></script>
<script type="text/javascript" src="${contextPath}resources/jquery-validation/1.12.0/messages.js"></script>

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

String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

</script>

