<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>秘书信息管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="secretaryManageList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">秘书信息列表</h3>
	</div>
	<div class="panel-body">
	
	<!-- 搜索 -->
	<div id="search-collapse" class="collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="secretaryManage" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="秘书代码：">秘书代码：</label>
				<form:input path="msdm" htmlEscape="false" maxlength="20"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="秘书姓名：">秘书姓名：</label>
				<form:input path="msxm" htmlEscape="false" maxlength="20"  class=" form-control"/>
			</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="秘书类型：">秘书类型：</label>
					<form:select name="mslx" path="mslx" class="form-control">
						<form:option value="" label=""/>
						<form:options items="${fns:getDictList('T_MSLX')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="移动电话：">移动电话：</label>
				<form:input path="yddh" htmlEscape="false" maxlength="11"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="邮件：">邮件：</label>
				<form:input path="yj" htmlEscape="false" maxlength="50"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="高校代码：">高校名称：</label>
				<sys:gridselect url="${ctx}/university/university/data" id="university" name="university.gxdm" value="${secretaryManage.university.gxdm}" labelName="university.gxmc" labelValue="${secretaryManage.university.gxdm}"
					title="选择高校代码" cssClass="form-control required" fieldLabels="university.gxdm|university.gxmc" fieldKeys="高校代码|高校名称" searchLabels="university.gxdm|university.gxmc" searchKeys="高校代码|高校名称" ></sys:gridselect>
			</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="高校代码：">高校名称：</label>
					<sys:gridselect url="${ctx}/departmentmanage/departmentManage/data" id="departmentManage" name="departmentManage.xydm" value="${secretaryManage.departmentManage.xydm}" labelName="departmentManage.xymc" labelValue="${secretaryManage.departmentManage.xymc}"
									title="选择学院代码" cssClass="form-control " fieldLabels="xydm|xymc" fieldKeys="学院代码|学院名称" searchLabels="xydm|xymc" searchKeys="学院代码|学院名称" ></sys:gridselect>
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
			<shiro:hasPermission name="secretarymanage:secretaryManage:add">
				<button id="add" class="btn btn-primary" onclick="add()">
					<i class="glyphicon glyphicon-plus"></i> 新建
				</button>
	</shiro:hasPermission>

			<shiro:hasPermission name="secretarymanage:secretaryManage:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="secretarymanage:secretaryManage:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="secretarymanage:secretaryManage:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="secretarymanage:secretaryManage:export">
	        		<button id="export" class="btn btn-warning">
					<i class="fa fa-file-excel-o"></i> 导出
				</button>
			 </shiro:hasPermission>
	                 <shiro:hasPermission name="secretarymanage:secretaryManage:view">
				<button id="view" class="btn btn-default" disabled onclick="view()">
					<i class="fa fa-search-plus"></i> 查看
				</button>
			</shiro:hasPermission>
		    </div>
		
	<!-- 表格 -->
	<table id="secretaryManageTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="secretarymanage:secretaryManage:view">
        <li data-item="view"><a>查看</a></li>
        </shiro:hasPermission>
    	<shiro:hasPermission name="secretarymanage:secretaryManage:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="secretarymanage:secretaryManage:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>