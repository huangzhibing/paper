<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="ani"/>
<%@include file="/webpage/include/treeview.jsp" %>
<%@ include file="/webpage/include/bootstraptable.jsp"%>
 <script type="text/javascript">
	 $(document).ready(function() {
		 $("#inputForm").validate({
				submitHandler: function(form){
					//var index=jp.loading();
					form.submit(); 
					//var isAdd='${isAdd}';
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
		 });
		$('#submitBtn1').click(function(){
			$.ajax({
				url:"${ctx}/endcheckout/endCheckout/resume",
				data:{date:$('#curdate').val()},
				success:function(re){
					$('#peri').val(re);
				}				
			});
		});
	 });
 </script>
<title>月末结账</title>
</head>
<body>
<div class="wrapper wrapper-content">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <a class="panelButton" href="${ctx}/endcheckout/endCheckout"><i class="ti-angle-left"></i> 返回</a>
                    </h3>
                </div>
                <div class="panel-body">
                	 <div class="row">
        			<div class="col-sm-12 col-md-12 animated fadeInRight">
                    <form:form id="inputForm" modelAttribute="billMain" action="${ctx}/endcheckout/endCheckout/checkoutLast" method="post" class="form-horizontal">
                        <form:hidden path="id"/>
                        <sys:message content="${message}"/>
                        <div class="form-group">
	                        <label class="col-sm-2 control-label"><font color="red">*</font>仓库编码：</label>
	                        <div class="col-sm-10">
	                            <sys:gridselect-item  url="${ctx}/warehouse/wareHouse/data" id="ware" name="ware.id" value="${billMain.ware.id}" labelName="ware.wareID" labelValue="${billMain.ware.wareID}"
	                                            title="选择库房号" cssClass="form-control required" fieldLabels="库房号|库房名称" fieldKeys="wareID|wareName" searchLabels="库房号|库房名称" searchKeys="wareID|wareName"
	                                            targetId="wareName" targetField="wareName"></sys:gridselect-item>
	                        </div>
	                    </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label"><font color="red">*</font>仓库名称：</label>
                            <div class="col-sm-10">
                                <form:input path="wareName" readonly="true" htmlEscape="false"    class="form-control required"/>
                            </div>
                        </div>
                         <div class="form-group">
                            <label class="col-sm-2 control-label"><font color="red">*</font>当前日期：</label>
                            <div class="col-sm-10">
                                <p class="input-group">
                                <div class='input-group form_datetime' id='billDate'>
                                    <input id="curdate" type='text' name="billDate" class="form-control required"  value="<fmt:formatDate value="${billMain.billDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                                    <span class="input-group-addon">
			                        <span class="glyphicon glyphicon-calendar"></span>
			                    </span>
                                </div>
                                </p>
                            </div>
                        <div class="form-group">
							<label class="col-sm-2 control-label"><font color="red">*</font>当前核算期：</label>
							<div class="col-sm-10">
								<form:input readOnly="true" id="peri" path="period.periodId" htmlEscape="false"    class="form-control required"/>
							</div>
							<form:input style="display:none;" readOnly="true" path="period.id" htmlEscape="false"    class="form-control required"/>
						</div>
                        </div>
					<div class="col-lg-2"></div>
			        <div class="col-lg-4">
			             <div class="form-group text-center">
			                 <div>
			                     <button type="button" id="submitBtn1" class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在提交...">生成核算期</button>
			                 </div>
			             </div>
			        </div>
			        <div class="col-lg-1"></div>
			        <div class="col-lg-4">
			             <div class="form-group text-center">
			                 <div>
			                     <button id="submitBtn"   class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在提交...">结账</button>
			                 </div>
			             </div>
			        </div>
			      </form:form>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>