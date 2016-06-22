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
	#logo, #naver_id_login {text-align: center}
</style>

</head>

<body>
	<div id="logo"><img src="resources/img/logo.png" alt="zuplay-logo" ></div>
	<!-- 네이버아이디로로그인 버튼 노출 영역 --> <br><br><br><br><br><br><br><br><br>
	<div id="naver_id_login"></div>
	

</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/zuplay.js"></script>

<!-- 네이버아디디로로그인 초기화 Script -->
<script type="text/javascript">
	var naver_id_login = new naver_id_login("MEu9lHVoIBXQU0fULcr6", "http://127.0.0.1:8000/zuplay/LoginInfo");
	var state = naver_id_login.getUniqState(); 
	naver_id_login.setButton("white", 3,40);
	naver_id_login.setDomain(".service.com"); 
	naver_id_login.setState(state);
	//naver_id_login.setPopup();
	naver_id_login.init_naver_id_login();


	/* function testcallback() {
		alert(callback);
		alert(naver_id_login.getProfileData('email'))
		$("#playerNaverId").val(naver_id_login.getProfileData('email'));
		$("#playerGender").val(naver_id_login.getProfileData('gender'));
		$("#playerAge").val(naver_id_login.getProfileData('age'));
		$("#nickModal").submit();
	}
	
	 
	 $(function() {
		 naver_id_login.get_naver_userprofile("testcallback()");
	})
	*/
	
/* 	
	
	$(function() {
		if("${firstLogin}"=="true"){
			$("#nickModal").modal("show");
		}
		
		$("#NickCheck").on("click",function (){
			$.ajax({
				url: "idcheck" ,
				type:"post",
				dataType:"text",  
				data : "playerNickname=" + $("#playerNickname").val(),
				success:function(result){
					$("#divIdCheck").html(result);
				} ,
				error:function(err){
					alert(err +"에러발생");
				}
			})
		})
		
		$("#nickConfirm").on("click",function (){
			$("#nickForm").submit();
		})
		
		
		

	}) */
	
</script>

</html>