<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>评审记录管理</title>
	<meta name="decorator" content="ani"/>
	<script type="text/javascript">

		$(document).ready(function() {
		    console.log(${order.lw.LWLXDM})
			jp.ajaxForm("#inputForm",function(data){
				if(data.success){
				    jp.success(data.msg);
					jp.go("${ctx}/paperReview/review");
				}else{
				    jp.error(data.msg);
				    $("#inputForm").find("button:submit").button("reset");
				}
			});

		});
		function justNum(a){
			a.value=a.value.replace(/\D/g,'')
		}
		function inField(a, num){
			var score = parseFloat(a.value);
            var num = parseFloat(num);
			if(score<0||score>num){
				layer.alert("请输入0~"+num+"的数字");
				document.getElementById("btnsub").disabled="disabled";

			}else{
				document.getElementById("btnsub").disabled="";
			}
		}
		function calZF() {
            lwxtfs=parseFloat(document.getElementById("lwxtfs").value);
            lwxzfs = parseFloat(document.getElementById("lwxzfs").value);
            if('${order.lw.LWLXDM}'=='1' ||'${order.lw.LWLXDM}'=='4') {
                lyljfs = parseFloat(document.getElementById("lyljfs").value);
                lwsp1fs = parseFloat(document.getElementById("lwsp1fs").value);
                lwsp2fs = parseFloat(document.getElementById("lwsp2fs").value);
                lwsp3fs = parseFloat(document.getElementById("lwsp3fs").value);
                document.getElementById("zf").value=lwxtfs+lyljfs+lwsp1fs+lwsp2fs+lwsp3fs+lwxzfs;
            }else {
                wxzsfs = parseFloat(document.getElementById("wxzsfs").value);
                zyspfs = parseFloat(document.getElementById("zyspfs").value);
                yjgzfs = parseFloat(document.getElementById("yjgzfs").value);
                yjcgfs = parseFloat(document.getElementById("yjcgfs").value);
                document.getElementById("zf").value=lwxtfs+lwxzfs+wxzsfs+zyspfs+yjgzfs+yjcgfs;
            }

        }
        function confirm(){
            layer.confirm('评审书提交后将不可更改',{btn:['确认', '取消']},function (){
                jp.close()
				jp.loading()
                jp.post('${ctx}/paperReview/review/save',$('#inputForm').serialize(),function (data){
                    jp.success(data.msg)
					window.location.href='${ctx}/paperReview/review'
                })
			},function(){

			})
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
				<a class="panelButton" href="${ctx}/paperReview/review"><i class="ti-angle-left"></i> 返回</a>
			</h3>
		</div>
		<div class="panel-body">
		<form:form onmousemove="calZF()"  id="inputForm" modelAttribute="order" action="${ctx}/paperReview/review/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文编号：</label>
					<div class="col-sm-10">
						<form:input readOnly="true" path="lw.LWBH" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">论文题目：</label>
					<div class="col-sm-10">
						<form:input readOnly="true" path="lw.LWMC" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">学生姓名：</label>
					<div class="col-sm-10" style="display: none;">
						<form:input readOnly="true" path="khxm" htmlEscape="false"    class="form-control "/>
					</div>
					<div class="col-sm-10">
						<input value="***" readOnly="true" path="" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">导师姓名：</label>
					<div class="col-sm-10" style="display: none">
						<form:input  readOnly="true" path="student.dsxm" htmlEscape="false"    class="form-control "/>
					</div>
					<div class="col-sm-10">
						<input value="***" readOnly="true" path="" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">专业名称：</label>
					<div class="col-sm-10">
						<form:input readOnly="true" path="student.specialityManage.zymc" htmlEscape="false"    class="form-control "/>
					</div>
				</div>
				<%--<div class="form-group">
					<label class="col-sm-2 control-label">学位点名称：</label>
					<div class="col-sm-10">
						<form:input readOnly="true" path="student.degreepointManage.xwdmc" htmlEscape="false"    class="form-control "/>
					</div>
				</div>--%>
				<%-- <div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>评审等级：</label>
					<div class="col-sm-10">
						<form:select path="psdj" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('T_reviewRank')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</div>
				</div> --%>
				<%--<div class="form-group">--%>
					<%--<label class="col-sm-2 control-label"><font color="red">*</font>评审分数：</label>--%>
					<%--<div class="col-sm-10">--%>
						<%--<form:input  onkeyup="justNum(this);inField(this)" onafterpaste="justNum(this);inField(this)" id="psfs" path="psfs" htmlEscape="false"    class="form-control required"/>--%>
					<%--</div>--%>
				<%--</div>--%>
			<c:if test="${order.lw.LWLXDM eq '0' or order.lw.LWLXDM eq '2' }">
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>论文选题：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">论文选题的理论意义或应用价值。满分10分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,10)" onafterpaste="justNum(this);inField(this,10)" id="lwxtfs" path="lwxtfs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>文献综述：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">论文所反映的作者对国内外动态、本学科领域前沿知识的了解程度，对文献资料的掌握及综述能力。满分10分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,10)" onafterpaste="justNum(this);inField(this,10)" id="wxzsfs" path="wxzsfs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>专业水平：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">论文所反映的作者已掌握的基础理论、专业知识、基本研究方法和技能，以及具有的独立进行科研工作的能力。满分20分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,20)" onafterpaste="justNum(this);inField(this,10)" id="zyspfs" path="zyspfs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>研究工作：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">研究资料、数据或论据的真实性和有效性，概念清晰的程度，分析的逻辑性，论证的严谨性，结论的合理性。满分20分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,20)" onafterpaste="justNum(this);inField(this,10)" id="yjgzfs" path="yjgzfs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>研究成果：</label>
				<label class="col-sm-8 control-label"><p class=" text-left">论文的新见解、新观点、新方法，研究成果的理论价值或实践意义。满分30分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,30)" onafterpaste="justNum(this);inField(this,10)" id="yjcgfs" path="yjcgfs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>论文写作：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">论文结构的条理性和层次性，论文格式（图表、公式、计量单位、参考文献等）的规范性，文字表达能力。满分10分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,10)" onafterpaste="justNum(this);inField(this,10)" id="lwxzfs" path="lwxzfs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>总　　分：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">综合评定等级：优秀90～100分；良好80～89分；合格70～79分；不合格＜70分。满分100分</p></label>
				<div class="col-sm-2">
					<form:input   id="zf" path="zf" htmlEscape="false" readonly="true"   class="form-control required"/>
				</div>
			</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>是否同意答辩：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="psdj" items="${fns:getDictList('T_Reply_X')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</div>
				</div>
			</c:if>
			<!------------------------------------------------------------------------------------>
			<c:if test="${order.lw.LWLXDM eq '1' or order.lw.LWLXDM eq '4'}">
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>论文选题：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">论文选题所具有的理论意义或实用价值。满分10分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,10)" onafterpaste="justNum(this);inField(this,10)" id="lwxtfs" path="lwxtfs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>对本学科及相关领域的了解：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">对国内外学术和实务动态、本学科领域前沿知识、本专业理论与技术的了解程度；对文献资料和事实材料（如案例背景）的掌握及综述能力。满分10分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,10)" onafterpaste="justNum(this);inField(this,10)" id="lyljfs" path="lyljfs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>论文水平：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">论文所反映的作者已掌握的基础理论和专业知识水平，发现、分析、解决问题能力，科学研究工作的能力或独立担负专门工作的能力。满分30分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,20)" onafterpaste="justNum(this);inField(this,10)" id="lwsp1fs" path="lwsp1fs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>论文水平：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">调查报告的广度或案例研究的深度；研究论文的新见解、新观点、新方法、新数据、新资料；研究成果的理论或实用价值。满分30分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,20)" onafterpaste="justNum(this);inField(this,10)" id="lwsp2fs" path="lwsp2fs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>论文水平：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">概念清晰与分析严谨的程度；材料的真实性和结论的合理性。满分10分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,30)" onafterpaste="justNum(this);inField(this,10)" id="lwsp3fs" path="lwsp3fs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>论文写作：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">论文规范性与文字、图表表达能力。满分10分</p></label>
				<div class="col-sm-2">
					<form:input  onkeyup="justNum(this);inField(this,10)" onafterpaste="justNum(this);inField(this,10)" id="lwxzfs" path="lwxzfs" htmlEscape="false"    class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label"><font color="red">*</font>总　　分：</label>
				<label class="col-sm-8 control-label text-left"><p class=" text-left">综合评定等级：优秀90～100分；良好80～89分；合格70～79分；不合格＜70分。满分100分</p></label>
				<div class="col-sm-2">
					<form:input   id="zf" path="zf" htmlEscape="false" readonly="true"   class="form-control required"/>
				</div>
			</div>
				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>是否同意答辩：</label>
					<div class="col-sm-10">
						<form:radiobuttons path="psdj" items="${fns:getDictList('T_Reply_Z')}" itemLabel="label" itemValue="value" htmlEscape="false" class="i-checks required"/>
					</div>
				</div>
			</c:if>

				<div class="form-group">
					<label class="col-sm-2 control-label"><font color="red">*</font>评审意见：</label>
					<div class="col-sm-10">
						<form:textarea path="psyj" htmlEscape="false" rows="10"    class="form-control required"/>
					</div>
				</div>
		<c:if test="${mode == 'add' || mode=='edit'}">
				<div class="col-lg-3"></div>
		        <div class="col-lg-6">
		             <div class="form-group text-center">
		                 <div>
		                     <button id="btnsub" onclick="confirm()" type="button" class="btn btn-primary btn-block btn-lg btn-parsley" data-loading-text="正在提交...">提 交</button>
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