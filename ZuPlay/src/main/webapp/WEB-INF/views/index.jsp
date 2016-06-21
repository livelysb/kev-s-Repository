<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
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
</head>

<body>
	<div class="zp-wrapper">
		<div class="container-fluid">
				<div class="row-fluid">
				
				<!-- 사이드바 Wrapper -->
					<div class="zp-wrapper-side">
						<div class="col-md-1">
							<!--사이드 영역-->
							
							<!-- 로고 영역 -->
							<div class="zp-wrapper-logo">
								<a href=<c:url value="/"/>><img src="resources/img/logo.png" alt="zuplay-logo"></a>
							</div>
							
							<!-- 메인 아바타 영역-->
							<div class="zp-wrapper-avatar-main">
								<div class="zp-wrapper-avatar">
									<img alt="avatar-head" src="">
								</div>
							</div>
							
							<!-- 광고 영역 -->
							<div class="zp-wrapper-ad">
							
							</div>
						</div>
					</div>
					<!-- 사이드바 Wrapper 끝 -->
					
					<div class="zp-wrapper-content">
						<div class="col-md-11">
							<!--본문-->
							Test
						</div>
					</div>
				</div>
		</div>
	</div>
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/zuplay.js"></script>
</html>
