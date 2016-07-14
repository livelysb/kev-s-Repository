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
<script src="resources/js/set.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />

<style type="text/css">
#div1 {border:black 1px solid; height:400px; width:50%; float:left;}
#div2 {border:red 1px solid; height:400px; width:50%; float:right;}
#div3 {border:blue 1px solid; height:400px; width:100%; clear: both;}
#history-content #page-align {text-align: center;}

#history-content .nav-tabs > li {
    float:none;
    display:inline-block;
    *display:inline; /* ie7 fix */
     zoom:1; /* hasLayout ie7 trigger */
}

#history-content .nav-tabs {
    text-align:center;
}

	
</style>

<script type="text/javascript">
	$(function(){
		
		/*히스토리 페이지네이션*/
		var historyPage = function(page){
			$("#history-content #page-selection").bootpag({
	            total: page, maxVisible: 10
	        });
		}
		
		/*히스토리 총페이지*/
		var historyStockListCount = function(page){
			$.ajax({
				url:"getHistoryCount",
				type:"post",
				dataType:"json",
				data:"targetPlayer=이석범짱",
				success:function(page){
					if(page%10==0){
						historyPage(page/10)
					}else{
						historyPage(page/10+1)	
					}
				},
				error:function(err){
					console.log("Exception : historyStockList")
				}
			})
		}
		
		/*히스토리 내역*/
		var historyStockList = function(page,orderBy,asc){

			$.ajax({
				url:"getStockDealHistory", 
				type:"post",
				dataType:"json",
				data:{"targetPlayer":"이석범짱","orderBy":orderBy,"asc":asc,"page":page},
				success:function(data){
					console.log(data);
					var str="";
					$.each(data,function(index,item){
						if(item.sdhBuySell=="b"){
							str+="<tr><td>구매</td>";
						}else{
							str+="<tr><td>판매</td>";
						}
						
						str+="<td>"+item.masterDTO.isuKorAbbrv+"</td>";
						str+="<td>"+item.masterDTO.kind+"</td>";
						str+="<td>"+item.sdhDealTime+"</td>";
						str+="<td>"+item.sdhQuantity+"</td>";
						str+="<td>"+item.masterDTO.priceDTO.trdPrc+"</td>";
						str+="<td>"+item.sdhDealPrice+"</td></tr>";
					});
					$("#history-stock-list").empty();
					$("#history-stock-list").html(str);
				},
				error:function(err){
					console.log("Exception : historyStockList")
				}
			})
		}
		
		/*페이지클릭 이벤트*/
		$('#history-content #page-selection').on("page", function(event, num){
			historyStockList(num);
        });
		
		
		/*Best 파이차트*/
		var historyBest = function(){
			$.ajax({
				url:"getBest",
				type:"post",
				dataType:"json",
				data:"targetPlayer=이석범짱",
				success:function(data){
					pieChartJson = new Array();
					$.each(data,function(index,item){
						var pieChartObj = new Object();
						pieChartObj.name=item.isuKorAbbrv;
						pieChartObj.y=(item.earningMoney);
						pieChartJson.push(pieChartObj);
					}) 
					pieChartdraw("#history-best-piechart",pieChartJson,"Profit");
				},
				error:function(err){
					console.log("Exception : historyBest")
				}
			})
		}
		/*Worst 파이차트*/
		var historyWorst = function(){
			$.ajax({
				url:"getWorst",
				type:"post",
				dataType:"json",
				data:"targetPlayer=이석범짱", //해당유저에맞게 수정요망
				success:function(data){
					pieChartJson = new Array();
					$.each(data,function(index,item){
						var pieChartObj = new Object();
						pieChartObj.name=item.isuKorAbbrv;
						pieChartObj.y=(-item.earningMoney);
						pieChartJson.push(pieChartObj);
					}) 
					pieChartdraw("#history-worst-piechart",pieChartJson,"Lost");
				},
				error:function(err){
					console.log("Exception : historyWorst")
				}
			})
		}
		
		
		/*수익률차트*/
		var earningChart = function(){
			$.ajax({
				url:"getEarningRateList",
				type:"post",
				dataType:"json",
				data:"targetPlayer=이석범짱", //해당유저에맞게 수정요망
				success:function(data){
					pieChartJson = new Array();
					$.each(data,function(index,item){
						var pieChartObj = new Object();
						pieChartObj.name=item.pehDate2 ;
						pieChartObj.x=item.pehDate2 ;
						console.log(pieChartObj.name);
						pieChartObj.y=item.pehPe;
						pieChartJson.push(pieChartObj);
					}) 	
					
					lineChartdraw(pieChartJson);
				},
				error:function(){
					console.log("Exception : earningChart")
				}
			})
		}
		
		
		 /*파이차트 그리기*/
		var pieChartdraw = function(historySelector,pieChartJson,chartTitle) {
			$(historySelector).highcharts({
				
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false,
		            type: 'pie'
		        },
		        title: {
		            text: chartTitle
		        },
		        tooltip: {
		             /* pointFormat: '{series.name}: <b>{this.y}원</b>' */
		        	 formatter: function() {
		                return 'The value for <b>' + this.series.name + '</b> is <b>' + this.y + '</b>';
		            } 
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>:  {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		        series: [{
				            name: "profit",
				            colorByPoint: true,
				            data: pieChartJson
	        	}] 
	  	  });
		}
		
		 /*라인차트 그리기*/
		var lineChartdraw = function(pieChartJson){
			$('#history-line-chart').highcharts({
	            chart: {
	                zoomType: 'x'
	            },
	            title: {
	                text: 'Today Earning Rate'
	            },
	            subtitle: {
	           /*      text: document.ontouchstart === undefined ?
	                        'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in' */
	            },
	            xAxis: {
	                type: 'datetime'
	            },
	            yAxis: {
	                title: {
	                    text: 'Earning Rate'
	                }
	            },
	            legend: {
	                enabled: false
	            },
	            plotOptions: {
	                area: {
	                    fillColor: {
	                        linearGradient: {
	                            x1: 0,
	                            y1: 0,
	                            x2: 0,
	                            y2: 1
	                        },
	                        stops: [
	                            [0, Highcharts.getOptions().colors[0]],
	                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
	                        ]
	                    },
	                    marker: {
	                        radius: 2
	                    },
	                    lineWidth: 1,
	                    states: {
	                        hover: {
	                            lineWidth: 1
	                        }
	                    },
	                    threshold: null
	                }
	            },

	            series: [{
	                type: 'area',
	                name: 'USD to EUR',
	                data: pieChartJson
	            }]
	        });
		}

		earningChart();
		historyWorst();
		historyBest();
		historyStockListCount()
		historyStockList()
		
	}) 
</script>

</head>
<body>
	<div id="history-window">
		<div id="history-header">Stock History</div>
		<div id="history-content">
			<div id="div1">
				<div id="history-line-chart"></div>
			</div>
			<div id="div2">
				<ul class="nav nav-tabs">
				  <li class="active"><a data-toggle="tab" href="#history-best-div">Best</a></li>
				  <li><a data-toggle="tab" href="#history-worst-div">Worst</a></li>
				</ul>
				<div class="tab-content">
				  <div id="history-best-div" class="tab-pane fade in active">	
				  	  <div id="history-best-piechart"></div>
				  </div>
				  <div id="history-worst-div" class="tab-pane fade in active">	
		  	  		  <div id="history-worst-piechart"></div>
				  </div>
			  	</div>
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
					<tbody id="history-stock-list">

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