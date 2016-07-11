<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="chatroom-window">
	<div>Chat</div>
	<div id="chatroom-content">
		<ul class="nav nav-tabs" id="chatlist-tab">
			<li class="active"><a data-target="#chatroom-mychat"
				data-toggle="tab">내 채팅</a></li>
			<li><a data-target="#chatroom-list" data-toggle="tab">채팅방</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="chatroom-mychat">
				<ul id="#chatroom-mychat">
					
				</ul>
			</div>
			<div class="tab-pane" id="chatroom-list">
				<ul id="chatroom-list-ul">

				</ul>
			</div>
		</div>
	</div>
</div>