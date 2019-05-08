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
                    jp.go("${ctx}/expertmanagement/expert");
                }else{
                    jp.error(data.msg);

                    $("#inputForm").find("button:submit").button("reset");
                }
            });

            $('#CSNY').datetimepicker({
                format: "YYYY-MM-DD"
            });

            //单选框选择之后触发的事件
            $('.carries_type').on('ifChecked',function () {
                var radioBtn = document.getElementsByName('ZJXLDM');
                if(radioBtn[0].checked){
                   $('#class1').css("display","block")
                    $('#class2').css("display","none")
                }else if(radioBtn[1].checked){
                    $('#class1').css("display","none")
                    $('#class2').css("display","block")
                }

            })

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
				<a class="panelButton" href="${ctx}/expertmanagement/expert"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="expert" action="${ctx}/expertmanagement/expert/save" method="post" class="form-horizontal" >
		<form:hidden path="id"/>
            <div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>用户账号：</label>
					<div class="col-sm-10">
						<form:input path="YHZH" htmlEscape="false" class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>专家姓名：</label>
					<div class="col-sm-10">
						<form:input path="ZJXM" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>余额：</label>
					<div class="col-sm-10">
						<form:input path="YE" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>评审次数：</label>
				<div class="col-sm-10">
					<form:input path="pscs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">身份证：</label>
				<div class="col-sm-10">
					<form:input path="sfzh" htmlEscape="false"    class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">开户行：</label>
				<div class="col-sm-10">
					<form:input path="khh" htmlEscape="false"    class="form-control"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">银行卡号：</label>
				<div class="col-sm-10">
					<form:input path="yhkh" htmlEscape="false"    class="form-control"/>
				</div>
			</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>专家类型：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="ZJXLDM" id="ZJXLDM" onclick="alert(1);" items="${fns:getDictList('T_ExpertType_C')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks carries_type required"/>
						<%--<form:radiobutton path="ZJXLDM" label="高校专家" value="0" class="i-checks " onclick="radioClick();"/>--%>
						<%--<form:radiobutton path="ZJXLDM" label="行业专家" value="1" onclick="radioClick();"/>--%>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font> 专业名称：</label>
					<div class="col-sm-10">
						<sys:gridselect url="${ctx}/specialitymanage/specialityManage/data" id="specialityManage" name="specialityManage.zydm" value="${expert.specialityManage.zydm}" labelName="specialityManage.zymc" labelValue="${expert.specialityManage.zymc}"
										title="选择专业" cssClass="form-control required" fieldLabels="专业代码|专业名称" fieldKeys="zydm|zymc" searchLabels="专业代码|专业名称" searchKeys="zydm|zymc" ></sys:gridselect>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>专业知识职务：</label>
					<div class="col-sm-10">
						<sys:gridselect url="${ctx}/basedata/positionmanagement/professionalTechniqueRank/data" id="ZYJSZWDM" name="ZYJSZWDM.rankCode" value="${expert.ZYJSZWDM.rankCode}" labelName="ZYJSZWDM.rankName" labelValue="${expert.ZYJSZWDM.rankName}"
							 title="选择专业知识职务代码" cssClass="form-control required" fieldLabels="专业技术级别代码|专业技术级别名称|岗位等级|国家专业技术岗位级别" fieldKeys="rankCode|rankName|rankTitle|rankSection" searchLabels="专业技术级别代码|专业技术级别名称|岗位等级|国家专业技术岗位级别" searchKeys="rankCode|rankName|rankTitle|rankSection" ></sys:gridselect>
					</div>
				</div>
				<div id="class1" class="form-group" style="display: none">
					<label class="col-sm-2 control-label"><font color="red">*</font>导师级别：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="DSJBDM" items="${fns:getDictList('T_TutorLevel_C')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</div>
				</div>
				<div id="class2" class="form-group" style="display: none">
					<label class="col-sm-2 control-label"><font color="red">*</font>导师级别：</label>
					<div class="col-sm-10">
						<form:radiobutton path="DSJBDM" label="实践博导" value="4" htmlEscape="false" class="i-checks "/>
						<form:radiobutton path="DSJBDM" label="实践硕导" value="5" htmlEscape="false" class="i-checks "/>
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-2 control-label">性别：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="XBDM" items="${fns:getDictList('T_SexType_C')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">区域：</label>
					<div class="col-sm-10">
						<sys:treeselect-area id="QYDM" name="QYDM.code" value="${expert.QYDM.code}" labelName="QYDM.name" labelValue="${expert.QYDM.name}"
							title="区域" url="/sys/area/treeData" cssClass="form-control" allowClear="true" notAllowSelectParent="true"></sys:treeselect-area>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font> 电子邮箱：</label>
					<div class="col-sm-10">
						<form:input path="DZYX" htmlEscape="false"  class="form-control email"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>单位：</label>
					<div class="col-sm-10">
						<sys:gridselect url="${ctx}/organizationmanagement/organizationManagement/data" id="DWDM" name="DWDM.dwdm" value="${expert.DWDM.dwdm}" labelName="DWDM.dwmc" labelValue="${expert.DWDM.dwmc}"
							 title="选择单位代码" cssClass="form-control required" fieldLabels="单位代码|单位名称" fieldKeys="dwdm|dwmc" searchLabels="单位代码|单位名称" searchKeys="dwdm|dwmc" ></sys:gridselect>
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
					<label class="col-sm-2 control-label">二级学院：</label>
					<div class="col-sm-10">
						<form:input path="EJXY" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<%--<div class="form-group">--%>
					<%--<label class="col-sm-2 control-label"><font color="red">*</font> 最高学位：</label>--%>
					<%--<div class="col-sm-10">--%>
						<%--<form:input path="ZJXW" htmlEscape="false"    class="form-control required"/>--%>
					<%--</div>--%>
				<%--</div>--%>
				<div class="form-group">
					<label class="col-sm-2 control-label">毕业院校：</label>
					<div class="col-sm-10">
						<sys:gridselect url="${ctx}/university/university/data" id="BYYXDM" name="BYYXDM.gxdm" value="${expert.BYYXDM.gxdm}" labelName="BYYXDM.gxmc" labelValue="${expert.BYYXDM.gxmc}"
							 title="选择毕业院校代码" cssClass="form-control" fieldLabels="高校代码|高校名称" fieldKeys="gxdm|gxmc" searchLabels="高校代码|高校名称" searchKeys="gxdm|gxmc" ></sys:gridselect>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">外语语种：</label>
					<div class="col-sm-10">
						<form:input path="WYYZ" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">外语熟悉程度：</label>
					<div class="col-sm-10">
						<form:input path="WYSXCD" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font> 移动电话：</label>
					<div class="col-sm-10">
						<form:input path="YDDH" htmlEscape="false"  size="11"  class="form-control digits"/>
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
					<label class="col-sm-2 control-label"><font color="red">*</font>专家状态：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="ZJZTDM" items="${fns:getDictList('T_ExpertStatus_C')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
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