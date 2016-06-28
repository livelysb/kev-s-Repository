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
<link href="resources/css/zuplay.css" rel="stylesheet">

<style type="text/css">
	/* #tabBtn {text-align: right} */
	#searchBar {float: right}
	.itemImg {width:100%;}
</style>
</head>

<body>
	<div>
		<div>
			<div>
				<div id="searchBar">
					<select class="form-control" id="auctionSelect">
					  <option value="all">전체</option>
					  <option value="hair">Hair</option>
					  <option value="clothes">Clothes</option>
					  <option value="eyes">Eyes</option>
					  <option value="mouse">Mouse</option>
					  <option value="earring">Earring</option>
					  <option value="acc">Acc</option>
					  <option value="etc">Etc</option>
					</select>
					<input type="text" class="form-control" placeholder="Search" id="auctionSearch">
					<input type="hidden" id="auctionHidden">
				</div>
			</div>

		</div>
		<div class="container">
			<ul id="myTab" class="nav nav-tabs " role="tablist">
				<li role="presentation" class="active "><a data-target="#home"
					id="buyTab" role="tab" data-toggle="tab" aria-controls="home" 
					aria-expanded="true">구매</a></li>
				<li role="presentation" class=""><a data-target="#profile"
					role="tab" id="sellTab" data-toggle="tab"
					aria-controls="profile" aria-expanded="false">판매목록</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div role="tabpanel" class="tab-pane fade active in" id="home"
					aria-labelledby="buyTab">
					<div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="bg-primary">
									<th width="10%">아이템</th>
									<th>아이템 이름 <span class="caret"></span></th>
									<th>판매가 <span class="caret"></span></th>
									<th>남은시간 <span class="caret"></span></th>
									<th>판매자이름 <span class="caret"></span></th>
									<th>구매</th>
								</tr>
							</thead>
							<tbody id="buyTBody">
							</tbody>
						</table>
						<button type="button" class="btn btn-primary" id="backBtn" >이전</button>
						<button type="button" class="btn btn-primary" id="nextBtn" >다음</button>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane fade" id="profile"
					aria-labelledby="sellTab">
						<div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="bg-primary">
									<th width="10%">아이템</th>
									<th>아이템 이름 <span class="caret"></span></th>
									<th>판매가 <span class="caret"></span></th>
									<th>남은시간 <span class="caret"></span></th>
									<th>취소</th>
								</tr>
							</thead>
							<tbody id="sellTBody">
							</tbody>
						</table>
					</div>
				</div>
				<br>
				<button type="button" id="auctionRegister" class="btn btn-primary">등록하기</button>
			</div>
		</div>
		
	</div>
	
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>

