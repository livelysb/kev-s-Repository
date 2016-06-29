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
	<div style="padding:30px 50px;">
		<table width="100%" class="table">
		<tr>
			<td colspan="4" style="padding: 9px 30px;">${boardDTO.boardTitle}</td>
		</tr>
		<tr>
			<td style = "padding: 9px 30px;">${boardDTO.playerNick}</td>
			<td>${boardDTO.boardHits}</td>
				<c:choose>
					<c:when test="${boardDTO eq 0}">
						<td>♡${boardDTO.boardLike}</td>
				    </c:when>
			    <c:otherwise>
			    	<td>♥${boardDTO.boardLike}</td>
			    </c:otherwise>
				</c:choose>
			<td>16.06.28 19:43:25</td>
		</tr>
		<tr>
			<td colspan="4">${boardDTO.content}</td>
		</tr>
		</table>
		<div align="right">
			<input type = "button" id = "update" value = "수정">
			<input type = "button" id = "delete" value = "삭제">
		</div>
	</div>
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>

<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/zuplay.js"></script>


</html>