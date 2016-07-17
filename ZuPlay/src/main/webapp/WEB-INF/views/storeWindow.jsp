<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="store-window">
   <div id="store-header">상점</div>
   <div id="store-content">

      <ul class="nav nav-tabs" id="store-nav-tabs">
         <li class="active" id="all"><a data-toggle="tab" href="#store-tab-all"> All</a></li>
         <li id="random"><a data-toggle="tab" href="#store-tab-random">Box</a></li>
         <li id="hair"><a data-toggle="tab" href="#store-tab-hair">Hair</a></li>
         <li id="clothes"><a data-toggle="tab" href="#store-tab-clothes">Clothes</a></li>
         <li id="eyes"><a data-toggle="tab" href="#store-tab-eyes">Eyes</a></li>
         <li id="mouse"><a data-toggle="tab" href="#store-tab-mouse">Mouse</a></li>
         <li id="earring"><a data-toggle="tab" href="#store-tab-earring">Earring</a></li>
         <li id="acc"><a data-toggle="tab" href="#store-tab-acc">Acc</a></li>
      </ul>

      <div id="store-prev-div">
         <button type="button" id="store-prev-btn"
            class="btn btn-default"><</button>
      </div>
      <div class="tab-content">
         <div id="store-tab-all" class="tab-pane fade in active">
            <div class="container-fluid store-container">
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemall0"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemall1"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemall2"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemall3"></div>
               </div>
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemall4"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemall5"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemall6"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemall7"></div>
               </div>
            </div>

         </div>
         <div id="store-tab-random" class="tab-pane fade">
            <div class="container-fluid store-container">
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemrandom0"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemrandom1"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemrandom2"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemrandom3"></div>
               </div>
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemrandom4"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemrandom5"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemrandom6"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemrandom7"></div>
               </div>
            </div>

         </div>
         <div id="store-tab-hair" class="tab-pane fade">
            <div class="container-fluid store-container">
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemhair0"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemhair1"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemhair2"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemhair3"></div>
               </div>
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemhair4"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemhair5"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemhair6"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemhair7"></div>
               </div>
            </div>

         </div>
         <div id="store-tab-clothes" class="tab-pane fade">
            <div class="container-fluid store-container">
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemclothes0"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemclothes1"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemclothes2"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemclothes3"></div>
               </div>
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemclothes4"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemclothes5"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemclothes6"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemclothes7"></div>
               </div>
            </div>

         </div>
         <div id="store-tab-eyes" class="tab-pane fade">
            <div class="container-fluid store-container">
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemeyes0"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemeyes1"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemeyes2"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemeyes3"></div>
               </div>
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemeyes4"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemeyes5"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemeyes6"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemeyes7"></div>
               </div>
            </div>

         </div>
         <div id="store-tab-mouse" class="tab-pane fade">
            <div class="container-fluid store-container">
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemmouse0"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemmouse1"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemmouse2"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemmouse3"></div>
               </div>
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemmouse4"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemmouse5"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemmouse6"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemmouse7"></div>
               </div>
            </div>

         </div>
         <div id="store-tab-earring" class="tab-pane fade">
            <div class="container-fluid store-container">
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemearring0"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemearring1"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemearring2"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemearring3"></div>
               </div>
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemearring4"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemearring5"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemearring6"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemearring7"></div>
               </div>
            </div>

         </div>
         <div id="store-tab-acc" class="tab-pane fade">
            <div class="container-fluid store-container">
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemacc0"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemacc1"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemacc2"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemacc3"></div>
               </div>
               <div class="row">
                  <div class="store-itemBox col-xs-3" id="store-itemacc4"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemacc5"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemacc6"></div>
                  <div class="store-itemBox col-xs-3" id="store-itemacc7"></div>
               </div>
            </div>
         </div>
      </div>
      <div id="store-next-div">
         <button type="button" id="store-next-btn" class="btn btn-default">></button>
      </div>
   </div>
</div>