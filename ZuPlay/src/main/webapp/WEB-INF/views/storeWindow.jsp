<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/jquery-ui.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">

<style type="text/css">
/*  bhoechie tab */
div.bhoechie-tab-container{
  z-index: 10;
  background-color: #ffffff;
  padding: 0 !important;
  border-radius: 4px;
  -moz-border-radius: 4px;
  border:1px solid #ddd;
  margin-top: 20px;
  margin-left: 50px;
  -webkit-box-shadow: 0 6px 12px rgba(0,0,0,.175);
  box-shadow: 0 6px 12px rgba(0,0,0,.175);
  -moz-box-shadow: 0 6px 12px rgba(0,0,0,.175);
  background-clip: padding-box;
  opacity: 0.97;
  filter: alpha(opacity=97);
}
div.bhoechie-tab-menu{
  padding-right: 0;
  padding-left: 0;
  padding-bottom: 0;
}
div.bhoechie-tab-menu div.list-group{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a{
  margin-bottom: 0;
}
div.bhoechie-tab-menu div.list-group>a .glyphicon,
div.bhoechie-tab-menu div.list-group>a .fa {
  color: #5A55A3;
}
div.bhoechie-tab-menu div.list-group>a:first-child{
  border-top-right-radius: 0;
  -moz-border-top-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a:last-child{
  border-bottom-right-radius: 0;
  -moz-border-bottom-right-radius: 0;
}
div.bhoechie-tab-menu div.list-group>a.active,
div.bhoechie-tab-menu div.list-group>a.active .glyphicon,
div.bhoechie-tab-menu div.list-group>a.active .fa{
  background-color: #5A55A3;
  background-image: #5A55A3;
  color: #ffffff;
}
div.bhoechie-tab-menu div.list-group>a.active:after{
  content: '';
  position: absolute;
  left: 100%;
  top: 50%;
  margin-top: -13px;
  border-left: 0;
  border-bottom: 13px solid transparent;
  border-top: 13px solid transparent;
  border-left: 10px solid #5A55A3;
}

div.bhoechie-tab-content{
  background-color: #ffffff;
  /* border: 1px solid #eeeeee; */
  padding-left: 20px;
  padding-top: 10px;
}

div.bhoechie-tab div.bhoechie-tab-content:not(.active){
  display: none;
}

 .itemBox {border:solid 2px black; margin: 3px 3px 3px 0; padding: 1px; float: left; width: 70px; height: 70px; text-align: center; } 

</style>
</head>

<body>
	<div class="container">
	<div class="row">
        <div class="col-lg-5 col-md-5 col-sm-8 col-xs-9 bhoechie-tab-container">
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 bhoechie-tab-menu">
              <div class="list-group">
                <a href="#" class="list-group-item active text-center">
                    All
                </a>
                <a href="#" class="list-group-item text-center">
                	Head
                </a>
                 <a href="#" class="list-group-item text-center">
                    Body
                </a>
                <a href="#" class="list-group-item text-center">
                    Acc
                </a>
                <a href="#" class="list-group-item text-center">
                    Eyes
                </a>
                <a href="#" class="list-group-item text-center">
                    Mouse
                </a>
                <a href="#" class="list-group-item text-center">
                    Earring
                </a>
              </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab">
                <div class="bhoechie-tab-content active">
					<div class="itemBox" id="itemAll0"></div>
					<div class="itemBox" id="itemAll1"></div>
					<div class="itemBox" id="itemAll2"></div>
					<div class="itemBox" id="itemAll3"></div>
					<div class="itemBox" id="itemAll4"> </div>
					<div class="itemBox" id="itemAll5"></div>
					<div class="itemBox" id="itemAll6"></div>
					<div class="itemBox" id="itemAll7"></div>
                </div>
                <div class="bhoechie-tab-content">
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>               
                </div>
    
                <div class="bhoechie-tab-content">
                	<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
                </div>
                <div class="bhoechie-tab-content">
                	<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
                </div>
                <div class="bhoechie-tab-content">
                	<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
                </div>
                <div class="bhoechie-tab-content">
                	<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
                </div>
                <div class="bhoechie-tab-content">
                	<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
					<div class="itemBox"></div>
                </div>
            </div>
        </div>
  </div>
</div>
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
    $("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
        e.preventDefault();
        $(this).siblings('a.active').removeClass("active");
        $(this).addClass("active");
        var index = $(this).index();
        $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
        $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");
    });
    
    store()
    
    //Body,Head등을 구분해서 파라미터로 넣어주면 거기에 해당되는 것을 뿌려줌
    function store(attr){
	    $.ajax({
	    	url: "store" ,
			type:"post",
			dataType:"json",  
			success:function(data){
				$.each(data, function(index, item){
					$("#"+attr +" "+index).text(item.attr);
				})
			},
			error:function(err){
				alert(err +"에러발생");
			}
	    })
	}
});
</script>
</html>








