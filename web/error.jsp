<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	   
	<title>My JSP 'error.jsp' starting page</title>
	   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="Bootstrap/css/bootstrap.min.css">
	<script src="JQuery/jquery-3.2.0.min.js"></script>
	<script src="Bootstrap/js/bootstrap.min.js"></script>
	
	<script>
		function goBack(){
			window.history.back()
		}
	</script>

</head>
  
<body>
	<div class="alert alert-danger">
		error page
		error message:${error_message};<button onclick="goBack()">GoBack</button>
		<s:select list="#{'1':'Yang','2':'Wang','3':'Liu'}" listKey="key" listValue="value"></s:select>
	</div>
</body>
</html>
