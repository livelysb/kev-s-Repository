$(function(){
   var updatePI = function(callBack){
      $.ajax({
         url:"updatePI",
         dataType:"json",
         success:function(data){
        	 console.log(data);
            userInfo.nickName=data.playerNickname;
            userInfo.gender =data.playerGender;
            userInfo.money =data.playerMoney;
            userInfo.ruby =data.playerRuby;
            userInfo.grade=data.playerGrade;
            userInfo.dailyRank=data.playerDailyRank;
            userInfo.seasonRank=data.playerSeasonRank;
            
            if(callBack){
               callBack();
            }

         },
         error:function(){
            console.log("Exception : updatePI");
            alert("유저 정보 로드를 실패했습니다. 잠시 후 다시 시도해주세요.");
            location.href="/";
         }
      })
   }
   
   /* 버튼클릭했을 때 이벤트 설정 */
   $.fn.setBtn = function(window){
      $(this).on("click",function(){
         $(window).jqxWindow("show");
         $(window).jqxWindow('bringToFront');
         $(window).jqxWindow('expand');
         $(window).jqxWindow('focus');
      })
      return this;
   };
   
   
   var initContent = function(){
      console.log("start init content");
      
    // 전체 우클릭 제어
      $("body").contextmenu(function(){
         return false;
      })
      
   

      
      /*아바타 옷입히기*/
      var avatarEqui = function(className,avatarGender,playerItem){
        var avatarEquiAry = new Array();
        var closetUrl = "resources/img/avatar/";
        var defaultItemAry=[
                            closetUrl+"clothes/"+avatarGender.toLowerCase()+"_clothes_00.png",
                            closetUrl+"hair/"+avatarGender.toLowerCase()+"_hair_00.png",
                            closetUrl+"eyes/a_eyes_00.png",
                            closetUrl+"mouse/a_mouse_00.png",
                            closetUrl+"empty.png",
                            closetUrl+"empty.png"
        ]
        
        
         for(var i=0; i<=5; i++){
           avatarEquiAry[i] = "<img src='"+defaultItemAry[i]+"' class='"+className+"-avatar-"+setting.parts[i]+"'>";
        }
        
        for(var j=0; j<playerItem.length; j++){
           avatarEquiAry[(playerItem[j].piIndex)-1] = 
              "<img src='"+playerItem[j].itemDTO.itemImg+"' class='"+className+"-avatar-"+setting.parts[(playerItem[j].piIndex)-1]+"'>";
        }
        return avatarEquiAry;
      }

   
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
                           $(tbd).append("<tr><td>"+item.isuKorAbbrv+"</td> <td>"+item.priceDTO.trdPrc+"</td> <td>"+item.priceDTO.cmpprevddPrc+"</td> <td>"+item.priceDTO.fluctuationRate+"%</td><td>"+item.priceDTO.accTrdvol+"</td><input type='hidden' value='"+item.isuCd+"'/></tr>")
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
        var rtaRefresh=setInterval(getRealTimeStock, 5000);
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
    var myStockListUpdate = function(){
        $.ajax({
           url:"playerStock",
           type:"post",
           dataType:"json",
           success:function(data){
              str="";
              $("#mystockListTBody").empty();
              $.each(data, function(index, item){
                 str+="<tr><td>"+item.isuKorAbbrv+"</td>";
                 str+="<td>"+item.kind+"</td>";
                 str+="<td>"+item.plQuantity+"</td>";
                 str+="<td>"+item.priceDTO.trdPrc+"</td>";
                 str+="<td>"+item.priceDTO.trdPrc * item.plQuantity+"</td>";
                 str+="<td>"+item.earningRate+"%"+"</td><input type='hidden' value='"+item.isuCd+"'/></tr>";
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
    
    
    
      /* 주식 구매 */
	   var buyStock = function(companyId, qty){
		      $.ajax({
		    	  url:"buyStock",
		    	  dataType:"text",
		    	  data:{isuCd:companyId, plQuantity:qty},
		    	  success:function(data){
		    		  if(data == "true"){
		    			  myStockListUpdate();
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
		  /* 주식 판매 */
		   var sellStock = function(companyId, qty){
			      $.ajax({
			    	  url:"sellStock",
			    	  dataType:"text",
			    	  data:{isuCd:companyId, plQuantity:qty},
			    	  success:function(data){
			    		  if(data == "true"){
			    			  myStockListUpdate();
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
                     if(item.piIndex==0){
                        return true;
                     }
                     var items = $("<img src='"+item.itemDTO.itemImg+"' class='item-img'>").data("item" , item);
                     $("#inven-player-"+item.piIndex).html(items);
                     $("#inven-player-"+item.piIndex+" img").jqxTooltip({ content: item.itemDTO.itemName+"("+item.itemDTO.itemGrade+")", position: 'bottom', autoHide: true, 
                         name: 'movieTooltip', theme : userInfo.theme });
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
           
           // 인벤토리 판매

           $("#inven-items td").contextmenu(function() {
              var storeIsOpen = $("#store-window").jqxWindow("isOpen");
              var auctionIsOpen = $("#auction-window").jqxWindow("isOpen");
              var dataItem=$(this).children().data("item");
              
              if(storeIsOpen==true && auctionIsOpen==true) {
              
              }else if(storeIsOpen==true){
                 // 상점에 아이템 판매
                 if(dataItem){
                    if(confirm("판매하시겠습니까?")){
                       storeSell($(this).children().data("item"));
                    };
                 }
              }else if(auctionIsOpen==true){
                 // 경매장에 아이템 판매
                 if(dataItem){
                    $(".inven-auction-modal").modal("show");
                    $("#inven-auction-piSq").val(dataItem.piSq);
                 }
              }
              return false;
           });

        
        // 모달에서 판매등록
        $("#inven-auction-sell-btn").on("click",function(){
           auctionSell($("#inven-auction-piSq").val(),$("#inven-auction-imPurchasePrice").val())
        })
        
        // 상점판매
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
        

     // 경매판매
        var auctionSell = function(piSq,imPurchasePrice){
           
           $.ajax({
              url:"auctionSell",
              type:"post",
              data:"piSq="+piSq+"&imPurchasePrice="+imPurchasePrice,
              dataType:"",
              success:function(result){ 
                 if(result=="true"){
                    $(".inven-auction-modal").modal("hide");
                    playerItemSelectAll();
                    $("#inven-auction-imPurchasePrice").val("");
                    search(1)
                    $("#auction-selltab").trigger("click");
                 }else{
                    alert("잘못 된 입력 값 입니다.");
                 }
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
      
      /* 주식 */
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
                           str+="<td>"+item.kind+"</td>"
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
                    
                    },
                  
                  
                  error:function(err){
                     console.log("Exception : stockListInit");
                  }
               })
            }
         	$(document).on("click", "#stock-window tr, #rta-Window tr, #mystock-window tr",function(e){
             var cd = $(this).find(":hidden").val();
             showCompanyInfo(cd);
           })

            /* 마지막 페이지 */
            function pagenation(pageNo){ 
                 $('#stock-content #page-selection').bootpag({
                     total: pageNo, maxVisible: 10
                 })
             }
            
            /* 페이지 클릭 */
            $('#stock-content #page-selection').on("page", function(event, num){
               
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
                $("#stock-content #page-selection ul li").eq(1).trigger("click");
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
                      var priceInfo="";
                      $(".store-itemBox").empty();
                      $.each(data, function(index, item){
                         $("#store-item"+itemClass+""+index).html("<img src='" + item.itemImg +"' style='width:100%; height:100%;' id='"+item.itemCode+"'/>");  
                         item.itemPrice!=0 ? priceInfo="₩"+item.itemPrice : priceInfo="구매할 수 없는 아이템 입니다.";
                         $("#store-item"+itemClass+""+index+" img").jqxTooltip({ content: "<b>"+item.itemName+"</b>("+item.itemGrade+")"+"<br>"+priceInfo+"<br>", position: 'bottom', autoHide: true, 
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
                      case "4" : alert("랜덤박스로만 구매할 수 있습니다."); break;
                      default : alert(result+"을(를) 획득하였습니다."); break;
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
            if(confirm(friendId+"님을 친구로 추가하시겠습니까?")==false || friendId=="") return;
            ws.send("friendAdd#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"+friendId)
            $(".friend-add-modal").removeClass("in");
           $(".modal-backdrop").remove();
           $(".friend-add-modal").hide();
            
            
         })
         
         /* 친구승락 */
         $(document).on("click",".friend-accept" ,function(){
        	 var friendNickName = $(this).parent().prevAll(".name").text();
            var friendSq = $(this).parent().prevAll(".requestedFSq").val();
            ws.send("friendAccept#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"+friendNickName+"#/fuckWebSocket/#"+friendSq+"#/fuckWebSocket/#");
            
         })
         
         /* 친구거절 및 삭제 */
         $(document).on("click",".friend-reject,.friend-sendBtn .glyphicon-trash",function(){
            var friendSq = $(this).parent().prevAll(".requestedFSq").val();
            if(!friendSq){
               friendSq = $(this).parent().parent().prevAll(".ListFriendFSq").val();
            }
            
            ws.send("friendDel#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"+friendSq+"#/fuckWebSocket/#")
            friendDelBtn=0
            $("#friend-del").text("친구삭제");
         })
         
         /* 친구신청 Notify */
         $("#friend-request-noti").jqxNotification({
        	 width: 250, position: "top-right", opacity: 0.9,
             autoOpen: false, animationOpenDelay: 800, autoClose: true, autoCloseDelay: 3000, template: "info"
            });
         
         
         /* 친구삭제 버튼 클릭 시 */
         $("#friend-del").on("click",function(){
        	 if(friendDelBtn==0){
	        	 $(this).html("&nbsp&nbsp&nbsp취소&nbsp&nbsp&nbsp");
	        	 $("#friend-list-current .glyphicon-envelope").attr("class","glyphicon glyphicon-trash");
	        	 $(".friend-sendBtn").attr("class","btn btn-danger friend-sendBtn ")
	        	 friendDelBtn=1
        	 }else{
        		 $(this).text("친구삭제");
        		 $("#friend-list-current .glyphicon-trash").attr("class","glyphicon glyphicon-envelope");
        		 $(".friend-sendBtn").attr("class","btn btn-info friend-sendBtn ")
	        	 friendDelBtn=0
        	 }
         })
         
         /* 친구리스트 */
         friendSelectAll = function(data){
              var requestedFriend=""; 
              var ListFriend=""; 
              var friendBtnColor=""; 
              var friendNickname= "";
             
              var avatarEquiAry;
              
              $("#friend-content .list-group > .title").siblings("li").remove(); 
              
              $.each(data.data,function(index,item){
                 
                 userInfo.nickName==item.playerNickname ? friendNickname=item.playerNickname2 : 
                                                  friendNickname=item.playerNickname;
                 
                 if(item.friendIsAccepted=="F"){
                    if(item.type=="A" && item.playerNickname==userInfo.nickName){
                         requestedFriend+="<li href='#' class='list-group-item text-left'>";
                         requestedFriend+="<div class='friend-avatar-div' >";
                         avatarEquiAry = avatarEqui("friend",item.friendGender,item.playerItemDTO)
                         
                         for(var k=0; k<=5; k++ ){
                            requestedFriend += avatarEquiAry[k];
                         }
                         
                         requestedFriend+="</div>"; 
                         requestedFriend+="<label class='name'>"+friendNickname+"</label>"; 
                         requestedFriend+="<input type='hidden' class='requestedFSq' value='"+item.friendSq+"'>" 
                         requestedFriend+="<div class='pull-right'>"; 
                         requestedFriend+="<button type='button' class='btn btn-success friend-accept btn-circle'><i class='glyphicon glyphicon-ok'></i></button>";
                         requestedFriend+="<button type='button' class='btn btn-danger friend-reject btn-circle'><i class='glyphicon glyphicon-remove'></i></button>"; 
                         requestedFriend+="</div></li>"; 
                    }else if(item.type=="B" && item.playerNickname2==userInfo.nickName){
                       requestedFriend+="<li href='#' class='list-group-item text-left'>";
                         requestedFriend+="<div class='friend-avatar-div' >";
                         avatarEquiAry = avatarEqui("friend",item.friendGender,item.playerItemDTO)
                         
                         for(var k=0; k<=5; k++ ){
                            requestedFriend += avatarEquiAry[k];
                         }
                         
                         requestedFriend+="</div>"; 
                         requestedFriend+="<label class='name'>"+friendNickname+"</label>"; 
                         requestedFriend+="<input type='hidden' class='requestedFSq' value='"+item.friendSq+"'>" 
                         requestedFriend+="<div class='pull-right'>"; 
                         requestedFriend+="<button type='button' class='btn btn-success friend-accept btn-circle'><i class='glyphicon glyphicon-ok'></i></button>";
                         requestedFriend+="<button type='button' class='btn btn-danger friend-reject btn-circle'><i class='glyphicon glyphicon-remove'></i></button>"; 
                         requestedFriend+="</div></li>"; 
                    }
               }else{
                  ListFriend += "<li href='#' class='list-group-item text-left'>";
                  ListFriend += "<div class='friend-avatar-div'>";
                  
                  avatarEquiAry = avatarEqui("friend",item.friendGender,item.playerItemDTO)
                  for(var k=0; k<=5; k++ ){
                     ListFriend += avatarEquiAry[k];
                  }
                  
                  ListFriend += "</div>";
                  
                  if(item.onOrOff==true){ 
                     friendBtnColor="green"; 
                  }else{
                     friendBtnColor="red"; 
                  } 
                  
                  ListFriend += "<div class='friend-icon "+friendBtnColor+"'> </div>";
                  ListFriend += "<label class='name'>"+friendNickname+"</label>";
                  ListFriend += "<input type='hidden' class='ListFriendFSq' value='"+item.friendSq+"'>";
                  ListFriend += "<div class='pull-right'>";
                  ListFriend += "<button type='button' class='btn btn-info friend-sendBtn '>";
                  ListFriend += "<i class='glyphicon glyphicon-envelope'></i></button></div></li>";
                  
               } 
            });
            $("#friend-list-que ul").append(requestedFriend);
            $("#friend-list-group ul").append(ListFriend);
           }
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
       
       /* 가나다인척하기 */
       financialSearch("가")
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
        	search()
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
      var userInfoInit = function(){
      
         
         /*유저 아바타*/
          var myInfoAvatar = function(nickName,window){
             $.ajax({
                url:"userInfo2",
                type:"post",
                data:"tragetPlayer="+nickName, 
                dataType:"json",
                success:function(data){
                   str="";
                   $.each(data,function(index,item){
                      var avatarEquiAry = avatarEqui("userinfo",item.playerGender,item.playerItemDTO);
                      for(var i=0; i<=5; i++ ){
                          str += avatarEquiAry[i];
                      }
                      $(window + " .userinfo-avatar-div").empty();
                      $(window + " .userinfo-avatar-div").html(str);   
                   })
                   
                },
                error:function(err){
                   console.log("Exception : myInfoAvatar ");
                }
             })
          }
          showUserInfo = function(nickName){
             $.ajax({
                url:"userInfo",
                data:{targetPlayer:nickName},
                dataType:"html",
                success:function(data){
                 $("#main").append(data);
                   $("#userinfo-player-"+nickName).jqxWindow({
                        width:"450",
                        height:"400",
                        showCollapseButton: true,
                        autoOpen:true,
                        closeButtonAction: 'close',
                        theme:userInfo.theme
                   });
                   myInfoAvatar(nickName,"#userinfo-player-"+nickName);
                },
                error:function(err){
                   console.log("Exception : showUserInfo");
                }
             })
          }
      };
      
      /*친구정보보기*/
      $(document).on("click","#friend-content .name, #ranking-content .ranking-playernickname",function(){
         showUserInfo($(this).text());
      })
      
      

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
                   $("#news-search-results").empty().append(str);              
                   
                },
                error:function(err){
                   console.log("Exception : searchNews(keyword : "+keyword+")");
                }
             });
          })
          
       }
       

       /* 1:1 채팅 */
       var showChatWindow = function(nick){
          if(!$("#chat-no-"+nick).length || !setting.chat[nick]){
        	 setting.chat[nick] = {
        			 roomNo : nick,
        			 count : 0,
        			 isOBO : true
        	 };
        	  
             var str = "";
             str += "<div class='chat-window container-fluid' id='chat-no-"+nick+"'>";
             str += "<div>Chat</div><div class='chat-content row-fluid'>"
             str += "<div class='col-md-12 bg-white'><div class='chat-message chat-one'><ul class='chat-group'>"
             str += "</ul></div>"
             str += "<div class='col-md-12 chat-box bg-white'><div class='input-group'>"
             str += "<input class='form-control border no-shadow no-rounded chat-output' placeholder='Type your message here'>"
             str += "<span class='input-group-btn chat-sendBtn'><button class='btn btn-success no-rounded' type='button'>Send</button>"
             str += "</span></div></div></div></div></div>"
               
           $("#main").append(str);
             var chatContent = $("#chat-no-"+nick);
             var chatOut = $("#chat-no-"+nick + " .chat-output");
             
           $("#chat-no-"+nick).jqxWindow({
             width:"430",
             height:"700",
             resizable:false,
             showCollapseButton: true,
             closeButtonAction: 'hide',
             theme:userInfo.theme
            });
           updateMyChat();
           /* 1:1 채팅 보내기 */
           $(document).on("click","#chat-no-"+nick+" .chat-sendBtn",function(evt){
        	   if($(chatOut).val() == ""){
        		   return;
        	   }
              sendMsg("chatOneByOne", userInfo.nickName, nick, $(chatOut).val());
              $(chatOut).val("");
           });
           
           $(chatOut).on("keyup",function(){
        	   if($(chatOut).val() == ""){
        		   return;
        	   }
               if(event.keyCode == 13) {
                   sendMsg("chatOneByOne", userInfo.nickName, nick, $(chatOut).val());
                   $(chatOut).val("");
               }
               
            });
          }else{
             $("#chat-no-"+nick).jqxWindow("show");
          }
       }
       
      var settingInit = function(){
    	  var psMyPage="";
  		  var psChatting="";
  	 	  var psFriendAdd="";
  		  var psTheme="";
  		
  		// 설정정보 저장
  		$("#setting-save").on("click", function(){
  			confirmCheckBox();
  			$.ajax({
  				url:"settingSave",
  				type:"post",
  				dataType:"text",
  				data:"psMyPage="+psMyPage+"&psChatting="+psChatting+"&psFriendAdd="+psFriendAdd+"&psTheme="+$("#setting-select").val()+"&psBgmSound=0",
  				success:function(){
  					location.reload(true)
  				},
  				error:function(err){
  					console.log("Exception : 설정정보 저장")
  				}
  			})
  		})
  		
  		// 설정 초기화
  		$("#setting-initialization").on("click",function(){
  			$.ajax({
  				url:"settingReset",
  				type:"post",
  				dataType:"text",
  				success:function(result){
  					$("#setting-myInfo").prop("checked",true);
  					$("#setting-whisper").prop("checked",true);
  					$("#setting-friend").prop("checked",true);
  					$("#setting-select").val("kokomo");
  					location.reload(true)
  				},
  				error:function(err){
  					console.log("Exception : 설정 초기화")
  				}
  			})
  		})
  		
  		// 셋팅정보 로드
  		function settingLoad(){
  			$.ajax({
  				url:"settingSelect",
  				type:"post",
  				dataType:"json",
  				success:function(result){
  					result.psMyPage=="T" ? $("#setting-myInfo").prop("checked",true) : $("#setting-myInfo").prop("checked",false);
  					result.psChatting=="T" ? $("#setting-whisper").prop("checked",true) : $("#setting-whisper").prop("checked",false)
  					result.psFriendAdd=="T" ? $("#setting-friend").prop("checked",true) : $("#setting-friend").prop("checked",false)
  					$("#setting-select").val(result.psTheme);
  				},
  				error:function(err){
  					console.log("Exception : settingLoad")
  				}
  			})
  		}
  		
  		// 체크박스 상태변수
  		function confirmCheckBox(){
  			$("#setting-myInfo").is(":checked") ? psMyPage="T" : psMyPage="F"
  			$("#setting-whisper").is(":checked") ? psChatting="T" : psChatting="F"
  			$("#setting-friend").is(":checked") ? psFriendAdd="T" : psFriendAdd="F" 
  		}
  		
  		 $("#setting-window").jqxWindow({
             width:"360",
             height:"360",
             resizable:true,
             showCollapseButton: true,
             autoOpen:false,
             theme:userInfo.theme
           });
  		
  		settingLoad()
      }
      
      /* 채팅방 윈도우 */
      var initChatRoom = function(){
         
     $("#chatroom-create-btn").click(function(){
        $("#chatroom-create-modal").modal('show');
     });
     
     $("#chat-create-confirm").click(function(){
        sendMsg("chatRoomCreate",userInfo.nickName, $("#chat-create-name").val(), $("#chat-create-pwd").val(), $("#chat-create-max").val());
        $("#chatroom-create-modal").modal('toggle');
     });
         
      $("#chatroom-window").jqxWindow({
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
      } 
      
      /*랭킹*/
      var initRanking = function(){
    	  /*랭킹 조회*/
 		 var rankingSelect = function(kind){
 			 $.ajax({
 				 url:"getRank",
 				 type:"post",
 				 dataType:"json",
 				 data:"kind="+kind,
 				 success:function(data){
 					 rankingdraw(kind,data);
 			   	 	
 				 },
 				 error:function(){
 					 console.log("Exception : 랭크조회");
 				 }
 			 })
 		 }
 		 
 		 /* 시즌랭킹 탭 */
 		 $("#ranking-tab-season").on("click",function(){
 			 rankingSelect("s");
 		 })
 		 
 		 /* 일일랭킹 탭*/
 		 $("#ranking-tab-daily").on("click",function(){
 			 rankingSelect("d");
 		 })
 		 
 		 /*랭킹검색*/ //ResponseBody아님!!
 		 $("#ranking-search").on("keyup",function(){
 			 
 			 if(event.keyCode == 13) {
 			    var kind ="";
 			 	$("#ranking-myTabContent .active").attr("id")=="ranking-season" ? kind="s" : kind="d";
 		 		$.ajax({
 					 url:"userInfo2",
 					 type:"post",
 					 data:"tragetPlayer="+$(this).val(),
 					 dataType:"json",
 					 success:function(data){
 						 rankingdraw(kind, data);
 					 },
 					 error:function(err){
 						 console.log("Exception : 랭킹검색 ");
 					 }
 				 })
 		 	 }
 		 })
 		 
 		/*랭킹 그리기*/
         var rankingdraw = function(kind, data){
            var str="";
            $.each(data,function(index,item){
               if(item.playerSeasonRank==0 || item.playerDailyRank==0){
                 return true
               }
               
               kind=="s" ? str+="<tr><td>"+item.playerSeasonRank+"</td>" : str+="<tr><td>"+item.playerDailyRank+"</td>";
               str+="<td><div class='ranking-avatar-div'>";
               
               var avatarEquiAry = avatarEqui("ranking",item.playerGender,item.playerItemDTO);
               
               for(var i=0; i<=5; i++ ){
                   str += avatarEquiAry[i];
                }
               str+="</div>";
               str+="</td>";
               str+="<td class='ranking-playernickname'>"+item.playerNickname+"</td>";
               kind=="s" ? str+="<td>"+item.totalEarningRate+"%</td>" : str+="<td>"+item.earningRate+"%</td>";
               str+="<td>"+item.totalMoney+"</td></tr>";
            }) 
            if(kind=="s"){
               $("#ranking-season-tbody").empty();
               $("#ranking-season-tbody").html(str);                   
            }else{
               $("#ranking-daily-tbody").empty();
               $("#ranking-daily-tbody").html(str);
            }
         }
 		 
 		$("#ranking-window").jqxWindow({
 	          theme:userInfo.theme,
 	          height:440,
 	          width:550,
 	          maxWidth:800,
 	          minWidth:400,
 	          minHeight:400,
 	          resizable:true,
 	          autoOpen:false,
 	          showCollapseButton: true
 	        });
 		 
 		 rankingSelect("s");
 		 
      }
      
      
      /* 방 유저 업데이트 */
      var getChatCurrentUsers = function(players){
    	  var str = "<div class='chat-room-online-users'><table class='table table-condensed table-bordered'><tbody>";
    	  
    	  
    	  for(var i=0; i<players.length; i++){
    		  str += "<tr>";
    		  str += "<td><div class='chat-avatar-div'>" + avatarEqui("chat",players[i].playerGender,players[i].playerItemDTO).join("") + "</div></td>";
    		  str += "<td>" + players[i].playerNickname + "</td>";
    		  str += "</tr>"
    	  }
    	  
    	  str += "</tbody></table></div>";
    	  
    	  return str;
      }

      /* 채팅 리스트 */
      var chatList = function(content){
         var str = "";
         var data = content.data;
         for(var i=0; i<data.length;i++){
           str += "<div class='chatroom-rooms'> <a href='#' class='clearfix'><div class='chatroom-name'><h3><strong>";
           str += "<input type='hidden' value='"+data[i].roomNo+"'>";
           str += data[i].roomName;
           str += "</strong></h3></a><small class='text-muted'>";
           str += data[i].playerList.length + " / " + data[i].maxNum;
           str += "</small>"
           if(data[i].password == "T"){
             str += "<small class='chat-alert label label-danger chat-label-pwd'>password</small>"
           }
           str += "</div>";
         }
         
         
         $("#chatroom-list-ul").empty().html(str);
         var eventTargets = $("#chatroom-list-ul .chatroom-rooms");
         
         $(eventTargets).click(function(event){
            var roomNo = $(this).find(":hidden").val();
            if(setting.chat[roomNo]){
            	return;
            }
            
            if($(this).find(".chat-label-pwd").length){
              var password = prompt("비밀번호를 입력해주세요", "");
              sendMsg("chatRoomJoin",userInfo.nickName,roomNo, password);
              return;
            }else{
            	sendMsg("chatRoomJoin",userInfo.nickName,roomNo);
            }
         })
      }
      
      /* Chat In */
      var chatIn = function(content){
    	  var roomNo = content.data.roomNo;
          setting.chat[roomNo].playerList = content.data.playerList;

         var target;
         var str = "";
         var n = $(document).height();
         target = $("#chat-roomNo-"+roomNo);
         
         str += "<li class='well-sm text-center'><div class='chat-body clearfix'><h4 class='text-success'>";
         str += content.data.sender + "님이 입장하셨습니다."
         str += "</h3></div></li>";
         
         var cul = $(target).find(".chat-group");
         var n = $(cul).append(str).css("height");
         $(target).find(".chat-message").animate({ scrollTop: n }, 50).jqxWindow("show");
         
         refreshRoomInfo(roomNo);
      }
      
      /* Chat Out */
      var chatOut = function(content){
    	 var roomNo = content.data.roomNo;
    	 setting.chat[roomNo].playerList = content.data.playerList;
         var target;
         var target;
         var str = "";
         var n = $(document).height();
         target = $("#chat-roomNo-"+roomNo);
         
         str += "<li class='well-sm text-center'><div class='chat-body clearfix'><h4 class='text-danger'>";
         str += content.data.sender + "님이 퇴장하셨습니다."
         str += "</h3></div></li>";
         
         var cul = $(target).find(".chat-group");
         var n = $(cul).append(str).css("height");
         $(target).find(".chat-message").animate({ scrollTop: n }, 50).jqxWindow("show");
         
         refreshRoomInfo(roomNo);
      }
      
      /* 방 정보 갱신 */
      var refreshRoomInfo = function(roomNo){
    	  var chatPage = setting.chat[roomNo];
    	  
          var chatContent = $("#chat-roomNo-"+roomNo);
          var popover = $(chatContent).find(".chat-room-popover");
          var onlineLabel = $(chatContent).find(".chat-current-online");
          
          if(chatPage.playerList.length>=chatPage.maxNum){
          	$(onlineLabel).removeClass("label-success").addClass("label-danger");
          }else{
        	  $(onlineLabel).removeClass("label-danger").addClass("label-success");
          }
          $(onlineLabel).text(chatPage.playerList.length + " / " + chatPage.maxNum);

          var popoverContents = $("#chat-room-popover-"+roomNo+" .chat-room-popover-contents");
          $(popoverContents).html(getChatCurrentUsers(chatPage.playerList));
          
          $(popover).jqxPopover({
       	   title: "접속 인원", 
       	   showCloseButton: true, 
       	   selector: $(chatContent).find(".chat-room-header"),
       	   theme : userInfo.theme
          });
          
      }
      
      /* 내 채팅 업데이트*/
      var updateMyChat = function(){
    	  var str = "";
    	  for(var key in setting.chat){
    		  str += "<li class='chatroom-mychat-li'><input type='hidden' value='"+setting.chat[key].roomNo+"'><a href='#' class='clearfix'>";
    		  str += "<img src='http://bootdey.com/img/Content/user_1.jpg' alt='' class='img-circle'>";
    		  str += "<div class='chatroom-name'><strong>";
    		  str += setting.chat[key].isOBO ? setting.chat[key].roomNo : setting.chat[key].roomName;
    		  str += "</strong></div><div class='chat-last-message text-muted'></div>";
    		  str += "<small class='chat-time text-muted'></small><small class='chat-alert chat-count label label-danger'></small>";
    		  str += "</a></li>";
    	  }

    	  $("#chatroom-mychat-ul").html(str);
    	  
    	  for(var key in setting.chat){
    		  updateMyChatOne(key);
    	  }
      }
      
      /* 내 채팅 개별 업데이트 */
      var updateMyChatOne = function(roomNo){
    	  var myChat = $("#chatroom-mychat-ul li input[value="+roomNo+"]").parent();
    	  $(myChat).find(".chat-last-message").text(setting.chat[roomNo].lastMsg);
    	  $(myChat).find(".chat-time").text(setting.chat[roomNo].lastTime);
    	  $(myChat).find(".chat-count").text(setting.chat[roomNo].count);
      }
      
      /* 내 채팅 클릭 이벤트 */
      $(document).on("click","#chatroom-mychat-ul li", function(event){
    	  var roomNo = $(this).find(":hidden").val();
    	  
    	  if(setting.chat[roomNo].isOBO){
    		  var targetWindow = $("#chat-no-"+roomNo);
    	  }else{
	    	  var targetWindow = $("#chat-roomNo-"+roomNo);
	      }
    	  $(targetWindow).jqxWindow("open");
    	  $(targetWindow).jqxWindow("focus");
    	  $(targetWindow).jqxWindow("expand");
    	  $(targetWindow).jqxWindow('bringToFront');
      });
      
      
      invenInit();
      rtaInit();
      stockListInit();
      storeInit();
      friendBookinit();
      financialInit();
      auctionInit();
      myStockInit();
      newsSearchInit();
      settingInit();
      initChatRoom();
      initRanking();
      userInfoInit();
      
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
            $("#setting-btn").setBtn($("#setting-window"));
            $("#chatroom-btn").setBtn($("#chatroom-window"));
            $("#ranking-btn").setBtn($("#ranking-window"));
            $("#myinfo-btn").click(function(){
            showUserInfo(userInfo.nickName);
          });
      }();

   
      /* ======================Set WebSocket============================= */
      var sendMsg = function(){
          var args = Array.prototype.slice.call(arguments, 0);
          var msg = args.join("#/fuckWebSocket/#");
          console.log("Send to server : " + msg);
          ws.send(msg);
       }
      
      connect(function(){

          
          $('#logout').click(function() {
              disconnect();
              location.href = "logout";
           });
          /* 채팅방 로드*/
          var chatRoomSelect = function(page){
             sendMsg("chatRoomSelect", userInfo.nickName,page);
          }
          
          
          
          /* 채팅 메세지 */
          var oneByOne = function(evt){
             var target;
             var roomNo;
             var str = "";
            
             var n = $(document).height();
             if(evt.data.sender == userInfo.nickName){
                target = $("#chat-no-"+evt.data.receiver);
                roomNo = evt.data.receiver;
                str += "<li class='right clearfix'><span class='chat-img pull-right'>";
             }else{
                target = $("#chat-no-"+evt.data.sender);
                if(!$(target).length){
                   showChatWindow(evt.data.sender);
                   roomNo = evt.data.sender;
                   oneByOne(evt);
                   return;
                }
                
                str += "<li class='left clearfix'><span class='chat-img pull-left'>";
                
             }
             
             var cul = $(target).find(".chat-group");
             
                 str += "<div class='chat-avatar-div'>";
                 
                 avatarEquiAry = avatarEqui("chat",evt.data.gender,evt.data.playerItem)
                 
             for(var i=0; i<=5; i++ ){
                str += avatarEquiAry[i];
             }
                 str += "</div>";
                 str += "</span><div class='chat-body clearfix'><div class='header'><strong class='primary-font'>";
                 str += evt.data.sender;
                 str += "</strong><small class='pull-right text-muted'><i class='fa fa-clock-o'></i>";
                 str += evt.data.time;
                 str += "</small></div><p>";
                 str += evt.data.msg;
                 str+="</p></div></li>";
               
                 var n = $(cul).append(str).css("height");
                 
                 $(target).find(".chat-message").animate({ scrollTop: n }, 50).jqxWindow("show");
                 
                 setting.chat[roomNo].lastMsg = evt.data.msg;
                 setting.chat[roomNo].lastTime = evt.data.time;
                 
                 updateMyChatOne(roomNo);
          }
          
          /* 채팅방 메세지*/
          var chatMsg = function(evt){
             var target;
             var str = "";
             var n = $(document).height();
             target = $("#chat-roomNo-"+evt.data.roomNo);
             if(evt.data.sender == userInfo.nickName){
                str += "<li class='right clearfix'><span class='chat-img pull-right'>";
             }else{
                str += "<li class='left clearfix'><span class='chat-img pull-left'>";
             }
             
             var cul = $(target).find(".chat-group");
             
             str += "<div class='chat-avatar-div'>";
             
             avatarEquiAry = avatarEqui("chat",evt.data.gender,evt.data.playerItem)
             
	         for(var i=0; i<=5; i++ ){
	            str += avatarEquiAry[i];
	         }
	             str += "</div>";
	             str += "</span><div class='chat-body clearfix'><div class='header'><strong class='primary-font'>";
	             str += evt.data.sender;
	             str += "</strong><small class='pull-right text-muted'><i class='fa fa-clock-o'></i>";
	             str += evt.data.time;
	             str += "</small></div><p>";
	             str += evt.data.msg;
	             str+="</p></div></li>";
	             
                 var n = $(cul).append(str).css("height");
                 $(target).find(".chat-message").animate({ scrollTop: n }, 50).jqxWindow("show");
               
                 var roomObj = setting.chat[evt.data.roomNo];
                 roomObj.lastMsg = evt.data.msg
                 roomObj.lastTime = evt.data.time;
                 updateMyChatOne(evt.data.roomNo);
                 
          }
          
          
          	
          /* 친구창 채팅 버튼*/
         $(document).on("click",".friend-sendBtn.btn-info",function(evt){
            var name = $(this).parent().siblings(".name").text();
            showChatWindow(name);
         });
             
          /* 채팅 시작 */
          var chatStart = function(content){
             var data = content.data;
             var roomNo = content.data.roomNo;
             if($("#chat-roomNo-"+roomNo).length || setting.chat[roomNo]){
            	return;   
               }
               
               setting.chat[roomNo] = data;
                  var str = "";
                  str += "<div class='chat-window container-fluid' id='chat-roomNo-"+roomNo+"'>";
                  str += "<div>Chat - "+ roomNo +"</div><div class='chat-content row-fluid'>";
                  str += "<div class='col-md-12 chat-room-info'><span class='label label-default pull-left'>No."+roomNo+"</span>";
                  str += "<label class='chat-room-header'>"+content.data.roomName+"</label>";
                  str += "<div id='chat-room-popover-"+roomNo+"'><div class='chat-room-popover-contents'></div>";
                  str += "<div class='col-xs-12' style='padding:0; margin-bottom:2px'><button class='btn btn-sm btn-danger btn-block'>나가기</button></div></div>"
                  str += "<span class='label label-success pull-right chat-current-online'></span>";
            	  if(content.data.password){
            		  str += "<span class='label label-danger pull-right'><i class='fa fa-key' aria-hidden='true'></i></span>";
            	  }
                  str += "</div><div class='chat-message col-md-12'><ul class='chat-group'></ul></div><div class='col-md-12 chat-box bg-white'><div class='input-group'>";
                  str += "<input class='form-control border no-shadow no-rounded chat-output' placeholder='Type your message here'>";
                  str += "<span class='input-group-btn chat-sendBtn'><button class='btn btn-success no-rounded' type='button'>Send</button>";
                  str += "</span></div></div></div></div></div>"
                    
                $("#main").append(str);
                  var chatContent = $("#chat-roomNo-"+roomNo);
                  var chatOut = $("#chat-roomNo-"+roomNo + " .chat-output");
                  var popover = $("#chat-room-popover-"+roomNo);
                  var closeBtn = $(chatContent).find("button.btn-danger.btn-block");
                $(chatContent).jqxWindow({
                  width:"430",
                  height:"700",
                  resizable:false,
                  showCollapseButton: true,
                  closeButtonAction: 'hide',
                  theme:userInfo.theme
                 });
                
                var onlineLabel = $(chatContent).find(".chat-current-online");
                
                if(content.data.playerList.length>=content.data.maxNum){
                	$(onlineLabel).removeClass("label-success").addClass("label-danger");
                }
                $(onlineLabel).text(content.data.playerList.length + " / " + content.data.maxNum);
                
                
                /* 채팅방 채팅 보내기 */
                $(document).on("click","#chat-roomNo-"+roomNo+" .chat-sendBtn",function(event){
             	   if($(chatOut).val() == ""){
             		   return;
             	   }
                   sendMsg("chatRoomChat", userInfo.nickName, roomNo, $(chatOut).val());
                   $(chatOut).val("");
                });
                
                $(chatOut).on("keyup",function(){
             	   if($(chatOut).val() == ""){
             		   return;
             	   }
                    if(event.keyCode == 13) {
     	               sendMsg("chatRoomChat", userInfo.nickName, roomNo, $(chatOut).val());
     	               $(chatOut).val("");
                    }
                 });
                
                $(closeBtn).click(function(evt){
                	delete setting.chat[roomNo];
                	sendMsg("chatRoomOut", userInfo.nickName, roomNo);
                	$("#chat-room-popover-"+roomNo).remove();
                	$(chatContent).remove();
                	updateMyChat();
                	
                });
                var popoverContents = $(popover).find(".chat-room-popover-contents");
                $(popoverContents).html(getChatCurrentUsers(content.data.playerList));
                //접속 인원 보기
                $(popover).jqxPopover({
             	   title: "접속 인원", 
             	   showCloseButton: true, 
             	   selector: $(chatContent).find(".chat-room-header"),
             	   theme : userInfo.theme
                });
                
                updateMyChat();
               
          }

          ws.onmessage = function(event){
              var data = JSON.parse(event.data);
              
              switch(data.type){
                 case "friendSelect" : friendSelectAll(data);  break;
                 
                 case "notiFriendAdd" : $("#noti-msg").text(data.data.playerNickname+"님께서 친구신청을 하셨습니다.");
						          	    $("#friend-request-noti").jqxNotification("open");
						          	    ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"); break;
						          	    
                 case "friendDel" :ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"); break;
                 
                 case "notiFriendAcceptMe" :ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"); break;
                 
                 case "notiFriendAcceptYou":ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#");
							          	    $("#noti-msg").text(data.data+"님께서 친구수락을 하셨습니다.");
							        	    $("#friend-request-noti").jqxNotification("open"); break;
							        	    
                 case "notiFriendLogin" : $("#noti-msg").text(data.data+"님께서 로그인을 하셨습니다.");
							          	  $("#friend-request-noti").jqxNotification("open");
							          	  ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"); break;
                 case "notiFriendLogout" :$("#noti-msg").text(data.data+"님께서 로그아웃 하셨습니다.");
						                  $("#friend-request-noti").jqxNotification("open");
							          	  ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#"); break;
                 case "notiIsAuctionFinish" : 
                	 				if(data.data=="true"){
							           $("#noti-msg").html("낙찰 된 물품이 있습니다.<br>확인해주십시오");
							           $("#friend-request-noti").jqxNotification("open");
							        }; break;
                 case "notiAuctionEndBySeller" : $("#noti-msg").html(data.data.itemName+"이 "+data.data.imPrice+"루비에 팔렸습니다.<br>낙찰금액을 수령해 주십시오.");
                    							 $("#friend-request-noti").jqxNotification("open");break;			          	  
							          	  
							          	  
							          	  
                 case "chatMsg" : chatMsg(data); break;
                 case "chatIn" : chatIn(data); break;
                 case "chatOut" : chatOut(data); break;
                 case "oneByOne" : oneByOne(data); break;
                 case "chatStart" : chatStart(data); break;
                 case "chatList" : chatList(data); break;
                 
                 default : console.log("unknow msg : " + data.type);
              }
           }
           
           /* 최초 친구리스트 */
           ws.send("friendSelect#/fuckWebSocket/#"+userInfo.nickName+"#/fuckWebSocket/#");
           
      });
      $(".side-avatar").css({
          width : $("#side-avatar-player-clothes").css("width"),
          height : $("#side-avatar-player-clothes").css("height")
       });
      
      $("#loader").css("visibility","visible");
      $("#loading-content").remove();
      
      
   };
   updatePI(initContent);
});