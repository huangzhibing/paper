<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/webpage/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="fieldLabels" type="java.lang.String" required="true" description="表格Th里显示的名字"%>
<%@ attribute name="fieldKeys" type="java.lang.String" required="true" description="表格Td里显示的值"%>
<%@ attribute name="searchLabels" type="java.lang.String" required="true" description="表格Td里显示的值"%>
<%@ attribute name="searchKeys" type="java.lang.String" required="true" description="表格Td里显示的值"%>
<%@ attribute name="targetId" type="java.lang.String" required="false" description="额外赋值目标Id"%>
<%@ attribute name="targetField" type="java.lang.String" required="false" description="额外赋值字段"%>
<%@ attribute name="extraField" type="java.lang.String" required="false" description="额外赋值字段"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="数据地址"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="isMultiSelected" type="java.lang.Boolean" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled"%>
<%@ attribute name="limite" type="java.lang.String" required="false" description=""%>
	<input id="${id}Id"  type="hidden"  name="${name}"   value="${value}"/>
	<div class="input-group" style="width: 100%">
		<input id="${id}Names"  name="${labelName}" ${allowInput?'':'readonly="readonly"'}  type="text" value="${labelValue}" data-msg-required="${dataMsgRequired}"
		class="${cssClass}" style="${cssStyle}"/>
       		 <span class="input-group-btn">
	       		 <button type="button"  id="${id}Button" class="btn <c:if test="${fn:contains(cssClass, 'input-sm')}"> btn-sm </c:if><c:if test="${fn:contains(cssClass, 'input-lg')}"> btn-lg </c:if>  btn-primary ${disabled} ${hideBtn ? 'hide' : ''}"><i class="fa fa-search"></i>
	             </button> 
	               <button type="button" id="${id}DelButton" class="close" data-dismiss="alert" style="position: absolute; top: 5px; right: 53px; z-index: 999; display: block;">×</button>
       		 </span>
       		
    </div>
	 <label id="${id}Name-error" class="error" for="${id}Names" style="display:none"></label>
<script type="text/javascript">
$(document).ready(function(){
	$("#${id}Button, #${id}Names").click(function(){
		if ($("#${id}Button").hasClass("disabled")){
			return true;
		}
		var limite='${limite}';
		var url2='${url}';
		if(limite){
			var re=/\?/;
			if(!re.test(url2)){
				url2+='?';
			}else url2+='&';
			var limiteArr=limite.split(';');
			for(var j=0;j<limiteArr.length;j++){
				limiteId=limiteArr[j].substring(0,limiteArr[j].indexOf(':'));
				if($('#'+limiteId).val()==null||$('#'+limiteId).val()==''){
					var info=$('#'+limiteId).parents(".form-group").eq(0).children("label").eq(0).text();
					info=info.replace(/[\*：:]/g,'');
					top.layer.msg("请输入"+info, {icon:0});
					return true;
				}
				limiteFiled=limiteArr[j].substring(limiteArr[j].indexOf(':')+1);
				url2+=limiteFiled+"="+$('#'+limiteId).val();
			}
			console.log(url2);
		}
		top.layer.open({
		    type: 2,  
		    area: ['800px', '500px'],
		    title:"${title}",
		    auto:true,
		    name:'friend',
		    content: "${ctx}/common/gridselect?url="+encodeURIComponent(encodeURIComponent(url2))+"&fieldLabels="+encodeURIComponent("${fieldLabels}")+"&fieldKeys="+encodeURIComponent("${fieldKeys}")+"&searchLabels="+encodeURIComponent("${searchLabels}")+"&searchKeys="+encodeURIComponent("${searchKeys}")+"&isMultiSelected=${isMultiSelected? true:false}",
		    btn: ['确定', '关闭'],
		    yes: function(index, layero){
		    	 var iframeWin = layero.find('iframe')[0].contentWindow; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		    	 var items = iframeWin.getSelections();
		    	 if(items == ""){
			    	jp.warning("必须选择一条数据!");
			    	return;
		    	 }
                var extra = '${extraField}';
                var fieldArr;
                if (extra) {
                    fieldArr = extra.split(';');
                }
		    	 var ids = [];
		    	 var names = [];
		    	 var targetValue = [];
		    	 for(var i=0; i<items.length; i++){
		    		 var item = items[i];
		    		 console.log(item)
		    		 ids.push(item.id);
		    		 try{
		    			 names.push(item${fn:substring(labelName, fns:lastIndexOf(labelName, '.'), fn:length(labelName))})
					 }catch (e) {
		    		     console.log("没有该字段")
                     }
                     targetValue.push(item['${targetField}'])
                     $("#${id}Id").val(ids.join(","));
			    	 $("#${id}Id").trigger("change");
					 $("#${targetId}").val(targetValue.join(","));
			    	 $("#${id}Names").val(names.join(","));
                     if (fieldArr) {
                         for(var j = 0;j<fieldArr.length;j++) {
                        	 try{
	                             $('#' + fieldArr[j].substring(0, fieldArr[j].indexOf(':'))).val(item[fieldArr[j].substring(fieldArr[j].indexOf(':')+1)])
	                             var tempArr=fieldArr[j].substring(fieldArr[j].indexOf(':')+1).split(".");
	                             var t,tempValue=item;
	                             if(tempValue[tempArr[0]]==null) continue;
	                             for(var k=0;k<tempArr.length;k++){
	                            	 console.log(tempArr[k]+"-");
	                            	 tempValue=tempValue[tempArr[k]];
	                        
	                             }
	                             console.log(tempValue);
	                             $('#' + fieldArr[j].substring(0, fieldArr[j].indexOf(':'))).val(tempValue);
                        	 }catch(e){
                        		 
                        	 }
                         }
                     }
		    	 }
                $("#${id}Names").trigger("change");
				 top.layer.close(index);//关闭对话框。
			  },
			  cancel: function(index){ 
		       }
		}); 
	})
	$("#${id}DelButton").click(function(){
		// 是否限制选择，如果限制，设置为disabled
		if ($("#${id}Button").hasClass("disabled")){
			return true;
		}
		// 清除	
		$("#${id}Id").val("");
		$("#${id}Names").val("");
		$("#${id}Names").focus();

	});
})
</script>
