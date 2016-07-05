var userInfo = {
	nickName : "",
	gender : "",
	theme : "kokomo",
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
	content : "body",
};

/*웹소켓*/
function connect() {
	ws = new WebSocket('ws://127.0.0.1:8000/zuplay/echo/test');
	ws.onopen = function() {
		console.log('websocket opened');
		var open = "open#/fuckWebSocket/#" + userInfo.nickName
				+ "#/fuckWebSocket/#null";
		ws.send(open);
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
