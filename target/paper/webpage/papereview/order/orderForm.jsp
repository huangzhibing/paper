<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>专家指派管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			jp.ajaxForm("#inputForm",function(data){
                    if(data.success){
                        jp.success(data.msg);
                        jp.go("${ctx}/order/order");
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
				<a class="panelButton" href="${ctx}/order/order"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="order" action="${ctx}/order/order/save" method="post" class="form-horizontal">
		<input type="hidden" name="pid" value="${order.id}"/>
		<input type="hidden" id="qydm" name="qydm.code"/>
		<input type="hidden" id="eid" name="eid"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">订单号：</label>
					<div class="col-sm-10">
						<form:input path="ddh" readOnly="readOnly" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">下单时间：</label>
					<div class="col-sm-10">
						<div class='input-group form_datetime' id='xdrq'>
							<input type='text' readOnly="readOnly"  name="xdrq" class="form-control "  value="<fmt:formatDate value="${order.xdrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文题目：</label>
					<div class="col-sm-10">
						<sys:gridselect-item disabled="disabled" id="lw" name="lw.LWBH" value="${order.lw.LWBH}" labelName="lw.LWMC" labelValue="${order.lw.LWMC}" fieldLabels="论文编号|论文题目" fieldKeys="lwbh|lwmc" searchLabels="论文编号|论文题目" searchKeys="lwbh|lwmc" title="选择论文" url="${ctx}/papermanage/paper/data?LWZTDM=a" cssClass="form-control"
						extraField="khxm:xsxh.xsxm;lwId:lwbh;lwNames:lwmc;">
						</sys:gridselect-item>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">客户姓名：</label>
					<div class="col-sm-10">
						<input name="khxm" value="${order.lw.XSXH.xsxm}" readOnly="readOnly" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">评审专家：</label>
					<div class="col-sm-10">
						<sys:gridselect-item id="zj" name="pszj.YHZH" value="${order.pszj.YHZH}" labelName="pszj.zjxm" labelValue="${order.pszj.ZJXM}" fieldLabels="专家姓名|所属高校|专业|评审次数" fieldKeys="zjxm|dwdm.dwmc|specialityManage.zymc|pscs" searchLabels="专家姓名|所属高校|专业|评审次数" searchKeys="zjxm|dwdm.dwmc|specialityManage.zymc|pscs" title="选择论文" url="${ctx}/expertmanagement/expert/data?ZJZTDM=0&specialityManage.zydm=${order.lw.XSXH.specialityManage.zydm}&pszt=0" cssClass="form-control required"
											 extraField="zjId:yhzh;zjNames:zjxm;zjyx:dzyx;zjdh:lxdh;qydm:qydm.code;eid:id">
						</sys:gridselect-item>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">专家电子邮箱：</label>
					<div class="col-sm-10">
						<input id="zjyx" readOnly="readOnly" value="${order.pszj.DZYX}"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">专家联系电话：</label>
					<div class="col-sm-10">
						<input id="zjdh" readOnly="readOnly"  value="${order.pszj.LXDH}"  class="form-control "/>
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