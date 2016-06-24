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
	#search{float: right}
	img {width:100%; heigh:100%}
</style>
</head>

<body>
	<div>
		<!-- <div id="tabBtn">
			<button type="button" id="buyTabBtn" class="btn btn-default">구매</button>
			<button type="button" id="sellTabBtn" class="btn btn-default">판매</button>
			<button type="button" id="listTabBtn" class="btn btn-default">판매목록</button>
		</div> -->
		<div>
			<div>
				<div id="search">
					<input type="text" class="form-control" placeholder="Search">
				</div>
			</div>

		</div>
		<div class="container">
			<ul id="myTab" class="nav nav-tabs " role="tablist">
				<li role="presentation" class="active "><a data-target="#home"
					id="home-tab" role="tab" data-toggle="tab" aria-controls="home" 
					aria-expanded="true">구매</a></li>
				<li role="presentation" class=""><a data-target="#profile"
					role="tab" id="profile-tab" data-toggle="tab"
					aria-controls="profile" aria-expanded="false">판매목록</a></li>
				<li role="presentation" class="dropdown"></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div role="tabpanel" class="tab-pane fade active in" id="home"
					aria-labelledby="home-tab">
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
							<tbody>
								<tr>
									<td><img src="resources/img/avatar/body/clothes-05.png"></td>
									<td>박스</td>
									<td>150000</td>
									<td>2015.05.05<br>20:00:00
									</td>
									<td>민수짱장님</td>
									<td><button type="button" class="btn btn-primary btn-sm">구매</button></td>
								</tr>
								<tr>
									<td><img src="resources/img/avatar/body/clothes-10.png"></td>
									<td>양복</td>
									<td>250000</td>
									<td>2015.05.05<br>20:00:00
									</td>
									<td>석범짱장님</td>
									<td><button type="button" class="btn btn-primary btn-sm">구매</button></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane fade" id="profile"
					aria-labelledby="profile-tab">
						<div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="bg-primary">
									<th>아이템</th>
									<th>아이템 이름 <span class="caret"></span></th>
									<th>판매가 <span class="caret"></span></th>
									<th>남은시간 <span class="caret"></span></th>
									<th>취소</th>
								</tr>
							</thead>
							<tbody id="auctionTBody">
								<tr>
									<td><img src=""></td>
									<td>한복</td>
									<td>150000</td>
									<td>2015.05.05<br>20:00:00
									</td>
									<td><button type="button" class="btn btn-primary btn-sm" >취소</button></td>
								</tr>
								<tr>
									<td><img src=""></td>
									<td>양복</td>
									<td>250000</td>
									<td>2015.05.05<br>20:00:00
									</td>
									<td><button type="button" class="btn btn-primary btn-sm">취소</button></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

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
        $('a[data-toggle="tab"]').on('hidden.bs.tab', function(e){
        });
        
        
        function stockAuction(){
	        $.ajax({
	        	url: "stockAction" ,
				type:"post",
				dataType:"text",  
				success:function(result){
					$("#auctionTBody").empty;
					$.each(data, function(index, item){
						
						
					}

				} ,
				error:function(err){
					alert(err +"에러발생");
				}
	        })
        }
    });
	
</script> 
</html> 








