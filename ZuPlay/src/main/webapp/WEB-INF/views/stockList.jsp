<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/jquery-ui.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">

<style type="text/css">
</style>
</head>

<body>
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
			<tbody id="stockListTBody">
				
			</tbody>
	</table>		
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		
		$.ajax({
			url:"realTimeStock",
			type:"post",
			dataType:"json",
			data:"page=1",
			success:function(data){
				str="";
				$.each(data, function(index,item){
					str+="<tr><td>"+item.isuKorAbbrv+"</td>"
					str+="<td>"+item.priceDTO.trdPrc +"</td>";
					str+="<td>"+item.priceDTO.cmpprevddPrc +"</td>";
					str+="<td>"+item.priceDTO.fluctuationRate +"</td>";
					str+="<td>"+item.priceDTO.trdvol +"</td>";
					str+="<td>"+item.priceDTO.openprc +"</td>";
					str+="<td>"+item.priceDTO.hgprc +"</td>";
					str+="<td>"+item.priceDTO.lwprc +"</td><tr>";
				})
				
				$("#stockListTBody").html(str);
			},
			error:function(err){
				alert(err+"에러발생")
			}
		})
	})
</script>
</html>