<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/zuplay.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
        //페이지 변수
        var count=1;
		var sellBtn="";
		var colorBtn="";
		//탭 토글
        $('a[data-toggle="tab"]').on('hidden.bs.tab', function(e){
        });
        
		//구매탭
		$("#buyTab").on("click",function(){
			$("#auctionSearch").show();
        	$("#auctionSelect").show();
		})
        
        //판매탭
        $("#sellTab").on("click",function(){
        	$("#auctionSearch").hide();
        	$("#auctionSelect").hide();
        	sellList();
        })
        
        //경매장 아이템등록
        $("#auctionRegister").on("click",function(){
        	
        })
        
        //아이템구매
        $(document).on("click",'input[value=구매]', function() {
			$.ajax({
				url:"auctionBuy",
				type:"post",
				dataType:"text",
				data:"imSq="+$(this).attr("id"),
				success:function(result){
					switch(result){
						case "1" : alert("구매되었습니다."); break;
						case "2" : alert("인벤토리가 부족합니다."); break;
						case "3" : alert("루비가 부족합니다."); break;
						case "4" : alert("이미 판매 된 물품입니다.");break;	
					}
					alert(count)
					search(count)
					
				},
				error:function(err){
					alert(err+"에러발생")
				}
			})
		})
		
		//판매취소
		$(document).on("click",'input[value=취소]', function() {
			$.ajax({
				url:"auctionCancel",
				type:"post",
				dataType:"text",
				data:"imSq="+$(this).attr("id"),
				success:function(result){
					alert("취소되었습니다.");
					///판매탭 새로고침하기!!!
					sellList();
				},
				error:function(err){
					alert(err+"에러발생")
				}
			})
		})
		
		//수령, 유찰
		$(document).on("click",'input[value=수령],input[value=유찰]', function() {
			var wordBtn =  $(this).val();
			$.ajax({
				url:"auctionBring",
				type:"post",
				dataType:"text",
				data:"imSq="+$(this).attr("id"),
				success:function(result){
					if(wordBtn=="수령"){
						alert("판매금을 수령하셨습니다.")
					}else{
						if(result!="false"){
							alert("유찰 된 아이템을 수령하셨습니다.")
						}else{
							alert("인벤토리를 비워 주십시오.")
						}
					} 
					sellList();
				},
				error:function(err){
					alert(err+"에러발생")
				}
			})
		})
		
		
        //검색
        $("#auctionSearch").on("keyup",function(){
        	count=1;
        	if(event.keyCode == 13) {
        		$("#auctionHidden").val($(this).val())
        		search(count); 
        		$("#auctionSearch").val("");
        	}
        })
	    
        //이전버튼
		$("#backBtn").on("click",function(){
			if($("#buyTBody").children().length<=0) return
			if(count>1){
				search(count-1);
			}
		})
		//다음버튼
		$("#nextBtn").on("click",function(){
			if($("#buyTBody").children().length<=0) return
			search(count+1)
		})
		 
		//페이지에따른 검색
		function search(page){
        	
        	$.ajax({
        		url:"auctionSearch",
        		type:"post",
        		dataType:"json",
        		data:"keyword=" + $("#auctionSearch").val()+"&itemClass="+$("#auctionSelect").val()+"&page="+page,
        		success:function(data){
        			if(data.length==0){
        				return;
        			} 
        			count=page;
        			$("#buyTBody").empty();
        			var str="";
        			$.each(data, function(index,item){
        				if(item.imAuctionEnd="T"){
	        				str+="<tr><td><img src='"+item.itemDTO.itemImg+"' class='itemImg'></td>";
	        				str+="<td>"+item.itemDTO.itemName+"</td>";
	        				str+="<td>"+item.imPurchasePrice+"</td>";
	        				str+="<td>"+item.imBidTime+"</td>";
	        				str+="<td>"+item.playerNickname+"</td>";
	        				str+="<td><input type='button' id='"+item.imSq+"' class='btn btn-primary btn-sm btnBuy' value='구매'></td>"
        				}
        			})
        			$("#buyTBody").html(str);
        		},
        		error:function(err){
        			alert(err+"에러발생");
        		}
        		
        	})
        }
		
		function sellList(){
			$.ajax({
	        	url: "auctionMyPage" ,
				type:"post",
				dataType:"json",  
				success:function(data){
					
					$("#sellTBody").empty;
					var str="";
					$.each(data, function(index, item){
						str+="<tr><td><img src='"+ item.itemDTO.itemImg +"' class='itemImg' ></td>";
						str+="<td>"+item.itemDTO.itemName+"</td>";
						str+="<td>"+item.imPurchasePrice+"</td>";
						str+="<td>"+item.imBidTime+"</td>";
						
						if(item.imAuctionEnd=="T"){
							sellBtn="취소";
							colorBtn="btn-primary";
						}else if(item.imAuctionEnd=="F"){
							sellBtn="수령";
							colorBtn="btn-success";
						}else{
							sellBtn="유찰";
							colorBtn="btn-danger";
						}
												
						str+="<td><input type='button' id='"+item.imSq+"' class='btn "+colorBtn+" btn-sm btnCancel' value='"+sellBtn+"'></td></tr>"
					})
					$("#sellTBody").html(str);
				} ,
				error:function(err){
					alert(err +"에러발생");
				}
	        })
		}
    });
</script> 
</html> 








