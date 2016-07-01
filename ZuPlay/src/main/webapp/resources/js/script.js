$(function(){
   
   
   /*버튼클릭했을 때 이벤트 설정*/
   $.fn.setBtn = function(window){
      $(this).on("click",function(){
         $(window).jqxWindow("show");
      })
   };
   
   /* 실시간 주가 정보 */
      var rtaInit = function(){

         var localData = {
            data : [
               {d1:"삼성전자",d2:"+10000",d3:"+3%",d4:"3000"},
               {d1:"삼성전자",d2:"+10000",d3:"+3%",d4:"3000"},
               {d1:"삼성전자",d2:"+10000",d3:"+3%",d4:"3000"},
               {d1:"삼성전자",d2:"+10000",d3:"+3%",d4:"3000"},
               {d1:"삼성전자",d2:"+10000",d3:"+3%",d4:"3000"}
            ]
            };
         
           var source =
           {
            localdata : localData.data,
               datafields:
               [
                   { name: 'd1', type: 'string'},
                   { name: 'd2', type: 'string' },
                   { name: 'd3', type: 'string' },
                   { name: 'd4', type: 'string' }
               ],
               datatype: "array"
           };
         
         var columns = [
                           { text: '종목명', datafield: 'd1', width: "30%", cellsalign: 'center', align: 'center' },
                           { text: '전일비', datafield: 'd2', width: "30%", cellsalign: 'center', align: 'center' },
                           { text: '등락률', datafield: 'd3', width: "15%", cellsalign: 'center', align: 'center' },
                           { text: '거래량', datafield: 'd4', width: "25%", cellsalign: 'center', align: 'center' }
                         ];
         
         var dataAdapter = new $.jqx.dataAdapter(source);
           $("#rta-data").jqxGrid(
                   {
                      width:"100%",
                       source: dataAdapter,
                       selectionmode: 'multiplecellsextended',
                       autoheight: true,
                       columns: columns,
                       theme:"kokomo"
                   });
           
         $("#rta-Window").jqxWindow({
               width:"300",
               height:"auto",
               resizable:true,
               showCollapseButton: true,
               autoOpen:false,
               theme:userInfo.theme
             });
      }
      
      
      /* 기업 정보 조회 */
      var companyInfo = function(code){
            
          $(code + " .company-window").jqxWindow({
                theme:userInfo.theme,
                minWidth:500,
                height:380,
                showCollapseButton: true,
                resizable : false,
                closeButtonAction: 'close'
              });

          $(code + " .company-sell-slider, .company-buy-slider").jqxSlider({
              width:"100%",
              showTickLabels: true,
              tooltip: true,
              mode: "fixed",
              min: 0,
              max: 100,
              ticksFrequency: 10,
              value: 50,
              step: 1,
              theme : "kokomo",
              tooltipPosition: "far",
              theme:userInfo.theme
          });
          
          $(code + " .company-buy-slider").jqxSlider({
              width:"100%",
              showTickLabels: true,
              tooltip: true,
              mode: "fixed",
              min: 0,
              max: 100,
              ticksFrequency: 10,
              value: 50,
              step: 1,
              theme : "kokomo",
              tooltipPosition: "far",
              theme:userInfo.theme
          });

          $(code + ".company-sell-input, .company-buy-input").jqxNumberInput({
            width: "100%",
            spinButtons: true,
            inputMode: 'simple',
            min:0,
            max:100,
            textAlign:"center",
            decimalDigits: 0,
            theme:userInfo.theme
          });
          
          $(code + ".company-buy-input").jqxNumberInput({
               width: "100%",
               spinButtons: true,
               inputMode: 'simple',
               min:0,
               max:100,
               textAlign:"center",
               decimalDigits: 0,
               theme:userInfo.theme
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
                   $("#inven-player-"+setting.parts[i-1]).attr("src","resources/img/avatar/"+setting.parts[i-1]+"/"+setting.parts[i-1]+"-01.png");
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
                           str+="<tr><td class='stock-select'><a href='#'>"+item.isuKorAbbrv+"</a></td>"
                           str+="<td>"+item.priceDTO.trdPrc +"</td>";
                           str+="<td>"+item.priceDTO.cmpprevddPrc +"</td>";
                           str+="<td>"+item.priceDTO.fluctuationRate +"</td>";
                           str+="<td>"+item.priceDTO.trdvol +"</td>";
                           str+="<td>"+item.priceDTO.opnprc +"</td>";
                           str+="<td>"+item.priceDTO.hgprc +"</td>";
                           str+="<td>"+item.priceDTO.lwprc +"</td><tr>";
                        }
                     })
                     
                     $("#stockListTBody").html(str);
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
            $(document).on("click", ".stock-select",function(){
               $.ajax({
                  url:"",
                  type:"post",
                  dataType:"",
                  data:"",
                  success:function(data){
                     
                  },
                  error:function(err){
                     alert(err+"에러발생");
                  }
               })
            })

            /*검색( 2,3페이지에있을때 1페이지로 다시 이동하게!)*/
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
      
      invenInit();
      rtaInit();
      stockListInit();
      //companyInfo();
      
      var setBtn = function(){
         
            $("#inven-btn").setBtn($("#inven-Window"));
            $("#rta-btn").setBtn($("#rta-Window"));
            $("#stockList-btn").setBtn($("#stock-window"));
            
      }();
      
});