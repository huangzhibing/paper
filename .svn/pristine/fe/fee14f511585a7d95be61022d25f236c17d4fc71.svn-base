<%@ page contentType="text/html;charset=UTF-8" %>
<script>
	    var $crawTaskTypeTreeTable=null;  
		$(document).ready(function() {
			$crawTaskTypeTreeTable=$('#crawTaskTypeTreeTable').treeTable({  
		    	   theme:'vsStyle',	           
					expandLevel : 2,
					column:0,
					checkbox: false,
		            url:'${ctx}/type/crawTaskType/getChildren?parentId=',  
		            callback:function(item) { 
		            	 var treeTableTpl= $("#crawTaskTypeTreeTableTpl").html();
		            	 item.dict = {};

		            	 var result = laytpl(treeTableTpl).render({
								  row: item
							});
		                return result;                   
		            },  
		            beforeClick: function($crawTaskTypeTreeTable, id) { 
		                //异步获取数据 这里模拟替换处理  
		                $crawTaskTypeTreeTable.refreshPoint(id);  
		            },  
		            beforeExpand : function($crawTaskTypeTreeTable, id) {   
		            },  
		            afterExpand : function($crawTaskTypeTreeTable, id) {  
		            },  
		            beforeClose : function($crawTaskTypeTreeTable, id) {    
		            	
		            }  
		        });
		        
		        $crawTaskTypeTreeTable.initParents('${parentIds}', "0");//在保存编辑时定位展开当前节点
		       
		});
		
		function del(con,id){  
			jp.confirm('确认要删除任务类型吗？', function(){
				jp.loading();
	       	  	$.get("${ctx}/type/crawTaskType/delete?id="+id, function(data){
	       	  		if(data.success){
	       	  			$crawTaskTypeTreeTable.del(id);
	       	  			jp.success(data.msg);
	       	  		}else{
	       	  			jp.error(data.msg);
	       	  		}
	       	  	})
	       	   
       		});
	
		} 
		
		function refreshNode(data) {//刷新节点
            var current_id = data.body.crawTaskType.id;
			var target = $crawTaskTypeTreeTable.get(current_id);
			var old_parent_id = target.attr("pid") == undefined?'1':target.attr("pid");
			var current_parent_id = data.body.crawTaskType.parentId;
			var current_parent_ids = data.body.crawTaskType.parentIds;
			if(old_parent_id == current_parent_id){
				if(current_parent_id == '0'){
					$crawTaskTypeTreeTable.refreshPoint(-1);
				}else{
					$crawTaskTypeTreeTable.refreshPoint(current_parent_id);
				}
			}else{
				$crawTaskTypeTreeTable.del(current_id);//刷新删除旧节点
				$crawTaskTypeTreeTable.initParents(current_parent_ids, "0");
			}
        }
		function refresh(){//刷新
			var index = jp.loading("正在加载，请稍等...");
			$crawTaskTypeTreeTable.refresh();
			jp.close(index);
		}
</script>
<script type="text/html" id="crawTaskTypeTreeTableTpl">
			<td>
			<c:choose>
			      <c:when test="${fns:hasPermission('type:crawTaskType:edit')}">
				    <a  href="#" onclick="jp.openSaveDialog('编辑任务类型', '${ctx}/type/crawTaskType/form?id={{d.row.id}}','800px', '500px')">
							{{d.row.typeName === undefined ? "": d.row.typeName}}
					</a>
			      </c:when>
			      <c:when test="${fns:hasPermission('type:crawTaskType:view')}">
				    <a  href="#" onclick="jp.openViewDialog('查看任务类型', '${ctx}/type/crawTaskType/form?id={{d.row.id}}','800px', '500px')">
							{{d.row.typeName === undefined ? "": d.row.typeName}}
					</a>
			      </c:when>
			      <c:otherwise>
							{{d.row.typeName === undefined ? "": d.row.typeName}}
			      </c:otherwise>
			</c:choose>
			</td>
			<td>
							{{d.row.remarks === undefined ? "": d.row.remarks}}
			</td>
			<td>
				<div class="btn-group">
			 		<button type="button" class="btn  btn-primary btn-xs dropdown-toggle" data-toggle="dropdown">
						<i class="fa fa-cog"></i>
						<span class="fa fa-chevron-down"></span>
					</button>
				  <ul class="dropdown-menu" role="menu">
					<shiro:hasPermission name="type:crawTaskType:view">
						<li><a href="#" onclick="jp.openViewDialog('查看任务类型', '${ctx}/type/crawTaskType/form?id={{d.row.id}}','800px', '500px')"><i class="fa fa-search-plus"></i> 查看</a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="type:crawTaskType:edit">
						<li><a href="#" onclick="jp.openSaveDialog('修改任务类型', '${ctx}/type/crawTaskType/form?id={{d.row.id}}','800px', '500px')"><i class="fa fa-edit"></i> 修改</a></li>
		   			</shiro:hasPermission>
		   			<shiro:hasPermission name="type:crawTaskType:del">
		   				<li><a  onclick="return del(this, '{{d.row.id}}')"><i class="fa fa-trash"></i> 删除</a></li>
					</shiro:hasPermission>
		   			<shiro:hasPermission name="type:crawTaskType:add">
						<li><a href="#" onclick="jp.openSaveDialog('添加下级任务类型', '${ctx}/type/crawTaskType/form?parent.id={{d.row.id}}','800px', '500px')"><i class="fa fa-plus"></i> 添加下级任务类型</a></li>
					</shiro:hasPermission>
				  </ul>
				</div>
			</td>
	</script>