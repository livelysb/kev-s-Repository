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
	#inven-content>table tr td{width: 80px; height: 107.5px; padding: 0 0 0 0; 
	margine: 0 0 0 0; border:1px black solid; overflow:hidden; white-space: nowrap; display: inline-block;}
	#inven-content>table tr td .item-img{width: 75px; height: 97.5px;}
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
	
	<div id="inven-Window">
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
					<td id="inven-player-1"></td>
					<td id="inven-player-2"></td>
					<td id="inven-player-3"></td>
				</tr>
				<tr>
					<td id="inven-player-4"></td>
					<td id="inven-player-5"></td>
					<td id="inven-player-6"></td>
				</tr>
			</table>
			<div class="vertical-line"></div>
			<table id="inven-items" class="item-socket">
				<tr>
					<td id="inven-player-11" ></td>
					<td id="inven-player-12"></td>
					<td id="inven-player-13"></td>
					<td id="inven-player-14"></td>
				</tr>
				<tr>
					<td id="inven-player-15"></td>
					<td id="inven-player-16"></td>
					<td id="inven-player-17"></td>
					<td id="inven-player-18"></td>
				</tr>
				<tr>
					<td id="inven-player-19"></td>
					<td id="inven-player-20"></td>
					<td id="inven-player-21"></td>
					<td id="inven-player-22"></td>
				</tr>
				<tr>
					<td id="inven-player-23"></td>
					<td id="inven-player-24"></td>
					<td id="inven-player-25"></td>
					<td id="inven-player-26"></td>
				</tr>
				<tr>
					<td id="inven-player-27"></td>
					<td id="inven-player-28"></td>
					<td id="inven-player-29"></td>
					<td id="inven-player-30"></td>
				</tr>
			</table>
			<button type="button"  id="inven-player-saveBtn">저장</button>
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
		
		//버튼클릭했을 때 이벤트 설정
		$.fn.setBtn = function(window){
			$(this).on("click",function(){
				$(window).jqxWindow("show");
			})
		}
		
		var gender = "m";
		var parts = ["clothes","hair","eyes","mouse","earring","acc"];
		
		
		//옮기는 거
    	var con = $(".item-socket td").sortable({
            connectWith: ".item-socket td",
            cursor: "move",
            scroll : false,
            forceHelperSize: true
    	});
    	
    	//옮겼을 때 반응
    	$("#inven-items td").on("sortreceive",function(e,ui){
        	if($(this).children().length>=2){
        		$(ui.sender).sortable("cancel");
        	}
    	});
    	
		//장비칸에 두개 이상이 못 들어가는 것
    	$("#inven-player td").on("sortupdate", function(e,ui){
        	if($(this).children().length>=2){
        		$(ui.sender).sortable("cancel");
        		return;
        	}
        	updateAvatar();
    	})
    	
    	var updateAvatar = function(){
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
    	 
    	
    	//내아이템 목록 조회
    	$.ajax({
    		url:"playerItemSelectAll",
    		type:"post",
    		dataType:"json",
    		success:function(data){
				$.each(data,function(index,item){
					var items = $("<img src='"+item.itemDTO.itemImg+"' class='item-img'>").data("item" , item)
					$("#inven-player-"+item.piIndex).html(items);
				})
    		},
    		error:function(err){
    			alert(err+"에러발생");
    		}
    	})
    	
    	//인덱스 값 파싱
    	function passingJson(){
			var jsonArr = new Array();
			var jsonObj = new Object();
			for(var i=1;i<=30;i++){
				if(i>=7 && i<=10) {continue;}
				var invenPlayerItem = $("#inven-player-"+i).children().data("item");
				
				
				if(typeof(invenPlayerItem)!="undefined"){
					jsonObj.piSq=$("#inven-player-"+i).children().data("item").piSq;
					jsonObj.piIndex=$("#inven-player-"+i).children().data("item").piIndex;
					jsonArr.push(jsonObj)
				}
			}
			return jsonArr;
			
    	}
    	
    	
    	//내 아이템 인덱스 저장
    	$("#inven-player-saveBtn").on("click",function(){
    		var jsonList = passingJson();
    		console.log(JSON.stringify(jsonList));
    		$.ajax({
        		url:"playerItemInsert", 
        		type:"post",
        		data:"itemParam="+(JSON.stringify(jsonList)).toString() ,
        		success:function(data){ 	
        			alert("정상실행")
        		},
        		error:function(err){
        			alert(err+"에러발생");
        		}
        	})
    	})
    	
	}( jQuery ));
	
</script>
</html>