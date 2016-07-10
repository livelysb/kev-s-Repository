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
<link href="resources/css/jquery-ui/jquery-ui.css" rel="stylesheet" />
<link href="resources/css/style.css" rel="stylesheet">
<style>
.switch {
	position: relative;
	display: block;
	vertical-align: top;
	width: 100px;
	height: 30px;
	padding: 3px;
	margin: 0 10px 10px 0;
	background: linear-gradient(to bottom, #eeeeee, #FFFFFF 25px);
	background-image: -webkit-linear-gradient(top, #eeeeee, #FFFFFF 25px);
	border-radius: 18px;
	box-shadow: inset 0 -1px white, inset 0 1px 1px rgba(0, 0, 0, 0.05);
	cursor: pointer;
}
.switch-input {
	position: absolute;
	top: 0;
	left: 0;
	opacity: 0;
}
.switch-label {
	position: relative;
	display: block;
	height: inherit;
	font-size: 10px;
	text-transform: uppercase;
	background: #eceeef;
	border-radius: inherit;
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.12), inset 0 0 2px rgba(0, 0, 0, 0.15);
}
.switch-label:before, .switch-label:after {
	position: absolute;
	top: 50%;
	margin-top: -.5em;
	line-height: 1;
	-webkit-transition: inherit;
	-moz-transition: inherit;
	-o-transition: inherit;
	transition: inherit;
}
.switch-label:before {
	content: attr(data-off);
	right: 11px;
	color: #aaaaaa;
	text-shadow: 0 1px rgba(255, 255, 255, 0.5);
}
.switch-label:after {
	content: attr(data-on);
	left: 11px;
	color: #FFFFFF;
	text-shadow: 0 1px rgba(0, 0, 0, 0.2);
	opacity: 0;
}
.switch-input:checked ~ .switch-label {
	background: #E1B42B;
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.15), inset 0 0 3px rgba(0, 0, 0, 0.2);
}
.switch-input:checked ~ .switch-label:before {
	opacity: 0;
}
.switch-input:checked ~ .switch-label:after {
	opacity: 1;
}
.switch-handle {
	position: absolute;
	top: 4px;
	left: 4px;
	width: 28px;
	height: 28px;
	background: linear-gradient(to bottom, #FFFFFF 40%, #f0f0f0);
	background-image: -webkit-linear-gradient(top, #FFFFFF 40%, #f0f0f0);
	border-radius: 100%;
	box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.2);
}
.switch-handle:before {
	content: "";
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -6px 0 0 -6px;
	width: 12px;
	height: 12px;
	background: linear-gradient(to bottom, #eeeeee, #FFFFFF);
	background-image: -webkit-linear-gradient(top, #eeeeee, #FFFFFF);
	border-radius: 6px;
	box-shadow: inset 0 1px rgba(0, 0, 0, 0.02);
}
.switch-input:checked ~ .switch-handle {
	left: 74px;
	box-shadow: -1px 1px 5px rgba(0, 0, 0, 0.2);
}
 
/* Transition
========================== */
.switch-label, .switch-handle {
	transition: All 0.3s ease;
	-webkit-transition: All 0.3s ease;
	-moz-transition: All 0.3s ease;
	-o-transition: All 0.3s ease;
}


/* Switch Flat
==========================*/
.switch-flat {
	padding: 0;
	background: #FFF;
	background-image: none;
}
.switch-flat .switch-label {
	background: #FFF;
	border: solid 2px #eceeef;
	box-shadow: none;
}
.switch-flat .switch-label:after {
	color: #0088cc;
}
.switch-flat .switch-handle {
	top: 6px;
	left: 6px;
	background: #dadada;
	width: 22px;
	height: 22px;
	box-shadow: none;
}
.switch-flat .switch-handle:before {
	background: #eceeef;
}
.switch-flat .switch-input:checked ~ .switch-label {
	background: #FFF;
	border-color: #0088cc;
}
.switch-flat .switch-input:checked ~ .switch-handle {
	left: 72px;
	background: #0088cc;
	box-shadow: none;
}
#setting-content{
	text-align: center; 
}
#setting-content .form-control {
width:auto;
display:inline-block;
}

.switch{

display:inline-block;

}

</style>
</head>

<div id="setting-window">
		<div id="setting-header">Setting</div>
		<div id="setting-content">
	<label>내정보 : </label>
	<label class="switch switch-flat" >
		<input class="switch-input" type="checkbox" id="setting-myInfo"/>
		<span class="switch-label" data-on="On" data-off="Off"></span> 
		<span class="switch-handle"></span> 
	</label><br><br>
	<label>귓속말 : </label>
	<label class="switch switch-flat">
		<input class="switch-input" type="checkbox" id="setting-whisper"/>
		<span class="switch-label" data-on="On" data-off="Off"></span> 
		<span class="switch-handle"></span> 
	</label><br><br>
	<label>&nbsp;&nbsp;친구&nbsp;&nbsp; : </label>
	<label class="switch switch-flat">
		<input class="switch-input" type="checkbox" id="setting-friend"/>
		<span class="switch-label" data-on="On" data-off="Off"></span> 
		<span class="switch-handle"></span> 
	</label><br><br>
	<label>테마 : </label>
		<select class="form-control" id="setting-select" style="width: 150px">
			<option value="android">android</option>
			<option value="arctic">arctic</option>
			<option value="black">black</option>
			<option value="blackberry">blackberry</option>
			<option value="bootstrap">bootstrap</option>
			<option value="classic">classic</option>
			<option value="dark">dark</option>
			<option value="darkblue">darkblue</option>
			<option value="energyblue">energyblue</option>
			<option value="fresh">fresh</option>
			<option value="glacier">glacier</option>
			<option value="highcontrast">highcontrast</option>
			<option value="kokomo">kokomo</option>
			<option value="light">light</option>
			<option value="metro">metro</option>
			<option value="metrodark">metrodark</option>
			<option value="mobile">mobile</option>
			<option value="office">office</option>
			<option value="orange">orange</option>
			<option value="shinyblack">shinyblack</option>
			<option value="summer">summer</option>
			<option value="ui-darkness">ui-darkness</option>
			<option value="ui-le-frog">ui-le-frog</option>
			<option value="ui-lightness">ui-lightness</option>
			<option value="ui-overcast">ui-overcast</option>
			<option value="ui-redmond">ui-redmond</option>
			<option value="ui-smoothness">ui-smoothness</option>
			<option value="ui-start">ui-start</option>
			<option value="ui-sunny">ui-sunny</option>
			<option value="web">web</option>
			<option value="windowsphone">windowsphone</option>
			
		</select><br><br><br>
	<button type="button" id="setting-save" class="btn btn-success">저장</button>
	<button type="button" id="setting-initialization" class="btn btn-danger">초기화</button>
	</div>
</div>
<body>
</body>

   
<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>
<script src="resources/js/jquery.cookie.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		
		var psMyPage="";
		var psChatting="";
		var psFriendAdd="";
		var psTheme="";
		
		//설정정보 저장
		$("#setting-save").on("click", function(){
			confirmCheckBox();
			$.ajax({
				url:"settingSave",
				type:"post",
				dataType:"text",
				data:"psMyPage="+psMyPage+"&psChatting="+psChatting+"&psFriendAdd="+psFriendAdd+"&psTheme="+$("#setting-select").val(),
				error:function(err){
					console.log("Exception : 설정정보 저장")
				}
			})
		})
		
		//설정 초기화
		$("#setting-initialization").on("click",function(){
			$.ajax({
				url:"settingReset",
				type:"post",
				dataType:"text",
				success:function(result){
					$("#setting-myInfo").prop("checked",true);
					$("#setting-whisper").prop("checked",true);
					$("#setting-friend").prop("checked",true);
					$("#setting-select").val("kokomo");
				},
				error:function(err){
					console.log("Exception : 설정 초기화")
				}
			})
		})
		
		//셋팅정보 로드
		function settingLoad(){
			$.ajax({
				url:"settingSelect",
				type:"post",
				dataType:"json",
				success:function(result){
					result.psMyPage=="T" ? $("#setting-myInfo").prop("checked",true) : $("#setting-myInfo").prop("checked",false);
					result.psChatting=="T" ? $("#setting-whisper").prop("checked",true) : $("#setting-whisper").prop("checked",false)
					result.psFriendAdd=="T" ? $("#setting-friend").prop("checked",true) : $("#setting-friend").prop("checked",false)
					$("#setting-select").val(result.psTheme);
				},
				error:function(err){
					console.log("Exception : settingLoad")
				}
			})
		}
		
		//체크박스 상태변수
		function confirmCheckBox(){
			$("#setting-myInfo").is(":checked") ? psMyPage="T" : psMyPage="F"
			$("#setting-whisper").is(":checked") ? psChatting="T" : psChatting="F"
			$("#setting-friend").is(":checked") ? psFriendAdd="T" : psFriendAdd="F" 
		}
		
		settingLoad()
	})
</script>
</html>







