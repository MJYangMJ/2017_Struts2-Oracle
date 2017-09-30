<%--
  Created by IntelliJ IDEA.
  User: yang
  Date: 2017/6/25
  Time: 18:39
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

    <title>Add Page</title>

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
        #case{
            display: none;
        }
        /*.form-group{*/
            /*padding-top: 20px;*/
        /*}*/
    </style>

    <script type="text/javascript">
        var temp_i = 0;
        $(document).ready(function () {
            var today = new Date();
            $(".panel-footer").append("<p id='time_set'>current time:"+today);
        })
        setInterval(function get_Date(){
            var today = new Date();
            $(".panel-footer p").remove();
            $(".panel-footer").append("<p id='time_set'>current time:"+today);
        },1000);
        $(document).ready(function(){
            $("#word_input input").focus();
        });

    </script>
</head>
<body>
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
                <li><a href="search.jsp">Search</a></li>
                <li class="active disabled"><a href="add.jsp">Add</a></li>
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
                <div class="panel panel-default">
                    <div class="panel-heading">Add New Word To DataBase</div>
                    <div class="panel-body">
                        <p class="panel-title">New Word Information</p>
                        <p>Please follow the step to add new word</p>
                        <div id="form_div">
                            <form role="form" method="post" action="search/addwords?transform=true">
                                <div class="form-group">
                                    <label for="character">character</label>
                                    <s:select list="#session.characterList" listKey="characterInfo" listValue="characterInfo" cssClass="form-group" id="character" name="globalCharacter.characterInfo">

                                    </s:select>
                                </div>
                                <div class="form-group">
                                    <label for="classes">classes</label>
                                    <s:select list="#session.classesList" listKey="classesInfo" listValue="classesInfo" cssClass="form-group" id="classes" name="globalClasses.classesInfo">

                                    </s:select>
                                </div>
                                <div class="form-group">
                                    <label class="checkbox-inline">transform
                                        <input class="form-control btn btn-primary" type="button" data-toggle="button" id="transform" name="transform" value="toggle">
                                    </label>
                                    <%--<label class="checkbox-inline">upcase--%>
                                        <%--<input class="form-control" type="radio" id="upcase" name="case" value="upcase">--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">lowcase--%>
                                        <%--<input class="form-control" type="radio" id="lowcase" name="case" value="lowcase">--%>
                                    <%--</label>--%>
                                    <label id="case" class="checkbox-inline">CaseSelect
                                        <s:radio label="CaseSelect" list="#{'1':'upcase','2':'lowcase'}" value="2" name="case_info"></s:radio>
                                    </label>
                                </div>
                                <div id="word_input" class="form-group">
                                    <input class="form-control" type="text" name="globalWords.word" placeholder="Input the word">
                                </div>
                                <div id="paraphrase_input" class="form-group">
                                    <input class="form-control" type="text" name="globalWords.paraphrase" placeholder="Input the paraphrase">
                                </div>
                                <div id="example_input" class="form-group">
                                    <input class="form-control" type="text" name="globalWords.example" placeholder="Input the example sentence">
                                </div>
                                <button id="submit" type="submit" class="btn btn-primary">AddWord</button>
                                <%--<button id="reset" type="reset" class="btn btn-primary">Reset</button>--%>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="panel-footer">author:${username}</div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $("#transform").on("click",function () {
            var trans;
            if(temp_i++%2===1)
            {
//                $("input[name='case']").css("display","none");
                $("#case").css("display","none");
                trans = true;
            }
            else
            {
//                $("input[name='case']").css("display","block");
                $("#case").css("display","block");
                trans = false;
            }
            var str_action = "search/addwords?transform="+trans;
            $("form").attr("action",str_action);
        })
        $("#word_input input").blur(function () {
            var $word = $("#word_input");
            $word.removeClass("has-error").removeClass("has-feedback");
            $("#word_input span").remove();
            if($(this).val()==null||$(this).val()==''){
                    $word.addClass("has-error").addClass("has-feedback");
                    $word.append("<span class='help-block'>The message you input is error</span>")
                        .append("<span class='glyphicon glyphicon-remove form-control-feedback'></span>");
            }
            else{
                $word.addClass("has-success").addClass("has-feedback");
                $word.append("<span class='help-block'>The message you input is ok</span>")
                     .append("<span class='glyphicon glyphicon-ok form-control-feedback'></span>");
            }
//            alert("no focus");
        });
        $("#paraphrase_input input").blur(function () {
            var $para = $("#paraphrase_input");
            $para.removeClass("has-error").removeClass("has-feedback");
            $("#paraphrase_input span").remove();
            if($(this).val()==null||$(this).val()==''){
                $para.addClass("has-error").addClass("has-feedback");
                $para.append("<span class='help-block'>The message you input is error</span>")
                     .append("<span class='glyphicon glyphicon-remove form-control-feedback'></span>");
            }
            else{
                $para.addClass("has-success").addClass("has-feedback");
                $para.append("<span class='help-block'>The message you input is ok</span>")
                     .append("<span class='glyphicon glyphicon-ok form-control-feedback'></span>");
            }
//            alert("no focus");
        });
        $("#example_input input").blur(function () {
            var $exa = $("#example_input");
            $exa.removeClass("has-warning").removeClass("has-feedback");
            $("#example_input span").remove();
            if($(this).val()==null||$(this).val()==''){
                $exa.addClass("has-warning").addClass("has-feedback");
                $exa.append("<span class='help-block'>The message you input is not so good</span>")
                    .append("<span class='glyphicon glyphicon-warning-sign form-control-feedback'></span>");
            }
            else{
                $exa.addClass("has-success").addClass("has-feedback");
                $exa.append("<span class='help-block'>The message you input is ok</span>")
                    .append("<span class='glyphicon glyphicon-ok form-control-feedback'></span>");
            }
//            alert("no focus");
        });
    </script>
</body>
</html>
