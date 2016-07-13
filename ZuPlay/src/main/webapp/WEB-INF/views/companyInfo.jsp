<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

  <div id="company-${masterDTO.isuCd}" class="company-window">
    <div class="company-header">기업정보 - ${masterDTO.isuCd}</div>
	<input type="hidden" value="${masterDTO.isuCd}" class="company-isuCd">
	<input type="hidden" value="${plQuantity}" class="company-qty">
    <div class="company-content">
      <div class="row-fluid">
        <div class="col-xs-12 company-title">
          <span class="company-title-name">${masterDTO.isuKorAbbrv}</span>
          <span class="company-title-stock">${masterDTO.priceDTO.trdPrc}</span>
        </div>
      </div>


      <div class="row-fluid">
         <div class="col-xs-6 company-chart-warpper">
         <div class="company-chart">
		<ul class="nav nav-tabs">
		  <li class="active"><a data-toggle="tab" href="#company-chart-today">오늘</a></li>
		  <li><a data-toggle="tab" href="#company-chart-week">1주</a></li>
		  <li><a data-toggle="tab" href="#company-chart-month">한달</a></li>
		</ul>
		
		<div class="tab-content">
		  <div id="company-chart-today" class="tab-pane fade in active">
		   <c:forEach items="${masterDTO.rtpList}" var="rtp" varStatus="stu">
		   		${rtp.rpTrdTm} : ${rtp.rpTrdPrc}
		   </c:forEach>
		</div>
	  	
		  <div id="company-chart-week" class="tab-pane fade">
		   <c:forEach items="${masterDTO.dpList}" var="dp" varStatus="stu">
		   		${dp.dpDate} : ${dp.dpClsprc}
		   </c:forEach>
		  </div>
		  
		  <div id="company-chart-month" class="tab-pane fade">
		   <c:forEach items="${masterDTO.dpList}" var="dp" varStatus="stu">
		   		${dp.dpDate} : ${dp.dpClsprc}
		   </c:forEach>
		  </div>
		</div>
	  </div>
	  </div>

          <div class="col-xs-6">
            <table class="table table-condensed">
              <tbody>
                <tr>
                  <th>전일비</th>
                  <td><fmt:formatNumber value="${masterDTO.priceDTO.cmpprevddPrc}" /></td>
                  <th>등락</th>
                  <td>${masterDTO.priceDTO.fluctuationRate}%</td>
                </tr>
                <tr>
                  <th>전일</th>
                  <td><fmt:formatNumber value="${masterDTO.priceDTO.trdPrc - masterDTO.priceDTO.cmpprevddPrc}"/></td>
                  <th>거래량</th>
                  <td><fmt:formatNumber value="${masterDTO.priceDTO.accTrdvol}"/></td>
                </tr>
                <tr>
                  <th>저가</th>
                  <td><fmt:formatNumber value="${masterDTO.priceDTO.lwprc}"/></td>
                  <th>고가</th>
                  <td><fmt:formatNumber value="${masterDTO.priceDTO.hgprc}"/></td>
                </tr>
                <tr>
                  <th>시가</th>
                  <td><fmt:formatNumber value="${masterDTO.priceDTO.opnprc}"/></td>
                  <th>시가총액</th>
                  <td><fmt:formatNumber value="${masterDTO.listShrs * masterDTO.priceDTO.trdPrc/100000000}"/></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      <hr>
      <div class="row-fluid company-buy">
        <div class="col-xs-8"><div class="company-buy-slider"></div></div>
        <div class="col-xs-2"><h5 class="company-buy-value">0</h5></div>
        <div class="col-xs-2"><button class="company-buy-btn btn btn-primary">구매</button></div>
      </div>
      
      <c:if test="${plQuantity gt 0}">
	     <div class="row-fluid company-sell">
	       <div class="col-xs-8"><div class="company-sell-slider"></div></div>
	       <div class="col-xs-2"><h5 class="company-sell-value">0</h5></div>
	       <div class="col-xs-2"><button class="company-sell-btn btn btn-danger">판매</button></div>
	     </div>
      </c:if>
    </div>
  </div>
  
<script type="text/javascript">
	/* $("#company-${masterDTO.isuCd}").ready(function(){
		
			console.log("${masterDTO.rtpList}")
			//console.log("${masterDTO.rtpList.rpTrdTm}")
			
	        $('#company-chart-today').highcharts({
	            chart: {
	                zoomType: 'x'
	            },
	            title: {
	                text: 'USD to EUR exchange rate over time'
	            },
	            subtitle: {
	                text: document.ontouchstart === undefined ?
	                        'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
	            },
	            xAxis: {
	                type: 'datetime',
	                
	            },
	            yAxis: {
	                title: {
	                    text: 'Exchange rate'
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
	                            y2: 5
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
	                data: [ ["2016.07.01" , 1] , ["2016.08.01" , 2] , ["2016.09.01" , 3] , ["2016.10.01", 4] , ["2016.11.01", 5] ]
	            }]
	        });
	    }); */
</script>