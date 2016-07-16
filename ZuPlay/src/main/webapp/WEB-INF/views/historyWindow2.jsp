<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<div id='history-user-window'><div id='history-user-header'>Stock History</div><div id='history-user-content'>
<div id='history-user-left'><div id='history-user-line-chart'></div></div><div id='history-user-right'>
<ul class='nav nav-tabs'><li class='active'><a data-toggle='tab' href='#history-user-best-div'>Profit</a></li>
<li><a data-toggle='tab' href='#history-user-worst-div'>Lost</a></li></ul><div class='tab-content'>
<div id='history-user-best-div' class='tab-pane active'><div id='history-user-best-piechart'></div>
</div><div id='history-user-worst-div' class='tab-pane active'>	
<div id='history-user-worst-piechart'></div></div></div></div><div id='history-user-foot'>
<table class='table table-bordered table-hover'><thead><tr>
<th>구분<span class='caret'></span></th><th>종목<span class='caret'></span></th>
<th>분야<span class='caret'></span></th><th>체결시각<span class='caret'></span></th>
<th>수량</th><th>체결가</th><th>거래가</th></tr></thead><tbody id='history-user-stock-list'>
</tbody></table><div id='page-align'><div id='page-selection'></div></div></div></div></div>