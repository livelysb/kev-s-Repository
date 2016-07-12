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
   #naver_id_login{
      left:42.5%;
      top:83%;
      position: absolute;
      z-index:1;
   }
   #bottom{
      top:95%;
      position: absolute;
      background-color:rgba(0,0,0,0.4);
      text-align:center;
      font-size:13px;
      color:white;
      width:100%;
      height:5%;
   }

body {
	margin: 0px;
	background-image: url(resources/img/loginView.png);
	background-size: 100% 100%;
	background-repeat: repeat;
	overflow-y: hidden;

	/* background-position:bottom; */
}
</style>

</head>

<body>
	<div id="naver_id_login"></div>
	<div id="bottom">
		<a>	ⓒ 석버미와 아이들</a>&nbsp;Corp. All Rights Reserved.
	</div>
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<!-- 네이버아디디로로그인 초기화 Script -->
<script type="text/javascript">
	$(function() {
		$("body").attr("style", "height:" + (screen.height-100) + "px");
	})

	var naver_id_login = new naver_id_login("MEu9lHVoIBXQU0fULcr6",
			"http://192.168.0.57/zuplay/LoginInfo");
	var state = naver_id_login.getUniqState();
	naver_id_login.setButton("white", 3, 40);
	naver_id_login.setDomain(".service.com");
	naver_id_login.setState(state);
	//naver_id_login.setPopup();
	naver_id_login.init_naver_id_login();
</script>

</html>