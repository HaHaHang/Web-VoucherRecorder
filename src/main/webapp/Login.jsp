<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE >
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户登陆页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-datetimepicker.css">
	<link rel="stylesheet" type="text/css" href="css/Login.css">
	<link rel="stylesheet" type="text/css" href="css/messenger.css">
    <link rel="stylesheet" type="text/css" href="css/messenger-theme-block.css">
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/messenger.js"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js"></script>
  	<script type="text/javascript">
  	$(function() {
  	         $._messengerDefaults = {
                extraClasses: 'messenger-fixed messenger-theme-block messenger-on-bottom'
             };
             function alertMessage(str,str1){
                Messenger().post({
                    message: str,
                    type: str1,    
                    showCloseButton: true,
                    hideAfter: 3,
                });
             }
         $("#submit").click(function(){
                if($("#username").val() == "" && $("#password").val() == ""){
                    alertMessage("非法登陆","error");
                }else{
                    $.ajax({
                        type:"post",
                        url:"login",
                        data:"username="+$("#username").val()+"&password="+$("#password").val(),
                        success:function(data){
                        
                            if(eval('('+data+')').result) {
                                alertMessage("登陆成功","success");
                                aform.submit();
                            }else{
                                alertMessage("口令错误","error");
                            }
                        }
                    });
                }
         });
  	});
  	
  	
  	

                
  	    
  	</script>
  </head>	
  
  <body>
   		<div class="container">
		    <div class="row">
		        <div class="col-md-offset-3 col-md-6">
		            <div class="form-horizontal">
		                <span class="heading">用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;录</span>
		                <div class="form-group">
		                    <input type="text" class="form-control" id="username" name="username" placeholder="用  户  名" onfocus="clear()">
		                    <i class="fa fa-user"></i>
		                </div>
		                <div class="form-group help">
		                    <input type="password" class="form-control" id="password" name="password" placeholder="密  码" onclick="clear()">
		                    <i class="fa fa-lock"></i>
		                </div>
		                <div class="form-group">
		                    <div class="main-checkbox">
                            <input type="checkbox" value="None" id="checkbox1" name="check"/>
                            <label for="checkbox1"></label>
                            </div>
                            <span class="text">记住 我</span>
                            <button class="btn btn-default" id="submit">登录</button>
		                </div>
		            </div>
		        </div>
		        <form action="Main" method="post" id="aform"></form>
		    </div>
		</div>
  </body>
</html>
