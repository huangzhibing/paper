<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>论文摘要详情</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/daishenpaper/daishenPaper");
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
				<a class="panelButton" href="${ctx}/daishenpaper/daishenPaper"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="paper" action="${ctx}/daishenpaper/daishenPaper/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文编号：</label>
					<div class="col-sm-10">
						<form:input path="LWBH" htmlEscape="false"  readonly="true"  class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文题目：</label>
					<div class="col-sm-10">
						<form:input path="LWMC" htmlEscape="false"  readonly="true"  class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>论文摘要：</label>
					<div class="col-sm-10">
						<form:textarea path="lwzy"  readonly="true" class="form-control required"/>
					</div>
				</div>

		<c:if test="${mode == 'view'}">
				<div class="col-lg-3"></div>
			<div class="col-lg-2"></div>
			<div class="col-lg-3">
				<div class="form-group text-center">
					<div>
						<button  name="jieshou" value="true" class="btn btn-primary btn-lg btn-parsley" data-loading-text="正在接受...">接 收</button>
						<button  name="jushou" value="true" class="btn btn-primary btn-lg btn-parsley" data-loading-text="正在拒收...">拒 收</button>
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