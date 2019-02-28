<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>库存资金占用</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="capitaloccupation.js" %>
	<script type="text/javascript">
	$(function(){
		$('#bar').click(function(){
			layer.open({
				type:2,
				auto:true,
				title:'库存资金占用',
				area: ['1000px', '800px'],
				content:"${ctx}/capitaloccupation/capitaloccupation/bar"
			});
		});
		
		$('#pie').click(function(){
			layer.open({
				type:2,
				auto:true,
				title:'库存资金占用',
				area: ['1000px', '800px'],
				content:"${ctx}/capitaloccupation/capitaloccupation/pie"
			});
		});
	});
	</script>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">库存资金占用</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">
	<div id="collapseTwo" class="accordion-body collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="sortCount" class="form form-horizontal well clearfix">
				<div class="col-xs-12 col-sm-6 col-md-3">
					<label class="label-item single-overflow pull-left" title="库房号：">仓库编码：</label>
					<sys:gridselect-item url="${ctx}/warehouse/wareHouse/data" id="ware" name="ware.id" value="${billMain.ware.id}" labelName="ware.wareID" labelValue="${billMain.ware.wareID}"
										   title="选择仓库编码" cssClass="form-control required" fieldLabels="仓库编码|仓库名称" fieldKeys="wareID|wareName" searchLabels="仓库编码|仓库名称" searchKeys="wareID|wareName"
										   targetId="wareName" targetField="wareName" ></sys:gridselect-item>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3">
					<label class="label-item single-overflow pull-left" title="库房名称：">仓库名称：</label>
					<input id="wareName" name="wareName"   class=" form-control"/>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3">
					<label class="label-item single-overflow pull-left"><font color="red">*</font>物料类型编码：</label>
					<sys:treeselect-modify targetId="className" targetField="classId" id="classCode" name="classCode.id" value="${item.classCode.id}" labelName="classCode.classId" labelValue="${item.classCode.classId}"
							title="物料类型编码" url="/item/itemClassNew/treeData"  cssClass="form-control required" allowClear="true" />

				</div>
				<div class="col-xs-12 col-sm-6 col-md-3" >
					<label class="label-item single-overflow pull-left"><font color="red">*</font>物料类型名称：</label>
					<input name="className"   id="className" class="form-control required"/>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3">
					<label class="label-item single-overflow pull-left" title="物料代码：">物料代码：</label>
					<sys:gridselect-modify urlParamId="classCodeName" urlParamName="classCode.classId" url="${ctx}/item/item/data2" id="item" name="item.id" value="${billDetail.item.id}" labelName="item.code" labelValue="${billDetail.item.code}"
										   title="选择物料代码" cssClass="form-control  required" fieldLabels="物料代码|物料名称|规格型号" fieldKeys="code|name|specModel" searchLabels="物料代码|物料名称|规格型号" searchKeys="code|name|specModel"
										   targetId="itemName" targetField="name" extraField="itemSpec:specModel">
					</sys:gridselect-modify>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3">
					<label class="label-item single-overflow pull-left" title="物料名称：">物料名称：</label>
					<input type='text' id="itemName" name="item.name"   class=" form-control"/>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-3">
					<label class="label-item single-overflow pull-left" title="规格型号：">规格型号：</label>
					<input type='text' id="itemSpec" name="item.specModel"   class=" form-control"/>
				</div>


		 <div class="col-xs-12 col-sm-6 col-md-3">
			<div style="margin-top:26px">
			  <a  id="search" class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i class="fa fa-search"></i> 查询</a>
			  <a  id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
			 </div>
	    </div>	
	</form:form>
	</div>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div id="toolbar">
		<a class="accordion-toggle btn btn-default" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
					<i class="fa fa-search"></i> 检索
		</a>
		<a id="bar" class="accordion-toggle btn btn-default" href="#">
					<i class="fa fa-search"></i> 柱状图
		</a>
		<a id="pie" class="accordion-toggle btn btn-default" href="#">
					<i class="fa fa-search"></i> 饼图
		</a>
	</div>
		
	<!-- 表格 -->
	<table id="myTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="invaccount:invAccount:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="invaccount:invAccount:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>
</body>
</html>