<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>爬取信息管理</title>
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
                jp.post("${ctx}/info/crawInfo/save",$('#inputForm').serialize(),function(data){
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
		<form:form id="inputForm" modelAttribute="crawInfo" class="form-horizontal">
		<form:hidden path="id"/>	
		<table class="table table-bordered">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>爬虫类Id：</label></td>
					<td class="width-35">
						<sys:treeselect id="crawType" name="crawType" value="${crawInfo.crawType}" labelName="" labelValue="${crawInfo.}"
							title="爬虫类Id" url="/crawType/crawTaskType/treeData" extId="${crawInfo.id}" cssClass="form-control required" allowClear="true"/>
					</td>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>姓名：</label></td>
					<td class="width-35">
						<form:input path="name" htmlEscape="false"    class="form-control required"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">职称：</label></td>
					<td class="width-35">
						<form:input path="professionalTitle" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">职级：</label></td>
					<td class="width-35">
						<form:input path="level" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">邮箱：</label></td>
					<td class="width-35">
						<form:input path="email" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">所属大学：</label></td>
					<td class="width-35">
						<form:input path="university" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">最高学位：</label></td>
					<td class="width-35">
						<form:input path="degree" htmlEscape="false"    class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">毕业院校：</label></td>
					<td class="width-35">
						<form:input path="graduatedFrom" htmlEscape="false"    class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right"><font color="red">*</font>爬取Url：</label></td>
					<td class="width-35">
						<form:input path="url" htmlEscape="false"    class="form-control required"/>
					</td>
					<td class="width-15 active"></td>
		   			<td class="width-35" ></td>
		  		</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>