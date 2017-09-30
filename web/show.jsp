<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
   	<title>Users list</title>
   
   	<link rel="stylesheet" type="text/css" href="Bootstrap/css/bootstrap.min.css">
	<script src="JQuery/jquery-3.2.0.min.js"></script>
	<script src="Bootstrap/js/bootstrap.min.js"></script>
   	<style type="text/css">
    	body{
			padding-top: 100px;
		}   	
		.row{
			padding-top:5px;
			padding-bottom:5px;
		}
   	</style>
</head>
  
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-1 col-md-offset-2">
				<span class="label label-info"><strong>Current</strong></span>
			</div>
		</div>
		<div class="row">
			<div class="col-md-1 col-md-offset-3">
				<span class="glyphicon glyphicon-user red"></span>:
					<h3><s:property value="username"/></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
			    <table class="table table-striped table-bordered table-hover">
			    	<caption>User Tables</caption>
			    	<thead>
			    	<tr>
			    		<th>&nbsp;&nbsp;UserId</th>
			    		<th>&nbsp;&nbsp;UserName</th>
			    		<th>&nbsp;&nbsp;Password</th>
			    		<th colspan="2">&nbsp;&nbsp;Options</th>
			    	</tr>
			    	</thead>
			    	<tbody>
				    	<s:iterator value="userlist" >
							<tr>
								<td>&nbsp;&nbsp;<s:property value="id" /></td>
								<td>&nbsp;&nbsp;<s:property value="username" /></td>
								<td>&nbsp;&nbsp;<s:property value="password" /></td>
								<td>&nbsp;&nbsp;<a class="disabled" href="update.jsp?id=<s:property value='id' />&username=<s:property value='username' />" >update</a></td>
								<td>&nbsp;&nbsp;<a href="delete.action?id=<s:property value='id' />" >delete</a></td>
							</tr>
						</s:iterator>
				    </tbody>
			    </table>
			</div>
		</div>
	</div>
 </body>
</html>
