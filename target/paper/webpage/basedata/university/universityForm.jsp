<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>高校代码表管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/university/university");
				}else{
				    jp.error(data.msg);
				    $("#inputForm").find("button:submit").button("reset");
				}
			});

		});
	</script>
	<script>
        function pickPoint() {
            // jp.open("./university/pickPoint.jsp");
            jp.open({
                type: 2,
                area: [1024, 768],
                auto: true,
                title:"地图选点",
                content: "${ctx}/university/university/pickPoint" ,
                btn: ['确定', '关闭'],
                /*                btn2: function(index, layero){
                                    var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                                    iframeWin.contentWindow.importExcel('${ctx}/task/crawTask/import', function (data) {
                        if(data.success){
                            jp.success(data.msg);
                            refresh();
                        }else{
                            jp.error(data.msg);
                        }
                        jp.close(index);
                    });//调用保存事件
                    return false;
                },*/
                btn1: function(index, layero) {
                    var iframeWin = layero.find('iframe')[0];
                    $("#gxjd").val(iframeWin.contentWindow.getJD());
                    $("#gxwd").val(iframeWin.contentWindow.getWD());
                    jp.close(index);
                },

                btn2: function(index){
                    jp.close(index);
                }
            });
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
				<a class="panelButton" href="${ctx}/university/university"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form id="inputForm" modelAttribute="university" action="${ctx}/university/university/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>高校代码：</label>
					<div class="col-sm-10">
						<c:if test="${mode == 'add'}">
							<form:input path="gxdm" htmlEscape="false"  class="form-control required"/>
						</c:if>
						<c:if test="${mode == 'edit'|| mode == 'view'}">
							<form:input path="gxdm" htmlEscape="false" readonly="true" class="form-control required" maxlength="5" minlength="5"/>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>高校名称：</label>
					<div class="col-sm-10">
						<form:input path="gxmc" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>高校纬度：</label>
					<div class="col-sm-10">
						<form:input path="gxwd" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>高校经度：</label>
					<div class="col-sm-10">
						<form:input path="gxjd" htmlEscape="false"    class="form-control required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否是985：</label>
					<div class="col-sm-10">
						<form:input path="whetheris985" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">是否是211：</label>
					<div class="col-sm-10">
						<form:input path="whetheris211" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<div class="form-group text-center" >
					<div>
						<button class="btn btn-primary btn-block btn-lg btn-parsley" onclick="pickPoint();return false" >地图选点</button>
					</div>
				</div>
			</div>
		<c:if test="${mode == 'add' || mode=='edit'}">
				<div class="col-lg-3"></div>
		        <div class="col-lg-9">
		             <div class="form-group text-center" style="margin-left:305px">
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