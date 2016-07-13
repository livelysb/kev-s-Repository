<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="bootstrap.min.js"></script>
<script type="text/javascript" src="jquery.bootpag.min.js"></script>
<link href="bootstrap.min.css" rel="stylesheet" />
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
        }) 
	}) 
</script>

</head>
<body>
	<div id="history-window">
		<div id="history-header">Stock History</div>
		<div id="history-content">
			<div id="div1">chart</div>
			<div id="div2">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>종목명</th>
							<th>현재가</th>
							<th>전일비</th>
							<th>등락률</th>
							<th>거래량</th>
							<th>시가</th>
							<th>고가</th>
							<th>저가</th>
						</tr>
					</thead>
					<tbody id="">

					</tbody>
				</table>
			</div>
			<div id="div3">
				<table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>종목명</th>
							<th>현재가</th>
							<th>전일비</th>
							<th>등락률</th>
							<th>거래량</th>
							<th>시가</th>
							<th>고가</th>
							<th>저가</th>
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