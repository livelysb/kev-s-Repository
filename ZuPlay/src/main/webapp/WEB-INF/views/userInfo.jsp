<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="userinfo-window" id="userinfo-player-${playerDTO.playerNickname}">
   <div class="userinfo-header">유저 정보 - ${playerDTO.playerNickname}</div>
   <div class="userinfo-content">
      <div class="panel-body">
         <div class="row">
            <div class="col-md-4 userinfo-row" align="center">
               <img alt="User Pic" src="resources/user.png" class="img-responsive">
               <label class="userinfo-favorite">
                  <c:choose>
                     <c:when test="${isLike}">
                        <span class="glyphicon glyphicon-heart">${likeNum}</span>
                     </c:when>
                     <c:otherwise>
                        <span class="glyphicon glyphicon-heart-empty">${likNum}</span>
                     </c:otherwise>
                  </c:choose>
               </label> 
               <span class="glyphicon glyphicon-king toppad">${playerDTO.playerDailyRank}</span>
               <span class="glyphicon glyphicon-queen">${playerDTO.playerSeasonRank}</span>
            </div>
            <div class="col-md-8">
               <table class="table table-user-information">
                  <tbody>
                     <tr>
                        <th>닉네임</th>
                        <td>
                           <c:choose>
                              <c:when test="${isOn}">
                                 <span class="userInfo-online">${playerDTO.playerNickname}</span>
                              </c:when>
                              <c:otherwise>
                                 <span class="userInfo-offline">${playerDTO.playerNickname}</span>
                              </c:otherwise>
                           </c:choose>
                        </td>
                     </tr>
                     <tr>
                        <th>성별</th>
                        <td>${playerDTO.playerGender}</td>
                     </tr>
                     <tr>
                        <th>마지막 접속일</th>
                        <td>${playerDTO.playerLastAccess}</td>
                     </tr>
                     <tr>
                        <th>연령대</th>
                        <td>${playerDTO.playerAge}</td>
                     </tr>
                     <tr>
                        <th>수익률</th>
                        <td><fmt:formatNumber value="${playerDTO.earningRate}" maxFractionDigits="6"/>%</td>
                     </tr>
                     <tr>
                        <th>루비</th>
                        <td><fmt:formatNumber value="${playerDTO.playerRuby}"/></td>
                     </tr>
                     <tr>
                        <th>현금</th>
                        <td><fmt:formatNumber value="${playerDTO.playerMoney}"/></td>
                     </tr>
                     <tr>
                        <th>총 자산</th>
                        <td><fmt:formatNumber value="${playerDTO.totalMoney}"/></td>
                     </tr>
                  </tbody>
               </table>
            </div>
         </div>
      </div>
   </div>
</div>