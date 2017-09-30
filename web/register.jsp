<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
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
			padding-top: 100px;
		}
	</style>
	
	

  </head>
  
  <body>
    <div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-4">
				<h2>Welcome to W-Search</h2>
			</div>
		</div>
		<div class="row" style="padding-top:50px">
			<div class="well col-md-6 col-md-offset-3">
				<form class="form-horizontal" action="register" method="post">
					<h2 class="">Register</h2>
					<table class="table table-bordered" style="positioning:center">
						<tr>
							<!-- 用户名的表单域 -->
							<td>
								<div class="input-group input-group-md">
									<span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span><input class="form-control" placeholder="input username" type="text" name="username" />
								</div>
							</td>
						</tr>
						<tr>
							<!-- 密码的表单域 -->
							<td>
								<div class="input-group input-group-md">
									<span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span><input class="form-control" placeholder="input password" type="password" name="password" />
								</div>
							</td>
						</tr>
						<tr align="center">
							<td colspan="2" align="right">
								<input class="btn btn-primary" type="submit" value="Register"/>
								<input class="btn btn-primary" type="reset" value="Reset"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<!-- 提交请求参数的表单 -->
		
		
	<script type="text/javascript" color="0,0,255" opacity='1.0' zIndex="-2" count="99" src="CSS/canvas-nest.min.js"></script>

  </body>
</html>
