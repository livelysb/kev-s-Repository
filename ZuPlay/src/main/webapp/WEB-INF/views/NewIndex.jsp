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
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<link href="resources/css/jqwidgets/jqx.base.css" rel="stylesheet" />
<link href="resources/css/jquery-ui/jquery-ui.css" rel="stylesheet"/>
<link href="resources/css/newStyle.css" rel="stylesheet" />
<style>
#inven-content{overflow: hidden;}
	#inven-content>table tr td{width: 80px; height: 80px; padding: 0 0 0 0; 
	margine: 0 0 0 0; border:1px black solid; overflow:hidden; white-space: nowrap; display: inline-block;}
	#inven-content>table tr td .item-img{width: 75px; height: 75px;}
	#inven-player-avatar{width: 240px; height: 240px;}
	#inven-content>table{table-layout: fixed; border-spacing:100px;}
	#inven-player{float: left;}
	div.vertical-line{
	  width: 2px;
	  background-color: black;
	  height: 100%;
	  float: left;
	  margin-right: 10px;
	  margin-left: 10px;
	}
	#inven-player-avatar-view{position: relative; width:100%; height:100%}
	#inven-player-avatar img{position: absolute; height: 100%; width: 100%}
</style>
</head>
<body class='default'>
	<button id="rta-btn">실시간 주가 정보</button>
	<button id="inven-btn">인벤토리</button>
	
	<div id="rta-Window">
		<div id="rta-Header"><span>실시간 주가 정보</span></div>
			<div id="rta-data">
		</div>
	</div>
	
	<div id="inven-Window" >
		<div id="inven-Header">인벤토리</div>
		<div id="inven-content">
			<table id="inven-player" class="item-socket">
				<tr>
					<th colspan="3" id="inven-player-avatar">
						<div id="inven-player-avatar-view">
							<img src="" id="inven-player-clothes" />
							<img src="" id="inven-player-hair" />
							<img src="" id="inven-player-eyes" />
							<img src="" id="inven-player-mouse" />
							<img src="" id="inven-player-earring" />
							<img src="" id="inven-player-acc" />
						</div>
					</th>
				</tr>
				<tr>
					<td id="inven-socket-colthes"></td>
					<td id="inven-socket-hair"></td>
					<td id="inven-socket-eyes"></td>
				</tr>
				<tr>
					<td id="inven-socket-mouse"></td>
					<td id="inven-socket-earring"></td>
					<td id="inven-socket-acc"></td>
				</tr>
			</table>
			<div class="vertical-line"></div>
			<table id="inven-items" class="item-socket">
				<tr>
					<td><img src="resources/img/avatar/acc/acc-02.png" class="item-img"></td>
					<td><img src="resources/img/avatar/eyes/eyes-02.png" class="item-img"></td>
					<td><img src="resources/img/avatar/hair/hair-09.png" class="item-img"></td>
					<td><img src="resources/img/avatar/clothes/clothes-05.png" class="item-img"></td>
				</tr>
				<tr>
					<td><img src="resources/img/avatar/earring/earring-02.png" class="item-img"></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
	</div>
	
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxwindow.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxcheckbox.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxscrollbar.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxpanel.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxgrid.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxgrid.selection.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxdata.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxpanel.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxsortable.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui/jquery-ui.js"></script>

<script src="resources/js/newScript.js"></script>

<script>
	(function ( $ ) {
		
		$.fn.setBtn = function(window){
			$(this).on("click",function(){
				$(window).jqxWindow("show");
			})
		}

		var gender = "m";
		var parts = ["clothes","hair","eyes","mouse","earring","acc"];

    	var con = $(".item-socket td").sortable({
            connectWith: ".item-socket td",
            cursor: "move",
            scroll : false,
            forceHelperSize: true
    	});
    	
    	
    	$("#inven-items td").on("sortreceive",function(e,ui){
        	if($(this).children().length>=2){
        		$(ui.sender).sortable("cancel");
        	}
    	});
    	
    	$("#inven-player td").on("sortupdate", function(e,ui){
        	if($(this).children().length>=2){
        		$(ui.sender).sortable("cancel");
        		return;
        	}
        	updateAvatar();
    	})
    	
    	var updateAvatar = function(gd){
    		for(var i=0; i<parts.length; i++){
    			var partSrc = $("#inven-socket-"+parts[i]+">.item-img").attr("src");
    			if(partSrc === "" || typeof(partSrc) === "undefined"){
    				$("#inven-player-"+parts[i]).attr("src","resources/img/avatar/"+parts[i]+"/"+parts[i]+"-01.png");
    			}else{
    				$("#inven-player-"+parts[i]).attr("src", partSrc);
    			}
    		}
    	}
    	updateAvatar();
    	
    	$("#inven-Window").jqxWindow({
            minWidth:600,
            minHeight:420,
            resizable:false,
            showCollapseButton: true,
            autoOpen:false
          });
    	
    	
    	$("#inven-btn").setBtn($("#inven-Window"));
    	$("#rta-btn").setBtn($("#rta-Window"));
    	
/*      	$("#inven-btn").click(function(e){
    		$("#inven-Window").jqxWindow("show");
    	}); */
    	 
	}( jQuery ));
</script>
</html>