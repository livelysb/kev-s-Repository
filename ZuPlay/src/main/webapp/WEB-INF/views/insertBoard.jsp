<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Board</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/jquery-ui.min.css" rel="stylesheet">
<link href="resources/css/zuplay.css" rel="stylesheet">

<style type="text/css">
	/* #tabBtn {text-align: right} */
</style>
</head>

<body>
	<table class="table table-striped table-hover" >
		<thead>
			<tr>
				<th style="border-bottom-color : white; padding-left : 20px" colspan="4">안녕하세요<%-- ${goods.boardTitle} --%></th>
			</tr>
			<tr style = "font-size: xx-small;">
				<th style = "padding-left : 20px;">문정하</th>
				<th>조회 3</th>
				<th>좋아요 5</th>
				<th>16.06.28 10:05</th>
			</tr>
		</thead>
			<tr>
				<td style = "padding-left: 20px" colspan="4">안녕하세요<p>오늘 날씨가 너무 좋네요^^<p>야호</td>
			</tr>
		</tbody>
	</table>
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>

<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/zuplay.js"></script>


</html>