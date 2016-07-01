<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!doctype html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<link href="resources/css/jqwidgets/jqx.base.css" rel="stylesheet" />
<link href="resources/css/jqwidgets/jqx.kokomo.css" rel="stylesheet" />
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />

<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>

<script type="text/javascript" src="resources/js/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxwindow.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxscrollbar.js"></script>

<style>

#friend-content .list-group-item img {
    height:50px;
    width:50px;
}

#friend-content .list-group .list-group-item{
  padding: 5 5 5 5;
}

#friend-content .list-group{
  margin-bottom: 3;
}

#friend-content .name {
    font-size: 15px;
    color:black;
}

#friend-content .friend-icon{
  height: 10px;
  width: 10px;
  display: inline-block;
  border-radius: 30px;
}

#friend-list-group{
  height: 73%;
}

#friend-content{
  overflow: auto;
}

#friend-list-que{
  max-height: 25%;
  height: auto;
}

#friend-content .list-group .title{
  border:2px solid #DDDDDD;
}

#friend-content .pull-right{
  line-height: 4vh;
}

.title>label{
  font-weight: bold;
  margin-left: 5px;
  margin-right: 5px;
}

#friend-content .friend-sendBtn{
  width: 35px;
  height: 35px;
  padding: 8px 0;
  text-align: center;
  font-size: 18px;
  line-height: 1.33;
  border-radius: 8px;
  margin-top: 7px;
}

.btn-circle {
  width: 30px;
  height: 30px;
  text-align: center;
  padding: 6px 0;
  font-size: 12px;
  line-height: 1.428571429;
  border-radius: 15px;
}
.btn-circle.btn-lg {
  width: 50px;
  height: 50px;
  padding: 10px 16px;
  font-size: 18px;
  line-height: 1.33;
  border-radius: 25px;
}

.btn-circle.btn-xl {
  width: 70px;
  height: 70px;
  padding: 10px 16px;
  font-size: 24px;
  line-height: 1.33;
  border-radius: 35px;
}

#friend-content .friend-menu{
  min-height: 35px;
  height: 5%;
  text-align: center;
}

.green{
  background-color: green;
}

.red{
  background-color: red;
}

.orange{
  background-color: orange;
}


</style>

<script>
  $(function(){
    $("#friend-window").jqxWindow({
      theme:"kokomo",
      width:400,
      maxWidth:400,
      minWidth:400,
      minHeight:400,
      height:"auto",
      maxHeight:900,
      showCollapseButton: true
    });
  });
</script>

</head>

<body class="default">
  <div id="friend-window">
    <div id="friend-header">Friend List</div>
    <div id="friend-content">

      <div id="friend-list-que">
        <ul class="list-group">

          <li class="list-group-item title">
            <div class="friend-icon orange"></div><label>ì¹êµ¬ ìì²­ : 2</label>
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
            <div class="friend-icon green"></div><label>ì¹êµ¬ ëª©ë¡ - 2/3</label>
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
          <button type="button" class="btn btn-success">ì¹êµ¬ ì¶ê°</button>
          <button type="button" class="btn btn-danger">ì¹êµ¬ ì­ì </button>
        </div>
      </div>

    </div>
    </div>
  </div>
</body>
</html>
