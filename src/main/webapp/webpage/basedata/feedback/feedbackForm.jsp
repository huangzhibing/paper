<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>反馈管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/feedback/feedback");
				}else{
				    jp.error(data.msg);
				    $("#inputForm").find("button:submit").button("reset");
				}
			});

	        $('#fksj').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
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
				<a class="panelButton" href="${ctx}/feedback/feedback"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="feedback" action="${ctx}/feedback/feedback/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>反馈流水号：</label>
					<div class="col-sm-10">
						<form:input path="fklsh" htmlEscape="false"  readonly="true"   class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>反馈类型代码：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="fklxdm" items="${fns:getDictList('T_FeedBackType_C')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">发生时间：</label>
					<div class="col-sm-10">
						<div class='input-group form_datetime' id='fksj'>
							<input type='text'  name="fksj" class="form-control "  value="<fmt:formatDate value="${feedback.fksj}" pattern="yyyy-MM-dd HH:mm:ss"/>" <%--//这里改了/--%>readonly="true" />
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>反馈内容：</label>
					<div class="col-sm-10">
						<form:textarea path="fknr" htmlEscape="false" rows="4"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>反馈人：</label>
					<div class="col-sm-10">
						<form:input path="fkr" htmlEscape="false"  readonly="true"  class="form-control required"/>
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