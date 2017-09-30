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
    
    <title>Search Begin</title>
    
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
			margin: 20px;
		}
        .container{
            padding-top: 70px;
        }
        form div{
            padding-left: 50px;
        }
        /*#word_input{*/
            /*position: relative;*/
            /*left: 450px;*/
        /*}*/
        #submit{
            float: right;
        }
    </style>

  </head>
  
  <body>
    <h3>This is success page.Now you can search words</h3>
    <%--<h4>Hello&nbsp;<s:property value="globalUser.username"/>&nbsp;and you login as id=&nbsp;<s:property value="globalUser.id"/></h4>--%>
    <h4>Hello&nbsp;${username}&nbsp;and you login as id=&nbsp;${id}</h4>
 <!--    <s:a action="search/showuser.action">click me</s:a> -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-responsive-collapse">
                <!--data-target's value must be nav's classname or idname,if not the nav will not show but icon-bar-->
                <span class="sr-only">Toggle Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand">W-Search</a>
        </div>
        <div class="collapse navbar-collapse navbar-responsive-collapse" id="navbar-responsive-collapse">
            <ul class="nav navbar-nav">
                <li class="active disabled"><a href="search.jsp">Search</a></li>
                <li><a href="add.jsp">Add</a></li>
                <li><a href="search_index.jsp">SearchLogin</a></li>
                <li><a href="index.jsp">ManagerLogin</a></li>
                <li><a href="#" data-toggle="modal" data-target="#mymodal">Contact</a></li>
            </ul>
        </div>
    </div>

    <div class="modal fade" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="mymodal" aria-hidden="true" data-backdrop="static" data-keyboard="true" data-show="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title">Following are the details of this program</h4>
                </div>
                <div class="modal-body">
                    <p>14103303 方彧冬</p>
                    <p>14103403 冯宇壮</p>
                    <p>14103419 杨梦杰</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

 	<div class="container">
        <div class="row">
            <div class="col-md-12">
                <form action="search/searchwords" class="form-inline" role="form" method="post">
                    <div class="form-group">
	                    <label for="character">character</label>
	                    <s:select list="#session.characterList" listKey="characterInfo" headerKey="*" headerValue="*" listValue="characterInfo" cssClass="form-group" id="character" name="globalCharacter.characterInfo">

	                    </s:select>
                    </div>
                    <div class="form-group">
	                    <label for="classes">classes</label>
	                    <s:select list="#session.classesList" listKey="classesInfo" headerKey="*" headerValue="*" listValue="classesInfo" cssClass="form-group" id="classes" name="globalClasses.classesInfo">

	                    </s:select>
                    </div>
                    <div class="form-group checkbox ">
                        <label for="transform">transform</label>
                        <input class="form-control" type="checkbox" id="transform" name="transform">
                    </div>
                    <div class="pull-right">
                        <div id="word_input" class="form-group">
                            <input class="form-control" type="text" name="globalWords.word" placeholder="Input the word">
                            <span class="glyphicon glyphicon-search"></span>
                        </div>
                        <button id="submit" type="submit" class="btn btn-primary form-control" style="padding-left: 20px;" method="search/searchwords?
                            globalUser.username=<s:property value='globalUser.username'/>&
                            globalUser.id=<s:property value='globalUser.id'/>&n=<s:property value='1'/>">Search</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <table id="word_result" class="table table-responsive table-striped table-bordered">
                    <caption>Result Table</caption>
                    <thead>
                        <tr>
                            <th>WordId</th>
                            <th>Word</th>
                            <%--<th>Character</th>--%>
                            <%--<th>Classes</th>--%>
                            <th>Paraphrase</th>
                            <th>Example</th>
                            <th>Frequency</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="wordList">
                            <tr>
                                <td><s:property value="wordId"/></td>
                                <td><s:property value="word"/></td>
                                <%--<td><s:property value="characterId"/></td>--%>
                                <%--<td><s:property value="classesId"/></td>--%>
                                <td><s:property value="paraphrase"/></td>
                                <td><s:property value="example"/></td>
                                <td><s:property value="frequency"/></td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
                <ul class="pagination pull-right">
                    <li><a href="<s:url action="search/searchwords.action"><s:param name="n" value="1"></s:param></s:url>">&laquo;</a></li>
                    <li><a href="<s:url action="search/searchwords.action"><s:param name="n" value="1"></s:param></s:url>">1</a></li>
                    <li><a href="<s:url action="search/searchwords.action"><s:param name="n" value="2"></s:param></s:url>">2</a></li>
                    <li><a href="<s:url action="search/searchwords.action"><s:param name="n" value="3"></s:param></s:url>">3</a></li>
                    <li><a href="<s:url action="search/searchwords.action"><s:param name="n" value="4"></s:param></s:url>">4</a></li>
                    <li><a href="<s:url action="search/searchwords.action"><s:param name="n" value="5"></s:param></s:url>">5</a></li>
                    <li><a href="<s:url action="search/searchwords.action"><s:param name="n" value="5"></s:param></s:url>">&raquo;</a></li>
                </ul>

            </div>
        </div>
        <div class="row">
            <div class="col-md-2 col-md-offset-5">
                <h5>No More To Show</h5>
            </div>
        </div>
	</div>

  <script type="text/javascript">

  </script>



</body>
</html>
