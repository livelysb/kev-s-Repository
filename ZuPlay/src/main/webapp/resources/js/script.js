$(function(){
	
	var updatePI = function(callBack){
		$.ajax({
			url:"updatePI",
			dataType:"json",
			success:function(data){
				userInfo.nickName=data.playerNickname
				userInfo.gender =data.playerGender
				userInfo.money =data.playerMoney
				userInfo.ruby =data.playerRuby
				userInfo.grade=data.playerGrade
				userInfo.dailyRank=data.playerDailyRank
				userInfo.seasonRank=data.playerSeasonRank
				if(callBack)
					callBack();
				//userInfo.friends =data.
				//userInfo.theme =data.
			},
			error:function(){
				
			}
		})
	}
	
	updatePI(function(){
		connect();

		$('#logout').click(function() {
			disconnect();

			location.href = "logout";
		});
		
       $(".side-avatar").css({
           width : $("#avatar-clothes").css("width"),
           height : $("#avatar-clothes").css("height")
        });
	});
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
     		if(stockPage==89) stockPage=1;
     		if(!isHover){
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
     		}
     	};
     	
     	//실시간 마우스 호버 이벤트
     	var rtaRefresh=setInterval(getRealTimeStock, 3000);
     	var isHover = false;
     	
 		$("#rta-content").hover(
			function(){
				isHover = true;
			},
			function(){
				isHover = false;
			}
 		)
     	
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
    	  
    	//내아이템 목록 조회
          playerItemSelectAll = function(){
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
          }
          
          playerItemSelectAll();
          
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
              }else{
            	  playerItemUpdate()
              }
          });
          
         //장비칸에 두개 이상이 못 들어가는 것
          $(document).on("sortupdate", "#inven-player td",function(e,ui){
              if($(this).children().length>=2){
                 $(ui.sender).sortable("cancel");
                 return;
              }else if($(this).children().length==0){
            	  playerItemUpdate()
              }else{
            	  var changeInven=$(this).children("img").data("item").itemDTO.itemClass;
            	  var index = $(this).attr("id").substr(-1);
            	  if(changeInven != setting.parts[index-1]){
            		  $(ui.sender).sortable("cancel");
            	  }
            	  playerItemUpdate()
              }
              updatePI(updateAvatar);
          })
          
////////////////////////////////////////////////////////////////////////////////////          
          	  //인벤토리 판매
        	  $("#inven-items td").contextmenu(function() {
        		  var storeIsOpen = $("#store-window").jqxWindow("isOpen");
        		  var auctionIsOpen = false //$("#auction-window").jqxWindow("isOpen");
        		  
        		  if(storeIsOpen==true && auctionIsOpen==true) {
        			  
        		  }else if(storeIsOpen==true){
        			  //상점에 아이템 판매
        			  if(confirm("판매하시겠습니까?")){
        				  storeSell($(this).children().data("item"))
        			  };
        		  }else if(auctionIsOpen==true){
        			  //경매장에 아이템 판매
        			  if(confirm("판매하시겠습니까?")) {
        				  auctionSell($(this).children().data("item"))
        			  };
        		  }
        		  return false;
        	  });
          
          
          //상점판매
          var storeSell = function(imgData){
        	  console.log(imgData)
        	  $.ajax({
        		  url:"itemStoreSell",
        		  type:"post",
        		  data:"piSq="+imgData.piSq+"&itemCode="+imgData.itemDTO.itemCode,
        		  dataType:"text",
        		  success:function(result){
        			  alert(result)
        		  },
        		  error:function(err){
        			  alert(err+"에러발생")
        		  }
        	  })
          }
          
          //경매판매 
          var auctionSell = function(imgData,imPurchasePrice){
        	  
        	  $.ajax({
        		  url:"auctionSell",
        		  type:"post",
        		  data:"piSq="+imgData.piSq+"&imPurchasePrice="+imPurchasePrice,
        		  dataType:"",
        		  success:function(result){
        			  alert(result)
        		  },
        		  error:function(err){
        			  alert(err+"에러발생")
        		  }
        	  })
          }
