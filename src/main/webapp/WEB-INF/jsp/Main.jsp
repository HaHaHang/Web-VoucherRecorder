<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
<head>
<base href="<%=basePath%>">

<title>主界面</title>

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
<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/select2.min.css">
<link rel="stylesheet" type="text/css" href="css/messenger.css">
<link rel="stylesheet" type="text/css" href="css/messenger-theme-block.css">
<link rel="stylesheet" type="text/css" href="css/Main.css">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/dataTables.rowsGroup.js"></script>
<script type="text/javascript" src="js/messenger.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="js/select2.min.js"></script>
<script type="text/javascript" src="js/main.js"  charset="gb2312"></script>
<script type="text/javascript">
    $(function() {
        initMain("${UserInfo.user}", "${UserInfo.user.userid}");
         //start
        showBooksetList();
        initCustomSubjectTable();
    });
</script>
</head>
<body>
    <div class="modal fade" id="myModal1" tabindex="-1" data-role="dialog" data-aria-labelledby="myModalLabel" data-aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content" >
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" data-aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">增加科目</h4>
	            </div>
	            <div class="modal-body" style="width: 320px;height: 320px;">
	                 <form>
	                      <div class="form-group">

							    <label >科目编码</label>&nbsp;&nbsp;&nbsp;<label id="subjectNumberTool" style="color: red"></label>
							    <input type="text" class="form-control addSubject" id="subjectNumber" onkeyup="this.value = this.value.replace(/[^\dx]/g, '')"
							     placeholder="输入科目编码,如1049" maxlength="10" ><label for="subjectNumber" 
							     id="subjectTool"  ></label>
							    <br>
							    <label >科目名称</label>&nbsp;&nbsp;&nbsp;<label id="subjectNameTool" style="color: red"></label>
                                <input type="text" class="form-control addSubject" id="subjectName" placeholder="输入科目名称" maxlength="20">
                                <br>
                                <label >余额方向</label><br>
                                <label class="checkbox-inline">
								    <input type="radio" name="isload" value=false checked>借方
								</label>
								<label class="checkbox-inline">
                                    <input type="radio" name="isload" value=true >贷方
                                </label><br><br>
                                <label>科目类别</label>&nbsp;&nbsp;&nbsp;<label id="subjectCateTool" style="color: red"></label>
                                <select class="form-control " id="subjectSelect">
                                <option selected="selected" value="0" disabled="disabled"  style="color:#FF0000;background-color:#DDDDDD;font-weight:bold" >请选择科目类别</option>
                                <c:forEach items="${UserInfo.subjcatelist }" var="su" varStatus="i">
                                    <c:choose>
                                    <c:when test="${i.count == 1}">
                                        <option disabled="disabled" style="background-color:#DDDDDD;font-weight:bold">资产类</option>
                                    </c:when>
                                    <c:when test="${i.count == 3}">
                                        <option disabled="disabled" style="background-color:#DDDDDD;font-weight:bold">负债类</option>
                                    </c:when>
                                    <c:when test="${i.count == 5}">
                                        <option disabled="disabled" style="background-color:#DDDDDD;font-weight:bold">权益类</option>
                                    </c:when>
                                    <c:when test="${i.count == 6}">
                                        <option disabled="disabled" style="background-color:#DDDDDD;font-weight:bold">成本类</option>
                                    </c:when>
                                    <c:when test="${i.count == 7}">
                                        <option disabled="disabled" style="background-color:#DDDDDD;font-weight:bold">损益类</option>
                                    </c:when>
                                    </c:choose>
                                    <option value="${su.subcatid }">
                                        <c:out value="${su.subcatname }"></c:out>
                                    </option>
                                </c:forEach>
                                </select>
                                <br>
	                      </div>
	                 </form>
                </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" disabled="disabled" id="addSubjectAdd">增添</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" data-role="dialog"
		data-aria-labelledby="myModal" data-aria-hidden="true"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">账套管理</h4>
				</div>
				<div class="modal-body">
					<div class="input-group">
						<span class="input-group-addon">公司名称</span><input id="compname"
							class="form-control" type="text" placeholder="请输入公司名称"
							style="width:350px"
							onkeyup="this.value = this.value.replace(/(?![\u4e00-\u9fa5])[^\w]/g,'')">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-primary" id="modalSubmit">
							创建账套</button>
					</div>
				</div>
				<div class="modal-footer" id="booksetinfo">
					<table class="table" id="addbooksetlocal">

					</table>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<!-- 头部:导航栏 -->
	<nav class="navbar navbar-default" data-role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" data-role="tab" data-toggle="tab" href="#zero">凭证通</a>
			</div>
			<div class="navbarright">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a
						class="dropdown-toggle" id="nowbookset" data-toggle="dropdown"></a>
						<ul class="dropdown-menu">
							<li><a data-toggle="modal" data-target="#myModal">账套管理</a>
							</li>
						</ul></li>

					<li><a href="logout">注销</a>
					</li>
					<li class="active"><a>${UserInfo.user.name }</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="row">
		<!-- 左侧:折叠标签卡 -->
		<div class="col-md-2 div_left">
			<div class="panel-group" id="accordion">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseOne"> 菜单 </a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body left-list-1" >
							<ul id="table-menu" class="nav nav-list collapse in">
								<li><a class="meun-item" href="#one" id="voucherEdit" 
									data-aria-controls="one" data-role="tab" data-toggle="tab">凭证编写</a>
								</li>
								<li><a class="meun-item" href="#two" id="openVoucherQueryTable"
									data-aria-controls="two" data-role="tab" data-toggle="tab">凭证查询</a>
								</li>
								<li><a class="meun-item" href="#three" id="openVoucherSummaryTable"
									data-aria-controls="three" data-role="tab" data-toggle="tab">凭证汇总表</a>
								</li>
								<li><a class="meun-item" href="#four" 
                                    data-aria-controls="four" data-role="tab" data-toggle="tab">科目设置</a>
                                </li>
							</ul>
						</div>
					</div>
				</div>
