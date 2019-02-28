<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>专家信息表管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="expertList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">专家信息表列表</h3>
	</div>
	<div class="panel-body">
	
	<!-- 搜索 -->
	<div id="search-collapse" class="collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="expert" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="用户账号：">用户账号：</label>
				<form:input path="YHZH" htmlEscape="false" maxlength="50"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="专家姓名：">专家姓名：</label>
				<form:input path="ZJXM" htmlEscape="false" maxlength="16"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<div class="form-group">
					<label class="label-item single-overflow pull-left" title="专家类型代码：">&nbsp;专家类型代码：</label>
					<div class="col-xs-12">
						<form:radiobuttons class="i-checks" path="ZJXLDM" items="${fns:getDictList('T_ExpertType_C')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</div>
				</div>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="专业知识职务代码：">专业知识职务代码：</label>
				<sys:gridselect url="${ctx}/positionmanagement/professionalTechniqueRank/data" id="ZYJSZWDM" name="ZYJSZWDM.ZYJSJBDM" value="${row.ZYJSZWDM.ZYJSJBDM}" labelName="" labelValue=""
					title="选择专业知识职务代码" cssClass="form-control " fieldLabels="" fieldKeys="" searchLabels="" searchKeys="" ></sys:gridselect>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<div class="form-group">
					<label class="label-item single-overflow pull-left" title="导师级别代码：">&nbsp;导师级别代码：</label>
					<div class="col-xs-12">
						<form:radiobuttons class="i-checks" path="DSJBDM" items="${fns:getDictList('T_TutorLevel_C')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</div>
				</div>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<div class="form-group">
					<label class="label-item single-overflow pull-left" title="性别代码：">&nbsp;性别代码：</label>
					<div class="col-xs-12">
						<form:radiobuttons class="i-checks" path="XBDM" items="${fns:getDictList('T_SexType_C')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</div>
				</div>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="区域代码：">区域代码：</label>
				<sys:treeselect id="QYDM" name="QYDM.code" value="${expert.QYDM.code}" labelName="QYDM.code" labelValue="${expert.QYDM.code}"
					title="区域" url="/sys/area/treeData" cssClass="form-control" allowClear="true" notAllowSelectParent="true"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="电子邮箱：">电子邮箱：</label>
				<form:input path="DZYX" htmlEscape="false" maxlength="100"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="单位代码：">单位代码：</label>
				<sys:gridselect url="${ctx}/basedata/organizationmanagement/organizationManagement/data" id="DWDM" name="DWDM.DWDM" value="${row.DWDM.DWDM}" labelName="" labelValue=""
					title="选择单位代码" cssClass="form-control " fieldLabels="" fieldKeys="" searchLabels="" searchKeys="" ></sys:gridselect>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="出生年月：">出生年月：</label>
				<form:input path="CSNY" htmlEscape="false"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="二级学院：">二级学院：</label>
				<form:input path="EJXY" htmlEscape="false" maxlength="20"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="最高学位：">最高学位：</label>
				<form:input path="ZJXW" htmlEscape="false" maxlength="10"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="毕业院校代码：">毕业院校代码：</label>
				<sys:gridselect url="${ctx}/University/university/data" id="BYYXDM" name="BYYXDM.GXDM" value="${row.BYYXDM.GXDM}" labelName="" labelValue=""
					title="选择毕业院校代码" cssClass="form-control " fieldLabels="" fieldKeys="" searchLabels="" searchKeys="" ></sys:gridselect>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="外语语种：">外语语种：</label>
				<form:input path="WYYZ" htmlEscape="false" maxlength="30"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="外语熟悉程度：">外语熟悉程度：</label>
				<form:input path="WYSXCD" htmlEscape="false" maxlength="10"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="移动电话：">移动电话：</label>
				<form:input path="YDDH" htmlEscape="false" maxlength="11"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="联系电话：">联系电话：</label>
				<form:input path="LXDH" htmlEscape="false" maxlength="20"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="联系地址：">联系地址：</label>
				<form:input path="LXDZ" htmlEscape="false" maxlength="100"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="邮编：">邮编：</label>
				<form:input path="YB" htmlEscape="false" maxlength="6"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="籍贯：">籍贯：</label>
				<form:input path="JG" htmlEscape="false" maxlength="100"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<div class="form-group">
					<label class="label-item single-overflow pull-left" title="专家状态代码：">&nbsp;专家状态代码：</label>
					<div class="col-xs-12">
						<form:radiobuttons class="i-checks" path="ZJZTDM" items="${fns:getDictList('T_ExpertStatus_C')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</div>
				</div>
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
			<shiro:hasPermission name="expertmanagement:expert:add">
				<button id="add" class="btn btn-primary" onclick="add()">
					<i class="glyphicon glyphicon-plus"></i> 新建
				</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="expertmanagement:expert:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="expertmanagement:expert:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="expertmanagement:expert:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="expertmanagement:expert:export">
	        		<button id="export" class="btn btn-warning">
					<i class="fa fa-file-excel-o"></i> 导出
				</button>
			 </shiro:hasPermission>
	                 <shiro:hasPermission name="expertmanagement:expert:view">
				<button id="view" class="btn btn-default" disabled onclick="view()">
					<i class="fa fa-search-plus"></i> 查看
				</button>
			</shiro:hasPermission>
		    </div>
		
	<!-- 表格 -->
	<table id="expertTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="expertmanagement:expert:view">
        <li data-item="view"><a>查看</a></li>
        </shiro:hasPermission>
    	<shiro:hasPermission name="expertmanagement:expert:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="expertmanagement:expert:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>