<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
</head>

<body>
	<div class="container-fluid zp-wrapper">
		<div class="row">
		
		   <!-- 사이드 -->
				<div class="col-md-1 zp-wrapper-side full-width">
					<div class="zp-wrapper-logo">
						<img src="resources/img/logo_kr.png" alt="zuplay-logo-kr">
					</div>
					<div class="zp-wrapper-avatar-main">
						<div class="zp-wrapper-avatar">
							<img src="resources/img/avatar/body/clothes-02.png" alt="avatar-body"> 
							<img src="resources/img/avatar/head/hair-02.png" alt="avatar-body">
							<img src="resources/img/avatar/eyes/eyes-03.png" alt="avatar-body"> 
							<img src="resources/img/avatar/mouse/mouse-01.png" alt="avatar-body">
							<img src="resources/img/avatar/acc/acc-01.png" alt="avatar-body">
						</div>
					</div>
					<div class="zp-wrapper-userinfo">
						<table>
							<tr>
								<td>name</td>
							</tr>
							<tr>
								<td>name</td>
							</tr>
							<tr>
								<td>name</td>
							</tr>
						</table>
					</div>
					<div class="zp-wrapper-ad">광고</div>
				</div>
			
				<!-- 오른쪽영역 -->
				<div class="col-md-12 zp-wrapper-content">
				
					<!-- Content -->
					<div class="row">
						<div class="col-md-12 zp-wrapper-screen" style="border: 2px red solid">Content</div>
					</div>
					
					<!-- Footer -->
					<div class="row jqui zp-wrapper-footer">
						<div class="col-md-5">
						
							<div class="zp-wrapper-chat">
								<ul>
								    <li><a href="#tabs-1">전체</a></li>
								    <li><a href="#tabs-2">Park</a></li>
								    <li><a href="#tabs-3">Kim</a></li>
								</ul>
								<div id="tabs-1">
									<p>11</p>
								</div>
								<div id="tabs-2">
									<p>22</p>
								</div>
								<div id="tabs-3">
									<p>33</p>
								</div>
							</div>
							
							<div class="zp-wrapper-chat-input">
							<input type="text" class="zp-chat-input-id"/><input type="text" class="zp-chat-input"/>
						</div>
						</div>
						<div class="col-md-7"></div>
					</div>
				</div>
		</div>
	</div>
								
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/zuplay.js"></script>
<script>
$(function() {
    $( ".zp-wrapper-chat" ).tabs();
  });
</script>
</html>
