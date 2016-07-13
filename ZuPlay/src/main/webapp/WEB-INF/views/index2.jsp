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
<link href="resources/css/font-awesome.min.css" rel="stylesheet" />
<link href="resources/css/jqwidgets/jqx.base.css" rel="stylesheet" />
<link href="resources/css/jqwidgets/jqx.kokomo.css" rel="stylesheet" />
<c:if test="${theme ne 'kokomo'}">
   <link href="resources/css/jqwidgets/jqx.${theme}.css" rel="stylesheet" />
</c:if>
<link href="resources/css/jquery-ui/jquery-ui.css" rel="stylesheet" />
<link href="resources/css/style.css" rel="stylesheet" />

</head>
<body class='default'>
<img src="resources/img/loading.gif" id="loading-content">

  <!-- 컨테이너 시작 -->
  <div class="container-fluid main-container test">
    <div class="row-fluid">

      <!-- 사이드 섹션 시작 -->
      <div class="col-md-1 side-section">
        <!-- 사이드 아바타 선언 -->
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
        <!-- 사이드 아바타 종료 -->
      </div>
      <!-- 사이드 섹션 종료 -->

      <!-- 메인 섹션 시작 -->
      <div class="col-md-11 main-section">

        <!-- 메인 영역 시작 -->
        <div class="row-fluid main-row" id="main">
            <div class="col-md-12 main-area">
            
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
            </div>
        </div>
        <!-- 메인 영역 종료 -->

        <!-- 푸터 섹션 시작 -->
        <div class="row-fluid footer-section">

          <!-- 채팅 영역 시작 -->
          <div class="col-md-5 chat-area">   
            Chat Area
          </div>
          <!-- 채팅 영역 종료 -->

          <!-- 툴바 영역 시작 -->
          <div class="col-md-7 toolbar-area">

            <!-- 버튼 영역 시작 -->
            <div class="row-fluid buttons-row">
              <div class="col-md-12 buttons-area">
               <button id="logout">로그아웃</button>
               <button id="rta-btn">실시간 주가 정보</button>
               <button id="inven-btn">인벤토리</button>
               <button id="stockList-btn">주가 보기</button>
               <button id="store-btn">상점</button>
               <button id="friend-btn">친구</button>
               <button id="financial-btn">용어사전</button>
               <button id="auction-btn">경매장</button>
               <button id="myinfo-btn">내 정보</button>
               <button id="mystock-btn">내 주식</button>
               <button id="news-search-btn">뉴스 검색</button>
               <button id="setting-btn">설정</button>
               <button id="chatroom-btn">채팅방</button>
               <button id="ranking-btn">랭킹</button>
               <!--Notifications-->
               <button id="friend-request-btn">노티파이</button>
                  <div id="friend-request-noti">
                      <span id="noti-msg">
                      </span>   
                     </div> 
               
              </div>
            </div>
            <!-- 버튼 영역 종료 -->

            <!-- 스테이터스 영역 시작 -->
            <div class="row-fluid status-row">
              <div class="col-md-12 status-area">
                Status Area
              </div>
            </div>
            <!-- 스테이터스 영역 종료 -->

          </div>
          <!-- 툴바 영역 종료 -->


        </div>
        <!-- 푸터 섹션 종료 -->

      </div>
      <!-- 메인 섹션 종료 -->

    </div>
  </div>
  <!-- 컨테이너 종료 -->

   
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
<script type="text/javascript" src="resources/js/jquery.bootpag.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="resources/js/highcharts.js"></script>
<script src="resources/js/set.js"></script>
<script type="text/javascript">
    userInfo.theme = "${theme}"; 
</script>
<script src="resources/js/script2.js"></script>
</html>