<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<div id="chatroom-window">
   <div>Chat</div>
   <div id="chatroom-content">
      <ul class="nav nav-tabs" id="chatlist-tab">
         <li class="active"><a data-target="#chatroom-mychat" data-toggle="tab">내 채팅</a></li>
         <li><a data-target="#chatroom-list" data-toggle="tab">채팅방</a></li>
      </ul>
      <div class="tab-content">
         <div class="tab-pane active" id="chatroom-mychat">
            <ul id="chatroom-mychat-ul">
               
            </ul>
         </div>
         <div class="tab-pane" id="chatroom-list">
            <button id="chatroom-create-btn btn btn-success">채팅방 생성</button>
            <ul id="chatroom-list-ul">
            </ul>
            <div id="chatroom-create-modal" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Confirmation</h4>
                        </div>
                        <div class="modal-body">
                              <input type="text" id="chat-create-name" value="" placeholder="채팅방 이름"></input>
                              <input type="text" id="chat-create-pwd" value="" placeholder="패스워드"></input>
                              <input type="text" id="chat-create-max" value="" placeholder="최대 인원"></input>
                        </div>
                        
                        <div class="modal-footer">
                              <button type="button" class="btn btn-success" id="chat-create-confirm">생성</button>
                              <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                        </div>
                        
                    </div>
                </div>
            </div>
         </div>
      </div>
   </div>
</div>