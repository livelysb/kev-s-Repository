<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Board</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/jquery-ui.min.css" rel="stylesheet">
<link href="resources/css/zuplay.css" rel="stylesheet">

<style type="text/css">
   /* #tabBtn {text-align: right} */
</style>
</head>

<body>
   <table border="0" width="100%">
   <tr>
      <td width="100%" style="padding:10px 50px; vertical-align:top">
         <form  class="form-horizontal" method="post" action = "insertBoard">
            <fieldset>
               <legend>글쓰기</legend>
               <div class="form-group">
                  <div>
                     <input type="text" class="form-control" name="boardTitle" id="boardTitle"
                        placeholder="제목">
                  </div>
               </div>
               
               <div class="form-group">
                  <div>
                     <textarea class="form-control" rows="3" name="boardContent" id="boardContent" placeholder="내용"></textarea>
                  </div>
               </div>
               <div class="form-group">
                  <div>
                     <input type="file" class="form-control" name="" id="imageFile"
                        placeholder="파일첨부">
                  </div>
               </div>
                  <div align="center">
                     <input type="submit" class="btn btn-primary" value="등록">
                     <button class="btn btn-default">취소</button>
                  </div>
               
            </fieldset>
         </form>
      </td>
   </table>
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>

<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/zuplay.js"></script>


