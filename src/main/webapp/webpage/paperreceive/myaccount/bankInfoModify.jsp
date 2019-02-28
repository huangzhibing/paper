<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>账户信息</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">
		$(document).ready(function() {
			if("${flag}" == "T"){
			    $('input').attr("readOnly",true);
			    $('#khhButton').attr("disabled",true);
			    $('#khhNames').attr("disabled",true);
			}
		});
	</script>
</head>
<body class="bg-white">
	<form:form id="inputForm" modelAttribute="expert" class="form-horizontal form-group">
		<form:hidden path="id"/>
		<sys:message  content="${message}"/>
		<div class="control-group">
		</div>
		
		<div class="control-group">
			<label class="col-sm-3 control-label"><font color="red">*</font>身份证号:</label>
			<div class="controls">
				<form:input path="sfzh" maxlength="50"   class="form-control  max-width-250 required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="col-sm-3 control-label"><font color="red">*</font>银行卡号:</label>
			<div class="controls">
				<form:input path="yhkh" maxlength="50"  class="form-control  max-width-250 required"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"><font color="red">*</font> 开户行:</label>
			<div class="col-sm-10">
				<sys:gridselect-bank url="${ctx}/myaccount/myAccount/bankData" id="khh" name="khh" value="${expert.khh}" labelName="khh" labelValue="${expert.khh}"
								title="选择开户行" cssClass="form-control required" fieldLabels="归属县|开户行" fieldKeys="xian|khh" searchLabels="归属县|开户行" searchKeys="xian|khh" ></sys:gridselect-bank>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label"><font color="red">*</font>开户行:</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="khh" maxlength="50"  class="form-control  max-width-250 required"/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" style="display:none" value="保 存"/>
		</div>
	</form:form>
</body>
</html>