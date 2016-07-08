$(function(){
   var updatePI = function(callBack){
      $.ajax({
         url:"updatePI",
         dataType:"json",
         success:function(data){
            userInfo.nickName=data.playerNickname;
            userInfo.gender =data.playerGender;
            userInfo.money =data.playerMoney;
            userInfo.ruby =data.playerRuby;
            userInfo.grade=data.playerGrade;
            userInfo.dailyRank=data.playerDailyRank;
            userInfo.seasonRank=data.playerSeasonRank;
            userInfo.theme = "kokomo";
            
            if(callBack){
               callBack();
            }

         },
         error:function(){
            console.log("Exception : updatePI");
            alert("유저 정보 로드를 실패했습니다. 잠시 후 다시 시도해주세요.");
         }
      })
   }
   
   /* 버튼클릭했을 때 이벤트 설정 */
   $.fn.setBtn = function(window){
      $(this).on("click",function(){
         $(window).jqxWindow("show");
      })
      return this;
   };
   
   
   var initContent = function(){
      console.log("start init content");
      
    //전체 우클릭 제어
      $("body").contextmenu(function(){
         return false;
      })
      
   
    $(".side-avatar").css({
        width : $("#avatar-clothes").css("width"),
        height : $("#avatar-clothes").css("height")
     });

   
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
                          $(tbd).append("<tr> <td>"+item.isuKorAbbrv+"</td> <td>"+item.priceDTO.trdPrc+"</td> <td>"+item.priceDTO.cmpprevddPrc+"</td> <td>"+item.priceDTO.fluctuationRate+"%</td><td>"+item.priceDTO.accTrdvol+"</td></tr>")
                       }
                     });
                    if(data.length < 11) stockPage=1;
                  },
                  error:function(e){
                    console.log("Exception : getRealTimeStock");
                    stockPage = 1;
                  }
               });
           }
        };
        
        // 실시간 마우스 호버 이벤트
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
      /* 주식 구매*/
	   var buyStock = function(companyId, qty){
		   console.log(companyId + " " + qty)
		      $.ajax({
		    	  url:"buyStock",
		    	  dataType:"text",
		    	  data:{isuCd:companyId, plQuantity:qty},
		    	  success:function(data){
		    		  console.log(data);
		    		  if(data == "true"){
		    			  alert("구매 성공하였습니다.");
		    		  }else{
		    			  alert("구매 실패하였습니다.");
		    		  }
		    		  updatePI();
		    	  },
		    	  error:function(){
		    		  console.log("Exception : buyStock");
		    	  }
		      });
		  };
		  /*주식 판매*/
		   var sellStock = function(companyId, qty){
			   console.log(companyId + " " + qty)
			      $.ajax({
			    	  url:"sellStock",
			    	  dataType:"text",
			    	  data:{isuCd:companyId, plQuantity:qty},
			    	  success:function(data){
			    		  if(data == "true"){
			    			  alert("판매 하였습니다.");
			    		  }else{
			    			  alert("판매 실패하였습니다.");
			    		  }
			    		  updatePI();
			    	  },
			    	  error:function(){
			    		  console.log("Exception : sellStock");
			    	  }
			      });
			  };
      /* 기업 정보 조회 */
      var companyInfo = function(companyId){
             var price = $(companyId + " .company-title-stock").text();
             var isuCd = $(companyId + " .company-isuCd").val();
             var qty = $(companyId + " .company-qty").val();
             var ticks = parseInt(userInfo.money/price);
             
             $(companyId).jqxWindow({
                   theme:userInfo.theme,
                   minWidth:700,
                   width:"auto",
                   height:380,
                   showCollapseButton: true,
                   resizable : false
                 });
              
              var buySlider = $(companyId + " .company-buy-slider");
              var sellSlider = $(companyId + " .company-sell-slider");
              
              if(qty>0){
	              $(sellSlider).jqxSlider({
	                  width:"100%",
	                  showTickLabels: true,
	                  tooltip: true,
	                  mode: "fixed",
	                  min: 0,
	                  max: qty,
	                  ticksFrequency: qty/10,
	                  value: 0,
	                  step: 1,
	                  theme : userInfo.theme,
	                  tooltipPosition: "far"
	              });
	              
	              $(document).on("change",$(sellSlider),function(event){
	                  $(companyId + " .company-sell-value").text(price * event.args.value);
	                  
	               });
	              
	              $(companyId + " .company-sell-btn").on("click",function(evt){
	            	  sellStock(isuCd,$(sellSlider).val());
	              })
              }

              $(buySlider).jqxSlider({
                  width:"100%",
                  showTickLabels: true,
                  tooltip: true,
                  mode: "fixed",
                  min: 0,
                  ticksFrequency: ticks/10,
                  step: 1,
                  theme : userInfo.theme,
                  tooltipPosition: "far",
                  max: ticks,
                  value: 0
              });
              
              $(document).on("click", companyId + " .company-buy-btn", function(event){
            	  buyStock(isuCd,$(buySlider).val());
              })
              
              $(document).on("change",$(buySlider),function(event){
                 $(companyId + " .company-buy-value").text(price * event.args.value);
          
              });

      }

      

      var invenInit = function(){
          
          // 내아이템 목록 조회
           playerItemSelectAll = function(){
              $.ajax({
                 url:"playerItemSelectAll",
                 type:"post",
                 dataType:"json",
                 success:function(data){
                   $("#inven-content td").empty();   
                   $.each(data,function(index,item){
                      var items = $("<img src='"+item.itemDTO.itemImg+"' class='item-img'>").data("item" , item);
                      $("#inven-player-"+item.piIndex).html(items);
                   });
                   updateAvatar()
                 },
                 error:function(err){
                    console.log("Exception : invenInit");
                 }
              })
           }
           
           var playerItemUpdate = function(){
               var jsonList = passingJson();
                $.ajax({
                    url:"playerItemInsert", 
                    type:"post",
                    data:"itemParam="+(JSON.stringify(jsonList)).toString() ,
                    error:function(err){
                       console.log("Exception : playerItemUpdate");
                    }
                 })
            }
            
            var updateAvatar = function(){
               for(var i=1; i<=6; i++){
                  var partSrc = $("#inven-player-"+i+">.item-img").attr("src");
                  var set = setting.parts[i-1];
                  var target = $("#inven-player-"+set+", #side-avatar-player-"+set);
                  if(partSrc === "" || typeof(partSrc) === "undefined"){
                     if(i<=2){ 
                        $(target).attr("src","resources/img/avatar/"+setting.parts[i-1]+"/"+(userInfo.gender).toLowerCase()+"_"+setting.parts[i-1]+"_00.png");
                        
                     }else if(i<=4){
                        $(target).attr("src","resources/img/avatar/"+setting.parts[i-1]+"/a_"+setting.parts[i-1]+"_00.png");
                     }else{
                        $(target).attr("src","resources/img/avatar/empty.png");
                     }
                  }else{
                    $(target).attr("src", partSrc);
                  }
               }
            }
           
           playerItemSelectAll();
           
          // 옮기는 거
           var con = $(".item-socket td").sortable({
                connectWith: ".item-socket td",
                cursor: "move",
                scroll : false,
                forceHelperSize: true
           });
           
           // 옮겼을 때 반응
           $("#inven-items td").on("sortreceive",function(e,ui){
             
               if($(this).children().length>=2){
                  $(ui.sender).sortable("cancel");
               }else{
                  playerItemUpdate();
               }
           });
           
          // 장비칸에 두개 이상이 못 들어가는 것
           $(document).on("sortupdate", "#inven-player td",function(e,ui){
               if($(this).children().length>=2){
                  $(ui.sender).sortable("cancel");
                  return;
               }else if($(this).children().length==0){
                  playerItemUpdate();
               }else{
                  var changeInven=$(this).children("img").data("item").itemDTO.itemClass;
                  var index = $(this).attr("id").substr(-1);
                  if(changeInven != setting.parts[index-1]){
                     $(ui.sender).sortable("cancel");
                  }
                  playerItemUpdate();
               }
               updateAvatar();
           })
           
           //인벤토리 판매

           $("#inven-items td").contextmenu(function() {
              var storeIsOpen = $("#store-window").jqxWindow("isOpen");
              var auctionIsOpen = $("#auction-window").jqxWindow("isOpen");
              var dataItem=$(this).children().data("item");
              
              if(storeIsOpen==true && auctionIsOpen==true) {
              
              }else if(storeIsOpen==true){
                 //상점에 아이템 판매
                 if(dataItem){
                    if(confirm("판매하시겠습니까?")){
                       storeSell($(this).children().data("item"));
                    };
                 }
              }else if(auctionIsOpen==true){
                 //경매장에 아이템 판매
                 if(dataItem){
                    $(".inven-auction-modal").modal("show");
                    $("#inven-auction-piSq").val(dataItem.piSq);
                 }
              }
              return false;
           });

        
        //모달에서 판매등록
        $("#inven-auction-sell-btn").on("click",function(){
           auctionSell($("#inven-auction-piSq").val(),$("#inven-auction-imPurchasePrice").val())
        })
        
        //상점판매
        var storeSell = function(imgData){
           $.ajax({
              url:"itemStoreSell",
              type:"post",
              data:"piSq="+imgData.piSq+"&itemCode="+imgData.itemDTO.itemCode,
              dataType:"text",
              success:function(result){
                 playerItemSelectAll()
              },
              error:function(err){
                 console.log("Exception : itemStoreSell");
              }
           })
        }
        

           //경매판매 
            var auctionSell = function(piSq,imPurchasePrice){
               
               $.ajax({
                  url:"auctionSell",
                  type:"post",
                  data:"piSq="+piSq+"&imPurchasePrice="+imPurchasePrice,
                  dataType:"",
                  success:function(result){ 
                     $(".inven-auction-modal").modal("hide");
                     playerItemSelectAll();
                     $("#inven-auction-imPurchasePrice").val("");
                     search(1)
                     $("#auction-selltab").trigger("click");
                  }, 
                  error:function(err){
                     console.log("Exception : auctionSell");
                  }
               })
            }
        
           // 인덱스 값 파싱
           function passingJson(){
             var jsonArr = new Array();
             
             for(var i=1;i<=30;i++){
                if(i>=7 && i<=10) {continue;}
                var invenPlayerItem = $("#inven-player-"+i).children().data("item");
       
                if(typeof(invenPlayerItem)!="undefined"){
                  var jsonObj = new Object();
                   jsonObj.piSq=$("#inven-player-"+i).children().data("item").piSq;
                   jsonObj.piIndex=i;
                   jsonArr.push(jsonObj);
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
                           str+="<td>"+item.priceDTO.accTrdvol +"</td>";
                           str+="<td>"+item.priceDTO.opnprc +"</td>";
                           str+="<td>"+item.priceDTO.hgprc +"</td>";
                           str+="<td>"+item.priceDTO.lwprc +"</td>";
                           str+="<input type='hidden' value='"+item.isuCd+"'/></tr>"
                        }
                     })
                     
                     $("#stockListTBody").html(str);
                     $(document).on("click", "#stock-window tr",function(e){
                       var cd = $(this).find(":hidden").val();
                       showCompanyInfo(cd);
                     })
                  },
                  error:function(err){
                     console.log("Exception : stockListInit");
                  }
               })
            }

            /* 마지막 페이지 */
            function pagenation(pageNo){ 
                 $('#page-selection').bootpag({
                     total: pageNo, maxVisible: 10
                 })
             }
            
            /* 페이지 클릭 */
            $('#page-selection').on("page", function(event, num){
               
               if($("#stock-search-keyword").val()==""){
                  $("#stock-search-keyword").val("undefined");
               }
                 stockPageSelect(num,$("#stock-search-keyword").val());
              });
            
            /* 종목명 클릭 시 상세정보 띄어줌. */
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
                     console.log("Exception : showCompanyInfo");
                  }
               });
            }

            /* 검색 */
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
      
      /* 상점 */
      var storeInit = function(){
          var count=1;
          var tabs="";
           var status = "next";

          
           $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
                storeSelect(1)
           });
          
           // Body,Head등을 구분해서 파라미터로 넣어주면 거기에 해당되는 것을 뿌려줌
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
                   console.log("Exception : storeSelect");
                }
              })
          }
           
           storeSelect(count);
           // 이전버튼
           $("#store-prev-btn").on("click",function(){
                status = "back";
             storeSelect(count-1)
           })
           
           // 다음버튼
           $("#store-next-btn").on("click", function(){
                status = "next";
             storeSelect(count+1)
           })
           
           // 아이템구매
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
                   playerItemSelectAll();
                   
                },
                error:function(err){
                   console.log("Exception : 아이템 구매");
                }
              })
          })
          $("#store-window").jqxWindow({
              width:640,
              height:390,
              resizable:false,
              showCollapseButton: true,
              autoOpen:false,
              theme:userInfo.theme
          });
           
         $(".nav-tabs a").click(function(){
             $(this).tab('show');
         });
      }
      
      
      /* 친구창 */
      
      var friendBookinit = function(){
    	  var friendDelBtn=0;
    	  
         $("#friend-window").jqxWindow({
             theme:userInfo.theme,
             width:400,
             maxWidth:800,
             minWidth:400,
             minHeight:400,
             autoOpen:false,
             height:600,
             maxHeight:900,
             showCollapseButton: true
           });
         var friendSearch = function(){
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
                    console.log("Exception : 친구 검색");
                 }
                 
              })
         }

         
         // 친구검색
         $("#friend-add-search").on("click", friendSearch);
         $("#friend-add-text").on("keyup",function(){
        	 if(event.keyCode == 13) {
        		 friendSearch()
              }
         })
         
         
         
         /* 친구추가 모달 */
         $("#friend-add-modal-btn").on("click",function(){
            $("#friend-add-text").focus();
         })
         
         /* 친구추가 */
         $(document).on("click",".friend-add-tr",function(){
            var friendId=$(this).children("td").eq(1).text();
            var myId=$("#friend-add-test").val()
            if(confirm(friendId+"님을 친구로 추가하시겠습니까?")==false || friendId=="") return;
            ws.send("friendAdd#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"+friendId)
         })
         
         /*친구승락*/
         $(document).on("click",".friend-accept" ,function(){
        	 var friendNickName = $(this).parent().prevAll(".name").text();
            var friendSq = $(this).parent().prevAll(".requestedFSq").val();
            console.log(friendNickName);
            console.log(friendSq);
            ws.send("friendAccept#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"+friendNickName+"#/fuckWebSocket/#"+friendSq+"#/fuckWebSocket/#");
            
         })
         
         /*친구거절 및 삭제*/
         $(document).on("click",".friend-reject,.friend-sendBtn .glyphicon-trash",function(){
        	 var friendSq = $(this).parent().prevAll(".requestedFSq").val();
        	 if(!friendSq){
        		 friendSq = $(this).parent().parent().prevAll(".ListFriendFSq").val();
        	 }
        	 
            ws.send("friendDel#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"+friendSq+"#/fuckWebSocket/#")
         })
         
         /*친구신청 알림*/
         $("#friend-request-noti").jqxNotification({
                width: 250, position: "top-right", opacity: 0.9,
                autoOpen: false, animationOpenDelay: 800, autoClose: true, autoCloseDelay: 3000, template:"info"
            });
         
         /*TEST*/
         $("#friend-request-btn").on("click",function(){
        	 $("#friend-request-noti").children().text("님께서 친구신청을 하셨습니다.")
        	   $("#friend-request-noti").jqxNotification("open");
         })
         
         /*친구삭제 버튼 클릭 시*/
         $("#friend-del").on("click",function(){
        	 if(friendDelBtn==0){
	        	 $(this).text("삭제취소");
	        	 $("#friend-list-current .glyphicon-envelope").attr("class","glyphicon glyphicon-trash");
	        	 friendDelBtn=1
        	 }else{
        		 $(this).text("친구삭제");
        		 $("#friend-list-current .glyphicon-trash").attr("class","glyphicon glyphicon-envelope");
	        	 friendDelBtn=0
        	 }
         })
      }
      
      /* 경제용어사전 */
      var financialInit = function(){
         $("#financial-window").jqxWindow({
             theme:userInfo.theme,
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
                  console.log("Exception : financialSearch");
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
       
       /*가나다인척하기*/
       //financialSearch("가")
      }
      
      /* 경매장 */
      var auctionInit = function(){
         // 페이지 변수
         var count=1;
        var sellBtn="";
        var colorBtn="";
        var sellFlag=false;
        // 탭 토글
          $('a[data-toggle="tab"]').on('hidden.bs.tab', function(e){
          });
          
        // 구매탭
        $("#auction-buytab").on("click",function(){
           $("#auction-search").show();
             $("#auction-select").show();
        })
          
          // 판매탭
          $("#auction-selltab").on("click",function(){
             $("#auction-search").hide();
             $("#auction-select").hide();
             auctionSellList();
          })
          
          // 아이템구매
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
                 playerItemSelectAll();
                 search(count);
                 
              },
              error:function(err){
                 console.log("Exception : 아이템 구매");
              }
           })
        })
        
        // 판매취소
        $(document).on("click",'input[value=취소]', function() {
           $.ajax({
              url:"auctionCancel",
              type:"post",
              dataType:"text",
              data:"imSq="+$(this).attr("id"),
              success:function(result){
                 alert("취소되었습니다.");
                 auctionSellList();
              },
              error:function(err){
                 console.log("Exception : 판매 취소");
              }
           })
        })
        
         // 수령, 유찰
        $(document).on("click",'input[value=수령],input[value=유찰]', function() {
           var wordBtn =  $(this).val();
           $.ajax({
              url:"auctionBring",
              type:"post",
              dataType:"text",
              data:"imSq="+$(this).attr("id"),
              success:function(result){
                 if(wordBtn=="수령"){
                    alert("판매금을 수령하셨습니다.");
                 }else{
                    if(result!="false"){
                       playerItemSelectAll();
                       search(1);
                       alert("유찰 된 아이템을 수령하셨습니다.");
                    }else{
                       alert("인벤토리를 비워 주십시오.");
                    }
                 } 
                 auctionSellList();
              },
              error:function(err){
                 console.log("Exception : 수령 유찰");
              }
           })
        })

        
        
          // 검색
          $("#auction-search").on("keyup",function(){
             count=1;
             if(event.keyCode == 13) {
                $("#auction-hidden").val($(this).val())
                search(count); 
                $("#auction-search").val("");
             }
          })
         
          // 이전버튼
        $("#auction-back-btn").on("click",function(){
           if($("#auction-buy-tbody").children().length<=0) return
           if(count>1){
              sellFlag=false;
              search(count-1);
           }
        })
        // 다음버튼
        $("#auction-next-btn").on("click",function(){
           if($("#auction-buy-tbody").children().length<=0) return
           sellFlag=false
           search(count+1)
        })
         
        // 페이지에따른 검색
       search = function (page){
             $.ajax({
                url:"auctionSearch",
                type:"post",
                dataType:"json",
                data:"keyword=" + $("#auction-search").val()+"&itemClass="+$("#auction-select").val()+"&page="+page,
                success:function(data){
                   
                   if(data.length==0){
                      if(sellFlag=true){
                         $("#auction-buy-tbody").empty();
                      }
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
                   console.log("Exception : search");
                }
                
             })
          }
        
        // 판매탭 접속
        function auctionSellList(){
           $.ajax({
                url: "auctionMyPage" ,
              type:"post",
              dataType:"json",  
              success:function(data){
                 $("#auction-sell-tbody").empty();
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
                 console.log("Exception : auctionSellList");
              }
             });
           
        }
        
        $("#auction-window").jqxWindow({
            minWidth:600,
            minHeight:420,
            resizable:true,
            showCollapseButton: true,
            autoOpen:false,
            theme : userInfo.theme
          });
        
        search(1);
       
      }
      
      /* 유저 정보 보기 */
      var showUserInfo = function(nickName){
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
                     theme:userInfo.theme
                });
                
             },
             error:function(err){
                console.log("Exception : showUserInfo");
             }
          })
       };
      
       var myStockListUpdate = function(){
    	   $.ajax({
    		   url:"playerStock",
    		   type:"post",
    		   dataType:"json",
    		   success:function(data){
    			   str="";
    			   console.log(data);
    			   $("#mystockListTBody").empty();
    			   $.each(data, function(index, item){
    				   str+="<tr><td>"+item.isuKorAbbrv+"</td>";
    				   str+="<td>"+item.kind+"</td>";
    				   str+="<td>"+item.plQuantity+"</td>";
    				   str+="<td>"+item.priceDTO.trdPrc+"</td>";
    				   str+="<td>"+item.priceDTO.trdPrc * item.plQuantity+"</td>";
    				   str+="<td>"+item.earningRate+"%"+"</td></tr>";
    			   });
    			   $("#mystockListTBody").html(str);
    		   },
    		   error:function(err){
    			   console.log("Exception : myStockListUpdate");
    		   }
    	   });
       }
       
       var myStockInit = function(){
	     $("#mystock-window").jqxWindow({
	           width:"400",
	           height:"450",
	           resizable:true,
	           showCollapseButton: true,
	           autoOpen:false,
	           theme:userInfo.theme
	         });
	     myStockListUpdate();
       }

       /* 뉴스 검색 */
       var newsSearchInit = function(){
          $("#news-search-window").jqxWindow({
                width:"500",
                height:"700",
                resizable:true,
                showCollapseButton: true,
                autoOpen:false,
                theme:userInfo.theme
              });
          
          $("#news-search-submit").click(function(){
             var keyword = $("#news-search-keyword").val();
             if(keyword == ""){
                alert("검색어를 입력해주세요.");
                return;
             }
             
             $.ajax({
                 url:"searchNews",
               type:'post',
                data:{"keyword":keyword},
                dataType:"json",
                success:function(result){
                    var str = "";
                    if(!result.found){
                       alert("검색된 결과가 없습니다.");
                       return;
                    }
                   var str = "";
                   $.each(result.data.docs,function(index, item){
                      console.log(item);
                      if(item.image_urls.length>0){
                         str += "<div class='hr-line-dashed'></div>";
                         str += "<div class='news-search-img col-md-4'>";
                         str += "<img src='"+item.image_urls[0]+"'/></div>";
                         str += "<div class='col-md-8 search-result'>";
                      }else{
                         str += "<div class='col-md-12 search-result'>";
                      }
                      str+="<h3><a href='#'>"+item.title+"</a></h3>";
                      str+="<small>"+item.updated_at+"</small><br>"
                     str+="<a href='#' class='search-link'>"+item.author+" 기자 ("+item.publisher+")</a>";
                     str+="<p>"+item.content+"</p>";
                     str+="</div>";
                   })
                   console.log(str);
                   $("#news-search-results").empty().append(str);              
                   
                },
                error:function(err){
                   console.log("Exception : searchNews(keyword : "+keyword+")");
                }
             });
          })
          
       }
       
      invenInit();
      rtaInit();
      stockListInit();
      storeInit();
      friendBookinit();
      financialInit();
      auctionInit();
      myStockInit();
      newsSearchInit();
      
      var setBtn = function(){
            $("#inven-btn").setBtn($("#inven-Window"));
            $("#rta-btn").setBtn($("#rta-Window"));
            $("#stockList-btn").setBtn($("#stock-window"));
            $("#store-btn").setBtn($("#store-window"));
            $("#friend-btn").setBtn($("#friend-window"));
            $("#financial-btn").setBtn($("#financial-window"));
            $("#auction-btn").setBtn($("#auction-window"));
            $("#mystock-btn").setBtn($("#mystock-window"));
            $("#news-search-btn").setBtn($("#news-search-window"));
            
          $("#myinfo-btn").click(function(){
             showUserInfo(userInfo.nickName);
          });
      }();

   
      /*======================Set WebSocket=============================*/
      connect(function(){
          $('#logout').click(function() {
              disconnect();
              location.href = "logout";
           });
          //추가 된 친구조회
          var friendselectAll = function(){
             ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#");
             /*내가접속했을때 친구에게 접속알림*/
             ws.send("notiFriendLogin#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#")
             ws.onmessage = function (event) {
               var data = JSON.parse(event.data);
               console.log(data)
                  
               var requestedFriend="";
               var ListFriend="";
               var friendBtnColor="";
               if(data.type=="friendSelect"){
                   
                   $.each(data.data,function(index,item){
                      if(userInfo.nickName==item.playerNickname){
                         var friendNickname=item.playerNickname2
                      }else{
                        var friendNickname=item.playerNickname
                      }
                      
                      if(item.friendIsAccepted=="F"){
                          if(userInfo.nickName==item.playerNickname2){
                    	      requestedFriend+="<li href='#' class='list-group-item text-left'>";
                              requestedFriend+="<img class='img-thumbnail' src='http://bootdey.com/img/Content/User_for_snippets.png'>";
                               
                              requestedFriend+="<label class='name'>"+friendNickname+"</label>";
                              requestedFriend+="<input type='hidden' class='requestedFSq' value='"+item.friendSq+"'>"
                              requestedFriend+="<div class='pull-right'>";
                              requestedFriend+="<button type='button' class='btn btn-success friend-accept btn-circle'><i class='glyphicon glyphicon-ok'></i></button>";
                              requestedFriend+="<button type='button' class='btn btn-danger friend-reject btn-circle'><i class='glyphicon glyphicon-remove'></i></button>";
                              requestedFriend+="</div></li>";
                          }
                        
                      }else{
                       ListFriend+="<li href='#' class='list-group-item text-left'>";
                       ListFriend+="<img class='img-thumbnail' src='http://bootdey.com/img/Content/User_for_snippets.png'>";
                       if(item.onOrOff=="true"){
                    	   friendBtnColor="green";
                       }else{
                    	   friendBtnColor="red";
                       }
                       ListFriend+="<div class='friend-icon "+friendBtnColor+"'> </div>";
                       ListFriend+="<label class='name'>"+friendNickname+"</label>";
                       ListFriend+="<input type='hidden' class='ListFriendFSq' value='"+item.friendSq+"'>";
                       ListFriend+="<div class='pull-right'>";
                       ListFriend+="<button type='button' class='btn btn-default friend-sendBtn '>";
                       ListFriend+="<i class='glyphicon glyphicon-envelope'></i></button></div></li>";
                     }
                  })
                  $("#friend-content .list-group > .title").siblings("li").remove();
                  $("#friend-list-que ul").append(requestedFriend);
                  $("#friend-list-group ul").append(ListFriend);
               }else if(data.type=="notiFriendAdd"){
            	   console.log(data.data.playerNickname);
            	   $("#friend-request-noti").children().text(data.data.playerNickname+"님께서 친구신청을 하셨습니다.")
            	   $("#friend-request-noti").jqxNotification("open");
            	   ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#");
               }else if(data.type=="friendDel"){
            	   ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#");
               }else if(data.type=="notiFriendAcceptMe"){
            	   ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#");
               }else if(data.type=="notiFriendAcceptYou"){
            	   ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#");
            	   $("#friend-request-noti").children().text(data.data+"님께서 친구수락을 하셨습니다.");
            	   $("#friend-request-noti").jqxNotification("open");
               }else if(data.type="notiFriendLogin"){
            	   console.log("노티파이!!")
            	   $("#friend-request-noti").children().text(data.data+"님께서 로그인을 하셨습니다.")
            	   $("#friend-request-noti").jqxNotification("open");
            	   ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#");
               }
             }
          }   
          friendselectAll();
      });
      $(".main-container").css("visibility","visible");
      $("#loading-content").remove();
   };
   updatePI(initContent);
});