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
	#logo {text-align: center}
</style>

</head>

<body>
	
	<div id="logo"><img src="resources/img/logo.png" alt="zuplay-logo" ></div>
	
	<%-- <!-- 네이버회원정보 submit -->
	<form id = 'f' name="f" method='post' action ='<c:url value="firstLoginCheck"/>' style="display: none">
      <input type="text" name="playerNaverId" id="playerNaverId"/>
      
      
   </form> --%>
   
   <!-- 모달 창 -->
	<div class="modal fade" id="nickModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- header -->
				<div class="modal-header">
					<!-- 닫기(x) 버튼 -->
					<button type="button" class="close" data-dismiss="modal">×</button>
					<!-- header title -->
					<h4 class="modal-title">회원가입</h4>
				</div>
				<!-- body -->
				<div class="modal-body">
					<form action="joinMember" method="post" id="nickForm">
						별명 :  <input type="text" name="playerNickname" id="playerNickname">
						<button type="button" id="NickCheck" class="btn btn-default">중복확인</button>
						<div id="divIdCheck"></div>
						<input type="hidden" name="playerGender" id="playerGender"/>
    					<input type="hidden" name="playerAge" id="playerAge"/>
						
						
						<!-- <input type="hidden" id="playerNaverId" name="playerNaverId">
						<input type="hidden" id="playerGender" name="playerGender">
						<input type="hidden" id="playerAge" name="playerAge"> -->
						
						
						
					</form>
				</div>
				<!-- Footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="nickConfirm">확인</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
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

    var naverLogin = new naver_id_login("MEu9lHVoIBXQU0fULcr6","http://127.0.0.1:8000/zuplay/LoginInfo");

   //get_naver_userprofile 동작후 callback 될 function
    function testcallback() {
      alert(naverLogin.getProfileData('email'))
      $("#playerNaverId").val(naverLogin.getProfileData('email'));
      $("#playerGender").val(naverLogin.getProfileData('gender'));
      $("#playerAge").val(naverLogin.getProfileData('age'));
   } 
    $(function() {
        naverLogin.get_naver_userprofile("testcallback()");
    	$.ajax({
    		url: "firstLoginCheck" ,
			type:"post",
			dataType:"text",  
			data : "playerNickname=" + $("#playerNaverId").val(),
			success:function(result){
				if(result==true){
					//모달띄어줘
					alert("모달성공 : " + result);
					$("#nickModal").modal("show");
				}else{
					//메인으로 페이지이동
					location.href="index";
				}
			} ,
			error:function(err){
				alert(err +"에러발생");
			}
    	})
    	
 
    	
   }) 
	</script>
</html>








