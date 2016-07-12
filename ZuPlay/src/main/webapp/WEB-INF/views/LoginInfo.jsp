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
</style>

</head>

<body>
	
   <!-- 모달 창 -->
	<div class="modal fade" id="nickModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- header -->
				<div class="modal-header">
					<!-- 닫기(x) 버튼 -->
					<button type="button" class="close" id="nickX" data-dismiss="modal">×</button>
					<!-- header title -->
					<h4 class="modal-title">회원가입</h4>
				</div>
				<!-- body -->
				<div class="modal-body">
					<form action="joinMember" method="post" id="nickForm" >
						별명 : <input type="text" name="playerNickname" id="playerNickname"> 
						<div id="divIdCheck"></div>
						<input type="hidden" name="playerNaverId" id="playerNaverId"/>
						<input type="hidden" name="playerGender" id="playerGender"/>
    					<input type="hidden" name="playerAge" id="playerAge"/>
						
					</form>
				</div>
				<!-- Footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="nickConfirm">확인</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" id="nickCancel">닫기</button>
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

	var naverLogin = new naver_id_login("MEu9lHVoIBXQU0fULcr6","http://192.168.0.57/zuplay/LoginInfo");

	//get_naver_userprofile 동작후 callback 될 function
	function testcallback() {
	    	    $("#playerNaverId").val(naverLogin.getProfileData('email'));
	    	    $("#playerGender").val(naverLogin.getProfileData('gender'));
	    	    $("#playerAge").val(naverLogin.getProfileData('age'));
	    	    Logincheck();
	    	 } 
	
		//모달 확인 버튼
		 $("#nickConfirm").on("click",function(){
			 $("#nickForm").submit();
		 })
	
		//최초로그인인지 확인 후 모달띄우거나 메인페이지로 이동	 
    	 function Logincheck(){
    		 $.ajax({
    			url: "firstLoginCheck" ,
    			type:"post",
    			dataType:"text",  
    			data : "playerNaverId=" + $("#playerNaverId").val(),
    			success:function(result){
    				if(result=="true"){
    					$("#nickModal").modal("show");
    				}else{
    					location.href="index";
    				}
    			} ,
    			error:function(err){
    				alert("여기")
    				alert(err +"에러발생");
    			}
    		}) 
    	 }
		//닉네임 중복체크
    	 $("#playerNickname").on("keyup",function (){
			if($(this).val()==""){
				$("#divIdCheck").text("");
			}else{
	 			$.ajax({
	 				url: "checkRepetition" ,
	 				type:"post",
	 				dataType:"text",  
	 				data : "playerNickname=" + $("#playerNickname").val(),
	 				success:function(result){
	
	 					if(result=="true"){
	 						$("#divIdCheck").text("사용가능한 닉네임입니다.");
	 					}else{
	 						$("#divIdCheck").text("사용불가능한 닉네임입니다.");
	 					}
	 				} ,
	 				error:function(err){
	 					alert(err +"에러발생");
	 				}
	 			});
			}
 		})	
    $(function() {
    	 naverLogin.get_naver_userprofile("testcallback()");
    	
    	 //모달이 포커스를 잃을 시 로그인화면으로 이동
    	 $('#nickModal').on('hidden.bs.modal', function() {
    		 alert("별명을 입력하지않으면 게임을 할 수 없습니다.")
    		 location.href="http://192.168.0.57/zuplay/Login";
    	 })
   }) 
	</script>
</html>








