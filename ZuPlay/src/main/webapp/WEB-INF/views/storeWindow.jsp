<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="store-window">
	<div id="store-header">상점</div>
	<div id="store-content">
		
		<ul class="nav nav-tabs">
			<li class="active" id="all"><a href="#home">All</a></li>
			<li id="randombox"><a href="#menu1">RandomBox</a></li>
			<li id="hair"><a href="#menu2">Hair</a></li>
			<li id="clothes"><a href="#menu3">Clothes</a></li>
			<li id="eyes"><a href="#menu4">Eyes</a></li>
			<li id="mouse"><a href="#menu5">Mouse</a></li>
			<li id="earring"><a href="#menu6">Earring</a></li>
			<li id="acc"><a href="#menu7">Acc</a></li>
		</ul>
		<div id="store-content-div">
			<div id="store-prev-div">
				<button type="button" id="store-prev-btn" class="btn btn-primary store-prev-btn"><</button>
			</div>
			<div id="store-next-div">
				<button type="button" id="store-next-btn" class="btn btn-primary store-next-btn">></button>
			</div>
					<%-- <div class="store-itemBox" id="store-itemall0"></div>
					<div class="store-itemBox" id="store-itemall1"></div>
					<div class="store-itemBox" id="store-itemall2"></div>
					<div class="store-itemBox" id="store-itemall3"></div>
					<div class="store-itemBox" id="store-itemall4"></div>
					<div class="store-itemBox" id="store-itemall5"></div>
					<div class="store-itemBox" id="store-itemall6"></div>
					<div class="store-itemBox" id="store-itemall7"></div>

				<c:set var="typeAry">randombox,hair,clothes,eyes,mouse,earring,acc</c:set>

				<c:forEach var="result" items="${typeAry}">
					<div class="tab-pane fade">
						<c:forEach var="count" begin="0" end="7" step="1">
							<div class="store-itemBox" id="store-item${result}${count}"></div>
						</c:forEach>
					</div>
				</c:forEach> --%>
		</div>
	</div>
</div>