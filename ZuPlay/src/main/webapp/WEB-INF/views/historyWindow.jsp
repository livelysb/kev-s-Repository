<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/jquery.bootpag.min.js"></script>
<script type="text/javascript" src="resources/js/highcharts.js"></script>

<link href="resources/css/bootstrap.min.css" rel="stylesheet" />

<style type="text/css">
#div1 {border:black 1px solid; height:220px; width:50%; float:left;}
#div2 {border:red 1px solid; height:220px; width:50%; float:right;}
#div3 {border:blue 1px solid; height:220px; width:100%; clear: both;}
#page-align {text-align: center;}
	
</style>

<script type="text/javascript">
	$(function(){
		$("#history-content #page-selection").bootpag({
            total: 10, maxVisible: 10
        });
		
		var pieChartData="";
		
		$.ajax({
			url:"getBest",
			type:"post",
			dataType:"json",
			data:"targetPlayer=이석범짱",
			success:function(data){
				console.log(data);
			},
			error:function(err){
				console.log("이석범바보")
			}
		})
		
		$('#history-piechart').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	            type: 'pie'
	        },
	        title: {
	            //text: 'Browser market shares January, 2015 to May, 2015'
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {
	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                    }
	                }
	            }
	        },
	        series: [{
	            name: 'Earning rate',
	            colorByPoint: true,
	            data: [{
	                name: 'Microsoft Internet Explorer',
	                y: 56.33
	            }, {
	                name: 'Chrome',
	                y: 24.03,
	                sliced: true,
	                selected: true
	            }, {
	                name: 'Firefox',
	                y: 10.38
	            }, {
	                name: 'Safari',
	                y: 4.77
	            }, {
	                name: 'Opera',
	                y: 0.91
	            }, {
	                name: 'Proprietary or Undetectable',
	                y: 0.2
	            }]
	        }]
	    });
        
	}) 
</script>

</head>
<body>
	<div id="history-window">
		<div id="history-header">Stock History</div>
		<div id="history-content">
			<div id="div1">chart</div>
			<div id="div2">
				<div id="history-piechart"></div>
			</div>
			<div id="div3">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>구분</th>
							<th>종목</th>
							<th>분야</th>
							<th>체결시각</th>
							<th>수량</th>
							<th>체결가</th>
							<th>거래가</th>
						</tr>
					</thead>
					<tbody id="">

					</tbody>
				</table>

				<div id="page-align">
		            <div id="page-selection"></div>
		        </div>
			</div>
			
		</div>
	</div>
</body>
</html>