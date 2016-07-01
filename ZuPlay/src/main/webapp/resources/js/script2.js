$(function() {

   $.fn.windowDialog = function(options) {
			  var parentEle = options.parent;
		      var ele = $(this);
		      var title = $(this).attr("title");
		      var contentWidth = parseInt($(this).css("width"));
		      var contentSize = parseInt($(this).css("height"));
		      var context = $("<div class='ui-widget-content'></div>").css({
		         "width" : contentWidth,
		         "height" : contentSize
		      });
		      var headerSize = 35;
		
		      /* Set Header */
		      var header = $("<div class='ui-widget-header ui-corner-all'></div>");
		      var closeBtn = $("<a href='#'><span class='glyphicon glyphicon-remove' title='닫기'></span></a>").css("font-size", headerSize - 10 + "px");
		      var minBtn = $("<a href='#'><span class='glyphicon glyphicon-minus' title='최소화'></span></a>").css("font-size", headerSize - 10 + "px");
		      var tools = $("<div></div>").css({float : "right",lineHeight : headerSize + "px"}).append(minBtn).append(closeBtn);
		
		      $(header).css({
		         width : "100%",
		         height : headerSize,
		         lineHeight : headerSize + "px"
		      }).append($(tools));
		
		      if (typeof title !== undefined) {
		         var titleBar = $("<p>" + title + "</p>").css({
		            "margin-bottom" : "0",
		            "text-align" : "center",
		            "font-size" : headerSize - 10 + "px"
		         });
		         $(header).append($(titleBar));
		         $(this).removeAttr("title");
		      }
		      $(context).append($(header));
		
		      /* Set Content */
		      var content = $("<div></div>").css({
		         "position" : "relative",
		         "overflow" : "auto"
		      }).wrapInner($(this)).css({
		         "width" : "100%",
		         "height" : contentWidth - headerSize
		      });
		      $(this).css({
		         "width" : "100%",
		         "height" : " 100%",
		         "display" : "inlineBlock"
		      });
		      $(context).append($(content));
		
		      /* Set Button */
		      $(closeBtn).on("click", function() {
		         $(context).hide("scale", {
		            percent : 0
		         }, 250, function() {
		        	 if(closeOption === "hide"){
		        		 $(context).hide();
		        	 }
		        	 else{
		        		 $(context).remove();
		        	 }
		         });
		      });
		
		      preHeight: parseInt($(context).css("height"));
		
		      $(minBtn).on("click", function() {
		         var currentHeight = parseInt($(context).css("height"));
		         if (currentHeight > headerSize + 2) {
		            preHeight = currentHeight;
		            $(context).resizable("disable").animate({height : headerSize + 2}, function() {
		               $(content).hide();
		            });
		         } else {
		            $(content).show();
		            $(context).resizable("enable").animate({height : preHeight});
		         }
		      })
		
		      /* Set Context */
		
		      containment = typeof parentEle !== undefined ? parentEle : false;
		      $(context).draggable({
		         "handle" : $(header),
		         "containment" : parentEle,
		         "scroll" : !containment
		      });
		
		      $(context).resizable({
		         "containment" : parentEle,
		         "autoHide" : true,
		         "minWidth" : 150,
		         "minHeight" : headerSize + 2,
		         "resize" : function() {
		            $(content).css("height", parseInt($(this).css("height")) - headerSize);
		         }
		      });
		
		      $(parentEle).append($(context));
		      
		      if(options.init === "hide")
		    	  $(context).hide();
		      return this;
		   }
   
   /* 실시간 주가 정보 */
   $("#zp-show-stock").on("click", function(){
	   $(".zp-wrapper-stock-current").windowDialog({parent : ".zp-wrapper-screen", "closeOption" : "close", "init":"open"});

   })
   
  
	var stockPage = 1;
	var getRealTimeStock = function(){
	   $.ajax({
	       url:'realTimeStock',
	       type:'post',
	       dataType:'json',
	       contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	       data: {"page":stockPage},
	       success:function(data){
	    	   
	    	   console.log(stockPage);
	    	   console.log(data);
	    	   stockPage++;
	    	   var tbd = $(".zp-wrapper-stock-current tbody").empty();
	    	   $(data).each(function(index, item) {
	    		   $(tbd).append("<tr> <td>"+item.isuKorAbbrv+"</td> <td>"+item.priceDTO.cmpprevddPrc+"</td> <td>"+item.priceDTO.fluctuationRate+"</td> <td>"+item.priceDTO.trdvol+"</td> </tr>")
	    	   });
	       },
	       error:function(e){
	    	   console.log(stockPage);
	    	   console.log("error" + e);
	       }
	    });
	};
	
	setInterval(getRealTimeStock, 1000);
	
})