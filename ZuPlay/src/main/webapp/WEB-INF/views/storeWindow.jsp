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
                <a href="#" class="list-group-item text-center active" id="all">All</a>
                <a href="#" class="list-group-item text-center" id="hair">Hair</a>
                 <a href="#" class="list-group-item text-center" id="clothes">Clothes</a>
                <a href="#" class="list-group-item text-center" id="eyes">Eyes</a>
                <a href="#" class="list-group-item text-center" id="mouse">Mouse</a>
                <a href="#" class="list-group-item text-center" id="earring">Earring </a>
                <a href="#" class="list-group-item text-center" id="acc">Acc</a>
                <a href="#" class="list-group-item text-center" id="etc">Etc</a>
              </div>
            </div>
            <div class="col-lg-9 col-md-9 col-sm-9 col-xs-9 bhoechie-tab">
                <div class="bhoechie-tab-content active">
					<div class="itemBox" id="itemall0"></div>
					<div class="itemBox" id="itemall1"></div>
					<div class="itemBox" id="itemall2"></div>
					<div class="itemBox" id="itemall3"></div>
					<div class="itemBox" id="itemall4"> </div>
					<div class="itemBox" id="itemall5"></div>
					<div class="itemBox" id="itemall6"></div>
					<div class="itemBox" id="itemall7"></div>
                </div>
                <div class="bhoechie-tab-content">
					<div class="itemBox" id="itemhair0"></div>
					<div class="itemBox" id="itemhair1"></div>
					<div class="itemBox" id="itemhair2"></div>
					<div class="itemBox" id="itemhair3"></div>
					<div class="itemBox" id="itemhair4"> </div>
					<div class="itemBox" id="itemhair5"></div>
					<div class="itemBox" id="itemhair6"></div>
					<div class="itemBox" id="itemhair7"></div>          
                </div>
                
                <div class="bhoechie-tab-content">
					<div class="itemBox" id="itemclothes0"></div>
					<div class="itemBox" id="itemclothes1"></div>
					<div class="itemBox" id="itemclothes2"></div>
					<div class="itemBox" id="itemclothes3"></div>
					<div class="itemBox" id="itemclothes4"> </div>
					<div class="itemBox" id="itemclothes5"></div>
					<div class="itemBox" id="itemclothes6"></div>
					<div class="itemBox" id="itemclothes7"></div>   
                </div>
                  <div class="bhoechie-tab-content">
					<div class="itemBox" id="itemeyes0"></div>
					<div class="itemBox" id="itemeyes1"></div>
					<div class="itemBox" id="itemeyes2"></div>
					<div class="itemBox" id="itemeyes3"></div>
					<div class="itemBox" id="itemeyes4"> </div>
					<div class="itemBox" id="itemeyes5"></div>
					<div class="itemBox" id="itemeyes6"></div>
					<div class="itemBox" id="itemeyes7"></div>        
                </div>
                 <div class="bhoechie-tab-content">
					<div class="itemBox" id="itemmouse0"></div>
					<div class="itemBox" id="itemmouse1"></div>
					<div class="itemBox" id="itemmouse2"></div>
					<div class="itemBox" id="itemmouse3"></div>
					<div class="itemBox" id="itemmouse4"> </div>
					<div class="itemBox" id="itemmouse5"></div>
					<div class="itemBox" id="itemmouse6"></div>
					<div class="itemBox" id="itemmouse7"></div>         
                </div>
                <div class="bhoechie-tab-content">
					<div class="itemBox" id="itemearring0"></div>
					<div class="itemBox" id="itemearring1"></div>
					<div class="itemBox" id="itemearring2"></div>
					<div class="itemBox" id="itemearring3"></div>
					<div class="itemBox" id="itemearring4"> </div>
					<div class="itemBox" id="itemearring5"></div>
					<div class="itemBox" id="itemearring6"></div>
					<div class="itemBox" id="itemearring7"></div>
                </div>
                <div class="bhoechie-tab-content">
					<div class="itemBox" id="itemacc0"></div>
					<div class="itemBox" id="itemacc1"></div>
					<div class="itemBox" id="itemacc2"></div>
					<div class="itemBox" id="itemacc3"></div>
					<div class="itemBox" id="itemacc4"> </div>
					<div class="itemBox" id="itemacc5"></div>
					<div class="itemBox" id="itemacc6"></div>
					<div class="itemBox" id="itemacc7"></div>
                </div>
                <div class="bhoechie-tab-content">
					<div class="itemBox" id="itemetc0"></div>
					<div class="itemBox" id="itemetc1"></div>
					<div class="itemBox" id="itemetc2"></div>
					<div class="itemBox" id="itemetc3"></div>
					<div class="itemBox" id="itemetc4"> </div>
					<div class="itemBox" id="itemetc5"></div>
					<div class="itemBox" id="itemetc6"></div>
					<div class="itemBox" id="itemetc7"></div>
                </div>
                
    				 <button type="button" id="backAllBtn" class="backBtn">이전</button>
                	<button type="button" id="nextAlltBtn" class="nextBtn">다음</button> 
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
	var count=1;
	var tabs="";
    var status = "next";
	storeSelect(count)
	
	
	//탭들을 클릭 했을 때 일어나는 이벤트
    $("div.bhoechie-tab-menu>div.list-group>a").click(function(e) {
    	count=1;
        //e.preventDefault();
        $(this).siblings('a.active').removeClass("active");
        $(this).addClass("active");
            	
        var index = $(this).index();

        $("div.bhoechie-tab>div.bhoechie-tab-content").removeClass("active");
        $("div.bhoechie-tab>div.bhoechie-tab-content").eq(index).addClass("active");
        storeSelect(1)
    });
	
    //Body,Head등을 구분해서 파라미터로 넣어주면 거기에 해당되는 것을 뿌려줌
    var shopIndex = 0;
    function storeSelect(page){
    	var itemClass = $(".active").attr("id");
    	
	    $.ajax({
	    	url: "itemStoreSelect" ,
			type:"post",
			dataType:"json",  
			data:"itemClass="+itemClass+"&page="+page,
			success:function(data){
				if(data.length==0){
					count=1;
					if(status == "next"){
						var next = $(".list-group>.active").next().attr("id");
						if(typeof(next) === "undefined"){
							next = $("#all").attr("id");
						}
					}else{
						var next = $(".list-group>.active").prev().attr("id");
						if(typeof(next) === "undefined"){
							next = $("#acc").attr("id");
						}
					}
					$("#"+next).trigger("click")
					return;
				}else{
					count=page;
					$.each(data, function(index, item){
						$("#item"+itemClass+""+index).html("<img src='" + item.itemImg +"' style='width:100%; height:50%;' id='"+item.itemCode+"'/><br>"+item.itemName+"<br>"+item.itemPrice);  //
				})
				}
			},
			error:function(err){
				alert(err +"에러발생");
			}
	    })
	}
    
    //이전버튼
    $(".backBtn").on("click",function(){
    		status = "back";
			storeSelect(count-1)

    })
    
    
    
    //다음버튼
    $(".nextBtn").on("click", function(){
    		status = "next";
			storeSelect(count+1)
    })
    
    
    
    //아이템구매
    $(".itemBox").on("click", function() {
		var itemCode = $(this).children().attr("id");
	
		if(typeof(itemCode)=='undefined') return
		
    	var buyCheck = confirm("구매하시겠습니까?");
    	
    	if(buyCheck==false) return
		
		$.ajax({
	    	url: "itemStoreBuy" ,
			type:"post",
			dataType:"text",  
			data:"quantity=1&itemCode="+itemCode,
			success:function(result){

				switch(result){
					case 1 : alert("구매되었습니다."); break;
					case 2 : alert("인벤토리가 부족합니다."); break;
					case 3 : alert("루비가 부족합니다."); break;
				}
				
			},
			error:function(err){
				alert(err +"에러발생");
			}
	    })
	})
});
</script>
</html>