<!-- 			<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseTwo"> 账簿条目 </a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse">
						<div class="panel-body">
							<ul id="table-menu" class="nav nav-list collapse in">
								<li><a href="#">明细账</a>
								</li>
								<li><a href="#">总账</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion"
								href="#collapseThree"> 报表条目 </a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse">
						<div class="panel-body">
							<ul id="table-menu" class="nav nav-list collapse in">
								<li><a href="#">资产负债表</a>
								</li>
								<li><a href="#">利润表</a>
								</li>
								<li><a href="#">现金流量表</a>
								</li>
							</ul>
						</div>
					</div>
				</div> -->
			</div>
		</div>
		<!-- 右侧:内容栏 -->
		<div class="col-md-10 div_right">
			<div class="tab-content">

				<!-- 标签卡0:欢迎页面 -->
				<div class="tab-pane active" id="zero">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">欢迎来到记账通</h3>
						</div>
						<div class="panel-body">你好,欢迎来到这里！！</div>
					</div>
				</div>

				<!-- 标签卡1:凭证编写 -->
				<div class="tab-pane" id="one">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">凭证编写</h3>
						</div>
						<div class="panel-body">
							<form action="">
								<div class="row">
									<div class="col-md-3">
										<div class="input-group spinner">
											<span class="input-group-addon">凭证字记</span><input
												class="form-control" type="text" maxlength="2"
												style="width:42px" id="numofvoucher"
												onkeyup="this.value = this.value.replace(/[^\dx]/g, '')">
												 <div class="input-group-btn-vertical">  
											      <button class="btn btn-default updown" type="button">
											      <i class="fa fa-caret-up"><span class="glyphicon glyphicon-plus" data-aria-hidden="true"></span></i></button>  
											      <button class="btn btn-default updown" type="button">
											      <i class="fa fa-caret-down"><span class="glyphicon glyphicon-minus" data-aria-hidden="true"></span></i></button></div>   
   												  <span class="input-group-addon" >号</span>
										</div>
									</div>
									   <div class="col-md-3">
										<div class="input-group">
											<span class="input-group-addon">日期</span><input
												class="form_datetime  form-control" type="text"
												value="2016-03-21" size="8px" id="dateview"
												style="width:225px" name="date">
										</div>
									   </div>
									   <div class="col-md-1 col-md-offset-1">
										<div class="input-group voucherheader">
											<input type="text" class="form-control " id="accounteidt"
												size="8px" style="width:79px" maxlength="2"
												readonly="readonly"><span
												class="input-group-addon">期</span>
										</div>
									   </div>
									   <div class="col-md-2 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon voucherheader">附单据</span><input
												class="form-control" type="text" style="width:42px"
												maxlength="2" value="0" id="docNum"
												onkeyup="this.value = this.value.replace(/[^\dx]/g, '')
												"><span
												class="input-group-addon">张</span>
										</div>
									   </div>
									<input type="hidden" value="" id="updateVoucherId" >
								</div>
								<table id="voucherTable" class="table table-striped">
									<thead>
										<tr>
											<th>摘要</th>
											<th>科目</th>
											<th>借方金额</th>
											<th>贷方金额</th>
										</tr>
									</thead>
									<tbody >
										<tr class="exampleRow">
											<td class="td_note">
												<div >
													<input id="vouchernote1" type="text" maxlength="64" class="form-control" name="note">
												</div>
											</td>
											<td class="td_subject">
												<div >
													<select class="form-control select2" id="voucherselect1"> 
														<c:forEach items="${UserInfo.subjlist }" var="sub">
															<option value="${sub.subjectid }">
																<c:out value="${sub.subjectid } ${sub.subjectname }"></c:out>
															</option>
														</c:forEach>
													</select>
												</div>
											</td>
											<td class="td_debit">
												<div class="input-group">
													<span class="input-group-addon">￥</span><input type="text"
														onkeyup="this.value = formatmoney(this.value)"
														onblur="this.value =  formatmoney2(this.value)"
														onclick="clearenemy(this,'u')" id="voucherdebit1"
														class="form-control debit" maxlength="12">
												</div>
											</td>
											<td class="td_credit">
												<div class="input-group">
													<span class="input-group-addon">￥</span><input type="text"
														onkeyup="this.value = formatmoney(this.value)"
														onblur="this.value =  formatmoney2(this.value)"
														onclick="clearenemy(this,'d')" id="vouchercredit1"
														class="form-control credit" maxlength="12">
												</div>
											</td>
										</tr>
										<tr class="exampleRow">
											<td class="td_note">
												<div >
													<input type="text" maxlength="64" class="form-control" name="note">
												</div>
											</td>
											<td class="td_subject">
												<div >
													<select class="form-control select2"> 
														
														<c:forEach items="${UserInfo.subjlist }" var="sub">
															<option value="${sub.subjectid }">
																<c:out value="${sub.subjectid } ${sub.subjectname }"></c:out>
															</option>
														</c:forEach>
													</select>
												</div>
											</td>
											<td class="td_debit">
												<div class="input-group">
													<span class="input-group-addon">￥</span><input type="text"
														onkeyup="this.value = formatmoney(this.value)"
														onblur="this.value =  formatmoney2(this.value)" maxlength="12"
														onclick="clearenemy(this,'u')" class="form-control debit">
												</div>
											</td>
											<td class="td_credit">
												<div class="input-group ">
													<span class="input-group-addon">￥</span><input type="text"
														onkeyup="this.value = formatmoney(this.value)"
														onblur="this.value =  formatmoney2(this.value)" maxlength="12"
														onclick="clearenemy(this,'d')" class="form-control credit">
												</div>
											</td>
										</tr>
										<tr class="exampleRow">
                                            <td class="td_note">
                                                <div >
                                                    <input type="text" maxlength="64" class="form-control" name="note">
                                                </div>
                                            </td>
                                            <td class="td_subject">
                                                <div >
                                                    <select class="form-control select2"> 
                                                        <c:forEach items="${UserInfo.subjlist }" var="sub">
                                                            <option value="${sub.subjectid }">
                                                                <c:out value="${sub.subjectid } ${sub.subjectname }"></c:out>
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </td>
                                            <td class="td_debit">
                                                <div class="input-group">
                                                    <span class="input-group-addon">￥</span><input type="text"
                                                        onkeyup="this.value = formatmoney(this.value)"
                                                        onblur="this.value =  formatmoney2(this.value)" maxlength="12"
                                                        onclick="clearenemy(this,'u')" class="form-control debit">
                                                </div>
                                            </td>
                                            <td class="td_credit">
                                                <div class="input-group ">
                                                    <span class="input-group-addon">￥</span><input type="text"
                                                        onkeyup="this.value = formatmoney(this.value)"
                                                        onblur="this.value =  formatmoney2(this.value)" maxlength="12"
                                                        onclick="clearenemy(this,'d')" class="form-control credit">
                                                </div>
                                            </td>
                                        </tr>
                                        <tr class="exampleRow">
                                            <td class="td_note">
                                                <div >
                                                    <input type="text" maxlength="64" class="form-control" name="note">
                                                </div>
                                            </td>
                                            <td class="td_subject">
                                                <div >
                                                    <select class="form-control select2"> 
                                                        
                                                        <c:forEach items="${UserInfo.subjlist }" var="sub">
                                                            <option value="${sub.subjectid }">
                                                                <c:out value="${sub.subjectid } ${sub.subjectname }"></c:out>
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </td>
                                            <td class="td_debit">
                                                <div class="input-group">
                                                    <span class="input-group-addon">￥</span><input type="text"
                                                        onkeyup="this.value = formatmoney(this.value)"
                                                        onblur="this.value =  formatmoney2(this.value)" maxlength="12"
                                                        onclick="clearenemy(this,'u')" class="form-control debit">
                                                </div>
                                            </td>
                                            <td class="td_credit">
                                                <div class="input-group ">
                                                    <span class="input-group-addon">￥</span><input type="text"
                                                        onkeyup="this.value = formatmoney(this.value)"
                                                        onblur="this.value =  formatmoney2(this.value)" maxlength="12"
                                                        onclick="clearenemy(this,'d')" class="form-control credit">
                                                </div>
                                            </td>
                                        </tr>
                                        <tr class="exampleSumRow" style="background-color: #CEF">
                                            <td colspan="2"><b>合计</b></td>  
                                            <td><div class="input-group "><span class="input-group-addon">￥</span><input type="text" disabled="disabled" class="form-control" id="debitSum"></div></td>
                                            <td><div class="input-group "><span class="input-group-addon">￥</span><input type="text" disabled="disabled" class="form-control" id="creditSum"></div></td>
                                        </tr>
									</tbody>
								</table>
								<div>
									<button id="addARow"
										class="col-md-1 col-md-offset-1 btn btn-info" type="button">新增一行</button>
									<button id="delARow"
										class=" btn btn-info" type="button">删除一行</button>
									<button class="col-md=1 col-md-offset-4 btn btn-success" id="updatevoucher"
										type="button"  style="display: none" >修改</button>
									<button class="btn btn-success" id="getupupdatevoucher"
										type="button"  style="display: none" onclick="getupupdatevoucher1()">放弃</button>
									<button class="col-md=1 col-md-offset-4 btn btn-success" id="submitvoucher"
										type="button">录制</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- 标签卡2:凭证查询 -->
				<div class="tab-pane" id="two">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">凭证查询</h3>
						</div>
						<div class="panel-body">
							<table  class="display" id="queryVoucherTable" style="width: 100%">
								<thead><tr><th>日期</th><th>凭证号</th><th>摘要</th><th>科目</th>
										<th>借方金额</th><th>贷方金额</th><th>操作</th></tr></thead>
								
								<tfoot><tr><th>日期</th><th>凭证号</th><th>摘要</th><th>科目</th>
										<th>借方金额</th><th>贷方金额</th><th>操作</th></tr></tfoot>
							</table>
						</div>
					</div>
				</div>
				<!-- 标签卡3:凭证汇总表 -->
				<div class="tab-pane" id="three">
					<div class="panel panel-default">
					<div class="panel-heading">
							<h3 class="panel-title">凭证汇总表</h3>
						</div>
						<div class="panel-body">
						<div class="input-group" style="margin-left:475px"><span class="input-group-addon">账期</span>
						<select class="form-control t_select" id="selectAccountPeriod1" style=" margin-left:-100px;cellspacing:0;width: 100px;">
							</select></div>
							<table  class="table table-striped" id="voucherSummaryTable">
								<thead><tr><th></th><th>科目编号</th><th>科目名称</th><th>借方金额</th><th>贷方金额</th></tr></thead>
								<tfoot><tr><th></th><th>科目编号</th><th>科目名称</th><th>借方金额</th><th>贷方金额</th></tr></tfoot>
							</table>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="four">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">科目设置</h3></div>
                        <div class="panel-body">
                            <button  class="btn btn-primary" data-toggle="modal" data-target="#myModal1">增加科目</button>
                            <table class="table table-striped" id="CustomSubjectTable">
                                <thead><tr><th>类别</th><th>编码</th><th>名称</th><th>余额方向</th><th>删除</th></tr></thead>
                                <tfoot><tr><th>类别</th><th>编码</th><th>名称</th><th>余额方向</th><th>删除</th></tr></tfoot>
                            </table>
                        </div>
                    </div>
                </div>
			</div>
		</div>
	</div>
	<img id="aflower" alt="" src="images/flower.png">
</body>
</html>
