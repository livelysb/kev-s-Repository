userInfo = {
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
	defaultCloset : ["_clothes_00.png", "_hair_00.png", "a_eyes_00.png", "a_mouse_00.png", "empty.png", "empty.png"],
	page : [],
	chat : [],
	content : "body",
};

/*웹소켓*/
function connect(callBack) {
	ws = new WebSocket('ws://127.0.0.1:8000/zuplay/echo/test');
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
