<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>>
<style>
.margine-top-and-botton{
  margin-top: 30px;
}

.userinfo-window{
  width:450px;
}
.userinfo-row {
	margin-bottom: 14px;
}

.userinfo-row:last-child {
	margin-bottom: 0;
}

.userinfo-favorite .glyphicon{
  font-size: 2.5em;
}

.dropdown-user {
	margin: 13px 0;
	padding: 5px;
	height: 100%;
}

.dropdown-user:hover {
	cursor: pointer;
}

.table-user-information>tbody>tr {
	border-top: 1px solid rgb(221, 221, 221);
}

.table-user-information>tbody>tr:first-child {
	border-top: 0;
}

.table-user-information>tbody>tr>td, .table-user-information>tbody>tr>th {
	border-top: 0;
}

.table-user-information>tbody>tr>th{
  width: 120px;
}

.toppad {
	margin-top: 20px;
}
</style>
<script>
$(document).ready(function() {
  var panels = $('.user-infos');
  var panelsButton = $('.dropdown-user');
  panels.hide();

  //Click dropdown
  panelsButton.click(function() {
      //get data-for attribute
      var dataFor = $(this).attr('data-for');
      var idFor = $(dataFor);

      //current button
      var currentButton = $(this);
      idFor.slideToggle(400, function() {
          //Completed slidetoggle
          if(idFor.is(':visible'))
          {
              currentButton.html('<i class="glyphicon glyphicon-chevron-up text-muted"></i>');
          }
          else
          {
              currentButton.html('<i class="glyphicon glyphicon-chevron-down text-muted"></i>');
          }
      })
  });


  $('[data-toggle="tooltip"]').tooltip();

  $('button').click(function(e) {
      e.preventDefault();
      alert("This is a demo.\n :-)");
  });
});
</script>
  <div class="userinfo-window">
    <div class="userinfo-header">유저 정보 - ${playerDTO.playerNickname}</div>
			<div class="panel panel-info">
					<div class="panel-heading">
					<c:choose>
						<c:when test="${isOn}">
							<span class="userInfo-online"></span>
						</c:when>
						<c:otherwise>
							<span class="userInfo-offline"></span>
						</c:otherwise>
					</c:choose>
						<h3 class="panel-title">${playerDTO.playerNickname}</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 userinfo-row" align="center">
								<img alt="User Pic" src="resources/user.png" class="img-responsive">
                <label class="userinfo-favorite">
                  <c:choose>
                  	<c:when test="${isLike}">
                  		<span class="glyphicon glyphicon-heart">
                  	</c:when>
                  	<c:otherwise>
                  		<span class="glyphicon glyphicon-heart-empty">
                  	</c:otherwise>
                  </c:choose>
                  ${likeNum}</span>
                </label>
                <label class="toppad"><span class="glyphicon glyphicon-king"></span>${playerDTO.playerDailyRank}</label>
                <label><span class="glyphicon glyphicon-queen"></span>${playerDTO.playerSeasonRank}</label>
              </div>

							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<th>닉네임</th>
											<td>${playerDTO.playerNickname}</td>
										</tr>
                    <tr>
                      <th>성별</th>
                      <td>${playerDTO.playerGender}</td>
                    </tr>
										<tr>
											<th>마지막 접속일:</th>
											<td>${playerDTO.playerLastAccess}</td>
										</tr>
										<tr>
											<th>연령대</th>
											<td>${playerDTO.playerAge}</td>
										</tr>
                    <tr>
                      <th>수익률</th>
                      <td>${playerDTO.earningRate}%</td>
                    </tr>
                    <tr>
                      <th>루비</th>
                      <td><fmt:formatNumber value="${playerDTO.playerRuby}" /></td>
                    </tr>
                    <tr>
										<th>사이버 머니</th>
										<td>(현금 : ${playerDTO.playerMoney})
                    <br>(총자산 : ${playerDTO.totalMoney})
										</td>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary">
              <i class="glyphicon glyphicon-envelope"></i>
            </a>
              <span class="pull-right">
              <a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning">
                <i class="glyphicon glyphicon-edit"></i>
              </a>
              <a data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger">
                <i class="glyphicon glyphicon-remove"></i>
              </a>
						</span>
					</div>

				</div>
      </div>
