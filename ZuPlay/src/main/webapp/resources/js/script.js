$(function(){
   
   
   /*버튼클릭했을 때 이벤트 설정*/
   $.fn.setBtn = function(window){
      $(this).on("click",function(){
         $(window).jqxWindow("show");
      })
      return this;
   };
   
   /* 실시간 주가 정보 */
      var rtaInit = function(){
    	  	
         $("#rta-Window").jqxWindow({
               width:"400",
               height:"450",
               resizable:true,
               showCollapseButton: true,
               autoOpen:false,
               theme:userInfo.theme
             });
         

     	var stockPage = 1;
     	var getRealTimeStock = function(){
     	   $.ajax({
     	       url:'realTimeStock',
     	       type:'post',
     	       dataType:'json',
     	       contentType:"application/x-www-form-urlencoded; charset=UTF-8",
     	       data: {"page":stockPage,"keyword":"undefined"},
     	       success:function(data){
     	    	   stockPage++;
     	    	   var tbd = $("#rta-tbody").empty();
     	    	   $(data).each(function(index, item) {
     	    		  if(index!=0){
     	    			  $(tbd).append("<tr> <td>"+item.isuKorAbbrv+"</td> <td>"+item.priceDTO.trdPrc+"</td> <td>"+item.priceDTO.cmpprevddPrc+"</td> <td>"+item.priceDTO.fluctuationRate+"</td><td>"+item.priceDTO.trdvol+"</td></tr>")
     	    		  }
     	    	   });
     	       },
     	       error:function(e){
     	    	   console.log(stockPage);
     	    	   console.log("error" + e);
     	       }
     	    });
     	};
     	
     	var realTimeHover = function(){
     		$("#rta-content").hover(
 				function(){
 					clearInterval(getRealTimeStock)
 				},
 				function(){
 					setInterval(getRealTimeStock, 3000);
 				}
     		)
     	}
     	
     	setInterval(getRealTimeStock, 3000);
      }
      
      
      /* 기업 정보 조회 */
      var companyInfo = function(companyId){
              var price = $(companyId + " .company-title-stock").text();
              
             $(companyId).jqxWindow({
                   theme:userInfo.theme,
                   minWidth:700,
                   width:"auto",
                   height:380,
                   showCollapseButton: true,
                   resizable : false
                 });
              
              
              var sellSlider = $(companyId + " .company-buy-slider");
              var buySlider = $(companyId + " .company-sell-slider");
            

             $(companyId + " .company-sell-slider").jqxSlider({
                 width:"100%",
                 showTickLabels: true,
                 tooltip: true,
                 mode: "fixed",
                 min: 0,
                 max: 100,
                 ticksFrequency: 10,
                 value: 0,
                 step: 1,
                 theme : userInfo.theme,
                 tooltipPosition: "far",
                 theme:userInfo.theme
             });
              
             $(companyId + " .company-buy-slider").jqxSlider({
                 width:"100%",
                 showTickLabels: true,
                 tooltip: true,
                 mode: "fixed",
                 min: 0,
                 ticksFrequency: 10,
                 step: 1,
                 theme : userInfo.theme,
                 tooltipPosition: "far",
                 theme:userInfo.theme,
                 max: 100,
                 value: 0
             });
             
             $(document).on("change",companyId + " .company-buy-slider",function(event){
                $(companyId + " .company-buy-value").text(price * event.args.value);
             });
             $(document).on("change",companyId + " .company-sell-slider",function(event){
                $(companyId + " .company-sell-value").text(price * event.args.value);
             });
      }
      

      var invenInit = function(){
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
             for(var i=1; i<=6; i++){
            	
                var partSrc = $("#inven-player-"+i+">.item-img").attr("src");
                
                if(partSrc === "" || typeof(partSrc) === "undefined"){
                   $("#inven-player-"+setting.parts[i-1]).attr("src","resources/img/avatar/"+setting.parts[i-1]+"/m_"+setting.parts[i-1]+"_00.png");
                }else{
                   $("#inven-player-"+setting.parts[i-1]).attr("src", partSrc);
                }
             }
          }
         
          
          
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
          
          $("#inven-Window").jqxWindow({
               minWidth:600,
               minHeight:420,
               resizable:false,
               showCollapseButton: true,
               autoOpen:false,
               theme : userInfo.theme
             });
          
          updateAvatar();

      }
      
      var stockListInit = function(){
         function stockPageSelect(page,keyword){
               $.ajax({  
                  url:"realTimeStock",
                  type:"post",
                  dataType:"json",
                  data:"page="+page+"&keyword="+keyword,
                  success:function(data){
                     str="";
                           
                     $.each(data, function(index,item){
                        if(index==0){
                           if(item.amount%10==0){
                              pagenation(item.amount/10);
                           }else{
                              pagenation(item.amount/10+1)
                           }
                        }else{
                           str+="<tr><td class='stock-select'>"+item.isuKorAbbrv+"</a></td>"
                           str+="<td>"+item.priceDTO.trdPrc +"</td>";
                           str+="<td>"+item.priceDTO.cmpprevddPrc +"</td>";
                           str+="<td>"+item.priceDTO.fluctuationRate +"</td>";
                           str+="<td>"+item.priceDTO.trdvol +"</td>";
                           str+="<td>"+item.priceDTO.opnprc +"</td>";
                           str+="<td>"+item.priceDTO.hgprc +"</td>";
                           str+="<td>"+item.priceDTO.lwprc +"</td>";
                           str+="<input type='hidden' value='"+item.isuCd+"'/></tr>"
                        }
                     })
                     
                     $("#stockListTBody").html(str);
                     $(document).on("click", "#stock-window tr",function(e){
                    	var cd = $(this).find(":hidden").val();
                    	console.log(cd);
                    	showCompanyInfo(cd);
                     })
                  },
                  error:function(err){
                     alert(err+"에러발생");
                  }
               })
            }

            /*마지막 페이지*/
            function pagenation(pageNo){ 
                 $('#page-selection').bootpag({
                     total: pageNo, maxVisible: 10
                 })
             }
            
            /*페이지 클릭*/
            $('#page-selection').on("page", function(event, num){
               
               if($("#stock-search-keyword").val()==""){
                  $("#stock-search-keyword").val("undefined");
               }
                 stockPageSelect(num,$("#stock-search-keyword").val());
              });
            
            /*종목명 클릭 시 상세정보 띄어줌.*/
            var showCompanyInfo = function(code){
                var companyId = "#company-"+code;
                if(setting.page.indexOf(companyId) > -1){
                	$(companyId).jqxWindow("show");
                	return;
                }
                setting.page.push(companyId);
               $.ajax({
                  url:"companyInfo",
                  type:"post",
                  dataType:"html",
                  data:"isuCd="+code,
                  success:function(data){
                     $(setting.content).append(data);
                     companyInfo(companyId);
                  },
                  error:function(err){
                     alert(err+"에러발생");
                  }
               });
            }

            /*검색*/
            $(document).on("keyup","#stock-search",function(){
               if(event.keyCode == 13) {
                  
                  if($(this).val()=="") return;
                  $("#page-selection ul li").eq(1).trigger("click");
                  $("#stock-search-keyword").val($(this).val());
                  stockPageSelect(1,$(this).val());
               }
            })
            stockPageSelect(1);
            
            
         $("#stock-window").jqxWindow({
             width:750,
             height:600,
             resizable:false,
             showCollapseButton: true,
             autoOpen:false,
             theme:userInfo.theme
           });
      
      }
      
      /*상점*/
      var storeInit = function(){
    		var count=1;
    		var tabs="";
    	    var status = "next";

    		
    	    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    	    	  storeSelect(1)
    	    });
    		
    	    //Body,Head등을 구분해서 파라미터로 넣어주면 거기에 해당되는 것을 뿌려줌
    	    function storeSelect(page){
    	    	var itemClass = $("#store-content .active").attr("id");
    		    $.ajax({
    		    	url: "itemStoreSelect" ,
    				type:"post",
    				dataType:"json",  
    				data:"itemClass="+itemClass+"&page="+page,
    				success:function(data){
    					if(data.length==0){
    						count=1;
    						if(status == "next"){
    							var next = $("#store-nav-tabs .active").next().attr("id");
    							if(typeof(next) === "undefined"){
    								next = $("#all").attr("id");
    							}
    						}else{
    							var next = $("#store-nav-tabs .active").prev().attr("id");
    							if(typeof(next) === "undefined"){
    								next = $("#acc").attr("id");
    							}
    						}
    						$('.nav-tabs a[href="#store-tab-' + next + '"]').tab('show');
    						return;
    					}else{
    						
    						count=page;
    						$(".tab-content div img").empty();
    						$.each(data, function(index, item){
    							$("#store-item"+itemClass+""+index).html("<img src='" + item.itemImg +"' style='width:100%; height:100%;' id='"+item.itemCode+"'/>");  
    							$("#store-item"+itemClass+""+index+" img").jqxTooltip({ content: item.itemName+"("+item.itemGrade+")"+"<br>₩"+item.itemPrice+"<br>", position: 'bottom', autoHide: true, 
    								name: 'movieTooltip', theme : userInfo.theme });
    						})
    					}
    				},
    				error:function(err){
    					alert(err +"에러발생");
    				}
    		    })
    		}
    	    
    	    storeSelect(count);
    	    //이전버튼
    	    $("#store-prev-btn").on("click",function(){
    	   		status = "back";
    			storeSelect(count-1)
    	    })
    	    
    	    //다음버튼
    	    $("#store-next-btn").on("click", function(){
    	   		status = "next";
    			storeSelect(count+1)
    	    })
    	    
    	    //아이템구매
    	    $(".store-itemBox").on("click", function() {
    			var itemCode = $(this).children().attr("id");
    		
    			if(typeof(itemCode)=='undefined') return
    			
    	    	if(confirm("구매하시겠습니까?")==false) return
    			
    			$.ajax({
    		    	url: "itemStoreBuy" ,
    				type:"post",
    				dataType:"text",  
    				data:"quantity=1&itemCode="+itemCode,
    				success:function(result){
    					switch(result){
    						case "1" : alert("구매되었습니다."); break;
    						case "2" : alert("인벤토리가 부족합니다."); break;
    						case "3" : alert("루비가 부족합니다."); break;
    					}
    				},
    				error:function(err){
    					alert(err +"에러발생");
    				}
    		    })
    		})
    	  $("#store-window").jqxWindow({
	          width:614.3,
	          height:380,
	          resizable:true,
	          showCollapseButton: true,
	          autoOpen:false,
	          theme:userInfo.theme
	     });
	      $(".nav-tabs a").click(function(){
	          $(this).tab('show');
	      });
      }
      
      
      /*친구창*/
      var friendBook = function(){
    	  $("#friend-window").jqxWindow({
    	      theme:"kokomo",
    	      width:400,
    	      maxWidth:400,
    	      minWidth:400,
    	      minHeight:400,
    	      autoOpen:false,
    	      height:"auto",
    	      maxHeight:900,
    	      showCollapseButton: true
    	    });
    	  
    	  //친구검색
    	  $("#friend-add-search").on("click",function(){
    		  console.log($("#friend-add-text").val());
    		 $.ajax({
    			  url:"playerInfoSelectAll",
    			  type:"post",
    			  data:"keyword="+$("#friend-add-text").val(),
    			  dataType:"json",
    			  success:function(data){
    				  str="";
    				  $.each(data,function(index,item){
    					  
    					  str+="<tr class='friend-add-tr'><td>아바타</td>";
    					  str+="<td>"+item.playerNickname+"</td>"
    					  str+="<td>"+item.playerGrade+"</td></tr>"
    				  })
    				  $("#friend-list-table").empty();
    				  $("#friend-list-table").html(str);
    			  },
    			  error:function(err){
    				  alert(err+"에러발생")
    			  }
    			  
    		  })
    	  })
    	  
    	  /*친구추가 모달*/
    	  $("#friend-add-modal-btn").on("click",function(){
    		  $("#friend-add-text").focus();
    	  })
    	  
    	  /*친구추가*/
    	  $(document).on("click",".friend-add-tr",function(){
    		  var friendId=$(this).children("td").eq(1).text();
    		  var myId=$("#friend-add-test").val()
    		  console.log($(this))
    		  if(confirm(friendId+"님을 친구로 추가하시겠습니까?")==false || friendId=="") return;
    		  ws.send("friendAdd#/fuckWebSocket/#"+myId+"#/fuckWebSocket/#"+friendId)
    	  })
      }
      
      /*경제용어사전*/
      var financialInit = function(){
    	  $("#financial-window").jqxWindow({
    	      theme:"kokomo",
    	      width:500,
    	      height:600,
    	      autoOpen:false,
    	      showCollapseButton: true
    	    });
    	  
    	  $( "#financial-accordion" ).accordion({
		      active: false,
		      autoHeight: false, 
		      collapsible: true,
	    });
	    
    	
    	  var financialSearch = function(term){
    		  $.ajax({ 
			    	url:"searchFinancialTerm",
			    	type:"get",
					dataType:"xml",
					data:"term="+term,
					success:function(data){
						str="";
						$(data).find("item").each(function(index,item){ 
							var fnceDictNm = $(this).find('fnceDictNm') ? $(this).find('fnceDictNm').text() : "";
							var ksdFnceDictDescContent = $(this).find('ksdFnceDictDescContent') ? $(this).find('ksdFnceDictDescContent').text() : "";
							str+="<h3 class='accordion-section-title'>"+ fnceDictNm+"</h3>";
							str+="<div class='accordion-section-content'>"+ ksdFnceDictDescContent +"</div>";
						})  
						$("#financial-accordion").empty();
						$("#financial-accordion").html(str);
						$('#financial-accordion').accordion("refresh");
						$("#financial-accordion" ).accordion( "option", "active", 0 );
						
					},
					error:function(err){
						alert(err+"에러발생")
					}
			    })
			    $("#financial-search").val("");
    	  }
    	  
    	  
	    $("#financial-search").on("keyup",function(){
	    	var term = $(this).val()
	    	if(event.keyCode == 13) {
	    		if(term =="") {
	    			alert("검색어를 입력하여 주십시오.")
	    			return;
	    		}
	    		
	    		financialSearch(term);
	    	}
	    })
	    
	    $("#financial-add-search").on("click",function(){
	    	var term = $("#financial-search").val()
	    	if(term =="") {
    			alert("검색어를 입력하여 주십시오.")
    			return;
    		}
    		financialSearch(term);
	    })
      }
      
      invenInit();
      rtaInit();
      stockListInit();
      storeInit();
      friendBook();
      financialInit();
      
      var setBtn = function(){
         
            $("#inven-btn").setBtn($("#inven-Window"));
            $("#rta-btn").setBtn($("#rta-Window"));
            $("#stockList-btn").setBtn($("#stock-window"));
            $("#store-btn").setBtn($("#store-window"));
            $("#friend-btn").setBtn($("#friend-window"))
            $("#financial-btn").setBtn($("#financial-window"))
      }();
      
});