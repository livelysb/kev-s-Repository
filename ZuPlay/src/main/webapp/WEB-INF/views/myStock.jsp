<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="mystock-window">
  <div id="mystock-header">내 주식</div>
   <div id="mystock-content">
      <table class="table table-bordered table-hover">
         <thead>
            <tr>
               <th>종목명</th>
               <th>분야</th>
               <th>수량</th>
               <th>체결가</th>
               <th>총가치</th>
               <th>등락률</th>
               <th>수익률</th>
            </tr>
         </thead>
            <tbody id="mystockListTBody">
               
            </tbody>
      </table>
   </div>
</div>