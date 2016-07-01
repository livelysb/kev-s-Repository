<%--
  Created by IntelliJ IDEA.
  User: jinho
  Date: 2016-06-22
  Time: 오후 1:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
  <head>
    <title>$Title$</title>
  </head>

  <body>
  <button id="connect">connect</button>
<button id="disconnect" disabled="">disconnect</button>
<input type="text" id="message">
<textarea id="messages" style="width: 100%; height: 200px;" readonly=""></textarea>
<script src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<script type="text/javascript">
    var ws;
    var json = {
    	flag : "",
    	nick : "",
    	data : {
    		test : [ {},{} ]	
    	}
    }
 
    function connect() {
        ws = new WebSocket('ws://127.0.0.1:8000/zuplay/echo/test');
        ws.onopen = function () {
            console.log('websocket opened');
            
        };
        ws.onmessage = function (message) {
            console.log(message);
            console.log('receive message : ' + message.data);
            $('#messages').val($('#messages').val() + message.data + '\n');
            document.getElementById('messages').scrollTop = document.getElementById('messages').scrollHeight;
        };
        ws.onclose = function (event) {
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
 
    $(function () {
        $('#connect').click(function () {
            connect();
            $(this).attr('disabled', true);
            $('#disconnect').removeAttr('disabled');
        });
 
        $('#disconnect').click(function () {
            disconnect();
            $(this).attr('disabled', true);
            $('#connect').removeAttr('disabled');
        });
 
        $('#message').keydown(event, function () {
            if (event.keyCode === 13) {
            	var openJson="open#/fuckWebSocket/#김경원#/fuckWebSocket/#null"
            	//ws.send(openJson);
            	//alert(openJson)
            	ws.send(openJson);
                console.log('전송성공');
            }
        });
    });
</script>
</html>
