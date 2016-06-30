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
  <textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea>
  <br/>
  <input id="inputMessage" type="text"/>
  <input type="submit" value="send" onclick="send()" />
  </body>
  <script type="text/javascript">
    var textarea = document.getElementById("messageWindow");
   // var webSocket = new WebSocket('ws://localhost:8000/controller/server');
   var webSocket = new WebSocket('ws://localhost:8000/zuplay/echo');
    var inputMessage = document.getElementById('inputMessage');
    webSocket.onerror = function(event) {
      onError(event)
    };
    webSocket.onopen = function(event) {
      onOpen(event)
    };
    webSocket.onmessage = function(event) {
      onMessage(event)
    };
    function onMessage(event) {
      textarea.value += "상대 : " + event.data + "\n";
    }
    function onOpen(event) {
      textarea.value += "연결 성공\n";
    }
    function onError(event) {
      alert(event.data);
    }

    function send() {

      var bytes = new ArrayBuffer();

      for (var i = 0; i < inputMessage.value.length; ++i) {
         var c = inputMessage.value.charCodeAt(i);
         while(c) {
          bytes += (c & 0xFF);
           c >>= 8;
        }
      }
      textarea.value += "나 : " + inputMessage.value + "\n";
      webSocket.send(bytes);
      inputMessage.value = "";
    }
  </script>
</html>