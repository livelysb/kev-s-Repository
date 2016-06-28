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
	<div>
		
		<div style="width:100%" class="container">
			<div id="myTabContent" class="tab-content">
				<div role="tabpanel" class="tab-pane fade active in" id="home"
					aria-labelledby="buyTab">
					<div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr class="bg-primary">
									<th width="5%">No</th>
									<th width = "50%">제 목 <span class="caret"></span></th>
									<th>작성자 <span class="caret"></span></th>
									<th>좋아요 <span class="caret"></span></th>
									<th>작성일 <span class="caret"></span></th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody id=selectAll>
								<c:forEach var="BoardDTO" items="${list}">
								<tr>
									<td>${BoardDTO.boardNo}</td>
									<td><a href="">${BoardDTO.boardTitle}</a></td>
									<td>${BoardDTO.playerNickname}</td>
									<td>${BoardDTO.boardLike}</td>
									<td>${BoardDTO.boardTime}</td>
									<td>${BoardDTO.boardHits}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
						<button type="button" class="btn btn-primary" id="backBtn" >이전</button>
						<button type="button" class="btn btn-primary" id="nextBtn" >다음</button>
					</div>
				</div>
				<br>
				<a href = "<c:url value='insertBoard'/>"><button type="button" id="insertBoard" class="btn btn-primary">등록하기</button></a>
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


</html> 








