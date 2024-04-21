<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>

<base href="<%=basePath%>">
<head>
<meta charset="UTF-8">

<link href="static/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="static/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="static/jquery/bs_pagination-master/css/jquery.bs_pagination.min.css">

<script type="text/javascript" src="static/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="static/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="static/jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<%--引入翻页插件开发包--%>
<script type="text/javascript" src="static/jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
<script type="text/javascript" src="static/jquery/bs_pagination-master/localization/en.js"></script>
<script type="text/javascript">

	$(function(){
		//给“创建”按钮添加单机事件
		$("#createActivityBtn").click(function(){
			//重置表单
			$("#createActivityForm").get(0).reset();

			//单机创建按钮弹出模态窗口
			$("#createActivityModal").modal("show");
		});
		//给保存按钮添加单机事件
		$("#saveCreateActivity").click(function (){
			//收集参数
			var owner=$("#create-marketActivityOwner").val();
			var name=$.trim($("#create-marketActivityName").val());
			var startDate=$("#create-startDate").val();
			var endDate=$("#create-endDate").val();
		 	var cost=$.trim($("#create-cost").val());
			var description=$.trim($("#create-describe").val());
			//表单验证
			if(owner==""){
				alert("所有者不能为空");
				return;
			}
			if(name==""){
				alert("名称不能为空");
				return;
			}
			if(startDate!=""&&endDate!=""){
				if(startDate>endDate){
					alert("结束日期不能比开始日期小");
					return;
				}
			}else{
				alert("日期不能为空");
			}
			//
			var regExp=/^(([1-9]\d*)|0)$/;
			if(!regExp.test(cost)){
				alert("成本只能为非负整数");
			}
			//发送请求
			$.ajax({
				url:"workbench/activity/saveCreateActivity",
				data:{
					owner:owner,
					name:name,
					startDate:startDate,
					endDate:endDate,
					cost:cost,
					description:description
				},
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.code=="1"){
						//关闭模态窗口
						$("#createActivityModal").modal("hide");
						//刷新市场活动先世道页面
						queryActivityByConditionForPage(1,$("#demo_page1").bs_pagination('getOption','rowsPerPage'));
					}else{
						//提示信息
						alert(data.message);
						//模态窗口不关闭
						$("#createActivityModal").modal("show");
					}
				}
			});
		});
		//分页
		//当容器加载完成时，对容器调用工具函数
		$(".mydate").datetimepicker({
			language:'zh-CN',//语言
			format:'yyyy-mm-dd',//日期格式
			minView:'month',//可以选择最小视图
			initialDate:new Date(),//初始化显示的日期
			autoclose:true,//设置选择完日期或事件之后，日历是否自动关闭
			todayBtn:true,//设置日期显示‘今天’按钮，默认为false
			clearBtn:true//设置显示清空按钮。默认为false
		});
		//点击市场活动显示页面，默认显示第一页，默认显示十条数据
		queryActivityByConditionForPage(1,10);
		//给查询按钮添加单机事件，默认显示第一页，默认显示十条数据
		$("#queryActivityBtn").click(function (){
			queryActivityByConditionForPage(1,$("#demo_page1").bs_pagination('getOption','rowsPerPage'));

		});

		//增加全选按钮
		$("#checkAll").click(function (){
			$("#tBody input[type='checkbox']").prop("checked",this.checked);
		});
		$("#tBody").on("click","input[type='checkbox']",function (){
			if($("#tBody input[type='checkbox']").size()==$("#tBody input[type='checkbox']:checked").size()){
				$("#checkAll").prop("checked",true);
			}else{
				$("#checkAll").prop("checked",false);
			}
		});

		//给删除按钮添加单击事件
		$("#deleteActivityBtn").click(function(){
			//收集参数
			var checkedIds=$("#tBody input[type='checkbox']:checked");
			if(checkedIds.size()==0){
				alert("请选择要删除的活动");
				return;
			}
			if(window.confirm("确定要删除数据吗")){
				//遍历收集到的参数
				var ids="";

				$.each(checkedIds,function(){
					ids+="ids="+this.value+"&";
				});
				ids=ids.substr(0,ids.length-1);
				//发送请求
				$.ajax({
					url:'workbench/activity/deleteActivityIds',
					data:ids,
					type:'post',
					dataType:'json',
					success:function (data){
						if(data.code=="1"){
							//刷洗市场活动页面，显示第一页的数据，保持每页的条数不变;
							queryActivityByConditionForPage(1,$("#demo_page1").bs_pagination('getOption','rowsPerPage'));

						}else{
							//删除成功,提示信息
							alert(data.message);

						}
					}
				});
			}
		});
		//给修改按钮添加单机事件
		$("#editActivityBtn").click(function (){
			var checkIds=$("#tBody input[type='checkbox']:checked");
			if(checkIds.size()==0){
				alert("请选择要修改的市场活动");
				return;
			}
			if(checkIds.size()>1){
				alert("每次只能修改一个市场活动");
				return;
			}
			var id=$.trim(checkIds[0].value);
			//发送请求
			$.ajax({
				url:'workbench/activity/queryActivityById',
				data:{
					id:id
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					//把市场活动的信息显示在修改的模态窗口上

					$("#edit-id").val(data.id);
					$("#edit-marketActivityOwner").val(data.owner);
					$("#edit-marketActivityName").val(data.name);
					$("#edit-startTime").val(data.startDate);
					$("#edit-endTime").val(data.endDate);
					$("#edit-cost").val(data.cost);
					$("#edit-description").val(data.description);
					//弹出模态窗口
					$("#editActivityModal").modal("show");
				}
			});
		});
		//给更新按钮添加单击事件
		$("#saveEditActivityBtn").click(function (){
			//收集参数
			var id=$("#edit-id").val();
			var owner=$("#edit-marketActivityOwner").val();
			var name=$.trim($("#edit-marketActivityName").val());
			var startDate=$("#edit-startTime").val();
			var endDate=$("#edit-endTime").val();
			var cost=$.trim($("#edit-cost").val());
			var description=$.trim($("#edit-description").val());


			//弹出模态窗口
			$("#editActivityModal").modal("show");
			//表单验证
			//发送请求
			$.ajax({
				url:'workbench/activity/saveEditActivity',
				data:{
					id:id,
					owner:owner,
					name:name,
					startDate:startDate,
					endDate:endDate,
					cost:cost,
					description:description
				},
				type:'post',
				dataType:'json',
				success:function(data){
					if(data.code=="1"){
						//关闭模态窗口
						$("#editActivityModal").modal("hide");
						queryActivityByConditionForPage($("#demo_page1").bs_pagination('getOption','currentPage'),$("#demo_page1").bs_pagination('getOption','rowsPerPage'));
					}else{
						//提示信息
						alert(data.message);
						$("#editActivityModal").modal("show");
					}
				}
			});

		});
		//导出市场活动
  		$("#exportActivityAllBtn").click(function (){
			var checkIds=$("#tBody input[type='checkbox']:checked")

			window.location.href="workbench/activity/exportAllActivitys";
		});

		//选择导出市场活动
		$("#exportActivityXzBtn").click(function (){
			var selectIds=$("#tBody input[type='checkbox']:checked");
			//遍历收集到的参数
			var ids="";
			$.each(selectIds,function(){
				ids+="ids="+this.value+"&";
			});
			ids=ids.substr(0,ids.length-1);
			window.location.href="workbench/activity/exportSomeActivitys?"+ids+"";
		});


		  //给导入按钮添加单击时间
		  $("#importActivityBtn").click(function (){
			 //收集数据
			  var activityFileName=$("#activityFile").val();//只能获取文件名
			  //验证是不是xls文件
			  var suffix=activityFileName.substr(activityFileName.lastIndexOf(".")+1).toLocaleLowerCase();
			  if(suffix!="xls"){
				  alert("只支持xls文件");
				  return;
			  }
			  var activityFile=$("#activityFile")[0].files[0];
			  if(activityFile.size>(5*1024*1024)){
				  alert("文件大小不能超过5MB");
				  return;
			  }

			  //FormData是ajax提供的接口，可以模拟键值对像后太提交参数
			  //FormData最大的优势在于不但能提交文本数据，还能提交二进制数据
			  var formData=new FormData();
			  formData.append("activityFile",activityFile);

			  $.ajax({
				  url:"workbench/activity/importActivity",
				  data:formData,
				  processData:false,//设置Ajax向后台提交数据之前，是否将参数统一转换成支付穿
				  contentType:false,//设置Ajax向后台提交数据之前，是否将参数统一按urlencoded编码：
				  type:"post",
				  dataType:'json',
				  success:function(data){
					  if(data.code=="1"){
						  //提示成功导入的记录条数
						  alert("成功导入"+data.retData+"条记录");
						  //关闭模态窗口
						  $("#importActivityModal").modal("hide");
						  //刷新市场活动列表，显示第一页的数据，保持每页显示条数不变
						  queryActivityByConditionForPage(1,$("#demo_page1").bs_pagination('getOption','rowsPerPage'));
					  }else{
						  //提示信息
						  alert(data.message);
						  //模态窗口不关闭
						  $("#importActivityModal").modal("show");
					  }
				  }
			  }) ;
		  });

	});

	function queryActivityByConditionForPage(pageNo,pageSize){
		//收集参数
		var name=$("#query-name").val();
		var owner=$("#query-owner").val();
		var startDate=$("#query-startDate").val();
		var endDate=$("#query-endDate").val();
		/*var pageNo=1;
		var pageSize=10;*/
		//发送请求
		$.ajax({
			url:'workbench/activity/queryActivityByConditionForPage',
			data:{
				name:name,
				owner:owner,
				startDate:startDate,
				endDate:endDate,
				pageNo:pageNo,
				pageSize:pageSize
			},
			type:'post',
			dataType: 'json',
			success:function(data){
				//显示总条数
				$("#totalRowB").text(data.totalRows);
				//显示市场活动列表
				var htmlStr="";
				$.each(data.activitiesList,function (index,obj){

					htmlStr+="<tr className=\"active\">";
					htmlStr+="<td><input type=\"checkbox\" value=\" "+obj.id+"\"/></td>";
					htmlStr+="<td><a style=\"text-decoration: none; cursor: pointer;\" onClick=\"window.location.href='workbench/activity/toDetail?id="+obj.id+"';\">"+obj.name+"</a></td>";
					htmlStr+="<td>"+obj.owner+"</td>";
					htmlStr+="<td>"+obj.startDate+"</td>";
					htmlStr+="<td>"+obj.endDate+"</td>";
					htmlStr+="</tr>";

				});
				$("#tBody").html(htmlStr);
				//取消全选按钮
				$("#checkAll").prop("checked",false);


				//计算总页数
				var totalPages;
				if(data.totalRows%pageSize==0){
					totalPages=data.totalRows/pageSize;
				}else{
					totalPages=parseInt(data.totalRows/pageSize)+1;
				}
				//调用bs_pagination工具函数，显示翻页信息
				$("#demo_page1").bs_pagination({
					currentPage:pageNo,//当前页号pageNo
					rowsPerPage:pageSize,//每页显示的条数pageSize
					totalPages:data.totalRows,//总条数
					totalPages:totalPages,//总页数 必填
					visiblePageLinks:5,//最多可以显示的卡片数

					showGoToPage: true,//是现实跳转到的部分 默认为true--显示
					showRowsPerPage: true,//是显示每页显示条数的部分 默认为true--显示
					showRowsInfo:true,//是显示记录的信息，默认为true--显示

					//每次用户切换页号，都自动出发该函数
					onChangePage: function (event,pageObj){
						queryActivityByConditionForPage(pageObj.currentPage,pageObj.rowsPerPage);
					}
				});
			}
		});
	}

