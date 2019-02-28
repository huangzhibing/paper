<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>专业技术级别管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/basedata/positionmanagement/professionalTechniqueRank");
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
				<a class="panelButton" href="${ctx}/basedata/positionmanagement/professionalTechniqueRank"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="professionalTechniqueRank" action="${ctx}/basedata/positionmanagement/professionalTechniqueRank/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>专业技术级别代码：</label>
					<div class="col-sm-10">
						<c:if test="${mode=='edit'}">
                            <form:input path="rankCode" htmlEscape="false"    class="form-control required"    readonly="true"/>
                        </c:if>
                        <c:if test="${mode=='add'}">
                            <form:input path="rankCode" htmlEscape="false"    class="form-control required"    readonly="false"/>
                        </c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>专业技术级别名称：</label>
					<div class="col-sm-10">
						<form:input path="rankName" htmlEscape="false" maxlength="20"  minlength="1"   class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>岗位等级：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="rankTitle" items="${fns:getDictList('T_RankTitle')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>国家专业技术岗位级别：</label>
					<div class="col-sm-10">
						<form:select path="rankSection" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('T_RankSection')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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