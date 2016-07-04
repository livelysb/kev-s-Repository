
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주플레이</title>

<script src="resources/js/jquery-2.2.4.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("body").attr("style", "height:" + (screen.height - 145) + "px");

		$("#back-btn").click(function() {
			location.href = "index";
		});
	})
</script>

<style type="text/css">
#errText {
	
}

#errMsg {
	position: relative;
	left: 12%;
	top: 45%;
	width: 800px;
	height: 200px;
}

#back-btn {
	position: absolute;
	margin: 0px;
	background-image: url(resources/img/errorBtn.png);
	background-size: 778px 100px;
	width: 778px;
	height: 100px;
	left: 9%;
	top: 80%;
}

body {
	margin: 0px;
	background-image: url(resources/img/errorView.png);
	background-color: F4F4F4;
	background-size: 100% 100%;
	background-repeat: no-repeat;
	overflow-y: hidden;
}
</style>

</head>
<body>
	<div id="errMsg">
		<p class="lead text-muted">${errorMsg}</p>
	</div>

	<div id="back-btn"></div>

</body>
</html>