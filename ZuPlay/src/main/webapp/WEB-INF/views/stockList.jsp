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
<link href="resources/css/jquery-ui/jquery-ui.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">

<style type="text/css">
</style>
</head>

<body>
<div id="stock-window">
	<div id="stock-header">전체 종목</div>
	<div id="stock-content">
	<input type="text" class="form-control" placeholder="Search" id="stock-search">
	<input type="hidden" id="stock-search-keyword">
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
		<div class="rows">
			<div id="page-align">
				<div id="page-selection"></div>
			</div>
		</div>
	</div>
</div>
			
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>
<script src="resources/js/jquery-ui/jquery-ui.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/jquery.bootpag.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		//종목 보여주기
		function stockPageSelect(page,keyword){
			console.log("page : " +page);
			console.log("keyword : " + keyword)
			$.ajax({  
				url:"realTimeStock",
				type:"post",
				dataType:"json",
				data:"page="+page+"&keyword="+keyword	,
				success:function(data){
					str="";
							
					$.each(data, function(index,item){
						if(index==0){
							if(item.amount%10==0){
								pagenation(item.amount/10);
							}else{
								pagenation(item.amount/10+1)
							}
						}else{
							str+="<tr><td class='stock-select'><a href='#'>"+item.isuKorAbbrv+"</a></td>"
							str+="<td>"+item.priceDTO.trdPrc +"</td>";
							str+="<td>"+item.priceDTO.cmpprevddPrc +"</td>";
							str+="<td>"+item.priceDTO.fluctuationRate +"</td>";
							str+="<td>"+item.priceDTO.trdvol +"</td>";
							str+="<td>"+item.priceDTO.opnprc +"</td>";
							str+="<td>"+item.priceDTO.hgprc +"</td>";
							str+="<td>"+item.priceDTO.lwprc +"</td><tr>";
						}
					})
					
					$("#stockListTBody").html(str);
				},
				error:function(err){
					alert(err+"에러발생")
				}
			})
		}

		//마지막 페이지
		function pagenation(pageNo){ 
	        $('#page-selection').bootpag({
	            total: pageNo, maxVisible: 10
	        })
		 }
		
		//페이지 클릭
		$('#page-selection').on("page", function(event, num){
			
			if($("#stock-search-keyword").val()==""){
				$("#stock-search-keyword").val("undefined");
			}
        	stockPageSelect(num,$("#stock-search-keyword").val())
        });
		
		//종목명 클릭 시 상세정보 띄어줌.
		$(document).on("click", ".stock-select",function(){
			$.ajax({
				url:"",
				type:"post",
				dataType:"",
				data:"",
				success:function(data){
					
				},
				error:function(err){
					alert(err+"에러발생")
				}
			})
		})
		
		//검색( 2,3페이지에있을때 1페이지로 다시 이동하게!)
		$(document).on("keyup","#stock-search",function(){
			if(event.keyCode == 13) {
				
				if($(this).val()=="") return;
				$("#page-selection ul li").eq(1).trigger("click");
				$("#stock-search-keyword").val($(this).val());
				stockPageSelect(1,$(this).val())
			}
		})
		stockPageSelect(1)
	})
</script>
</html>