</script>
</head>
<body>

	<!-- 创建市场活动的模态窗口 -->
	<div class="modal fade" id="createActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
				</div>
				<div class="modal-body">
				
					<form class="form-horizontal" role="form" id="createActivityForm">
					
						<div class="form-group">
							<label for="create-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="create-marketActivityOwner">
								  <c:forEach items="${userList}" var="u" >
									  <option value="${u.id}">${u.name}</option>
								  </c:forEach>
								</select>
							</div>
                            <label for="create-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-marketActivityName">
                            </div>
						</div>
						
						<div class="form-group">
							<label for="create-startDate" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control mydate" id="create-startDate" >
							</div>
							<label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control mydate" id="create-endDate" >
							</div>
						</div>
                        <div class="form-group">

                            <label for="create-cost" class="col-sm-2 control-label">成本</label>
                            <div class="col-sm-10" style="width: 300px;">
                                <input type="text" class="form-control" id="create-cost">
                            </div>
                        </div>
						<div class="form-group">
							<label for="create-describe" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="create-describe"></textarea>
							</div>
						</div>
						
					</form>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveCreateActivity">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 修改市场活动的模态窗口 -->
	<div class="modal fade" id="editActivityModal" role="dialog">
		<div class="modal-dialog" role="document" style="width: 85%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" role="form">
						<input type="hidden" id="edit-id">
						<div class="form-group">
							<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
							<div class="col-sm-10" style="width: 300px;">
								<select class="form-control" id="edit-marketActivityOwner">
									<c:forEach items="${userList}" var="u">
										<option value="${u.id}">${u.name}</option>
									</c:forEach>
								</select>
							</div>
							<label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>

							<div class="col-sm-10" style="width: 300px;">

								<input type="text" class="form-control" id="edit-marketActivityName" value="发传单">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-startTime" value="2020-10-10">
							</div>
							<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-endTime" value="2020-10-20">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-cost" class="col-sm-2 control-label">成本</label>
							<div class="col-sm-10" style="width: 300px;">
								<input type="text" class="form-control" id="edit-cost" value="5,000">
							</div>
						</div>

						<div class="form-group">
							<label for="edit-description" class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10" style="width: 81%;">
								<textarea class="form-control" rows="3" id="edit-description">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
							</div>
						</div>

					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="saveEditActivityBtn">更新</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 导入市场活动的模态窗口 -->
    <div class="modal fade" id="importActivityModal" role="dialog">
        <div class="modal-dialog" role="document" style="width: 85%;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
                </div>
                <div class="modal-body" style="height: 350px;">
                    <div style="position: relative;top: 20px; left: 50px;">
                        请选择要上传的文件：<small style="color: rgb(128,128,128);">[仅支持.xls]</small>
                    </div>
                    <div style="position: relative;top: 40px; left: 50px;">
                        <input type="file" id="activityFile">
                    </div>
                    <div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
                        <h3>重要提示</h3>
                        <ul>
                            <li>操作仅针对Excel，仅支持后缀名为XLS的文件。</li>
                            <li>给定文件的第一行将视为字段名。</li>
                            <li>请确认您的文件大小不超过5MB。</li>
                            <li>日期值以文本形式保存，必须符合yyyy-MM-dd格式。</li>
                            <li>日期时间以文本形式保存，必须符合yyyy-MM-dd HH:mm:ss的格式。</li>
                            <li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
                            <li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
                        </ul>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button id="importActivityBtn" type="button" class="btn btn-primary">导入</button>
                </div>
            </div>
        </div>
    </div>
	
	
	<div>
		<div style="position: relative; left: 10px; top: -10px;">
			<div class="page-header">
				<h3>市场活动列表</h3>
			</div>
		</div>
	</div>
	<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
		<div style="width: 100%; position: absolute;top: 5px; left: 10px;">
		
			<div class="btn-toolbar" role="toolbar" style="height: 80px;">
				<form class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">名称</div>
				      <input  class="form-control" type="text" id="query-name"/>
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">所有者</div>
				      <input  class="form-control" type="text" id="query-owner"/>
				    </div>
				  </div>


				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">开始日期</div>
					  <input  class="form-control" type="text" id="query-startDate"  />
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="input-group">
				      <div class="input-group-addon">结束日期</div>
					  <input  class="form-control" type="text"  id="query-endDate"/>
				    </div>
				  </div>
				  
				  <button type="button" class="btn btn-default" id="queryActivityBtn">查询</button>
				  
				</form>
			</div>
			<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
				<div class="btn-group" style="position: relative; top: 18%;">
				  <button type="button" class="btn btn-primary" id="createActivityBtn"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				  <button type="button" class="btn btn-default" id="editActivityBtn"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				  <button type="button" class="btn btn-danger" id="deleteActivityBtn"><span class="glyphicon glyphicon-minus" ></span> 删除</button>
				</div>
				<div class="btn-group" style="position: relative; top: 18%;">
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#importActivityModal" ><span class="glyphicon glyphicon-import"></span> 上传列表数据（导入）</button>
                    <button id="exportActivityAllBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（批量导出）</button>
                    <button id="exportActivityXzBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 下载列表数据（选择导出）</button>
                </div>
			</div>
			<div style="position: relative;top: 10px;">
				<table class="table table-hover">
					<thead>
						<tr style="color: #B3B3B3;">
							<td><input type="checkbox" id="checkAll"/></td>
							<td>名称</td>
                            <td>所有者</td>
							<td>开始日期</td>
							<td>结束日期</td>
						</tr>
					</thead>
					<tbody id="tBody">
					<%--	<tr class="active">
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
							<td>2020-10-10</td>
							<td>2020-10-20</td>
						</tr>
                        <tr class="active">
                            <td><input type="checkbox" /></td>
                            <td><a style="text-decoration: none; cursor: pointer;" onclick="window.location.href='detail.jsp';">发传单</a></td>
                            <td>zhangsan</td>
                            <td>2020-10-10</td>
                            <td>2020-10-20</td>
                        </tr>--%>
					</tbody>
				</table>
				<div id="demo_page1"></div>
			</div>
			
		<%--	<div style="height: 50px; position: relative;top: 30px;">
				<div>
					<button type="button" class="btn btn-default" style="cursor: default;">共<b id="totalRowB">50</b>条记录</button>
				</div>
				<div class="btn-group" style="position: relative;top: -34px; left: 110px;">
					<button type="button" class="btn btn-default" style="cursor: default;">显示</button>
					<div class="btn-group">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							10
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">20</a></li>
							<li><a href="#">30</a></li>
						</ul>
					</div>
					<button type="button" class="btn btn-default" style="cursor: default;">条/页</button>
				</div>
				<div style="position: relative;top: -88px; left: 285px;">
					<nav>
						<ul class="pagination">
							<li class="disabled"><a href="#">首页</a></li>
							<li class="disabled"><a href="#">上一页</a></li>
							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">下一页</a></li>
							<li class="disabled"><a href="#">末页</a></li>
						</ul>
					</nav>
				</div>
			</div>--%>
			
		</div>
		
	</div>
</body>
</html>