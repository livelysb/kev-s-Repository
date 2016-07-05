<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="stock-window">
  <div id="stock-header">전체 종목</div>
   <div id="stock-content">
	<div class="input-group">
	   <input type="text" class="form-control" placeholder="Search" id="stock-search">
	   <span class="input-group-btn">
				<button type="button" id="stock-list-search" class="btn btn-success">검색</button>
			</span>
     	<input type="hidden" id="stock-search-keyword">
    </div>
      <table class="table table-bordered table-hover">
         <thead>
            <tr>
               <th>종목명</th>
               <th>현재가</th>
               <th>전일비</th>
               <th>등락률</th>
               <th>거래량</th>
               <th>시가</th>
               <th>고가</th>
               <th>저가</th>
            </tr>
         </thead>
            <tbody id="stockListTBody">
               
            </tbody>
      </table>
      <div class="rows">
         <div id="page-align">
            <div id="page-selection"></div>
         </div>
      </div>
   </div>
</div>