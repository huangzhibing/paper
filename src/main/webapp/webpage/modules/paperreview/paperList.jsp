<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>评审记录管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="paperList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">评审记录列表</h3>
	</div>
	<div class="panel-body">
	
	<!-- 搜索 -->
	<div id="search-collapse" class="collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="paperReview" class="form form-horizontal well clearfix">
			 <%--<div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="创建者：">创建者：</label>
				<form:input path="createBy.id" htmlEscape="false" maxlength="64"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				 <div class="form-group">
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
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="更新者：">更新者：</label>
				<form:input path="updateBy.id" htmlEscape="false" maxlength="64"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<div class="form-group">
					<label class="label-item single-overflow pull-left" title="更新时间：">&nbsp;更新时间：</label>
					<div class="col-xs-12">
						<div class='input-group date' id='updateDate' >
			                   <input type='text'  name="updateDate" class="form-control"  />
			                   <span class="input-group-addon">
			                       <span class="glyphicon glyphicon-calendar"></span>
			                   </span>
			            </div>	
					</div>
				</div>
			</div>--%>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<label class="label-item single-overflow pull-left" title="论文编号：">论文编号：</label>
					<form:input path="lw.LWBH" htmlEscape="false" maxlength="200"  class=" form-control"/>
				</div>
			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="论文题目：">论文题目：</label>
				<form:input path="lw.LWMC" htmlEscape="false" maxlength="200"  class=" form-control"/>
			</div>
			<div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="姓名：">姓名：</label>
				<form:input path="khxm" htmlEscape="false" maxlength="200"  class=" form-control"/>
			</div>
<%--			 <div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="学生学号：">学生学号：</label>
				 <sys:gridselect url="${ctx}/studentManage/studentManage/data" id="XSXH" name="XSXH" value="${paper.XSXH}" labelName="XSXH" labelValue="${paper.XSXH}"
								 title="选择学生学号" cssClass="form-control " fieldLabels="学号|姓名" fieldKeys="xsxh|xsxm" searchLabels="学号|姓名" searchKeys="xsxh|xsxm" ></sys:gridselect>
			 </div>--%>
			 
			 <%--<div class="col-xs-12 col-sm-6 col-md-4">
				<label class="label-item single-overflow pull-left" title="论文状态：">论文状态：</label>
				<form:select path="LWZTDM"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('T_PaperStatus_C')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>--%>
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
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 评审
	        	</button>
			</shiro:hasPermission>
			
				<%--<shiro:hasPermission name="review:paper:view">
           <button id="view" class="btn btn-default" disabled onclick="view()">
               <i class="fa fa-search-plus"></i> 查看
           </button>
       </shiro:hasPermission>--%>
		    </div>
		
	<!-- 表格 -->
	<table id="paperTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <%--<ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="review:paper:view">
        <li data-item="view"><a>查看</a></li>
        </shiro:hasPermission>
    	<shiro:hasPermission name="review:paper:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="review:paper:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  --%>
	</div>
	</div>
	</div>
</body>
</html>