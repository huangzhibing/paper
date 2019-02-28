<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<head>
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/echarts.jsp"%>
</head>
<body>
	<div id="test" style="width:800px;height:500px;margin:50px auto;" ></div>
	<script type="text/javascript">
		$(function(){
			var sum=[];
			$.ajax({
				url:'${ctx}/capitaloccupation/capitaloccupation/data',
				data:{
				},
				success:function(res){
					for(var i=0;i<res.rows.length;i++){
						sum[i]={
							name:res.rows[i].ware.wareName,
						    value:res.rows[i].nowSum
						}
					}
					iniG();
				}
			});
			function iniG(){
				var myChart = echarts.init(document.getElementById('test')); 
				option = {
					title : {
						text: '仓库物料统计',
						x:'center'
					},
					series : [
						{
							name: '数量',
							type: 'pie',
							center: ['50%', '50%'],
							data:sum,
							 itemStyle: {
			                    emphasis: {
			                        shadowBlur: 10,
			                        shadowOffsetX: 0,
			                        shadowColor: 'rgba(0, 0, 0, 0.5)'
			                    },
			                    normal:{ 
			                        label:{ 
			                            show: true, 
			                            formatter: '{b} : {c} ({d}%)' 
			                        }, 
			                        labelLine :{show:true} 
			                    } 
			                }
						}
					]
				};
				myChart.setOption(option); 
			}
		});
	</script>
</body>