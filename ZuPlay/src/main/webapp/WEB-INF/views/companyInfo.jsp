<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div id="company-${masterDTO.isuCd}" class="company-window">
   <div class="company-header">기업정보 - ${masterDTO.isuCd}</div>
   <input type="hidden" value="${masterDTO.isuCd}" class="company-isuCd">
   <input type="hidden" value="${plQuantity}" class="company-qty">
   <input type="hidden" value="${masterDTO.priceDTO.trdPrc}" class="company-price">
   <div class="company-content">
      <div class="row-fluid">
      
         <div class="col-xs-12 company-title">
            <span class="company-title-name">${masterDTO.isuKorAbbrv}</span> 
           	<c:choose>
                 <c:when test="${masterDTO.priceDTO.trdPrc ge 1}">
                    <span class="company-title-stock" style="color:red"><fmt:formatNumber value="${masterDTO.priceDTO.trdPrc}" /></span>
                 </c:when>
                <c:when test="${masterDTO.priceDTO.trdPrc le -1}">
                   <span class="company-title-stock blue" style="color:blue"><fmt:formatNumber value="${masterDTO.priceDTO.trdPrc}" /></span>
                </c:when>
				<c:otherwise>
                   <span class="company-title-stock" ><fmt:formatNumber value="${masterDTO.priceDTO.trdPrc}" /></span>
				</c:otherwise> 
              </c:choose>
         </div>
      </div>
      <div class="row-fluid">
         <div class="col-xs-6 company-chart-warpper">
            <div class="company-chart">
               <ul class="nav nav-tabs">
                  <li class="active"><a data-toggle="tab"
                     href="#company-chart-today-${masterDTO.isuCd}">오늘</a></li>
                  <li><a data-toggle="tab" href="#company-chart-month-${masterDTO.isuCd}">한달</a></li>
               </ul>

               <div class="tab-content">
                  <div id="company-chart-today-${masterDTO.isuCd}" class="tab-pane fade in active">
                     <%-- <c:forEach items="${masterDTO.rtpList}" var="rtp" varStatus="stu">
                           ${rtp.rpTrdTm2} : ${rtp.rpTrdPrc}
                     </c:forEach> --%>
                  </div> 
                  <div id="company-chart-month-${masterDTO.isuCd}" class="tab-pane fade">
                     <%-- <c:forEach items="${masterDTO.dpList}" var="dp" varStatus="stu">
                           ${dp.dpDate2} : ${dp.dpClsprc}
                     </c:forEach> --%>
                  </div>
               </div>
            </div>
         </div>

         <div class="col-xs-6">
            <table class="table table-condensed">
               <tbody>
                  <tr class="no-border-top">
                     <th>전일비</th>
                     <td>
                     	<c:choose>
			           		<c:when test="${masterDTO.priceDTO.cmpprevddPrc gt 0}">
			           			<span class="price-up"><fmt:formatNumber value="${masterDTO.priceDTO.cmpprevddPrc}" /></span>
			           		</c:when>
			          		<c:when test="${masterDTO.priceDTO.cmpprevddPrc lt 0}">
			          			<span class="price-down"><fmt:formatNumber value="${-masterDTO.priceDTO.cmpprevddPrc}" /></span>
			          		</c:when>
							<c:otherwise>
			          			<span><fmt:formatNumber value="${masterDTO.priceDTO.cmpprevddPrc}" /></span>
							</c:otherwise>
			           	</c:choose>
			         </td>
                     <th>등락률</th>
                     
                     <td>
                     	<c:choose>
			           		<c:when test="${masterDTO.priceDTO.fluctuationRate gt 0}">
			           			<span class="price-up">${masterDTO.priceDTO.fluctuationRate}%</span>
			           		</c:when>
			          		<c:when test="${masterDTO.priceDTO.fluctuationRate lt 0}">
			          			<span class="price-down">${-masterDTO.priceDTO.fluctuationRate}%</span>
			          		</c:when>
							<c:otherwise>
			          			<span>${masterDTO.priceDTO.fluctuationRate}%</span>
							</c:otherwise>
			           	</c:choose>
                     
                     </td>
                  </tr>
                  <tr>
                     <th>전일</th>
                     <td><fmt:formatNumber
                           value="${masterDTO.priceDTO.trdPrc - masterDTO.priceDTO.cmpprevddPrc}" /></td>
                     <th>거래량</th>
                     <td><fmt:formatNumber
                           value="${masterDTO.priceDTO.accTrdvol}" /></td>
                  </tr>
                  <tr>
                     <th>저가</th>
                     <td class="price-label"><fmt:formatNumber value="${masterDTO.priceDTO.lwprc}" /></td>
                     <th>고가</th>
                     <td><fmt:formatNumber value="${masterDTO.priceDTO.hgprc}" /></td>
                  </tr>
                  <tr>
                     <th>시가</th>
                     <td><fmt:formatNumber value="${masterDTO.priceDTO.opnprc}" /></td>
                     <th>시가총액(억)</th>
                     <td><fmt:formatNumber value="${masterDTO.listShrs * masterDTO.priceDTO.trdPrc/100000000}"/></td>
                  </tr>
                   <tr>
                     <th>하한가</th>
                     <td><fmt:formatNumber value="${masterDTO.lwlmtprc}" /></td>
                     <th>상한가</th>
                     <td><fmt:formatNumber value="${masterDTO.uplmtprc}" /></td>
                  </tr>
               </tbody>
            </table>
         </div>
      </div>
      <hr>
      <div class="company-buy">
         <div class="row-fluid">
            <div class="col-xs-12"><div class='company-buy-slider company-slider'></div></div>
         </div>
         <div class="row-fluid">
            <div class="col-xs-4"><div class="company-buy-input company-input"></div></div>
            <div class="col-xs-5"><h4 class="company-buy-value"></h4></div>
            <div class="col-xs-3"><button class="company-buy-btn btn btn-block btn-success">매수</button></div>
         </div>
      </div>
      <div class="company-sell">
         <div class="row-fluid">
            <div class="col-xs-12"><div class='company-sell-slider company-slider'></div></div>
         </div>
         <div class="row-fluid">
            <div class="col-xs-4"><div class="company-sell-input company-input"></div></div>
            <div class="col-xs-5"><h4 class="company-sell-value"></h4></div>
            <div class="col-xs-3"><button class="company-sell-btn btn btn-danger btn-block">매도</button></div>
         </div>
      </div>
      
   </div>