///////////////////////////////////////////////////////////////////////////////////          
          
          var playerItemUpdate = function(){
        	  var jsonList = passingJson();
              $.ajax({
                  url:"playerItemInsert", 
                  type:"post",
                  data:"itemParam="+(JSON.stringify(jsonList)).toString() ,
                  error:function(err){
                     alert(err+"에러발생");
                  }
               })
          }
          
          var updateAvatar = function(){
             for(var i=1; i<=6; i++){
            	
                var partSrc = $("#inven-player-"+i+">.item-img").attr("src");
                
                if(partSrc === "" || typeof(partSrc) === "undefined"){
                	if(i<=2){ 
                		$("#inven-player-"+setting.parts[i-1]).attr("src","resources/img/avatar/"+setting.parts[i-1]+"/"+(userInfo.gender).toLowerCase()+"_"+setting.parts[i-1]+"_00.png");
                	}else if(i<=4){
                		$("#inven-player-"+setting.parts[i-1]).attr("src","resources/img/avatar/"+setting.parts[i-1]+"/a_"+setting.parts[i-1]+"_00.png");
                	}else{
                		$("#inven-player-"+setting.parts[i-1]).attr("src","resources/img/avatar/empty.png");
                	}
                }else{
                   $("#inven-player-"+setting.parts[i-1]).attr("src", partSrc);
                }
             }
          }
         
          
          
          
          //인덱스 값 파싱
          function passingJson(){
            var jsonArr = new Array();
            
            for(var i=1;i<=30;i++){
               if(i>=7 && i<=10) {continue;}
               var invenPlayerItem = $("#inven-player-"+i).children().data("item");
      
               if(typeof(invenPlayerItem)!="undefined"){
            	  var jsonObj = new Object();
                  jsonObj.piSq=$("#inven-player-"+i).children().data("item").piSq;
                  jsonObj.piIndex=i;
                  jsonArr.push(jsonObj)
               }
            }
            return jsonArr;
            
          }
          
          $("#inven-Window").jqxWindow({
               minWidth:600,
               minHeight:420,
               resizable:false,
               showCollapseButton: true,
               autoOpen:false,
               theme : userInfo.theme
             });
          
          updatePI(updateAvatar);

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
                           str+="<tr class='stock-evt'><td class='stock-select'>"+item.isuKorAbbrv+"</a></td>"
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
            	if(!code)
            		return;
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
            $("#stock-search").on("keyup",function(){
               if(event.keyCode == 13) {
            	   stockListSearch()
               }
            })
            
            $("#stock-list-search").on("click",function(){
            	stockListSearch()
            })
            
            var stockListSearch = function(){
            	if($("#stock-search").val()=="") return;
                $("#page-selection ul li").eq(1).trigger("click");
                $("#stock-search-keyword").val($("#stock-search").val());
                stockPageSelect(1,$("#stock-search").val());
            }
            
            
            
            
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
    						
    						$(".store-itemBox").empty();
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
    					playerItemSelectAll()
    					
    				},
    				error:function(err){
    					alert(err +"에러발생");
    				}
    		    })
    		})
    	  $("#store-window").jqxWindow({
	          width:640,
	          height:390,
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
      
      /*경매장*/
      var auctionInit = function(){
    	  //페이지 변수
          var count=1;
  		var sellBtn="";
  		var colorBtn="";
  		//탭 토글
          $('a[data-toggle="tab"]').on('hidden.bs.tab', function(e){
          });
          
  		//구매탭
  		$("#auction-buytab").on("click",function(){
  			$("#auction-search").show();
          	$("#auction-select").show();
  		})
          
          //판매탭
          $("#auction-selltab").on("click",function(){
          	$("#auction-search").hide();
          	$("#auction-select").hide();
          	auctionSellList();
          })
          
          //경매장 아이템등록
          $("#auction-register").on("click",function(){
          	$.ajax({
          		url:"auctionSell",
          		type:"post",
          		dataType:"text",
          		data:"piSq=90&imPurchasePrice=15000",
          		success:function(result){
          			alert(result);
          		},
          		error:function(err){
          			alert(err+"에러발생");
          		}
          	})
          })
          
          //아이템구매
          $(document).on("click",'input[value=구매]', function() {
  			$.ajax({
  				url:"auctionBuy",
  				type:"post",
  				dataType:"text",
  				data:"imSq="+$(this).attr("id"),
  				success:function(result){
  					switch(result){
  						case "1" : alert("구매되었습니다."); break;
  						case "2" : alert("인벤토리가 부족합니다."); break;
  						case "3" : alert("루비가 부족합니다."); break;
  						case "4" : alert("이미 판매 된 물품입니다.");break;	
  					}
  					alert(count)
  					search(count)
  					
  				},
  				error:function(err){
  					alert(err+"에러발생")
  				}
  			})
  		})
  		
  		//판매취소
  		$(document).on("click",'input[value=취소]', function() {
  			$.ajax({
  				url:"auctionCancel",
  				type:"post",
  				dataType:"text",
  				data:"imSq="+$(this).attr("id"),
  				success:function(result){
  					alert("취소되었습니다.");
  					///판매탭 새로고침하기!!!
  					auctionSellList();
  				},
  				error:function(err){
  					alert(err+"에러발생")
  				}
  			})
  		})
  		
  		//수령, 유찰
  		$(document).on("click",'input[value=수령],input[value=유찰]', function() {
  			var wordBtn =  $(this).val();
  			$.ajax({
  				url:"auctionBring",
  				type:"post",
  				dataType:"text",
  				data:"imSq="+$(this).attr("id"),
  				success:function(result){
  					if(wordBtn=="수령"){
  						alert("판매금을 수령하셨습니다.")
  					}else{
  						if(result!="false"){
  							alert("유찰 된 아이템을 수령하셨습니다.")
  						}else{
  							alert("인벤토리를 비워 주십시오.")
  						}
  					} 
  					auctionSellList();
  				},
  				error:function(err){
  					alert(err+"에러발생")
  				}
  			})
  		})
  		
  		
          //검색
          $("#auction-search").on("keyup",function(){
          	count=1;
          	if(event.keyCode == 13) {
          		$("#auction-hidden").val($(this).val())
          		search(count); 
          		$("#auction-search").val("");
          	}
          })
  	    
          //이전버튼
  		$("#auction-back-btn").on("click",function(){
  			if($("#auction-buy-tbody").children().length<=0) return
  			if(count>1){
  				search(count-1);
  			}
  		})
  		//다음버튼
  		$("#auction-next-btn").on("click",function(){
  			if($("#auction-buy-tbody").children().length<=0) return
  			search(count+1)
  		})
  		 
  		//페이지에따른 검색
  		function search(page){
          	$.ajax({
          		url:"auctionSearch",
          		type:"post",
          		dataType:"json",
          		data:"keyword=" + $("#auction-search").val()+"&itemClass="+$("#auction-select").val()+"&page="+page,
          		success:function(data){
          			console.log(data);
          			if(data.length==0){
          				return;
          			} 
          			count=page;
          			$("#auction-buy-tbody").empty();
          			var str="";
          			$.each(data, function(index,item){
          				if(item.imAuctionEnd="T"){
  	        				str+="<tr><td><img src='"+item.itemDTO.itemImg+"' class='auction-itemImg'></td>";
  	        				str+="<td>"+item.itemDTO.itemName+"</td>";
  	        				str+="<td>"+item.imPurchasePrice+"</td>";
  	        				str+="<td>"+item.imBidTime+"</td>";
  	        				str+="<td>"+item.playerNickname+"</td>";
  	        				str+="<td><input type='button' id='"+item.imSq+"' class='btn btn-primary btn-sm btnBuy' value='구매'></td>"
          				}
          			})
          			$("#auction-buy-tbody").html(str);
          		},
          		error:function(err){
          			alert(err+"에러발생");
          		}
          		
          	})
          }
  		
  		//판매탭 접속
  		function auctionSellList(){
  			$.ajax({
  	        	url: "auctionMyPage" ,
  				type:"post",
  				dataType:"json",  
  				success:function(data){
  					$("#auction-sell-tbody").empty;
  					var str="";
  					$.each(data, function(index, item){
  						str+="<tr><td><img src='"+ item.itemDTO.itemImg +"' class='auction-itemImg' ></td>";
  						str+="<td>"+item.itemDTO.itemName+"</td>";
  						str+="<td>"+item.imPurchasePrice+"</td>";
  						str+="<td>"+item.imBidTime+"</td>";
  						
  						if(item.imAuctionEnd=="T"){
  							sellBtn="취소";
  							colorBtn="btn-primary";
  						}else if(item.imAuctionEnd=="F"){
  							sellBtn="수령";
  							colorBtn="btn-success";
  						}else{
  							sellBtn="유찰";
  							colorBtn="btn-danger";
  						}
  												
  						str+="<td><input type='button' id='"+item.imSq+"' class='btn "+colorBtn+" btn-sm btnCancel' value='"+sellBtn+"'></td></tr>"
  					})
  					$("#auction-sell-tbody").html(str);
  				} ,
  				error:function(err){
  					alert(err +"에러발생");
  				}
  	        })
  		}
  		
  		$("#auction-window").jqxWindow({
            minWidth:600,
            minHeight:420,
            resizable:true,
            showCollapseButton: true,
            autoOpen:false,
            theme : userInfo.theme
          });
       
      }
      
      /* 유저 정보 보기 */
      var userInfo = function(nickName){
          $.ajax({
             url:"userInfo",
             data:{targetPlayer:nickName},
             dataType:"html",
             success:function(data){

              $(".main-area").append(data);
                $("#userinfo-player-"+nickName).jqxWindow({
                     width:"450",
                     height:"400",
                     showCollapseButton: true,
                     autoOpen:true,
                     closeButtonAction: 'close',
                     theme:"kokomo"
                });
                
             },
             error:function(err){
                
             }
          })
       }
      
      
      invenInit();
      rtaInit();
      stockListInit();
      storeInit();
      friendBook();
      financialInit();
      auctionInit();
      
      var setBtn = function(){
            $("#inven-btn").setBtn($("#inven-Window"));
            $("#rta-btn").setBtn($("#rta-Window"));
            $("#stockList-btn").setBtn($("#stock-window"));
            $("#store-btn").setBtn($("#store-window"));
            $("#friend-btn").setBtn($("#friend-window"))
            $("#financial-btn").setBtn($("#financial-window"))
            $("#auction-btn").setBtn($("#auction-window"))
    		$("#myinfo-btn").click(function(){
    			userInfo(userInfo.nickName);
    		});
      }();
      
});