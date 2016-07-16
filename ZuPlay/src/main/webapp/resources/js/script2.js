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
            
            updateLabel();

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
   
   $.fn.upDown = function(){
	   var checkVal = $(this).text();
	   console.log("value : " + checkVal);
	   if(!checkVal || checkVal == "0" || checkVal == 0){
		   return;
	   }
	   
	   var checker = checkVal.substr(0,1);
	   console.log("value : " + checker);
	   if(checker == "-"){
		   $(this).addClass("price-down");
		   $(this).text(checkVal.substr(1).format());
	   }else{
		   $(this).addClass("price-up");
	   }
	   
	   return this;
   }
   
   /* 내 정보 갱신 (Money, Ruby) */
   var updateLabel = function(){
	   $(".side-money label").text(userInfo.money.format());
	   $(".side-ruby label").text(userInfo.ruby.format());
   }
   
   
   var initContent = function(){
      console.log("start init content");
      
    // 전체 우클릭 제어
      $("body").contextmenu(function(){
         return false;
      })
      
   
      updateLabel();
      
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
             width:650,
             height:480,
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
                             $(tbd).append("<tr><td>"+item.isuKorAbbrv+"</td> <td>"+(item.priceDTO.trdPrc).format()+"</td> <td>"+(item.priceDTO.cmpprevddPrc).format()+"</td> <td>"+item.priceDTO.fluctuationRate+"</td><td>"+(item.priceDTO.accTrdvol).format()+"</td><input type='hidden' value='"+item.isuCd+"'/></tr>")
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
      var myStockListUpdate = function(targetPlayer){
          if(targetPlayer != userInfo.nickName){
             userStockListUpdate(targetPlayer);
             return;
          }
           $.ajax({
              url:"playerStock",
              type:"post",
              data:"targetPlayer="+userInfo.nickName,
              dataType:"json",
              success:function(data){
                 var str="";
                 $("#mystockListTBody").empty();
                 $.each(data, function(index, item){
                    str+="<tr><td>"+item.isuKorAbbrv+"</td>";
                    str+="<td>"+item.kind+"</td>";
                    str+="<td>"+(item.plQuantity).format()+"</td>";
                    str+="<td>"+(item.priceDTO.trdPrc).format()+"</td>";
                    str+="<td>"+(item.priceDTO.trdPrc * item.plQuantity).format()+"</td>";
                    str+="<td>"+item.priceDTO.fluctuationRate+"</td>"
                    str+="<td>"+(item.earningRate).toFixed(2)+"</td><input type='hidden' value='"+item.isuCd+"'/></tr>";
                 });
                 $("#mystockListTBody").html(str);
              },
              error:function(err){
                 console.log("Exception : myStockListUpdate");
              }
           });
        }
       /*유저 주식 확인*/
      var userStockListUpdate = function(targetPlayer){
          $.ajax({
             url:"playerStock",
             type:"post",
             data:"targetPlayer="+targetPlayer,
             dataType:"json",
             success:function(data){
            	console.log(data);
            	var table = "<div class='stock-user-window'><div>유저주식 - "+targetPlayer+"</div><div class='stock-user-content'>";
            	table += "<table class='table table-bordered table-hover'><thead><tr>";
            	table += "<th>종목명</th><th>분야</th><th>수량</th><th>체결가</th><th>총가치</th><th>등락률</th><th>수익률</th></tr>"
            	table += "</thead><tbody></tbody></table></div></div>";
                var str="";

                $.each(data, function(index, item){
                   str+="<tr><td>"+item.isuKorAbbrv+"</td>";
                   str+="<td>"+item.kind+"</td>";
                   str+="<td>"+(item.plQuantity).format()+"</td>";
                   str+="<td>"+(item.priceDTO.trdPrc).format()+"</td>";
                   str+="<td>"+(item.priceDTO.trdPrc * item.plQuantity).format()+"</td>";
                   str+="<td>"+item.priceDTO.fluctuationRate+"</td>"
                   str+="<td>"+(item.earningRate).toFixed(2)+"</td><input type='hidden' value='"+item.isuCd+"'/></tr>";
                });
                
                console.log(str);

                $(table).jqxWindow({
                    width:"650",
                    height:"450",
                    resizable:true,
                    showCollapseButton: true,
                    autoOpen:true,
                    closeButtonAction: "close",
                    theme:userInfo.theme
                }).find('tbody').html(str);
             },
             error:function(err){
                console.log("Exception : userStockListUpdate");
             }
          });
       }
      
       var myStockInit = function(){
          $("#mystock-window").jqxWindow({
                width:"650",
                height:"450",
                resizable:true,
                showCollapseButton: true,
                autoOpen:false,
                theme:userInfo.theme
              });
          myStockListUpdate(userInfo.nickName);
       }
      

       
       /* 주식 구매 */
       /* buyStock , sellStock*/
        var tradingStock = function(companyId, qty, url){
              $.ajax({
                 url:url.toString(),
                 dataType:"text",
                 data:{isuCd:companyId, plQuantity:qty},
                 success:function(data){
                    alert(data);
                    
                    showCompanyInfo(companyId);
                    updatePI(myStockListUpdate(userInfo.nickName));
                 },
                 error:function(){
                    console.log("Exception : tradingStock");
                 }
              });
          };
          

          /* 종목명 클릭 시 상세정보 띄어줌. */
          var showCompanyInfo = function(code){
             if(!code)
                return;
              var companyId = "#company-"+code;

             $.ajax({
                url:"companyInfo",
                type:"post",
                dataType:"html",
                data:"isuCd="+code,
                success:function(data){
    	        	var position;
    	            if($(companyId).length){
    	            	position = $(companyId).offset(); 
        	            console.log(position);
    	            	$(companyId).remove();
    	            }

                    $("body").append(data);
	                   var company = $(companyId);
	                   var price = parseInt($(company).find(".company-price").val());
	                   var sellPrice = price - (price*0.015);
	                   price = price + (price * 0.03);
	                   var isuCd = $(company).find(".company-isuCd").val();
	                   var qty = parseInt($(company).find(".company-qty").val()/1);
	                   var ticks = parseInt(userInfo.money/price);
	                   
	                   var buySlider = $(company).find(".company-buy-slider");
	                   var buyNum = $(company).find(".company-buy-input");
	                   
	                   var sellSlider = $(company).find(".company-sell-slider");
	                   var sellNum = $(company).find(".company-sell-input");
	                   $(companyId).jqxWindow({
	                       theme:userInfo.theme,
	                       minWidth:700,
	                       width:"auto",
	                       height:500,
	                       showCollapseButton: true,
	                       closeButtonAction: 'close',
	                       position : position ? position : "center, center",
	                       resizable : true
	                     });
	                 
	                   if(ticks>0){
	
	                      $(buySlider).jqxSlider({
	                          width: "100%",
	                          tooltip: true,
	                          mode: 'fixed',
	                          min : 1,
	                          max : ticks,
	                          ticksFrequency: ticks/10,
	                          theme : userInfo.theme,
	                          step: 1
	                      }).on("change",companyBind(event,price));
	                      
	                      $(buyNum).jqxNumberInput({
	                          width: '100%',
	                          height: '25px',
	                          inputMode: 'simple',
	                          decimal: 1,
	                          spinButtons: true,
	                          spinButtonsStep: 1,
	                          min : 1,
	                          max : ticks,
	                          textAlign: "center",
	                          decimalDigits: 0,
	                          theme : userInfo.theme
	                        }).on("change",companyBind(event,price));
	                       
	                      
	                   }else{
	                      $(company).find(".company-buy").remove();
	                   }
	                   
	                    if(qty>0){
	                       
	                        $(sellNum).jqxNumberInput({
	                            width: '100%',
	                            height: '25px',
	                            inputMode: 'simple',
	                            decimal: 1,
	                            spinButtons: true,
	                            spinButtonsStep: 1,
	                            min : 1,
	                            max : qty,
	                            textAlign: "center",
	                            decimalDigits: 0,
	                            theme : userInfo.theme
	                          }).on("change",companyBind(event,sellPrice));
	                        
	                        $(sellSlider).jqxSlider({
	                            width: "100%",
	                            tooltip: true,
	                            mode: 'fixed',
	                            min : 1,
	                            ticksFrequency: qty/10,
	                            max : qty,
	                            theme : userInfo.theme,
	                            step: 1
	                        }).on("change",companyBind(event,sellPrice));
	                        
	                    }else{
	                       $(company).find(".company-sell").remove();
	                    }
	          
                },
                error:function(err){
                   console.log("Exception : showCompanyInfo");
                }
             });
          }

        /* 기업 정보 조회 */
        
        
        var companyBind = function(event,price){
           return function(event){
              var value = $(this).val();
              var parentCompany = $(this).parent().parent().parent();
              $(parentCompany).find(".company-slider").val(value);
              $(parentCompany).find(".company-input").val(value);
              $(parentCompany).find("h4").text(parseInt((value*price)).format());
           }
        }

        /*주식 구매*/
        $(document).on("click", ".company-buy-btn" ,function(event){
           var pValue = $(this).parent().parent().find(".company-input").val();
           var pIsuId = $(this).parents(".company-window").attr("id").substr(8);
           tradingStock(pIsuId, pValue, "buyStock");
         });
        
        /*주식 판매*/   
        $(document).on("click", ".company-sell-btn" ,function(event){
         var pValue = $(this).parent().parent().find(".company-input").val();
           var pIsuId = $(this).parents(".company-window").attr("id").substr(8);
           tradingStock(pIsuId, pValue, "sellStock");
        });
      

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
                  
                  /*for(var i=1; i<=6; i++){
                	  if(!$("#inven-player-"+i).children("img").attr("class")){
                		  console.log($("#inven-player-"+i).children("img").attr("class"));
                		  $("#inven-player-"+i).html("<img src='resources/img/inven/inven-"+i+".png' class='item-img'>")
                	  }
                  }*/ 
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
                    if(confirm(dataItem.itemDTO.itemName+"의 판매가격은 1000원입니다.\n판매하시겠습니까?")){
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
                 console.log(result);
                 if(result==true){
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
                           str+="<td>"+(item.priceDTO.trdPrc).format() +"</td>";
                           str+="<td>"+(item.priceDTO.cmpprevddPrc).format() +"</td>";
                           str+="<td>"+item.priceDTO.fluctuationRate +"</td>";
                           str+="<td>"+(item.priceDTO.accTrdvol).format() +"</td>";
                           str+="<td>"+(item.priceDTO.opnprc).format() +"</td>";
                           str+="<td>"+(item.priceDTO.hgprc).format() +"</td>";
                           str+="<td>"+(item.priceDTO.lwprc).format() +"</td>";
                           str+="<input type='hidden' value='"+item.isuCd+"'/></tr>"
                        }
                     })
                     
                     $("#stockListTBody").html(str);
                    $("#stockListTBody td").upDown();
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
                $("#stock-content #page-selection ul li").eq(1).trigger("click");
                $("#stock-search-keyword").val($("#stock-search").val());
                stockPageSelect(1,$("#stock-search").val());
            }

            
            stockPageSelect(1);
            
            
         $("#stock-window").jqxWindow({
             width:880,
             maxWidth:1200,
             height:630,
             maxHeight:1200,
             resizable:true,
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

          
           $('#store-content a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
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
                         item.itemPrice!=0 ? priceInfo="₩"+item.itemPrice.format() : priceInfo="구매할 수 없는 아이템 입니다.";
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
              width:"680",
              height:"390",
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
         /*친구추가 검색*/
         var friendSearch = function(){
            $.ajax({
                 url:"playerInfoSelectAll",
                 type:"post",
                 data:"keyword="+$("#friend-add-text").val(),
                 dataType:"json",
                 success:function(data){
                    console.log(data);
                    str="";
                    $.each(data,function(index,item){
                       str+="<tr class='friend-add-tr'>";
                       str+="<td><div class='friend-seach-avatar-div'>";
                       
                       var avatarEquiAry = avatarEqui("friend-seach",item.playerGender,item.playerItemDTO);
                       
                       for(var i=0; i<=5; i++ ){
                           str += avatarEquiAry[i];
                        }
                       str+="</div>";
                       str+="</td>";      
                          
                       str+="<td>"+item.playerNickname+"</td></tr>";
                       
                       
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
            position: "top-right", opacity: 0.9,
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
            console.log(data.data)
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
                         requestedFriend+="</div>"; 
                         requestedFriend+="</li>";
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
            minWidth:700,
            maxWidth:1200,
            width:700,
            minHeight:430,
            height:430,
            maxHeight:1200,
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
                        width:580,
                        height:460,
                        minHeight:420,
                        maxHeight:1000,
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
      
      /*친구주식현황보기*/
      $(document).on("click", ".userinfo-stocklist-btn",function(){
         var friendNick = $(this).parents(".userinfo-window").attr("id").split("-")[2];
         myStockListUpdate(friendNick);

      })
      
      /*친구주식분석보기*/
      $(document).on("click", ".userinfo-stockHistory-btn",function(){
         var friendNick = $(this).parents(".userinfo-window").attr("id").split("-")[2];
         
         console.log(friendNick);
         historyInit(friendNick);
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
                   console.log(result)
                  var str = "";
                  $.each(result.data.docs,function(index, item){
                    setting.page[item.uid_str] = item;
                     str += "<div class='col-md-12 search-result'>";
                     str+="<h3 class='news-search-tlink'>"+item.title;
                     str+="<input type='hidden' value='"+item.uid_str+"'></h3>";
                     str+="<small>"+item.updated_at+"</small><br>"
                    str+="<a href='#' class='search-link'>"+item.author ? item.author : "" +"("+item.publisher+")</a>";
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
     
     /*뉴스 버튼 클릭*/
     $(document).on("click", ".news-search-tlink", function(){
        console.log($(this).find("input[type=hidden]").val());
        showNews($(this).find("input[type=hidden]").val());
     })

     /* 뉴스 보기 */
     var showNews = function(uid){
        var page = setting.page[uid];
        var str = "<div class='news-page-window'><div>"+page.title+"</div><div class='news-page-content'>";
        str += "<div class='news-page-head'><h4 class=''news-page-title'>"+page.title+"</h4></div><hr class='style-glyph'><div><small>";
        str += "<a href='"+page.content_url+"' class='pull-right'>"+page.content_url+"</a></div><div class=''news-page-info'>"
        str += "<small>"+page.uid.updated_at+"</small><small>"+page.author ? page.author : "" + "</small>";
        str += "<small class='news-page-publisher pull-right block'>"+page.publisher+"</small></div>";
        str += "<div class='news-page-context'>" + page.content + "</div></div></div>";
        
        $(str).jqxWindow({
             width:"500",
             height:"700",
             showCollapseButton: true,
             closeButtonAction: 'close',
             theme:userInfo.theme
        });
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
               
               item.playerNickname==userInfo.nickName ? str+="<tr style='background-color:rgba(255,255,0,0.3);'>" : str+="<tr>";
               
               
               if(kind=="s"){
                  switch (item.playerSeasonRank) {
                  case 1: str+="<td><img src='resources/img/grade/gold.png' style='width:60px;height:60px;'></td>";   break;
                  case 2:   str+="<td><img src='resources/img/grade/silver.png' style='width:50px;height:50px;'></td>"; break;
                  case 3:   str+="<td><img src='resources/img/grade/bronze.png' style='width:40px;height:40px;'></td>"; break;
      
                  default: str+="<td>"+item.playerSeasonRank+"</td>"; break;
               }
               }else{
                  switch (item.playerDailyRank) {
                      case 1: str+="<td><img src='resources/img/grade/gold.png' style='width:60px;height:60px;'></td>";   break;
                  case 2:   str+="<td><img src='resources/img/grade/silver.png' style='width:50px;height:50px;'></td>"; break;
                  case 3:   str+="<td><img src='resources/img/grade/bronze.png' style='width:40px;height:40px;'></td>"; break;
       
                  default: str+="<td>"+item.playerDailyRank+"</td>"; break;
                  }
               }
               
               //kind=="s" ? str+="<tr><td>"+item.playerSeasonRank+"</td>" : str+="<tr><td>"+item.playerDailyRank+"</td>";
               str+="<td><div class='ranking-avatar-div'>";
               
               var avatarEquiAry = avatarEqui("ranking",item.playerGender,item.playerItemDTO);
               
               for(var i=0; i<=5; i++ ){
                   str += avatarEquiAry[i];
                }
               str+="</div>";
               str+="</td>";
               str+="<td class='ranking-playernickname'>"+item.playerNickname+"</td>";
               kind=="s" ? str+="<td>"+(item.totalEarningRate).toFixed(2)+"</td>" : str+="<td>"+(item.earningRate).toFixed(2)+"</td>";
               str+="<td>"+(item.totalMoney).format()+"</td></tr>";
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
              width:600,
              maxWidth:800,
              minWidth:400,
              minHeight:400,
              resizable:true,
              autoOpen:false,
              showCollapseButton: true
            });
        
        rankingSelect("s");
        
      }
      
      /*최초접속일 노티파이*/
      var firstNotify = function(){
         if($("#index-firstLogin")=="true"){ 
            $("#noti-msg").html( userInfo.nickName+ "님 오늘 최초접속!!<br>20000루비를 획득하셨습니다.");
            $("#friend-request-noti").jqxNotification("open");
         }
      }

      
      /*히스토리 */
      var historyInit = function(targetPlayer){
         var nowPage=0;
         var nowOrderby="";
         var nowAsc=false;
         
         /*히스토리 페이지네이션*/
         var historyPage = function(page){
            $("#history-content #page-selection").bootpag({
                  total: page, maxVisible: 10
              });
         }
         
         /*히스토리 총페이지*/
         var historyStockListCount = function(page){
            $.ajax({
               url:"getHistoryCount",
               type:"post",
               dataType:"json",
               data:"targetPlayer="+targetPlayer,
               success:function(page){
                  if(page%5==0){
                     historyPage(page/5)
                  }else{
                     historyPage(page/5+1)   
                  }
               },
               error:function(err){
                  console.log("Exception : historyStockList")
               }
            })
         }
         
         /*히스토리 내역*/
         var historyStockList = function(page,orderBy,asc){
   
            $.ajax({
               url:"getStockDealHistory", 
               type:"post",
               dataType:"json",
               data:{"targetPlayer":targetPlayer,"orderBy":orderBy,"asc":asc,"page":page},
               success:function(data){
                  var str="";
                  $.each(data,function(index,item){
                     if(item.sdhBuySell=="b"){
                        str+="<tr><td>매수</td>";
                     }else{
                        str+="<tr><td>매도</td>";
                     }
                     
                     str+="<td>"+item.masterDTO.isuKorAbbrv+"</td>";
                     str+="<td>"+item.masterDTO.kind+"</td>";
                     str+="<td>"+item.sdhDealTime+"</td>";
                     str+="<td>"+(item.sdhQuantity).format()+"</td>";
                     str+="<td>"+((item.masterDTO.priceDTO.trdPrc).format())+"</td>";
                     str+="<td>"+((item.sdhDealPrice).format())+"</td></tr>";
                  });
                  $("#history-stock-list").empty();
                  $("#history-stock-list").html(str);
               },
               error:function(err){
                  console.log("Exception : historyStockList")
               }
            })
         }
         
         /*페이지클릭 이벤트*/
         $('#history-content #page-selection').on("page", function(event, num){
            historyStockList(num);
           });
         
         /*탭 클릭 이벤트*/
         $("#history-content .nav-tabs  a").on("click",function(){
            console.log()
            if($(this).text()=="Profit"){
               $("#history-worst-piechart").empty();
               historyBest();
            }else{
               $("#history-best-piechart").empty();
               historyWorst();
            }
         })
         
         /*히스토리 Order By */
         var orderFlag=3;
         $("#history-foot thead th").on("click",function(){
            var thIndex=($(this).index());
            switch ($(this).index()) {
               case 0: orderFnc("SDH_BUY_SELL",thIndex); break;
               case 1: orderFnc("isuKorAbbrv",thIndex); break;         
               case 2: orderFnc("kind",thIndex); break;
               case 3: orderFnc("SDH_DEAL_TIME",thIndex); break;
               default: return;
            }
         })
         
         var orderFnc = function(orderBy,index){
            if(orderFlag!=index){
               historyStockList(1,orderBy,false);
               orderFlag=index;
            }else{
               historyStockList(1,orderBy,true);
               orderFlag=5;
            }
         }
         
         
         /*Best 파이차트*/
         var historyBest = function(){
            $.ajax({
               url:"getBest",
               type:"post",
               dataType:"json",
               data:"targetPlayer="+targetPlayer,
               success:function(data){
                  pieChartJson = new Array();
                  var etcMoney=0;
                  $.each(data,function(index,item){
                     var pieChartObj = new Object();
                     if(index<=4){
                        pieChartObj.name=item.isuKorAbbrv;
                        pieChartObj.y=(item.earningMoney);
                        pieChartJson.push(pieChartObj);
                     }else{
                        etcMoney+=item.earningMoney;
                        if(index+1 == data.length) {
                           pieChartObj.name="etc";
                           pieChartObj.y=etcMoney;
                           pieChartJson.push(pieChartObj);
                        }
                     }
                  }) 
                  pieChartdraw("#history-best-piechart",pieChartJson,"Profit");
               },
               error:function(err){
                  console.log("Exception : historyBest")
               }
            })
         }
         /*Worst 파이차트*/
         var historyWorst = function(){
            $.ajax({
               url:"getWorst",
               type:"post",
               dataType:"json",
               data:"targetPlayer="+targetPlayer, //해당유저에맞게 수정요망
               success:function(data){
                  pieChartJson = new Array();
                  var etcMoney=0;
                  $.each(data,function(index,item){
                     var pieChartObj = new Object();
                     if(index<=4){
                        pieChartObj.name=item.isuKorAbbrv;
                        pieChartObj.y=(-item.earningMoney);
                        pieChartJson.push(pieChartObj);
                     }else{
                        etcMoney+=item.earningMoney;
                        if(index+1 == data.length) {
                           pieChartObj.name="etc";
                           pieChartObj.y=(-etcMoney);
                           pieChartJson.push(pieChartObj);
                        }
                     }
                  }) 
                  pieChartdraw("#history-worst-piechart",pieChartJson,"Lost");
                  
               },
               error:function(err){
                  console.log("Exception : historyWorst")
               }
            })
         }
         
         
         /*수익률차트*/
            var earningChart = function(){
               $.ajax({
                  url:"getEarningRateList",
                  type:"post",
                  dataType:"json",
                  data:"targetPlayer="+targetPlayer, //해당유저에맞게 수정요망
                  success:function(data){
                     pieChartJson = new Array();
                     $.each(data,function(index,item){
                        var pieChartObj = new Object();
                        pieChartObj.x=item.pehDate2 ;
                        pieChartObj.y=parseFloat((item.pehPe).toFixed(2));
                        pieChartObj.name=item.pehDate2;
                        pieChartJson.push(pieChartObj);
                     })    
                     
                     lineChartdraw(pieChartJson);
                  },
                  error:function(){
                     console.log("Exception : earningChart")
                  }
               })
            }
         
         
          /*파이차트 그리기*/
         var pieChartdraw = function(historySelector,pieChartJson,chartTitle) {
            $(historySelector).highcharts({
               
                 chart: {
                     plotBackgroundColor: null,
                     plotBorderWidth: null,
                     plotShadow: false,
                     type: 'pie',
                     height:350,
                     width:480
                 },
                 title: {
                     text: chartTitle
                 },
                 tooltip: {
                     formatter: function() {
                         return this.series.name + ' : <b>₩' + this.y.format() + '</b>';
                     } 
                 },
                 plotOptions: {
                     pie: {
                         allowPointSelect: true,
                         cursor: 'pointer',
                         dataLabels: {
                             enabled: true,
                             format: '<b>{point.name}</b>:  {point.percentage:.1f} %',
                             style: {
                                 color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                             }
                         }
                     }
                 },
                 series: [{
                           name: chartTitle,
                           colorByPoint: true,
                           data: pieChartJson
                 }],
             });
         }
         
          /*라인차트 그리기*/
         var lineChartdraw = function(pieChartJson){
            $('#history-line-chart').highcharts({
                  chart: {
                      zoomType: 'x',
                      width:350,
                      width:480
                  },
                  title: {
                      text: 'Daily Earning Rate'
                  },
                  xAxis: {
                      type: 'datetime'
                  },
                  yAxis: {
                      title: {
                          text: 'Earning Rate'
                      }
                  },
                  legend: {
                      enabled: false
                  },
                  tooltip: {
                     formatter: function() {
                        var result = new Date(this.x).toUTCString().split("00:")[0];
                         return result+"<br>"+this.series.name + ' : <b>' + this.y + '%</b>';
                     } 
                 },
                  plotOptions: {
                      area: {
                          fillColor: {
                              linearGradient: {
                                  x1: 0,
                                  y1: 0,
                                  x2: 0,
                                  y2: 1
                              },
                              stops: [
                                  [0, Highcharts.getOptions().colors[0]],
                                  [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                              ]
                          },
                          marker: {
                              radius: 2
                          },
                          lineWidth: 1,
                          states: {
                              hover: {
                                  lineWidth: 1
                              }
                          },
                          threshold: null
                      }
                  },
   
                  series: [{
                      type: 'area',
                      name: 'Earning rate',
                      data: pieChartJson
                  }]
              });
         }
         $("#history-window").jqxWindow({
             width:1000,
             maxWidth:1200,
             height:800,
             maxHeight:1200,
             resizable:true,
             showCollapseButton: true,
             autoOpen:false,
             theme : userInfo.theme
           });
         
         earningChart();
         historyBest();
         historyStockListCount();
         historyStockList();
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
      firstNotify();
      historyInit(userInfo.nickName);
      
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
            $("#history-btn").setBtn($("#history-window"));
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