</div>
<script type="text/javascript">
   $("#company-${masterDTO.isuCd}")
         .ready(function() {
                     var chartData = function(data,kind) {
                     chartJson = new Array();
                     
                     $.each(data, function(index, item) {
                    	 console.log(data)
                        var ChartObj = new Object();
                        if(kind=="rtp"){
                           ChartObj.x = item.rpTrdTm2+32400000;
                           ChartObj.y = item.rpTrdPrc;
                           ChartObj.name = item.rpTrdTm2;
                        }else{
                           ChartObj.x = item.dpDate2+32400000;
                           ChartObj.y = item.dpClsprc;
                           ChartObj.name = item.dpDate2;
                        }
                           chartJson.push(ChartObj);
                     })

                     kind=="rtp" ? companylineChart(chartJson,"#company-${masterDTO.isuCd} #company-chart-today-${masterDTO.isuCd}") : 
                                companylineChart(chartJson,"#company-${masterDTO.isuCd} #company-chart-month-${masterDTO.isuCd}");

                  }

                  /*라인차트 그리기*/
                  var companylineChart = function(chartJson,chartId) {
                     console.log(chartId);
                     $(chartId).highcharts({ chart : {
                                       zoomType : 'x',
                                       height : 200,
                                       width : 300
                                    },
                                    title : {
                                       text: ' '
                                    },
                                    xAxis : {
                                       type : 'datetime'
                                    },
                                    yAxis : {
                                       title : {
                                          text : 'Profit'
                                       }
                                    },
                                    legend : {
                                       enabled : false
                                    },
                                    tooltip : {
                                       formatter : function() {
                                          var result = new Date(this.x).toUTCString().split("GMT")[0];
                                          return result+ "<br>"+ this.series.name+ ' : <b>'+ (this.y).format()+ '원</b>';
                                       }
                                    },
                                    plotOptions : {
                                       area : {
                                          fillColor : {
                                             linearGradient : {
                                                x1 : 0,
                                                y1 : 0,
                                                x2 : 0,
                                                y2 : 1
                                             },
                                             stops : [
                                                   [
                                                         0,
                                                         Highcharts
                                                               .getOptions().colors[0] ],
                                                   [
                                                         1,
                                                         Highcharts
                                                               .Color(
                                                                     Highcharts
                                                                           .getOptions().colors[0])
                                                               .setOpacity(
                                                                     0)
                                                               .get(
                                                                     'rgba') ] ]
                                          },
                                          marker : {
                                             radius : 2
                                          },
                                          lineWidth : 1,
                                          states : {
                                             hover : {
                                                lineWidth : 1
                                             }
                                          },
                                          threshold : null
                                       }
                                    },

                                    series : [ {
                                       type : 'area',
                                       name : '체결가',
                                       data : chartJson
                                       /* dataGrouping: {
                                           enabled: false
                                       } */
                                    } ]
                                 });
                  }
                  
                  /*탭 클릭 이벤트*/
                      $("#company-${masterDTO.isuCd} .nav-tabs  a").on("click",function(){
                        if($(this).text()=="오늘"){
                           $("#company-${masterDTO.isuCd} #company-chart-today").empty();
                           chartData(JSON.parse('${rtpList}'),"rtp"); 
                        }else{
                           $("#company-${masterDTO.isuCd} #company-chart-month").empty();
                           chartData(JSON.parse('${dpList}'),"dp")
                        }
                     });
                  chartData(JSON.parse('${rtpList}'),"rtp"); 
                });
</script>