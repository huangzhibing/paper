<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>论文接收管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/receives/receives");
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
		
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
			$(list+idx).find(".form_datetime").each(function(){
				 $(this).datetimepicker({
					 format: "YYYY-MM-DD HH:mm:ss"
			    });
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
<div class="wrapper wrapper-content">				
<div class="row">
	<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title"> 
				<a class="panelButton" href="${ctx}/receives/receives"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="receives" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>订单号：</label>
					<div class="col-sm-10">
						<form:input path="ddh" htmlEscape="false"  readonly="true"  class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>下单时间：</label>
					<div class="col-sm-10">
						<div class='input-group form_datetime' id='xdrq'>
							<input type='text'  name="xdrq" class="form-control required" readonly="true"  value="<fmt:formatDate value="${receives.xdrq}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">预约时间：</label>
					<div class="col-sm-10">
						<div class='input-group form_datetime' id='yysj'>
							<input type='text'  name="yysj" class="form-control "  value="<fmt:formatDate value="${receives.yysj}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>订单类型：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="ddlxdm" items="${fns:getDictList('T_OrderType_C')}" readonly="true" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">总价：</label>
					<div class="col-sm-10">
						<form:input path="zj" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">付款时间：</label>
					<div class="col-sm-10">
						<div class='input-group form_datetime' id='fksj'>
							<input type='text'  name="fksj" class="form-control "  value="<fmt:formatDate value="${receives.fksj}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">客户姓名：</label>
					<div class="col-sm-10">
						<form:input path="khxm" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">移动电话：</label>
					<div class="col-sm-10">
						<form:input path="yddh" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">申请退款时间：</label>
					<div class="col-sm-10">
						<div class='input-group form_datetime' id='sqtksj'>
							<input type='text'  name="sqtksj" class="form-control "  value="<fmt:formatDate value="${receives.sqtksj}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">退款理由：</label>
					<div class="col-sm-10">
						<form:input path="tkly" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">退款审核结果：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="tkshjg" items="${fns:getDictList('T_TKSHJG')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">用户评价：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="yhpj" items="${fns:getDictList('T_YHPJ')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">评价详情：</label>
					<div class="col-sm-10">
						<form:input path="pjxq" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>订单状态：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="ddztdm" items="${fns:getDictList('T_OrderStatus_C')}" readonly="true" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>区域：</label>
					<div class="col-sm-10">
				<div class=" input-group" style=" width: 100%;">
					  <form:input path="qydm.code" htmlEscape="false"  class="required" data-toggle="city-picker" style="height: 34px;"/>
				</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>评审专家：</label>
					<div class="col-sm-10">
						<form:input path="pszj" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">备注：</label>
					<div class="col-sm-10">
						<form:input path="note" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">评审等级：</label>
					<div class="col-sm-10">
						<form:select path="psdj" class="form-control ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('T_reviewRank')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">评审意见：</label>
					<div class="col-sm-10">
						<form:textarea path="psyj" htmlEscape="false" rows="4"    class="form-control "/>
					</div>
				</div>
		<div class="tabs-container">
            <ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#tab-1" aria-expanded="true">论文表：</a>
                </li>
            </ul>
            <div class="tab-content">
				<div id="tab-1" class="tab-pane fade in  active">
			<a class="btn btn-white btn-sm" onclick="addRow('#papersList', papersRowIdx, papersTpl);papersRowIdx = papersRowIdx + 1;" title="新增"><i class="fa fa-plus"></i> 新增</a>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
					<tr>
						<th class="hide"></th>
						<th>论文编号</th>
						<th>论文题目</th>
						<th>学生学号</th>
						<th>论文文件</th>
						<th>论文类型</th>
						<th>论文状态</th>
						<th width="10">&nbsp;</th>
					</tr>
				</thead>
				<tbody id="papersList">
				</tbody>
			</table>
			<script type="text/template" id="papersTpl">//<!--
				<tr id="papersList{{idx}}">
					<td class="hide">
						<input id="papersList{{idx}}_id" name="papersList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
						<input id="papersList{{idx}}_delFlag" name="papersList[{{idx}}].delFlag" type="hidden" value="0"/>
					</td>
					
					<td>
						<input id="papersList{{idx}}_LWBH" name="papersList[{{idx}}].LWBH" type="text" value="{{row.LWBH}}"    class="form-control "/>
					</td>
					
					
					<td>
						<input id="papersList{{idx}}_LWMC" name="papersList[{{idx}}].LWMC" type="text" value="{{row.LWMC}}"    class="form-control "/>
					</td>
					
					
					<td>
						<sys:gridselect url="${ctx}/degreepoint/degreepointManage/data" id="papersList{{idx}}_XSXH" name="papersList[{{idx}}].XSXH" value="{{row.XSXH}}" labelName="papersList{{idx}}." labelValue="{{row.}}"
							 title="选择学生学号" cssClass="form-control  " fieldLabels="学号|姓名" fieldKeys="xsxh|xsxm" searchLabels="学号|姓名" searchKeys="xsxh|xsxm" ></sys:gridselect>
					</td>
					
					
					<td>
						<sys:fileUpload path="papersList{{idx}}_LWWJ"  value="{{row.LWWJ}}" type="file" uploadPath="/receives/receives"/>
					</td>
					
					
					<td>
						<select id="papersList{{idx}}_LWLXDM" name="papersList[{{idx}}].LWLXDM" data-value="{{row.LWLXDM}}" class="form-control m-b  ">
							<option value=""></option>
							<c:forEach items="${fns:getDictList('T_PaperType_C')}" var="dict">
								<option value="${dict.value}">${dict.label}</option>
							</c:forEach>
						</select>
					</td>
					
					
					<td>
						<select id="papersList{{idx}}_LWZTDM" name="papersList[{{idx}}].LWZTDM" data-value="{{row.LWZTDM}}" class="form-control m-b  ">
							<option value=""></option>
							<c:forEach items="${fns:getDictList('T_PaperStatus_C')}" var="dict">
								<option value="${dict.value}">${dict.label}</option>
							</c:forEach>
						</select>
					</td>
					
					<td class="text-center" width="10">
						{{#delBtn}}<span class="close" onclick="delRow(this, '#papersList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
					</td>
				</tr>//-->
			</script>
			<script type="text/javascript">
				var papersRowIdx = 0, papersTpl = $("#papersTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
				$(document).ready(function() {
					var data = ${fns:toJson(receives.papersList)};
					for (var i=0; i<data.length; i++){
						addRow('#papersList', papersRowIdx, papersTpl, data[i]);
						papersRowIdx = papersRowIdx + 1;
					}
				});
			</script>
			</div>
		</div>
		</div>

		</form:form>
            <div class="col-lg-3"></div>
            <div class="col-lg-6">
                <div class="form-group text-center">
                    <div>
                        <a href="${ctx}/receives/receives">
                            <button class="btn btn-primary btn-block btn-lg btn-parsley" href="${ctx}/receives/receives" >关 闭</button>
                        </a>
                    </div>
                </div>
            </div>
		</div>				
	</div>
	</div>
</div>
</div>
</body>
</html>