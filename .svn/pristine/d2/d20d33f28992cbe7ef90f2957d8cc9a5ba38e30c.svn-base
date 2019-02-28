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
					jp.go("${ctx}/paperReview/review");
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
				<a class="panelButton" href="${ctx}/paperReview/review"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="order" action="${ctx}/paperReview/review/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文编号：</label>
					<div class="col-sm-10">
						<form:input readOnly="true" path="lw.LWBH" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文题目：</label>
					<div class="col-sm-10">
						<form:input readOnly="true" path="lw.LWMC" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">学生姓名：</label>
					<div class="col-sm-10">
						<form:input readOnly="true" path="khxm" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">导师姓名：</label>
					<div class="col-sm-10">
						<form:input readOnly="true" path="student.dsxm" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">专业名称：</label>
					<div class="col-sm-10">
						<form:input readOnly="true" path="student.specialityManage.zymc" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">学位点名称：</label>
					<div class="col-sm-10">
						<form:input readOnly="true" path="student.degreepointManage.xwdmc" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>评审等级：</label>
					<div class="col-sm-10">
						<form:select path="psdj" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('T_reviewRank')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>评审意见：</label>
					<div class="col-sm-10">
						<form:textarea path="psyj" htmlEscape="false" rows="4"    class="form-control required"/>
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