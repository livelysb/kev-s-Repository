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

/*웹소켓*/
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
