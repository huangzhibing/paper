<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>专家增长趋势统计</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@ include file="/webpage/include/echarts.jsp"%>
	<script type ="text/javascript" src="js/jquery.datetimepicker.js"></script>
	<script type="text/javascript">
        $(document).ready(function() {

            var oneMonthAgo=new Date();
            var nowDate=new Date();
            new Date(oneMonthAgo.setMonth((new Date().getMonth()-1)));
            var begintime= formatDate(oneMonthAgo);
            var endtime=formatDate(nowDate);
            Graph(begintime,endtime,"周");

            $('#beginDate input').val(begintime);
            $('#endDate input').val(endtime);

            $('#beginDate').datetimepicker({
                format: "YYYY-MM-DD HH:mm:ss"
            });
            $('#endDate').datetimepicker({
                format: "YYYY-MM-DD HH:mm:ss"
            });

            $('#search').click(function () {
                //window.timer=jp.loading();
                var beginDate = $('#beginDate input').val();
                var endDate = $('#endDate input').val();
                var zq = $('#zq').val();
                Graph(beginDate,endDate,zq);
                document.forms[0].target="rfFrame";
            });
		});
        function Graph(beginDate,endDate,zq) {
            var barChart = echarts.init(document.getElementById('barGraph'));
            var pieChart = echarts.init(document.getElementById('pieGraph'));
            var lineChart = echarts.init(document.getElementById('lineGraph'));
            jp.get("${ctx}/experttrend/expertTrend/barGraph?beginDate="+beginDate+"&endDate="+endDate+"&zq="+zq,function (barOption) {
				//折线图
                var lineOption = {};
                lineOption = $.extend(true,{},barOption);
				lineOption.series[0].type = "line";
				lineOption.series[1].type = "line";
                //饼状图
                var idx = 1;
                var dataSize = barOption.xAxis[0].data.length;
                pieOption = {
                    timeline : {
                        //x轴的数值为离散的value
                        axisType: 'category',
                        show: true,
                        autoPlay: true,
                        playInterval: 1500,
                        data : barOption.xAxis[0].data,
                    },
                    options : [
                        {
                            title : {
                                text: '企业专家和高校专家占比',
                            },
                            tooltip : {
                                trigger: 'item',
                                formatter: "{a} <br/>{b} : {c} ({d}%)"
                            },
                            legend: {
                                data:['企业专家','高校专家']
                            },
                            toolbox: {
                                show : true,
                                feature : {
                                    mark : {show: true},
                                    dataView : {show: true, readOnly: false},
                                    magicType : {
                                        show: true,
                                        type: ['pie', 'funnel'],
                                        option: {
                                            funnel: {
                                                x: '25%',
                                                width: '50%',
                                                funnelAlign: 'left',
                                                max: 1700
                                            }
                                        }
                                    },
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                            series : [
                                {
                                    name:'企业专家',
                                    type:'pie',
                                    center: ['50%', '45%'],
                                    radius: '50%',
                                    data:[
                                        {},
										{}
									]
                                }
                            ]
                        }
                    ]
                };
				for(var i=0;i<dataSize;i++) {
                    if(i != 0){
                        pieOption.options[i] = {
                            series:[
                                {
                                    name: '高校专家',
                                    type: 'pie',
                                    center: ['50%', '45%'],
                                    radius: '50%',
                                    data: [
                                        {},
                                        {}
                                    ]
                                }
                            ]
                        }
					}
					pieOption.options[i].series[0].data[0].value = barOption.series[0].data[i];
                    pieOption.options[i].series[0].data[1].value = 10;
                    pieOption.options[i].series[0].data[0].name = '企业专家';
                    pieOption.options[i].series[0].data[1].name = '高校专家';
                }
                barChart.setOption(barOption);
				lineChart.setOption(lineOption);
                pieChart.setOption(pieOption);
            });
        }
        function formatDate(time){
            var date = new Date(time);

            var year = date.getFullYear(),
                month = date.getMonth() + 1,//月份是从0开始的
                day = date.getDate(),
                hour = date.getHours(),
                min = date.getMinutes(),
                sec = date.getSeconds();
            var newTime = year + '-' +
                month + '-' +
                day + ' ' +
                hour + ':' +
                min + ':' +
                sec;
            return newTime;
        }
	</script>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">专家增长趋势统计</h3>
	</div>
	<div class="panel-body">
		<sys:message content="${message}"/>
	
	<!-- 搜索 -->
	<div class="accordion-group">

		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="expertTrend" target="rfFrame" class="form form-horizontal well clearfix">
				<iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>
				<div class="col-xs-12 col-sm-6 col-md-9">
					<div class="form-group">
						<label class="label-item single-overflow pull-left" title="日期：">&nbsp;日期：</label>
						<div class="col-xs-12">
							<div class="col-xs-12 col-sm-5">
								<div class='input-group date' id='beginDate' style="left: -10px;" >
									<input type='text'  name="beginDate" class="form-control"  />
									<span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
								</div>
							</div>
							<div class="col-xs-12 col-sm-1">
								~
							</div>
							<div class="col-xs-12 col-sm-5">
								<div class='input-group date' id='endDate' style="left: -10px;" >
									<input type='text'  name="endDate" class="form-control" />
									<span class="input-group-addon">
					                       <span class="glyphicon glyphicon-calendar"></span>
					                   </span>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-1">
					<label class="label-item single-overflow pull-left" title="周期：">周期：</label>
					<select id="zq" name="zq"class="form-control required">
						<option value="周">周</option>
						<option value="年">年</option>
						<option value="月">月</option>
						<option value="日">日</option>
					</select>
				</div>
		 <div class="col-xs-12 col-sm-6 col-md-1">
			<div style="margin-top:26px">
			  <button  id="search"  class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i class="fa fa-search"></i> 统计</button>
			 </div>
	    </div>	
	</form:form>
	</div>

	</div>
	
	<!-- tab -->
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="#zzt" data-toggle="tab">柱状图</a></li>
			<li role="presentation"><a href="#bzt" data-toggle="tab">饼状图</a></li>
			<li role="presentation"><a href="#xxt" data-toggle="tab">线形图</a></li>
		</ul>

	<!-- 图表 -->
		<div id="tabContent" class="tab-content">
			<div class="tab-pane fade in active" id="zzt">
				<div id="barGraph" style="width:1600px;height:600px;margin:50px auto;" ></div>
			</div>
			<div class="tab-pane fade" id="bzt">
				<div id="pieGraph" style="width:1600px;height:600px;margin:50px auto;" ></div>
			</div>
			<div class="tab-pane fade" id="xxt">
				<div id="lineGraph" style="width:1600px;height:600px;margin:50px auto;" ></div>
			</div>
		</div>


	</div>
	</div>
	</div>
</body>
</html>