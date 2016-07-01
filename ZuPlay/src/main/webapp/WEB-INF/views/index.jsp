<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<link href="resources/css/jqwidgets/jqx.base.css" rel="stylesheet" />
<link href="resources/css/jqwidgets/jqx.kokomo.css" rel="stylesheet"/>
<link href="resources/css/jquery-ui/jquery-ui.css" rel="stylesheet"/>
<link href="resources/css/style.css" rel="stylesheet" />
</head>
<body class='default'>
	<button id="rta-btn">실시간 주가 정보</button>
	<button id="inven-btn">인벤토리</button>
	
	<div id="rta-Window">
		<div id="rta-Header"><span>실시간 주가 정보</span></div>
			<div id="rta-data">
		</div>
	</div>
	
	<div id="inven-Window">
		<div id="inven-Header">인벤토리</div>
		<div id="inven-content">
			<table id="inven-player" class="item-socket">
				<tr>
					<th colspan="3" id="inven-player-avatar">
						<div id="inven-player-avatar-view">
							<img src="" id="inven-player-clothes" />
							<img src="" id="inven-player-hair" />
							<img src="" id="inven-player-eyes" />
							<img src="" id="inven-player-mouse" />
							<img src="" id="inven-player-earring" />
							<img src="" id="inven-player-acc" />
						</div>
					</th>
				</tr>
				<tr>
					<td id="inven-player-1"></td>
					<td id="inven-player-2"></td>
					<td id="inven-player-3"></td>
				</tr>
				<tr>
					<td id="inven-player-4"></td>
					<td id="inven-player-5"></td>
					<td id="inven-player-6"></td>
				</tr>
			</table>
			<div class="vertical-line"></div>
			<table id="inven-items" class="item-socket">
				<tr>
					<td id="inven-player-11" ></td>
					<td id="inven-player-12"></td>
					<td id="inven-player-13"></td>
					<td id="inven-player-14"></td>
				</tr>
				<tr>
					<td id="inven-player-15"></td>
					<td id="inven-player-16"></td>
					<td id="inven-player-17"></td>
					<td id="inven-player-18"></td>
				</tr>
				<tr>
					<td id="inven-player-19"></td>
					<td id="inven-player-20"></td>
					<td id="inven-player-21"></td>
					<td id="inven-player-22"></td>
				</tr>
				<tr>
					<td id="inven-player-23"></td>
					<td id="inven-player-24"></td>
					<td id="inven-player-25"></td>
					<td id="inven-player-26"></td>
				</tr>
				<tr>
					<td id="inven-player-27"></td>
					<td id="inven-player-28"></td>
					<td id="inven-player-29"></td>
					<td id="inven-player-30"></td>
				</tr>
			</table>
			<button type="button"  id="inven-player-saveBtn">저장</button>
		</div>
	</div>
			<jsp:include page="companyInfo.jsp" />
	
</body>

<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>

<script type="text/javascript" src="resources/js/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxwindow.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxcheckbox.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxscrollbar.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxpanel.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxgrid.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxgrid.selection.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxdata.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxpanel.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxsortable.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxslider.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxnumberinput.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui/jquery-ui.js"></script>

<script src="resources/js/set.js"></script>
<script src="resources/js/script.js"></script>

<script>
	(function ( $ ) {

		
	}( jQuery ));
	
</script>
</html>