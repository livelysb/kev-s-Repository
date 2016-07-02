<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<script>
</script>

</head>

<body class="default">
  <div id="friend-window">
    <div id="friend-header">Friend List</div>
    <div id="friend-content">

      <div id="friend-list-que">
        <ul class="list-group">

          <li class="list-group-item title">
            <div class="friend-icon orange"></div><label>친구요청</label>
          </li>

          <li href="#" class="list-group-item text-left">
            <img class="img-thumbnail" src="http://bootdey.com/img/Content/User_for_snippets.png">
            <div class="friend-icon green"> </div>
            <label class="name">Peter</label>
            <div class="pull-right">
              <button type="button" class="btn btn-success btn-circle">
                <i class="glyphicon glyphicon-ok"></i>
              </button>
              <button type="button" class="btn btn-danger btn-circle">
                <i class="glyphicon glyphicon-remove"></i>
              </button>
            </div>
          </li>

        </ul>
      </div>

      <div id="friend-list-group">
        <ul class="list-group">
          <li class="list-group-item title">
            <div class="friend-icon green"></div><label>친구목록 - 2/5</label>
          </li>
          <li href="#" class="list-group-item text-left">
            <img class="img-thumbnail" src="http://bootdey.com/img/Content/User_for_snippets.png">
            <div class="friend-icon green"> </div>
            <label class="name">Park</label>
            <div class="pull-right">
              <button type="button" class="btn btn-default friend-sendBtn ">
                <i class="glyphicon glyphicon-send"></i>
              </button>
            </div>
          </li>

          <li href="#" class="list-group-item text-left">
            <img class="img-thumbnail" src="http://bootdey.com/img/Content/User_for_snippets.png">
            <div class="friend-icon green"> </div>
            <label class="name">Kim</label>
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
          </li>
        </ul>
        <div class="friend-menu">
          <button type="button" class="btn btn-success">친구추가</button>
          <button type="button" class="btn btn-danger">친구삭제</button>
        </div>
      </div>

    </div>
    </div>
  </div>
</body>
</html>
