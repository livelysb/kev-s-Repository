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
<link href="resources/css/jquery-ui/jquery-ui.css" rel="stylesheet">

<style type="text/css">
	/*----- Accordion -----*/
.accordion, .accordion * {
    -webkit-box-sizing:border-box; 
    -moz-box-sizing:border-box; 
    box-sizing:border-box;
}
 
.accordion {
    overflow:hidden;
    box-shadow:0px 1px 3px rgba(0,0,0,0.25);
    border-radius:3px;
    background:#f7f7f7;
}
 
/*----- Section Titles -----*/
.accordion-section-title {
    width:100%;
    padding:15px;
    display:inline-block;
    border-bottom:1px solid #1a1a1a;
    background:#333;
    transition:all linear 0.15s;
    /* Type */
    font-size:1.200em;
    text-shadow:0px 1px 0px #1a1a1a;
    color:#fff;
}
 
.accordion-section-title.active, .accordion-section-title:hover {
    background:#4c4c4c;
    /* Type */
    text-decoration:none;
}
 
.accordion-section:last-child .accordion-section-title {
    border-bottom:none;
}
  
/*----- Section Content -----*/
.accordion-section-content {
    padding:15px;
    display:none;
}/*----- Accordion -----*/
.accordion, .accordion * {
    -webkit-box-sizing:border-box; 
    -moz-box-sizing:border-box; 
    box-sizing:border-box;
}
 
.accordion {
    overflow:hidden;
    box-shadow:0px 1px 3px rgba(0,0,0,0.25);
    border-radius:3px;
    background:#f7f7f7;
}
 
/*----- Section Titles -----*/
.accordion-section-title {
    width:100%;
    padding:15px;
    display:inline-block;
    border-bottom:1px solid #1a1a1a;
    background:#333;
    transition:all linear 0.15s;
    /* Type */ 
    font-size:1.200em;
    text-shadow:0px 1px 0px #1a1a1a;
    color:#fff;
}
 
.accordion-section-title.active, .accordion-section-title:hover {
    background:#4c4c4c;
    /* Type */
    text-decoration:none;
}
 
.accordion-section:last-child .accordion-section-title {
    border-bottom:none;
}
 
/*----- Section Content -----*/
.accordion-section-content {
    padding:15px;
    display:none;
}

</style>
</head>

<body>
	<input type="text" id="stockSearch" name="stockSearch" class="form-control" placeholder="Search">
<div id="accordion">
  <!-- <h3 class="accordion-section-title">제목</h3>
  <div class="accordion-section-content">
    내용
    
  </div> -->
 
</div>
 
  
</body>

	
<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>

<script src="resources/js/jquery-ui/jquery-ui.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function() {
	    $( "#accordion" ).accordion({
	      collapsible: true,
	      active: false
	    });
	    
	    $("#stockSearch").on("keyup",function(){
	    	
	    	var term = $(this).val()
	    	if(event.keyCode == 13) {
	    		alert("엔터클릭!");
	    		if(term =="") return;
	    		
		    	$.ajax({ 
			    	url:"searchFinancialTerm",
			    	type:"get",
					dataType:"xml",
					data:"term="+term,
					success:function(data){
						console.log(data)
						$.each(data,function(index,item){ 
							$("#accordion").append("<h3 class='accordion-section-title'>"+ item.fnceDictNm+"</h3>");
							$("#accordion").append("<div class='accordion-section-content'>"+ item.ksdFnceDictDescContent +"</div>");
						}) 
					},
					error:function(err){
						alert(err+"에러발생")
					}
			    })
	    	}
	    })
	  });
</script>
</html>








