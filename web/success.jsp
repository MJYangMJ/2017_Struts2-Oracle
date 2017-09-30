<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">
        body{
            padding-top: 100px;
            align: center;
        }
    </style>
  </head>
  
  <body>
    <h3>This is success page.Only Yang can use show action</h3>
    <h4>Hello&nbsp;<s:property value="username"/>&nbsp;and you login as id=&nbsp;<s:property value="id"/>
    <br /><br />
    <a href="show.action?username=<s:property value='username'/>">show user table</a>
  </body>
</html>
