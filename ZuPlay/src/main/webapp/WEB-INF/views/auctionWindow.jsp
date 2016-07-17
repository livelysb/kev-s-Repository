<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

	<div id="auction-window">
		<div id="auction-header">Auction List</div>
		<div id="auction-content">
			<div id="auction-container">
				 <!-- tab -->
				  <div id="auction-tab-div">
					<ul id="myTab" class="nav nav-tabs">
						<li class="active ">
						<a data-target="#home"
							id="auction-buytab" data-toggle="tab">구매</a>
						</li>
						<li class=""><a data-target="#profile" id="auction-selltab"
							data-toggle="tab">판매목록</a>
						</li>
					</ul>
                 </div>
                 
                   <!-- 검색 -->
					<div id="auction-searchBar" >
						<select class="form-control" id="auction-select">
							<option value="all">전체</option>
							<option value="hair">Hair</option>
							<option value="clothes">Clothes</option>
							<option value="eyes">Eyes</option>
							<option value="mouse">Mouse</option>
							<option value="earring">Earring</option>
							<option value="acc">Acc</option>
							<option value="etc">Etc</option>
						</select>
						 <input type="text"  id="auction-search" class="form-control " placeholder="Search"> 
							<input type="hidden" id="auction-hidden">
					</div>

			</div>
			
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade active in" id="home">
					<div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th width="10%">아이템</th>
									<th>아이템 이름</th>
									<th>판매가</th>
									<th>종료시간</th>
									<th>판매자이름</th>
									<th>구매</th>
								</tr>
							</thead>
							<tbody id="auction-buy-tbody">
							</tbody>
						</table>
						<!-- <div id="auction-page-btn">
							<button type="button" class="btn btn-default"
								id="auction-back-btn">이전</button>
							<button type="button" class="btn btn-default"
								id="auction-next-btn">다음</button>
						</div> -->  <!-- 기능적인 부분의오류로인한 주석 -->
					</div>
				</div>
				<div class="tab-pane fade" id="profile">
					<div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th width="10%">아이템</th>
									<th>아이템 이름</th>
									<th>판매가</th>
									<th>종료시간</th>
									<th>취소</th>
								</tr>
							</thead>
							<tbody id="auction-sell-tbody">
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>








