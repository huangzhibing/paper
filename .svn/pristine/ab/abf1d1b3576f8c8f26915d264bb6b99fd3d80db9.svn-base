<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>专家信息表管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {


			if($("#YHZH").val() != ""){
			    $("#YHZH").attr('readOnly',true);
			}

            jp.ajaxForm("#inputForm",function(data){
                if(data.success){
                    jp.success(data.msg);
                    jp.go("${ctx}/expertinfo/expertInfo");
                }else{
                    jp.error(data.msg);
                    $("#inputForm").find("button:submit").button("reset");
                }
            });

            $('#CSNY').datetimepicker({
                format: "YYYY-MM-DD"
            });

            $('#jihuoButton').click(function () {
				var expertId = $('#YHZH').val();
				jp.get("${ctx}/expertinfo/expertInfo/jihuo?expertId="+expertId,function (data) {
                    if(data.success){
                        jp.success(data.msg);
                        jp.go("${ctx}/expertinfo/expertInfo");
                    }else{
                        jp.error(data.msg);
                        $("#inputForm").find("button:submit").button("reset");
                    }
                })
            });

        });

	</script>
</head>
<body>
<div class="wrapper wrapper-content">
<div class="row">
	<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="expert" action="${ctx}/expertinfo/expertInfo/save" method="post" class="form-horizontal" >
		<form:hidden path="id"/>
            <div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>用户账号：</label>
					<div class="col-sm-10">
						<form:input path="YHZH" htmlEscape="false" class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>姓名：</label>
					<div class="col-sm-10">
						<form:input path="ZJXM" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>性别：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="XBDM" items="${fns:getDictList('T_SexType_C')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>身份证号:</label>
					<div class="col-sm-10">
						<form:input path="sfzh" maxlength="50"   class="form-control isIdCardNo required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>银行卡号:</label>
					<div class="col-sm-10">
						<form:input path="yhkh"  maxlength="19"  class="form-control  max-width-250 digits required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font> 开户行:</label>
					<div class="col-sm-10">
						<sys:gridselect-bank url="${ctx}/myaccount/myAccount/bankData" id="khh" name="khh" value="${expert.khh}" labelName="khh" labelValue="${expert.khh}"
											 title="选择开户行" cssClass="form-control required" fieldLabels="归属县|开户行" fieldKeys="xian|khh" searchLabels="归属县|开户行" searchKeys="xian|khh" ></sys:gridselect-bank>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">电子邮箱：</label>
					<div class="col-sm-10">
						<form:input path="DZYX" htmlEscape="false"  class="form-control email required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>出生年月：</label>
					<div class="col-sm-10">
						<p class="input-group">
						<div class='input-group form_datetime' id='CSNY'>
							<input type='text'  name="CSNY" class="form-control required" value="<fmt:formatDate value="${expert.CSNY}" pattern="yyyy-MM-dd"/>"/>
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
						</div>
						</p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font> 移动电话：</label>
					<div class="col-sm-10">
						<form:input path="YDDH" htmlEscape="false"  size="11"  class="form-control digits required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>区域：</label>
					<div class="col-sm-10">
						<sys:treeselect-area id="QYDM" name="QYDM.code" value="${expert.QYDM.code}" labelName="QYDM.name" labelValue="${expert.QYDM.name}"
											 title="区域" url="/sys/area/treeData" cssClass="form-control required" allowClear="true" notAllowSelectParent="true"></sys:treeselect-area>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">联系电话：</label>
					<div class="col-sm-10">
						<form:input path="LXDH" htmlEscape="false"  class="form-control digits"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">联系地址：</label>
					<div class="col-sm-10">
						<form:input path="LXDZ" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">邮编：</label>
					<div class="col-sm-10">
						<form:input path="YB" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">籍贯：</label>
					<div class="col-sm-10">
						<form:input path="JG" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">专家状态：</label>
				<div class="col-sm-10">
					<form:radiobuttons disabled="true" path="ZJZTDM" items="${fns:getDictList('T_ExpertStatus_C')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
				</div>
			</div>
				<div class="col-lg-3"></div>
		        <div class="col-lg-6">
		             <div class="form-group text-center">
		                 <div>
		                     <button class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在保存...">保 存</button>
		                 </div>
		             </div>
					<div class="form-group text-center">
						<div>
							<button id="jihuoButton" type="button" class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在激活...">激 活</button>
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