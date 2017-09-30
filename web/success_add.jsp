<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 2017/6/25
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>Title</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="Bootstrap/css/bootstrap.min.css">
    <script src="JQuery/jquery-3.2.0.min.js"></script>
    <script src="Bootstrap/js/bootstrap.min.js"></script>

    <style type="text/css">
        body{
            padding-top: 300px;
        }
    </style>
</head>
<body>
<center>
    <%--<p><s:a action="search/addwords">add word page</s:a></p>--%>
    <%--<p><s:a action="search/searchwords">search word page</s:a></p>--%>
    <p><a href="search.jsp">search word page</a></p>
    <p><a href="add.jsp">add word page</a></p>
</center>
</body>
</html>
