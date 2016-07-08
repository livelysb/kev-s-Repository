<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div id="news-search-window">
  <div>News</div>
    <div class="row news-search-content">
      <div class="col-xs-12">
            <div class="ibox-content">
                <div class="search-form">
                    <div class="input-group">
                        <input type="text" placeholder="Keyword" name="search" class="form-control input-md" id="news-search-keyword">
                        <div class="input-group-btn">
                            <button class="btn btn-md btn-primary" id="news-search-submit">
                                Search
                            </button>
                        </div>
                    </div>
                </div>
                
                <div class="hr-line-dashed"></div>
            <div class="row" id="news-search-results">
            
            </div>
           </div>
       </div>
   </div>
</div>