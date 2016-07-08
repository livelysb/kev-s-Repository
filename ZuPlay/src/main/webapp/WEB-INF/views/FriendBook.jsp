<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<body class="default">
	<div id="friend-window">
		<div id="friend-header">Friend List</div>
		<div id="friend-content">

			<div id="friend-list-que">
				<ul class="list-group" id="friend-list-request">

					<li class="list-group-item title">
						<div class="friend-icon orange"></div> <label>친구요청</label>
					</li>

					<!-- <li href="#" class="list-group-item text-left">
			            <img class="img-thumbnail" src="http://bootdey.com/img/Content/User_for_snippets.png">
			            <div class="friend-icon green"> </div>
			            <label class="name">Peter</label>
			            <input type='hidden' class='requestedFSq' value='"+item.friendSq+"'>
			            <div class="pull-right">
			              <button type="button" class="btn btn-success friend-accept btn-circle">
			                <i class="glyphicon glyphicon-ok"></i>
			              </button>
			              <button type="button" class="btn btn-danger friend-reject btn-circle">
			                <i class="glyphicon glyphicon-remove"></i>
			              </button>
			            </div>
			          </li>
			           -->
				</ul>
			</div>

			<div id="friend-list-group">
				<ul class="list-group" id="friend-list-current">
					<li class="list-group-item title">
						<div class="friend-icon green"></div> <label>친구목록</label>
					</li>
					<li href="#" class="list-group-item text-left">
					
				
			            <div class="friend-avatar-div" > <!-- relative -->
				          <img src="" class="friend-avatar-clothes"> <!-- absolute -->
				          <img src="" class="friend-avatar-hair">
				          <img src="" class="friend-avatar-eyes">
				          <img src="" class="friend-avatar-mouse">
				          <img src="" class="friendavatar-earring">
				          <img src="" class="friend-avatar-acc">
				        </div>
			            
			            <div class="friend-icon green"> </div>
			            <label class="name">Park</label>
			            <div class="pull-right">
			              <button type="button" class="btn btn-default friend-sendBtn ">
			                <i class="glyphicon glyphicon-envelope"></i>
			              </button>
			            </div>
		          </li>
          <!-- <li href="#" class="list-group-item text-left">
            <img class="img-thumbnail" src="http://bootdey.com/img/Content/User_for_snippets.png">
            <div class="friend-icon green"> </div>
            <label class="name">Kim</label>
            <input type='hidden' class='ListFriendFSq' value='"+item.friendSq+"'>;
          </li>

          <li href="#" class="list-group-item text-left">
            <img class="img-thumbnail" src="http://bootdey.com/img/Content/User_for_snippets.png">
            <div class="friend-icon red"> </div>
            <label class="name">Jung</label>
          </li>

          <li href="#" class="list-group-item text-left">
            <img class="img-thumbnail" src="http://bootdey.com/img/Content/User_for_snippets.png">
            <div class="friend-icon red"> </div>
            <label class="name">Moon</label>
          </li>

          <li href="#" class="list-group-item text-left">
            <img class="img-thumbnail"  src="http://bootdey.com/img/Content/user_2.jpg">
            <div class="friend-icon red"> </div>
            <label class="name">Juan guillermo cuadrado</label>
          </li> -->
				</ul>
				<div class="friend-menu">
					<button type="button" class="btn btn-success" data-toggle="modal"
						data-target=".friend-add-modal" id="friend-add-modal-btn">친구추가</button>
					<button type="button" class="btn btn-danger" id="friend-del">친구삭제</button>
				</div>
			</div>
			<!-- 친구 추가 모달 -->
			<div class="modal fade friend-add-modal" tabindex="-1" role="dialog"
				aria-labelledby="mySmallModalLabel" aria-hidden="true">
				<div class="vertical-alignment-helper">
					<div class="modal-dialog vertical-align-center">

						<div class="modal-dialog modal-sm">
							<div class="modal-content">
								<div class="modal-header">
									<h4>
										<div class="modal-title">친구추가</div>
									</h4>
								</div>
								<div class="modal-body">
									<div class="input-group">
										<input type="text" id="friend-add-text" class="form-control"
											placeholder="NickName">
											<span class="input-group-btn">
												<button type="button" id="friend-add-search"
													class="btn btn-success">검색</button>
										</span>
									</div>
									<table class="table table-bordered table-hover"
										id="friend-list-table">
									</table>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
