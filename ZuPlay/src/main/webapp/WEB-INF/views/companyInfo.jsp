<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


  <div class="company-window">
    <div class="company-header">Hello!</div>

    <div class="company-content">
      <div class="row-fluid">
        <div class="col-xs-12 company-title">
          <span class="company-title-name">삼성전자</span>
          <span class="company-title-stock">(768,000)</span>
        </div>
      </div>


      <div class="row-fluid">
         <div class="col-xs-6 company-chart-warpper">
         <div class="company-chart">
		<ul class="nav nav-tabs">
		  <li class="active"><a data-toggle="tab" href="#company-chart-today">오늘</a></li>
		  <li><a data-toggle="tab" href="#company-chart-week">1주</a></li>
		  <li><a data-toggle="tab" href="#company-chart-month">한달</a></li>
		</ul>
		
		<div class="tab-content">
		  <div id="company-chart-today" class="tab-pane fade in active">
		    <h3>TODAY</h3>
		   <p>Some content.</p>
		</div>
		  
		  <div id="company-chart-week" class="tab-pane fade">
		    <h3>WEEK</h3>
		    <p>Some content in menu 1.</p>
		  </div>
		  
		  <div id="company-chart-month" class="tab-pane fade">
		    <h3>MONTH</h3>
		    <p>Some content in menu 1.</p>
		  </div>
		</div>
	  </div>
	  </div>

          <div class="col-xs-6">
            <table class="table table-condensed">
              <tbody>
                <tr>
                  <th>전일비</th>
                  <td>669.15</td>
                  <th>등락</th>
                  <td>9.85(+1.49%)</td>
                </tr>
                <tr>
                  <th>전일</th>
                  <td>819,162</td>
                  <th>거래량</th>
                  <td>3,571,653</td>
                </tr>
                <tr>
                  <th>저가</th>
                  <td>123,456</td>
                  <th>고가</th>
                  <td>123,456</td>
                </tr>
                <tr>
                  <th>기준가격</th>
                  <td>1,000,000</td>
                  <th>기준가액</th>
                  <td>1,000,000</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      <hr>
      <div class="row-fluid company-buy">
        <div class="col-xs-6"><div class="company-buy-slider"></div></div>
        <div class="col-xs-2"><div class="company-buy-input"></div></div>
        <div class="col-xs-2"><h5 class="company-buy-value">100,000</h5></div>
        <div class="col-xs-2"><button class="company-buy-btn btn btn-primary">구매</button></div>
      </div>
      <div class="row-fluid company-sell">
        <div class="col-xs-6"><div class="company-sell-slider"></div></div>
        <div class="col-xs-2"><div class="company-sell-input"></div></div>
        <div class="col-xs-2"><h5 class="company-sell-value">100,000</h5></div>
        <div class="col-xs-2"><button class="company-sell-btn btn btn-danger">판매</button></div>
      </div>
    </div>
  </div>