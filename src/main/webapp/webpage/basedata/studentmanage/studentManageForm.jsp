<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>学生信息管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
		    $("#test").val($("#universityId").val())
			$("#universityNames").change(function () {
                $("#test").val($("#universityId").val())
            })
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/studentmanage/studentManage");
				}else{
				    jp.error(data.msg);
				    $("#inputForm").find("button:submit").button("reset");
				}
			});

		});
	</script>
	<style>
		.form-control-xsxh{
			width: 80px;
			text-align: center;
			margin-right: 20px;
		}
	</style>
</head>
<body>
<div class="wrapper wrapper-content">				
<div class="row">
	<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title"> 
				<a class="panelButton" href="${ctx}/studentmanage/studentManage"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="studentManage" action="${ctx}/studentmanage/studentManage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>学生学号：</label>
					<div class="col-sm-10"  style="display: inline-flex">
						<input id="test" readonly="true" class="form-control form-control-xsxh"/>
						<input name="xsxh" value="${fn:substring(studentManage.xsxh,fn:length(studentManage.university.gxdm),fn:length(studentManage.xsxh))}" placeholder="请输入学生学号" type="text" style="width: 100%"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">学生姓名：</label>
					<div class="col-sm-10">
						<form:input path="xsxm" name="xsxm_input" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">移动电话：</label>
					<div class="col-sm-10">
						<form:input path="yddh" name="yddh_input" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">邮件：</label>
					<div class="col-sm-10">
						<form:input path="yj" name="yj_input" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>导师姓名：</label>
					<div class="col-sm-10">
						<form:input path="dsxm" name="dsxm_input" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>高校名称：</label>
					<div class="col-sm-10">
						<sys:gridselect-item url="${ctx}/university/university/data" id="university" name="university.gxdm" value="${studentManage.university.gxdm}" labelName="university.gxmc" labelValue="${studentManage.university.gxmc}"
							 title="选择高校" cssClass="form-control required" fieldLabels="高校代码|高校名称" fieldKeys="gxdm|gxmc" searchLabels="高校代码|高校名称" searchKeys="gxdm|gxmc"
								extraField="universityId:gxdm;universityNames:gxmc"
						></sys:gridselect-item>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>学位点名称：</label>
					<div class="col-sm-10">
						<sys:gridselect url="${ctx}/degreepoint/degreepointManage/data" id="degreepointManage" name="degreepointManage.xwddm" value="${studentManage.degreepointManage.xwddm}" labelName="degreepointManage.xwdmc" labelValue="${studentManage.degreepointManage.xwdmc}"
							 title="选择学位点" cssClass="form-control required" fieldLabels="学位点代码|学位点名称" fieldKeys="xwddm|xwdmc" searchLabels="学位点代码|学位点名称" searchKeys="xwddm|xwdmc"
						></sys:gridselect>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">专业名称：</label>
					<div class="col-sm-10">
						<sys:gridselect url="${ctx}/specialitymanage/specialityManage/data" id="specialityManage" name="specialityManage.zydm" value="${studentManage.specialityManage.zydm}" labelName="specialityManage.zymc" labelValue="${studentManage.specialityManage.zymc}"
							 title="选择专业" cssClass="form-control " fieldLabels="专业代码|专业名称" fieldKeys="zydm|zymc" searchLabels="专业代码|专业名称" searchKeys="zydm|zymc" ></sys:gridselect>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注信息：</label>
					<div class="col-sm-10">
						<form:textarea path="remarks" htmlEscape="false" rows="4"    class="form-control "/>
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