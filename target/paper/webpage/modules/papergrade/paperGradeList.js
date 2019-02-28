<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#paperGradeTable').bootstrapTable({
		 
		  //请求方法
               method: 'post',
               //类型json
               dataType: "json",
               contentType: "application/x-www-form-urlencoded",
               //显示检索按钮
	           showSearch: true,
               //显示刷新按钮
               showRefresh: true,
               //显示切换手机试图按钮
               showToggle: true,
               //显示 内容列下拉框
    	       showColumns: true,
    	       //显示到处按钮
    	       showExport: true,
    	       //显示切换分页按钮
    	       showPaginationSwitch: true,
    	       //最低显示2行
    	       minimumCountColumns: 2,
               //是否显示行间隔色
               striped: true,
               //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）     
               cache: false,    
               //是否显示分页（*）  
               pagination: true,   
                //排序方式 
               sortOrder: "asc",  
               //初始化加载第一页，默认第一页
               pageNumber:1,   
               //每页的记录行数（*）   
               pageSize: 10,  
               //可供选择的每页的行数（*）    
               pageList: [10, 25, 50, 100],
               //这个接口需要处理bootstrap table传递的固定参数,并返回特定格式的json数据  
               url: "${ctx}/papergrade/paperGrade/data",
               //默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
               //queryParamsType:'',   
               ////查询参数,每次调用是会带上这个参数，可自定义                         
               queryParams : function(params) {
               	var searchParam = $("#searchForm").serializeJSON();
               	searchParam.pageNo = params.limit === undefined? "1" :params.offset/params.limit+1;
               	searchParam.pageSize = params.limit === undefined? -1 : params.limit;
               	searchParam.orderBy = params.sort === undefined? "" : params.sort+ " "+  params.order;
                   return searchParam;
               },
               //分页方式：client客户端分页，server服务端分页（*）
               sidePagination: "server",
               contextMenuTrigger:"right",//pc端 按右键弹出菜单
               contextMenuTriggerMobile:"press",//手机端 弹出菜单，click：单击， press：长按。
               contextMenu: '#context-menu',
               onContextMenuItem: function(row, $el){
                   if($el.data("item") == "edit"){
                   		edit(row.id);
                   }else if($el.data("item") == "view"){
                       view(row.id);
                   } else if($el.data("item") == "delete"){
                        jp.confirm('确认要删除该论文等级统计记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/papergrade/paperGrade/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#paperGradeTable').bootstrapTable('refresh');
                   	  			jp.success(data.msg);
                   	  		}else{
                   	  			jp.error(data.msg);
                   	  		}
                   	  	})
                   	   
                   	});
                      
                   } 
               },
              
               onClickRow: function(row, $el){
               },
               	onShowSearch: function () {
			$("#search-collapse").slideToggle();
		},
               columns: [{
		        checkbox: true
		       
		    }
			,{
		        field: 'ddh',
		        title: '订单号',
		        sortable: true,
		        sortName: 'ddh'
		        ,formatter:function(value, row , index){
		        	value = jp.unescapeHTML(value);
				   <c:choose>
					   <c:when test="${fns:hasPermission('papergrade:paperGrade:edit')}">
					      return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
				      </c:when>
					  <c:when test="${fns:hasPermission('papergrade:paperGrade:view')}">
					      return "<a href='javascript:view(\""+row.id+"\")'>"+value+"</a>";
				      </c:when>
					  <c:otherwise>
					      return value;
				      </c:otherwise>
				   </c:choose>
		         }
		       
		    }
			,{
		        field: 'xdrq',
		        title: '下单时间',
		        sortable: true,
		        sortName: 'xdrq'
		       
		    }
			,{
		        field: 'yysj',
		        title: '预约时间',
		        sortable: true,
		        sortName: 'yysj'
		       
		    }
			,{
		        field: 'ddlxdm',
		        title: '订单类型',
		        sortable: true,
		        sortName: 'ddlxdm',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('T_OrderType_C'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'zj',
		        title: '总价',
		        sortable: true,
		        sortName: 'zj'
		       
		    }
			,{
		        field: 'fksj',
		        title: '付款时间',
		        sortable: true,
		        sortName: 'fksj'
		       
		    }
			,{
		        field: 'khxm',
		        title: '客户姓名',
		        sortable: true,
		        sortName: 'khxm'
		       
		    }
			,{
		        field: 'yddh',
		        title: '移动电话',
		        sortable: true,
		        sortName: 'yddh'
		       
		    }
			,{
		        field: 'sqtksj',
		        title: '申请退款时间',
		        sortable: true,
		        sortName: 'sqtksj'
		       
		    }
			,{
		        field: 'tkly',
		        title: '退款理由',
		        sortable: true,
		        sortName: 'tkly'
		       
		    }
			,{
		        field: 'tkshjg',
		        title: '退款审核结果',
		        sortable: true,
		        sortName: 'tkshjg',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('T_TKSHJG'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'yhpj',
		        title: '用户评价',
		        sortable: true,
		        sortName: 'yhpj',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('T_YHPJ'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'pjxq',
		        title: '评价详情',
		        sortable: true,
		        sortName: 'pjxq'
		       
		    }
			,{
		        field: 'ddztdm',
		        title: '订单状态',
		        sortable: true,
		        sortName: 'ddztdm',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('T_OrderStatus_C'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'qydm.code',
		        title: '区域',
		        sortable: true,
		        sortName: 'qydm.code'
		       
		    }
			,{
		        field: 'pszj',
		        title: '评审专家',
		        sortable: true,
		        sortName: 'pszj'
		       
		    }
			,{
		        field: 'note',
		        title: '备注',
		        sortable: true,
		        sortName: 'note'
		       
		    }
			,{
		        field: 'psdj',
		        title: '评审等级',
		        sortable: true,
		        sortName: 'psdj',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('T_reviewRank'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'psyj',
		        title: '评审意见',
		        sortable: true,
		        sortName: 'psyj'
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#paperGradeTable').bootstrapTable("toggleView");
		}
	  
	  $('#paperGradeTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#paperGradeTable').bootstrapTable('getSelections').length);
            $('#view,#edit').prop('disabled', $('#paperGradeTable').bootstrapTable('getSelections').length!=1);
        });
		  
		$("#btnImport").click(function(){
			jp.open({
			    type: 2,
                area: [500, 200],
                auto: true,
			    title:"导入数据",
			    content: "${ctx}/tag/importExcel" ,
			    btn: ['下载模板','确定', '关闭'],
				    btn1: function(index, layero){
					 jp.downloadFile('${ctx}/papergrade/paperGrade/import/template');
				  },
			    btn2: function(index, layero){
				        var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						iframeWin.contentWindow.importExcel('${ctx}/papergrade/paperGrade/import', function (data) {
							if(data.success){
								jp.success(data.msg);
								refresh();
							}else{
								jp.error(data.msg);
							}
						   jp.close(index);
						});//调用保存事件
						return false;
				  },
				 
				  btn3: function(index){ 
					  jp.close(index);
	    	       }
			}); 
		});
		    
		
	  $("#export").click(function(){//导出Excel文件
			jp.downloadFile('${ctx}/papergrade/paperGrade/export');
	  });

		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#paperGradeTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#paperGradeTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#paperGradeTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该论文等级统计记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/papergrade/paperGrade/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#paperGradeTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  function refresh(){
  	$('#paperGradeTable').bootstrapTable('refresh');
  }
  function add(){
		jp.go("${ctx}/papergrade/paperGrade/form/add");
	}

  function edit(id){
	  if(id == undefined){
		  id = getIdSelections();
	  }
	  jp.go("${ctx}/papergrade/paperGrade/form/edit?id=" + id);
  }

  function view(id) {
      if(id == undefined){
          id = getIdSelections();
      }
      jp.go("${ctx}/papergrade/paperGrade/form/view?id=" + id);
  }
  
</script>