<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>论文等级统计管理</title>
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/echarts.jsp"%>
	<script type="text/javascript">

		$(document).ready(function() {

            var oneMonthAgo=new Date();
            var nowDate=new Date();
            new Date(oneMonthAgo.setMonth((new Date().getMonth()-1)));
            var beginDate= formatDate(oneMonthAgo);
            var endDate=formatDate(nowDate);
            Graph(beginDate,endDate);

            $('#beginDate input').val(beginDate);
            $('#endDate input').val(endDate);

            $('#beginDate').datetimepicker({
                format: "YYYY-MM-DD HH:mm:ss"
            });
            $('#endDate').datetimepicker({
                format: "YYYY-MM-DD HH:mm:ss"
            });

            $('#search').click(function () {
                var beginDate = $('#beginDate input').val();
                var endDate = $('#endDate input').val();
                Graph(beginDate,endDate);
            });
        });
        function Graph(beginDate,endDate) {
            var barChart = echarts.init(document.getElementById('barGraph'));
            var pieChart = echarts.init(document.getElementById('pieGraph'));
            var lineChart = echarts.init(document.getElementById('lineGraph'));
            jp.get("${ctx}/papergrade/paperGrade/bar?beginDate="+beginDate+"&endDate="+endDate,function (barOption) {
                barChart.setOption(barOption);
            });
            jp.get("${ctx}/papergrade/paperGrade/pie?beginDate="+beginDate+"&endDate="+endDate,function (pieOption) {
                pieChart.setOption(pieOption);
            });
            jp.get("${ctx}/papergrade/paperGrade/line?beginDate="+beginDate+"&endDate="+endDate,function (lineOption) {
                lineChart.setOption(lineOption);
            });
        }
        function formatDate(time){
            var date = new Date(time);

            var year = date.getFullYear(),
                month = date.getMonth() + 1,  // 月份是从0开始的
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
				<h3 class="panel-title">论文等级统计</h3>
			</div>
			<div class="panel-body">
				<sys:message content="${message}"/>

				<!-- 搜索 -->
				<div class="accordion-group">

					<div class="accordion-inner">
						<form:form id="searchForm" modelAttribute="papergrade" class="form form-horizontal well clearfix">
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
						<div id="barGraph" style="width:100%;height:400px;margin:50px auto;" ></div>
					</div>
					<div class="tab-pane fade" id="bzt">
						<div id="pieGraph" style="width:1000px;height:400px;margin:50px auto;" ></div>
					</div>
					<div class="tab-pane fade" id="xxt">
						<div id="lineGraph" style="width:1000px;height:400px;margin:50px auto;" ></div>
					</div>
				</div>


			</div>
		</div>
	</div>
</body>
</html>