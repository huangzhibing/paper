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
			alert("sss");
			var names=[],sum=[];
			$.ajax({
				url:'${ctx}/capitaloccupation/capitaloccupation/data',
				data:{
				},
				success:function(res){
					for(var i=0;i<res.rows.length;i++){
						names[i]=res.rows[i].ware.wareName;
						sum[i]=res.rows[i].nowSum;
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
					 yAxis :{},
					xAxis: {
						data:names
					},
					series : [
						{
							name: '数量',
							type: 'bar',
							center: ['50%', '50%'],
							data: sum,
							label: {
				                normal: {
				                    show: true,
				                    position: 'top'
				                }
				            },
						}
					],
				};
				myChart.setOption(option);
			}
		});
	</script>
</body>