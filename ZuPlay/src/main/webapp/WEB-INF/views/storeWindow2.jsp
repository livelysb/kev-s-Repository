<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="typeAry">randombox,hair,clothes,eyes,mouse,earring,acc</c:set>
<div id="store-window">
<div id="store-header">상점</div>
	<div id="store-content" class="container">
	<div class="row">
        <div class="col-lg-5 col-md-5 col-sm-8 col-xs-9 bhoechie-tab-container">
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu">
              <div class="list-group">
                <a href="#" class="list-group-item text-center active" id="all">All</a>
                <a href="#" class="list-group-item text-center" id="randombox">RandomBox</a>
                <a href="#" class="list-group-item text-center" id="hair">Hair</a>
                 <a href="#" class="list-group-item text-center" id="clothes">Clothes</a>
                <a href="#" class="list-group-item text-center" id="eyes">Eyes</a>
                <a href="#" class="list-group-item text-center" id="mouse">Mouse</a>
                <a href="#" class="list-group-item text-center" id="earring">Earring </a>
                <a href="#" class="list-group-item text-center" id="acc">Acc</a>
              </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab">
                <div class="bhoechie-tab-content active">
					<div class="itemBox" id="itemall0"></div>
					<div class="itemBox" id="itemall1"></div>
					<div class="itemBox" id="itemall2"></div>
					<div class="itemBox" id="itemall3"></div>
					<div class="itemBox" id="itemall4"> </div>
					<div class="itemBox" id="itemall5"></div>
					<div class="itemBox" id="itemall6"></div>
					<div class="itemBox" id="itemall7"></div>
                </div>
                
                
               <c:forEach var="result" items="${typeAry}">
	               		<div class="bhoechie-tab-content">
		               		<c:forEach var="count"  begin="0" end="7" step="1">
								<div class="itemBox" id="item${result}${count}"></div>
							</c:forEach>
		                </div>
               </c:forEach>
               
    				 <button type="button" id="backAllBtn" class="backBtn">이전</button>
                	<button type="button" id="nextAlltBtn" class="nextBtn">다음</button> 
            </div>
        </div>
  </div>
</div>
</div>