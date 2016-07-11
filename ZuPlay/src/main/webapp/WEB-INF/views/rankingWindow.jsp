<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>

	<div id="ranking-window">
		<div id="ranking-header">Ranking</div>
		<div id="ranking-content">
			<div id="ranking-container">
				 <!-- tab -->
				  <div id="ranking-tab-div">
					<ul id="ranking-myTab" class="nav nav-tabs">
						<li class="active ">
						<a data-target="#ranking-season"
							id="ranking-tab-season" data-toggle="tab">시즌랭킹</a>
						</li>
						<li class="">
							<a data-target="#ranking-daily" id="ranking-tab-daily" data-toggle="tab">
								일간랭킹
							</a>
						</li>
					</ul>
                 </div>
                 
               		 <input type="text"  id="ranking-search" class="form-control " placeholder="Search"> 
			</div>
			
			<div id="ranking-myTabContent" class="tab-content">
				<div class="tab-pane fade active in" id="ranking-season">
					<div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>순위</th>
									<th style="width:110px">아바타</th>
									<th>닉네임</th>
									<th>수익률</th>
									<th>총자산</th>
								</tr>
							</thead>
							<tbody id="ranking-season-tbody">
							</tbody>
						</table>
					</div>
				</div>
				<div class="tab-pane fade" id="ranking-daily">
					<div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>순위</th>
									<th style="width:110px">아바타</th>
									<th>닉네임</th>
									<th>수익률</th>
									<th>총자산</th>
								</tr>
							</thead>
							<tbody id="ranking-daily-tbody">
							</tbody>
						</table>
					</div>
				</div>
				
			</div>
		</div>
	</div>