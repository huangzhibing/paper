<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>专家指派管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="orderList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">专家指派列表</h3>
	</div>
	<div class="panel-body">
	
	<!-- 搜索 -->
	<div id="search-collapse" class="collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="paper" class="form form-horizontal well clearfix">
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="创建时间：">&nbsp;创建时间：</label>
					<div class="col-xs-12">
						<div class="col-xs-12 col-sm-5">
							<div class='input-group date' id='beginCreateDate' style="left: -10px;" >
								<input type='text'  name="beginCreateDate" class="form-control"  />
								<span class="input-group-addon">
									   <span class="glyphicon glyphicon-calendar"></span>
								   </span>
							</div>
						</div>
						<div class="col-xs-12 col-sm-1">
							~
						</div>
						<div class="col-xs-12 col-sm-5">
							<div class='input-group date' id='endCreateDate' style="left: -10px;" >
								<input type='text'  name="endCreateDate" class="form-control" />
								<span class="input-group-addon">
									   <span class="glyphicon glyphicon-calendar"></span>
								   </span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="论文题目：">论文编号：</label>
					<input name="LWBH" htmlEscape="false" maxlength="200"  class=" form-control"/>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="论文题目：">论文题目：</label>
					<input name="LWMC" htmlEscape="false" maxlength="200"  class=" form-control"/>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="论文状态：">论文状态：</label>
					<select name="LWZTDM" class=" form-control">
						<option value="b">已缴费</option>
						<option value="c">已指派专家</option>
						<option value="ab">已缴费且已指派</option>
					</select>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="高校名称：">高校名称：</label>
					<sys:gridselect-item url="${ctx}/university/university/data" id="gx" name="XSXH.university.gxdm" value=""  labelName="gxmc" labelValue=""
										 title="选择高校" cssClass="form-control " fieldLabels="高校代码|高校姓名" fieldKeys="gxdm|gxmc" searchLabels="高校代码|高校姓名" searchKeys="gxdm|gxmc"
										 extraField="gxId:gxdm;gxNames:gxmc"></sys:gridselect-item>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="专业名称：">专业名称：</label>
					<sys:gridselect url="${ctx}/specialitymanage/specialityManage/data" id="specialityManage" name="XSXH.specialityManage.zydm" value="${studentManage.specialityManage.zydm}" labelName="xsxh.specialityManage.zymc" labelValue="${studentManage.specialityManage.zydm}"
									title="选择专业" cssClass="form-control " fieldLabels="专业代码|专业名称" fieldKeys="zydm|zymc" searchLabels="专业代码|专业名称" searchKeys="zydm|zymc" ></sys:gridselect>
				</div>
			 	<div class="col-xs-12 col-sm-6 col-md-4">
					<div style="margin-top:26px">
					  <a  id="search" class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i class="fa fa-search"></i> 查询</a>
					  <a  id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
					 </div>
				</div>
			</form:form>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div id="toolbar">
			<shiro:hasPermission name="order:order:edit">
				<button id="distribute" class="btn btn-primary" onclick="distribute()">
					<i class="glyphicon glyphicon-plus"></i> 批量指派
				</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="order:order:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 指派
	        	</button>
			</shiro:hasPermission>
			<%--<shiro:hasPermission name="order:order:del">--%>
				<%--<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">--%>
	            	<%--<i class="glyphicon glyphicon-remove"></i> 删除--%>
	        	<%--</button>--%>
			<%--</shiro:hasPermission>--%>
			<%--<shiro:hasPermission name="order:order:import">--%>
				<%--<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>--%>
			<%--</shiro:hasPermission>--%>
			<shiro:hasPermission name="order:order:export">
	        		<button id="export" class="btn btn-warning">
					<i class="fa fa-file-excel-o"></i> 导出
				</button>
			 </shiro:hasPermission>
			<shiro:hasPermission name="order:order:view">
				<button id="view" class="btn btn-default" disabled onclick="view()">
					<i class="fa fa-search-plus"></i> 查看
				</button>
			</shiro:hasPermission>
		    </div>
		
	<!-- 表格 -->
	<table id="orderTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="order:order:view">
        <li data-item="view"><a>查看</a></li>
        </shiro:hasPermission>
    	<shiro:hasPermission name="order:order:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="order:order:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>