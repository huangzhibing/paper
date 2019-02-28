<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>评审记录管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/review/paper");
				}else{
				    jp.error(data.msg);
				    $("#inputForm").find("button:submit").button("reset");
				}
			});

		});
	</script>
</head>
<body>
<div class="wrapper wrapper-content">				
<div class="row">
	<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title"> 
				<a class="panelButton" href="${ctx}/review/paper"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="paper" action="${ctx}/review/paper/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文编号：</label>
					<div class="col-sm-10">
						<form:input path="LWBH" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文题目：</label>
					<div class="col-sm-10">
						<form:input path="LWMC" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">学生学号：</label>
					<div class="col-sm-10">
						<sys:gridselect url="${ctx}/degreepoint/degreepointManage/data" id="XSXH" name="XSXH" value="${paper.XSXH}" labelName="" labelValue="${paper.}"
							 title="选择学生学号" cssClass="form-control " fieldLabels="学号|姓名" fieldKeys="xsxh|xsxm" searchLabels="学号|姓名" searchKeys="xsxh|xsxm" ></sys:gridselect>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文文件：</label>
					<div class="col-sm-10">
						<sys:fileUpload path="LWWJ"  value="${paper.LWWJ}" type="file" uploadPath="/review/paper"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文类型：</label>
					<div class="col-sm-10">
						<form:select path="LWLXDM" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('T_PaperType_C')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文状态：</label>
					<div class="col-sm-10">
						<form:select path="LWZTDM" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('T_PaperStatus_C')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
		<c:if test="${mode == 'add' || mode=='edit'}">
				<div class="col-lg-3"></div>
		        <div class="col-lg-6">
		             <div class="form-group text-center">
		                 <div>
		                     <button class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在提交...">提 交</button>
		                 </div>
		             </div>
		        </div>
		</c:if>
		</form:form>
		</div>				
	</div>
	</div>
</div>
</div>
</body>
</html>