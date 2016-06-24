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
	#sortable { list-style-type: none; margin: 0; padding: 0; width: 450px; }
  	#sortable div {border:solid 2px black; margin: 3px 3px 3px 0; padding: 1px; float: left; width: 100px; height: 90px; font-size: 4em; text-align: center; }
  	.closet {border:solid 2px black; margin: 3px 3px 3px 0; padding: 1px; float: left; width: 100px; height: 90px; text-align: center; } 
  	
  /* 	.draggable-4 { 
            width: 90px; height: 50px; padding: 0.5em; float: left;
            margin: 0px 5px 10px 0;
            border: 1px solid red;  
         }
         .droppable-7 { 
            width: 100px; height: 90px;padding: 0.5em; float: left; 
            margin: 10px; 
            border: 1px solid black; 
         }
         .droppable.active { 
         } */
  	
  	
</style>
</head>

<body>
	<div class="col-xs-12" style="border: 2px red solid; height: 100%" id="">
		<div class="row" style="height: 100%">
			<div class="col-xs-6"
				style="height: 100%; border: 2px yellow solid;">
				<div style="border: 2px blue solid; height: 60%" id="invenCharacter">
					
				</div>
				<div style="border: 2px black solid; height: 40%" class="dropable">
					<div class="closet droppable-7 " title="head">head</div>
					<div class="closet droppable-7 " title="body">body</div>
					<div class="closet droppable-7 " title="eyes">eyes</div>
					<div class="closet droppable-7 " title="mouse">mouse</div>
					<div class="closet droppable-7 " title="earring">earring</div>
					<div class="closet droppable-7 " title="acc">acc</div>
				</div>
			</div>
				<div>
					<div class="col-xs-6" id="sortable">
						
						<div class="ui-state-default"> <img src="resources/img/avatar/body/clothes-10.png" style="width:100%; height:100%;" id="body"/></div>		
						<div class="ui-state-default"> <img src="resources/img/avatar/body/clothes-10.png" style="width:100%; height:100%;" id="body"/></div>
						<div class="ui-state-default"> <img src="resources/img/avatar/body/clothes-10.png" style="width:100%; height:100%;" id="body"/></div>
						<div class="ui-state-default"> <img src="resources/img/avatar/body/clothes-10.png" style="width:100%; height:100%;" id="body"/></div>
						<div class="ui-state-default"> <img src="resources/img/avatar/body/clothes-10.png" style="width:100%; height:100%;" id="body"/></div>
						<div class="ui-state-default"> <img src="resources/img/avatar/body/clothes-10.png" style="width:100%; height:100%;" id="body"/></div>
						<div class="ui-state-default"> <img src="resources/img/avatar/body/clothes-10.png" style="width:100%; height:100%;" id="body"/></div>
						<div class="ui-state-default"> <img src="resources/img/avatar/body/clothes-10.png" style="width:100%; height:100%;" id="body"/></div>
						<div class="ui-state-default"> <img src="resources/img/avatar/body/clothes-10.png" style="width:100%; height:100%;" id="body"/></div>
						<div class="ui-state-default"> <img src="resources/img/avatar/body/clothes-10.png" style="width:100%; height:100%;" id="body"/></div>
						<div class="ui-state-default"> </div>
						<div class="ui-state-default"> </div>
						<div class="ui-state-default"> </div>
						<div class="ui-state-default"> </div>
						<div class="ui-state-default"> </div>
						<div class="ui-state-default"> </div>
						<div class="ui-state-default"> </div>
						<div class="ui-state-default"> </div>
						<div class="ui-state-default"> </div>
						<div class="ui-state-default"> </div>
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

	//인벤토리 보유아이템 조회
	$(function() {
	    $( "#sortable" ).sortable();
	    $( "#sortable" ).disableSelection();
	    
	     $.ajax({
			url: "playerItemSelectAll" ,
			type:"post",
			dataType:"json",  
			success:function(data){
				console.log(data);
				invenUrl="";
				$.each(data, function(index, item){
					invenUrl+="<div class='ui-state-default'> <img src='" + item.itemDTO.itemImg +"' style='width:100%; height:100%;'/></div>";
				})
				
				//$("#sortable").html(invenUrl);
					
				} ,
			error:function(err){
				alert(err +"에러발생");
			}
		})  
		
         $('.droppable-7').droppable({
       	  
            hoverClass: 'active',
            drop: function(e, ui) {
            	
            	var droptitle = $(this).attr("title");
            	alert(droptitle);
				var drophtml = $(this).html();
				var dragid = ui.draggable.attr("id");
				alert(dragid);
				if( dragid == droptitle ) {
					ui.draggable.draggable( "option", "revert", false );
					var droppableOffset = $(this).offset();
					var x = droppableOffset.left + 1;
					var y = droppableOffset.top + 1;
					ui.draggable.offset({ top: y, left: x });
				}
            	
               $(this).html(ui.draggable.remove().html());
               $(this).droppable('destroy');
               $( this )
               .addClass( "ui-state-highlight" )
               .find( "p" )
            }
         
         });
	     
	  });
</script> 
</html> 








