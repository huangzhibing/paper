<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>费用表管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="costCList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">费用表列表</h3>
	</div>
	<div class="panel-body">
	
	<!-- 搜索 -->
	<div id="search-collapse" class="collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="costC" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="高校代码：">高校代码：</label>
				<sys:gridselect url="${ctx}/university/university/data" id="university" name="university.gxdm" value="${costC.university.gxdm}" labelName="university.gxmc" labelValue="${costC.university.gxdm}"
					title="选择高校代码" cssClass="form-control required" fieldLabels="高校代码|高校名称" fieldKeys="gxdm|gxmc" searchLabels="高校代码|高校名称" searchKeys="gxdm|gxmc" ></sys:gridselect>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="专业代码：">专业代码：</label>
				<sys:gridselect url="${ctx}/specialitymanage/specialityManage/data" id="specialityManage" name="specialityManage.zydm" value="${costC.specialityManage.zydm}" labelName="specialityManage.zymc" labelValue="${costC.specialityManage.zydm}"
					title="选择专业代码" cssClass="form-control required" fieldLabels="专业代码|专业名称" fieldKeys="zydm|zymc" searchLabels="专业代码|专业名称" searchKeys="zydm|zymc" ></sys:gridselect>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="评审费用：">评审费用：</label>
				<form:input path="psfy" htmlEscape="false" maxlength="4"  class=" form-control"/>
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
			<shiro:hasPermission name="costc:costC:add">
				<button id="add" class="btn btn-primary" onclick="add()">
					<i class="glyphicon glyphicon-plus"></i> 新建
				</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="costc:costC:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="costc:costC:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="costc:costC:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="costc:costC:export">
	        		<button id="export" class="btn btn-warning">
					<i class="fa fa-file-excel-o"></i> 导出
				</button>
			 </shiro:hasPermission>
	                 <shiro:hasPermission name="costc:costC:view">
				<button id="view" class="btn btn-default" disabled onclick="view()">
					<i class="fa fa-search-plus"></i> 查看
				</button>
			</shiro:hasPermission>
		    </div>
		
	<!-- 表格 -->
	<table id="costCTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="costc:costC:view">
        <li data-item="view"><a>查看</a></li>
        </shiro:hasPermission>
    	<shiro:hasPermission name="costc:costC:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="costc:costC:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>