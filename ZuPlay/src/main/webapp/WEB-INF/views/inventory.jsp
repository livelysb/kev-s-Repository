<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<div id="inven-Window">
      <div id="inven-Header">인벤토리</div>
      <div id="inven-content">
         <table id="inven-player" class="item-socket">
            <tr>
               <th colspan="3" id="inven-player-avatar">
                  <div id="inven-player-avatar-view">
                     <img src="" id="inven-player-clothes" /> <img src=""
                        id="inven-player-hair" /> <img src="" id="inven-player-eyes" />
                     <img src="" id="inven-player-mouse" /> <img src=""
                        id="inven-player-earring" /> <img src="" id="inven-player-acc" />
                  </div>
               </th>
            </tr>
            <tr>
               <td id="inven-player-1"></td>
               <td id="inven-player-2"></td>
               <td id="inven-player-3"></td>
            </tr>
            <tr>
               <td id="inven-player-4"></td>
               <td id="inven-player-5"></td>
               <td id="inven-player-6"></td>
            </tr>
         </table>
         <div class="vertical-line"></div>
         <table id="inven-items" class="item-socket">
            <tr>
               <td id="inven-player-11"></td>
               <td id="inven-player-12"></td>
               <td id="inven-player-13"></td>
               <td id="inven-player-14"></td>
            </tr>
            <tr>
               <td id="inven-player-15"></td>
               <td id="inven-player-16"></td>
               <td id="inven-player-17"></td>
               <td id="inven-player-18"></td>
            </tr>
            <tr>
               <td id="inven-player-19"></td>
               <td id="inven-player-20"></td>
               <td id="inven-player-21"></td>
               <td id="inven-player-22"></td>
            </tr>
            <tr>
               <td id="inven-player-23"></td>
               <td id="inven-player-24"></td>
               <td id="inven-player-25"></td>
               <td id="inven-player-26"></td>
            </tr>
            <tr>
               <td id="inven-player-27"></td>
               <td id="inven-player-28"></td>
               <td id="inven-player-29"></td>
               <td id="inven-player-30"></td>
            </tr>
         </table>
         <!-- 경매장 판매 모달 -->
         <div class="modal fade inven-auction-modal" tabindex="-1" role="dialog"
            aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="vertical-alignment-helper">
               <div class="modal-dialog vertical-align-center">

                  <div class="modal-dialog modal-sm">
                     <div class="modal-content">
                        <div class="modal-header">
							<div class="modal-title"><h4>경매장판매</h4></div>
                        </div>
                        <div class="modal-body">
                           <label>판매가</label>
                           <input type="text" id="inven-auction-imPurchasePrice">
                           <input type="hidden" id="inven-auction-piSq">
                        </div>
                        <div class="modal-footer">
                           <button type="button" class="btn btn-success" id="inven-auction-sell-btn">Sell</button>
                           <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         
      </div>
   </div>