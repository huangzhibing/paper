<%@ page contentType="text/html;charset=UTF-8" %>
<script>
$(document).ready(function() {
	$('#expertTable').bootstrapTable({
		 
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
               url: "${ctx}/expertmanagement/expert/data",
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
                        jp.confirm('确认要删除该专家信息表记录吗？', function(){
                       	jp.loading();
                       	jp.get("${ctx}/expertmanagement/expert/delete?id="+row.id, function(data){
                   	  		if(data.success){
                   	  			$('#expertTable').bootstrapTable('refresh');
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
		        field: 'yhzh',
		        title: '用户账号',
		        sortable: true,
		        sortName: 'yhzh'
		        ,formatter:function(value, row , index){
		        	value = jp.unescapeHTML(value);
				   <c:choose>
					   <c:when test="${fns:hasPermission('expertmanagement:expert:edit')}">
					      return "<a href='javascript:edit(\""+row.id+"\")'>"+value+"</a>";
				      </c:when>
					  <c:when test="${fns:hasPermission('expertmanagement:expert:view')}">
					      return "<a href='javascript:view(\""+row.id+"\")'>"+value+"</a>";
				      </c:when>
					  <c:otherwise>
					      return value;
				      </c:otherwise>
				   </c:choose>
		         }

		    }
			,{
		        field: 'zjxm',
		        title: '专家姓名',
		        sortable: true,
		        sortName: 'zjxm'
		       
		    }
		    ,{
               	field: 'ye',
				title: '余额',
				sortable: true,
            }
			,{
		        field: 'zjxldm',
		        title: '专家类型',
		        sortable: true,
		        sortName: 'zjxldm',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('T_ExpertType_C'))}, value, "-");
		        }
		       
		    }
                   ,{
                       field: 'specialityManage.zymc',
                       title: '专业名称',
                       sortable: true,
                       sortName: ''

                   }
			,{
		        field: 'zyjszwdm.rankName',
		        title: '专业知识职务名称',
		        sortable: true,
		        sortName: ''
		       
		    }
			,{
		        field: 'dsjbdm',
		        title: '导师级别',
		        sortable: true,
		        sortName: 'dsjbdm',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('T_TutorLevel_C'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'xbdm',
		        title: '性别',
		        sortable: true,
		        sortName: 'xbdm',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('T_SexType_C'))}, value, "-");
		        }
		       
		    }
			,{
		        field: 'qydm.name',
		        title: '区域名称',
		        sortable: true,
		        sortName: 'qydm.name'
		       
		    }
			,{
		        field: 'dzyx',
		        title: '电子邮箱',
		        sortable: true,
		        sortName: 'dzyx'
		       
		    }
			,{
		        field: 'dwdm.dwmc',
		        title: '单位名称',
		        sortable: true,
		        sortName: 'dwdm.dwmc'
		       
		    }
			,{
		        field: 'csny',
		        title: '出生年月',
		        sortable: true,
		        sortName: 'csny'
		       
		    }
			,{
		        field: 'ejxy',
		        title: '二级学院',
		        sortable: true,
		        sortName: 'ejxy'
		       
		    }
			,{
		        field: 'zjxw',
		        title: '最高学位',
		        sortable: true,
		        sortName: 'zjxw'
		       
		    }
			,{
		        field: 'byyxdm.gxmc',
		        title: '毕业院校名称',
		        sortable: true,
		        sortName: 'byyxdm.gxdm'
		       
		    }
			,{
		        field: 'wyyz',
		        title: '外语语种',
		        sortable: true,
		        sortName: 'wyyz'
		       
		    }
			,{
		        field: 'wysxcd',
		        title: '外语熟悉程度',
		        sortable: true,
		        sortName: 'wysxcd'
		       
		    }
			,{
		        field: 'yddh',
		        title: '移动电话',
		        sortable: true,
		        sortName: 'yddh'
		       
		    }
			,{
		        field: 'lxdh',
		        title: '联系电话',
		        sortable: true,
		        sortName: 'lxdh'
		       
		    }
			,{
		        field: 'lxdz',
		        title: '联系地址',
		        sortable: true,
		        sortName: 'lxdz'
		       
		    }
			,{
		        field: 'yb',
		        title: '邮编',
		        sortable: true,
		        sortName: 'yb'
		       
		    }
			,{
		        field: 'jg',
		        title: '籍贯',
		        sortable: true,
		        sortName: 'jg'
		       
		    }
			,{
		        field: 'zjztdm',
		        title: '专家状态',
		        sortable: true,
		        sortName: 'zjztdm',
		        formatter:function(value, row , index){
		        	return jp.getDictLabel(${fns:toJson(fns:getDictList('T_ExpertStatus_C'))}, value, "-");
		        }
		       
		    }
		     ]
		
		});
		
		  
	  if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//如果是移动端

		 
		  $('#expertTable').bootstrapTable("toggleView");
		}
	  
	  $('#expertTable').on('check.bs.table uncheck.bs.table load-success.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $('#remove').prop('disabled', ! $('#expertTable').bootstrapTable('getSelections').length);
            $('#view,#edit').prop('disabled', $('#expertTable').bootstrapTable('getSelections').length!=1);
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
					 jp.downloadFile('${ctx}/expertmanagement/expert/import/template');
				  },
			    btn2: function(index, layero){
				        var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
						iframeWin.contentWindow.importExcel('${ctx}/expertmanagement/expert/import', function (data) {
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
			jp.downloadFile('${ctx}/expertmanagement/expert/export');
	  });

		    
	  $("#search").click("click", function() {// 绑定查询按扭
		  $('#expertTable').bootstrapTable('refresh');
		});
	 
	 $("#reset").click("click", function() {// 绑定查询按扭
		  $("#searchForm  input").val("");
		  $("#searchForm  select").val("");
		  $("#searchForm  .select-item").html("");
		  $('#expertTable').bootstrapTable('refresh');
		});
		
		
	});
		
  function getIdSelections() {
        return $.map($("#expertTable").bootstrapTable('getSelections'), function (row) {
            return row.id
        });
    }
  
  function deleteAll(){

		jp.confirm('确认要删除该专家信息表记录吗？', function(){
			jp.loading();  	
			jp.get("${ctx}/expertmanagement/expert/deleteAll?ids=" + getIdSelections(), function(data){
         	  		if(data.success){
         	  			$('#expertTable').bootstrapTable('refresh');
         	  			jp.success(data.msg);
         	  		}else{
         	  			jp.error(data.msg);
         	  		}
         	  	})
          	   
		})
  }
  function refresh(){
  	$('#expertTable').bootstrapTable('refresh');
  }
  function add(){
		jp.go("${ctx}/expertmanagement/expert/form/add");
	}

  function edit(id){
	  if(id == undefined){
		  id = getIdSelections();
	  }
	  jp.go("${ctx}/expertmanagement/expert/form/edit?id=" + id);
  }

  function view(id) {
      if(id == undefined){
          id = getIdSelections();
      }
      jp.go("${ctx}/expertmanagement/expert/form/view?id=" + id);
  }
  
</script>