<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>费用表管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/costc/costC");
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
				<a class="panelButton" href="${ctx}/costc/costC"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="costC" action="${ctx}/costc/costC/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>高校代码：</label>
					<div class="col-sm-10">
						<sys:gridselect url="${ctx}/university/university/data" id="university" name="university.gxdm" value="${costC.university.gxdm}" labelName="university.gxmc" labelValue="${costC.university.gxdm}"
							 title="选择高校" cssClass="form-control required" fieldLabels="高校代码|高校名称" fieldKeys="gxdm|gxmc" searchLabels="高校代码|高校名称" searchKeys="gxdm|gxmc" ></sys:gridselect>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>专业代码：</label>
					<div class="col-sm-10">
						<sys:gridselect url="${ctx}/specialitymanage/specialityManage/data" id="specialityManage" name="specialityManage.zydm" value="${costC.specialityManage.zydm}" labelName="specialityManage.zymc" labelValue="${costC.specialityManage.zydm}"
							 title="选择专业" cssClass="form-control required" fieldLabels="专业代码|专业名称" fieldKeys="zydm|zymc" searchLabels="专业代码|专业名称" searchKeys="zydm|zymc" ></sys:gridselect>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">评审费用：</label>
					<div class="col-sm-10">
						<form:input path="psfy" htmlEscape="false" maxlength="4"  minlength="0"   class="form-control  isIntGtZero"/>
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