<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="resources/img/favicon.ico"/>
<title>ZuPlay</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<link href="resources/css/font-awesome.min.css" rel="stylesheet" />
<link href="resources/css/jqwidgets/jqx.base.css" rel="stylesheet" />
<link href="resources/css/jqwidgets/jqx.kokomo.css" rel="stylesheet" />
<link href="resources/css/dockmenu.css" rel="stylesheet" />

<c:if test="${theme ne 'kokomo'}">
   <link href="resources/css/jqwidgets/jqx.${theme}.css" rel="stylesheet" />
</c:if>
<link href="resources/css/jquery-ui/jquery-ui.css" rel="stylesheet" />
<link href="resources/css/style.css" rel="stylesheet" />
</head>
<body class='default' id="main">
	<img src="resources/img/loading.gif" id="loading-content">
	
	<div id="loader">
	<img src="resources/img/logo_kr.png" id="logo">
		<div class="side-section">
			<div class="side-id">
				<label>${playerNickname}</label>
			</div>
			<div class="side-avatar" > <!-- relative -->
				<img src="" id="side-avatar-player-clothes"> <!-- absolute -->
				<img src="" id="side-avatar-player-hair">
				<img src="" id="side-avatar-player-eyes">
				<img src="" id="side-avatar-player-mouse">
				<img src="" id="side-avatar-player-earring">
				<img src="" id="side-avatar-player-acc">
			</div>
			<div class="side-money side-info">
				<img src="resources/img/money.png" class='pull-left'><label>loading</label>
			</div>
			<div class="side-ruby side-info">
				<img src="resources/img/ruby-small.png" class='pull-left'><label>loading</label>
			</div>
			<input type="hidden" id="index-firstLogin" value="${firstLoginToday}">
		</div>
	      
	    <!-- includes JSP -->
	    <jsp:include page="realTimeListWindow.jsp"/>
	    <jsp:include page="financialTermWindow.jsp"/>
	    <jsp:include page="stockList.jsp" />
	    <jsp:include page="storeWindow.jsp" />
	    <jsp:include page="FriendBook.jsp"/>
	    <jsp:include page="inventory.jsp" />
	    <jsp:include page="auctionWindow.jsp"/>
	    <jsp:include page="myStock.jsp"/>
	    <jsp:include page="newsList.jsp"/>
	    <jsp:include page="settingWindow.jsp"/>
	    <jsp:include page="chatRoom.jsp"/>
	    <jsp:include page="rankingWindow.jsp"/>
	    <jsp:include page="historyWindow.jsp"/>
	    <!-- includes END -->
	    
		<div id="friend-request-noti">
			<span id="noti-msg">
			</span>
		</div>
		
		<div id="menu-bar" class="center-block"></div>
	
	</div>
</body>

    
<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxnotification.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxwindow.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxtooltip.js"></script>
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
<script type="text/javascript" src="resources/js/jqwidgets/jqxpopover.js"></script>
<script type="text/javascript" src="resources/js/jquery.bootpag.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/highcharts.js"></script>
<script type="text/javascript" src="resources/js/dockmenu.min.js"></script>
<script src="resources/js/set.js"></script>
<script type="text/javascript">
    userInfo.theme = "${theme}"; 
    
    $(function(){
    	$("#rta-btn li").append("<div>");
    })
</script>
<script src="resources/js/script.js"></script>
</html>