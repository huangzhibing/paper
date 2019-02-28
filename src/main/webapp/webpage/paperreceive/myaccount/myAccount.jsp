<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="ani"/>
    <%@include file="/webpage/include/treeview.jsp" %>
    <%@ include file="/webpage/include/bootstraptable.jsp"%>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#editInfoBtn").click(function(){
                jp.open({
                    type: 2,
                    area: ['600px', '350px'],
                    title:"修改账户信息",
                    content: "${ctx}/myaccount/myAccount/bankInfoForm" ,
                    btn: ['确定', '关闭'],
                    yes: function(index, layero){
                        var body = top.layer.getChildFrame('body', index);
                        var inputForm = $(body).find('#inputForm');
                        var btn = body.find('#btnSubmit');
                        var top_iframe = top.getActiveTab().attr("name");//获取当前active的tab的iframe
                        inputForm.attr("target",top_iframe);//表单提交成功后，从服务器返回的url在当前tab中展示
                        if(inputForm.valid()){
                            jp.post("${ctx}/myaccount/myAccount/saveBankInfo",inputForm.serialize(),function(data){
                                if(data.success){
                                    jp.success(data.msg);
                                    jp.close(index);//关闭dialog
                                    jp.go("${ctx}/myaccount/myAccount");
                                }else{
                                    jp.error(data.msg);
                                }
                            });
                        }else{
                            return;
                        }
                    },
                    cancel: function(index){
                    }
                });
            });
            $('#submitBtm').click(function () {
                if("${expert.sfzh}" == ""||"${expert.yhkh}" == ""||"${expert.khh}" == ""){
                    layer.alert("请先完善账户信息");
                    return;
                }else {
                    jp.open({
                        type: 2,
                        area: ['600px','350px'],
                        title: "银行账户信息",
                        content: "${ctx}/myaccount/myAccount/bankInfoForm?flag=T",
                        btn: ['提现','关闭'],
                        yes:function (index,data) {
                            var body = top.layer.getChildFrame('body',index);
                            var inputForm = $(body).find("#inputForm");
                            var btn = body.find('#btnSubmit');
                            var top_ifram = top.getActiveTab().attr("name");
                            inputForm.attr("target",top_ifram);
                            if(inputForm.valid()){
                                jp.loading();
                                jp.post("${ctx}/myaccount/myAccount/save",$('#inputForm').serialize(),function (data) {

                                    if(data.success){
                                        jp.go("${ctx}/myaccount/myAccount");
                                        jp.success(data.msg);
                                        jp.close(index);
                                    }else {
                                        $("#inputForm").find("button:submit").button("reset");
                                        jp.error(data.msg);
                                    }
                                });
                            }else {
                                return;
                            }
                        },
                        cancel:function (index) {
                            
                        }
                    });
                    
                }
            });
        });
    </script>
    <title>我的账户</title>
</head>
<body>

<div class="wrapper wrapper-content">

            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">个人资料</h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                        <form:form id="inputForm" method="post" onsubmit="return false">
                        <sys:message content="${message}"/>
                                <div class="col-sm-10">
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <tbody>
                                            <tr>
                                                <td><strong>用户账号：</strong></td>
                                                <td>${expert.YHZH}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>姓名：</strong></td>
                                                <td>${expert.ZJXM}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>手机</strong></td>
                                                <td>${expert.YDDH}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>余额(人民币/元)：</strong></td>
                                                <td>${expert.YE}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                        <div>
                                            <p align="right">
                                                <strong>提现金额：</strong>
                                                <input id="txje" name="txje" htmlEscape="false" size="5" class="required input-lg" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
                                                <button id="submitBtm"class="btn btn-primary btn-lg btn-parsley" data-loading-text="正在提交...">提 现</button>
                                            </p>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">账户信息
                                <a id="editInfoBtn" title="修改账户信息" class="panelButton pull-right"><i class="fa fa-edit"></i>修改</a>
                            </h3>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-sm-8">
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <tbody>
                                            <tr>
                                                <td><strong>身份证号</strong></td>
                                                <td>${expert.sfzh}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>银行卡号</strong></td>
                                                <td>${expert.yhkh}</td>
                                            </tr>
                                            <tr>
                                                <td><strong>开户行</strong></td>
                                                <td>${expert.khh}</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
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