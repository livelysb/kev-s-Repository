<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

	<div id="history-window">
		<div id="history-header">Stock History</div>
		<div id="history-content">
			<div id="history-left">
				<div id="history-line-chart"></div>
			</div>
			<div id="history-right">
				<ul class="nav nav-tabs">
				  <li class="active"><a data-toggle="tab" href="#history-best-div">Profit</a></li>
				  <li><a data-toggle="tab" href="#history-worst-div">Lost</a></li>
				</ul>
				<div class="tab-content">
				  <div id="history-best-div" class="tab-pane active">	
				  	  <div id="history-best-piechart"></div>
				  </div>
				  <div id="history-worst-div" class="tab-pane active">	
		  	  		  <div id="history-worst-piechart"></div>
				  </div>
			  	</div>
			</div>
			<div id="history-foot">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>구분<span class="caret"></span></th>
							<th>종목<span class="caret"></span></th>
							<th>분야<span class="caret"></span></th>
							<th>체결시각<span class="caret"></span></th>
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