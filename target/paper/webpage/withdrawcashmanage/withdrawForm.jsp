<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>提现管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/withdrawmanage/orderManage");
				}else{
				    jp.error(data.msg);
				    $("#inputForm").find("button:submit").button("reset");
				}
			});

	        $('#xdrq').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
	        $('#yysj').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
	        $('#fksj').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
	        $('#sqtksj').datetimepicker({
				 format: "YYYY-MM-DD HH:mm:ss"
		    });
            $('input').attr('readonly',true)
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
				<a class="panelButton" href="${ctx}/withdrawmanage/orderManage"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="orderManage" action="${ctx}/withdrawmanage/orderManage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>订单号：</label>
					<div class="col-sm-10">
						<form:input path="ddh"  htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<%--<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>提取码：</label>
					<div class="col-sm-10">
						<form:input path="tqm"  htmlEscape="false"    class="form-control required"/>
					</div>
				</div>--%>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>下单时间：</label>
					<div class="col-sm-10">
						<div class='input-group form_datetime' id='xdrq'>
							<input type='text' readonly="true"  name="xdrq" class="form-control required"  value="<fmt:formatDate value="${orderManage.xdrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<%--<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font> 预约时间：</label>
					<div class="col-sm-10">
						<div class='input-group form_datetime' id='yysj'>
							<input type='text' readonly="true"  name="yysj" class="form-control required"  value="<fmt:formatDate value="${orderManage.yysj}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>--%>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>订单类型：</label>
					<div class="col-sm-10">
						<form:radiobuttons disabled="true" path="ddlxdm" items="${fns:getDictList('T_OrderType_C')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">总价：</label>
					<div class="col-sm-10">
						<form:input readonly="true" path="zj" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<%--<div class="form-group">
					<label class="col-sm-2 control-label">付款时间：</label>
					<div class="col-sm-10">
						<div class='input-group form_datetime' id='fksj'>
							<input type='text' readonly="true"  name="fksj" class="form-control "  value="<fmt:formatDate value="${orderManage.fksj}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>--%>
				<div class="form-group">
					<label class="col-sm-2 control-label">客户姓名：</label>
					<div class="col-sm-10">
						<form:input readonly="true" path="khxm" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">移动电话：</label>
					<div class="col-sm-10">
						<form:input readonly="true"  path="yddh" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<%--<div class="form-group">
					<label class="col-sm-2 control-label">申请退款时间：</label>
					<div class="col-sm-10">
						<div class='input-group form_datetime' id='sqtksj'>
							<input readonly="true" type='text'  name="sqtksj" class="form-control "  value="<fmt:formatDate value="${orderManage.sqtksj}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">退款理由：</label>
					<div class="col-sm-10">
						<form:input readonly="true" path="tkly" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">退款审核结果：</label>
					<div class="col-sm-10">
						<form:radiobuttons disabled="true" path="tkshjg" items="${fns:getDictList('T_TKSHJG')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户评价：</label>
					<div class="col-sm-10">
						<form:radiobuttons disabled="true" path="yhpj" items="${fns:getDictList('T_YHPJ')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">评价详情：</label>
					<div class="col-sm-10">
						<form:input readonly="true" path="pjxq" htmlEscape="false"    class="form-control "/>
					</div>
				</div>--%>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>订单状态：</label>
					<div class="col-sm-10">
						<form:radiobuttons disabled="true" path="ddztdm" items="${fns:getDictList('T_OrderStatus_C')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</div>
				</div>
				<%--<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>区域：</label>
					<div class="col-sm-10">
				<div class=" input-group" style=" width: 100%;">
					  <form:input disabled="true" path="qydm.code" htmlEscape="false"  class="required" data-toggle="city-picker" style="height: 34px;"/>
				</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>评审专家：</label>
					<div class="col-sm-10">
						<form:input  path="pszj" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注：</label>
					<div class="col-sm-10">
						<form:input  path="note" htmlEscape="false"    class="form-control "/>
					</div>
				</div>--%>
			<c:if test="${mode == 'add' ||  mode=='edit'}">
				<div class="col-lg-3"></div>
		        <div class="col-lg-6">
		             <div class="form-group text-center">
		                 <div>
		                     <button class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在确认...">确认已提现</button>
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