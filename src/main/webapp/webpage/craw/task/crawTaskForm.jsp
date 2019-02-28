<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>爬虫任务管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {

		});
		function save() {
            var isValidate = jp.validateForm('#inputForm');//校验表单
            if(!isValidate){
                return false;
			}else{
                jp.loading();
                jp.post("${ctx}/task/crawTask/save",$('#inputForm').serialize(),function(data){
                    if(data.success){
                        jp.getParent().refresh();
                        var dialogIndex = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                        parent.layer.close(dialogIndex);
                        jp.success(data.msg)

                    }else{
                        jp.error(data.msg);
                    }
                })
			}

        }
	</script>
</head>
<body class="bg-white">
		<form:form id="inputForm" modelAttribute="crawTask" class="form-horizontal">
		<form:hidden path="id"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>任务名称：</label></td>
					<td class="width-35">
						<form:input path="taskName" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>目标入口URL：</label></td>
					<td class="width-35">
						<form:input path="helpUrl" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>目标URL(RegEx)：</label></td>
					<td class="width-35">
						<form:input path="targetUrl" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"><label class="pull-right">url前缀(可选)：</label></td>
					<td class="width-35">
						<form:input path="urlPrefix" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label><font color="red">*</font>爬取Xpath(用分号隔开)：</label></td>
					<td class="width-70">
						<form:textarea path="xpath" htmlEscape="false" rows="8"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label><font color="red">*</font>爬取信息名(用分号隔开)：</label></td>
					<td class="width-70">
						<form:textarea path="name" htmlEscape="false" rows="8"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">爬取方式：</label></td>
					<td class="width-35">
						<form:input path="crawMethod" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">爬虫类型：</label></td>
					<td class="width-35">
						<sys:treeselect id="crawType" name="crawType" value="${crawTask.crawType}" labelName="类别名称" labelValue="${crawTask.crawType.typeName}"
							title="爬虫类型" url="/type/crawTaskType/treeData" extId="${crawTask.id}" cssClass="form-control " allowClear="true"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">爬取失败列表：</label></td>
					<td class="width-35">
						<form:input path="failUrl" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">需要异步爬取的信息：</label></td>
					<td class="width-35">
						<form:input path="ajaxUrl" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">异步爬取的参数：</label></td>
					<td class="width-35">
						<form:input path="ajaxParam" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">爬取方式：</label></td>
					<td class="width-35">
						<form:input path="type" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">匹配标题：</label></td>
					<td class="width-35">
						<form:input path="title" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>