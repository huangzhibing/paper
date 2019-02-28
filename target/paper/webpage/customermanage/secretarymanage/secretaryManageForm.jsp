<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>秘书信息管理</title>
	<meta name="decorator" content="ani"/>
    <style>
        #questionmark{
            float: right;
            color: red;
        }
        #questionmarktext{
            position: absolute;
            right: 20px;
            width: 350px;
            height: auto;          /* 设置最大高度，当高度达到此值时出现滚动条 */
            z-index: 10;
            background-color: #E0E5E5;
            overflow: auto;              /* 自动添加滚动条 */
            box-shadow:0px 0px 10px #000;   /* 外阴影 */
            display: none;   /* 默认隐藏 */
        }
        #questiontable{
            table-layout:fixed;         /* 用于实现表格td自动换行的部分代码*/
            width: 325px;
        }

        #questiontable tr td{
            width: 325px;
            height:30px;
            font-size: 16px;
            font-family: Georgia;
            color: #555555;
            word-wrap:break-word;   /*自动换行*/
            padding: 0 0 0 0;
        }
        #questiontable tr td:hover{
            background-color: #D9D9D9;
        }
        #questiontable hr{
            border:1px;
            width: 325px;
            margin-bottom: 0px;
        }
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/secretarymanage/secretaryManage");
				}else{
				    jp.error(data.msg);
				    $("#inputForm").find("button:submit").button("reset");
				}
		});
        });
        //显示悬浮层
        function showInfo(){
            document.getElementById("questionmarktext").style.display='block';
            // document.getElementById("inform").css("display","block");
        }
        //隐藏悬浮层
        function hideInfo(event){
            var informDiv = document.getElementById('questionmarktext');
            var ev = window.event || arguments.callee.caller.arguments[0];
            var x=ev.clientX;
            console.log(x);
            var y=ev.clientY;
            var divx1 = informDiv.offsetLeft;
            var divy1 = informDiv.offsetTop;
            var divx2 = informDiv.offsetLeft + informDiv.offsetWidth;
            var divy2 = informDiv.offsetTop + informDiv.offsetHeight;
            console.log(divx1);
            console.log(divx2);
            if( x < divx1 || x > divx2 || y < divy1 || y > divy2){
                document.getElementById('questionmarktext').style.display='none';
            }

        }
	</script>
</head>
<body>
<div class="wrapper wrapper-content">				
<div class="row">
	<div class="col-md-12">
	<div class="panel panel-primary">
		<div class="panel-heading" >
			<h3 class="panel-title" style="display: inline;">
				<a class="panelButton" href="${ctx}/secretarymanage/secretaryManage"><i class="ti-angle-left"></i> 返回</a>
			</h3>
            <p id="questionmark" style="float: right" onmouseover="showInfo()" onmouseout="hideInfo()">功能说明</p>
            <div id="questionmarktext" onmouseover="showInfo()" onmouseout="hideInfo(event)">
                <table id="questiontable">
                    <tr>
                        <td>
                            秘书代码：前半部分由选择秘书所在高校后自动代入，后半部分由用户自行确认输入，必填项。
                            <hr/>
                        </td>
                        <td>
                            秘书姓名：输入对应秘书姓名，必填项。
                            <hr/>
                        </td>
                        <td>
                            移动电话：输入对应秘书的电话号码，选填项。
                            <hr/>
                        </td>
                        <td>
                            邮件：输入对应秘书的邮箱地址，选填项。
                            <hr/>
                        </td>
                        <td>
                            高校名称：点击后选择秘书所在高校，选择后带入秘书代码前六位，必填项。
                            <hr/>
                        </td>
                    </tr>
                </table>
            </div>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="secretaryManage" action="${ctx}/secretarymanage/secretaryManage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>秘书代码：</label>
					<div class="col-sm-10" style="display: inline-flex" >
						<input type="text" name="gxdm_input" id = "gxdm" placeholder="请选择所在高校" readonly="readonly" style="    margin-right: 20px;width: 80px;text-align: center;"/>
						<form:input path="msdm" name="msdm_input" id="msdm" htmlEscape="false" placeholder="请输入秘书工号"  class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>秘书姓名：</label>
					<div class="col-sm-10">
						<form:input path="msxm" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">移动电话：</label>
					<div class="col-sm-10">
						<form:input path="yddh" name="yddm_input" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">邮件：</label>
					<div class="col-sm-10">
						<form:input path="yj" name="yj_input" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>高校名称：</label>
					<div class="col-sm-10" >
						<sys:gridselect-select url="${ctx}/university/university/data" id="university" name="university.gxdm" value="${secretaryManage.university.gxdm}" labelName="university.gxmc" labelValue="${secretaryManage.university.gxdm}"
										targetName="university.gxmc" targetId="gxdm" title="选择高校" cssClass="form-control required" fieldLabels="高校代码|高校名称" fieldKeys="gxdm|gxmc" searchLabels="高校代码|高校名称" searchKeys="gxdm|gxmc"></sys:gridselect-select>
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