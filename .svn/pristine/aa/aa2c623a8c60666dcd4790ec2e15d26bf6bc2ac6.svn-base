<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>专业技术级别管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="professionalTechniqueRankList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">专业技术级别列表</h3>
	</div>
	<div class="panel-body">
	
	<!-- 搜索 -->
	<div id="search-collapse" class="collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="professionalTechniqueRank" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="专业技术级别代码：">专业技术级别代码：</label>
				<form:input path="rankCode" htmlEscape="false" maxlength="10"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="专业技术级别名称：">专业技术级别名称：</label>
				<form:input path="rankName" htmlEscape="false" maxlength="20"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<div class="form-group">
					<label class="label-item single-overflow pull-left" title="岗位等级：">&nbsp;岗位等级：</label>
					<div class="col-xs-12">
						<form:radiobuttons class="i-checks" path="rankTitle" items="${fns:getDictList('T_RankTitle')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</div>
				</div>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="国家专业技术岗位级别：">国家专业技术岗位级别：</label>
				<form:select path="rankSection"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('T_RankSection')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
			<shiro:hasPermission name="basedata:positionmanagement:professionalTechniqueRank:add">
				<button id="add" class="btn btn-primary" onclick="add()">
					<i class="glyphicon glyphicon-plus"></i> 新建
				</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="basedata:positionmanagement:professionalTechniqueRank:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="basedata:positionmanagement:professionalTechniqueRank:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="basedata:positionmanagement:professionalTechniqueRank:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="basedata:positionmanagement:professionalTechniqueRank:export">
	        		<button id="export" class="btn btn-warning">
					<i class="fa fa-file-excel-o"></i> 导出
				</button>
			 </shiro:hasPermission>
	                 <shiro:hasPermission name="basedata:positionmanagement:professionalTechniqueRank:view">
				<button id="view" class="btn btn-default" disabled onclick="view()">
					<i class="fa fa-search-plus"></i> 查看
				</button>
			</shiro:hasPermission>
		    </div>
		
	<!-- 表格 -->
	<table id="professionalTechniqueRankTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="basedata:positionmanagement:professionalTechniqueRank:view">
        <li data-item="view"><a>查看</a></li>
        </shiro:hasPermission>
    	<shiro:hasPermission name="basedata:positionmanagement:professionalTechniqueRank:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="basedata:positionmanagement:professionalTechniqueRank:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>