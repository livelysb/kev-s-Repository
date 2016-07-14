userInfo = {
	nickName : "",
	gender : "",
	theme : "",
	money : 0,
	ruby : 0,
	grade : "",
	seasonRank : 0,
	dailyRank : 0,
	friends : {

	}
}
/* 셋팅 정보 */
var setting = {
	parts : [ "clothes", "hair", "eyes", "mouse", "earring", "acc" ],
	page : [],
	chat : {},
	content : "body",
};

/* 웹소켓 */
function connect(callBack) {
	ws = new WebSocket('ws://192.168.0.57/zuplay/echo/test');
	ws.onopen = function() {
		console.log('websocket opened');
		var open = "open#/fuckWebSocket/#" + userInfo.nickName
				+ "#/fuckWebSocket/#null";
		ws.send(open);

		callBack();
	};
	ws.onmessage = function(message) {
		console.log('receive message : ' + message.data);

	};
	ws.onclose = function(event) {
		console.log(event);
		console.log('websocket closed');
	};
}
function disconnect() {
	if (ws) {
		ws.close();
		ws = null;
	}
}
/* 메뉴바 */
$('#menu-bar').dockmenu({
	position : "absolute",
	menuPosition : "bottom",
	buttons : [ {
		'title' : '실시간 주가 정보',
		'id' : "rta-btn",
		'imgURL' : 'resources/img/icons/diagram.png'
	}, {
		'title' : '주가 보기',
		'id' : "stockList-btn",
		'imgURL' : 'resources/img/icons/all_company.png'
	}, {
		'title' : '내 주식',
		'id' : "mystock-btn",
		'imgURL' : 'resources/img/icons/mystock.png'
	}, {
		'title' : '뉴스',
		'id' : "news-search-btn",
		'imgURL' : 'resources/img/icons/news.png'
	}, {
		'title' : '용어사전',
		'id' : "financial-btn",
		'class' : 'button-margin-right',
		'imgURL' : 'resources/img/icons/notepad.png'
	}, {
		'title' : '인벤 토리',
		'id' : "inven-btn",
		'imgURL' : 'resources/img/icons/suitcase.png'
	}, {
		'title' : '상점',
		'id' : "store-btn",
		'imgURL' : 'resources/img/icons/store.png'
	}, {
		'title' : '경매장',
		'id' : "auction-btn",
		'imgURL' : 'resources/img/icons/badge.png'
	}, {
		'title' : '친구',
		'id' : "friend-btn",
		'imgURL' : 'resources/img/icons/users.png'
	}, {
		'title' : '채팅방',
		'id' : "chatroom-btn",
		'imgURL' : 'resources/img/icons/chat.png'
	}, {
		'title' : '랭킹',
		'id' : "ranking-btn",
		'class' : 'button-margin-right',
		'imgURL' : 'resources/img/icons/medal.png'
	}, {
		'title' : '내 정보',
		'id' : "myinfo-btn",
		'imgURL' : 'resources/img/icons/id-card.png'
	}, {
		'title' : '설정',
		'id' : "setting-btn",
		'imgURL' : 'resources/img/icons/cogwheel.png'
	}, {
		'title' : '로그 아웃',
		'id' : "logout",
		'imgURL' : 'resources/img/icons/exit.png'
	} ]
});