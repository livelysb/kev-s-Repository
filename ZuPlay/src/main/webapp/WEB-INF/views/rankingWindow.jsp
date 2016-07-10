<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet" />
<link href="resources/css/jqwidgets/jqx.base.css" rel="stylesheet" />
<link href="resources/css/jqwidgets/jqx.kokomo.css" rel="stylesheet" />
<%-- <c:if test="${theme ne 'kokomo'}">
   <link href="resources/css/jqwidgets/jqx.${theme}.css" rel="stylesheet" />
</c:if> --%>
<link href="resources/css/jquery-ui/jquery-ui.css" rel="stylesheet" />
<link href="resources/css/style.css" rel="stylesheet" />

<style type="text/css">
	
/* .auction-itemImg {
	width: 100%;
}
 */
#ranking-tab-div{
	float:left
}

#ranking-container {
	margin-left: 0;
	padding-left: 0;
}

#ranking-search{
	float:right;
	width: auto;
}

#ranking-all-btn,#ranking-week-btn{
	text-align: center;
}
	
</style>

</head>
<body>


	<div id="ranking-window">
		<div id="ranking-header">Ranking</div>
		<div id="ranking-content">
			<div id="ranking-container">
				 <!-- tab -->
				  <div id="ranking-tab-div">
					<ul id="ranking-myTab" class="nav nav-tabs">
						<li class="active ">
						<a data-target="#ranking-all"
							id="ranking-tab-all" data-toggle="tab">전체랭킹</a>
						</li>
						<li class=""><a data-target="#ranking-week" id="ranking-tab-week"
							data-toggle="tab">주간랭킹</a>
						</li>
					</ul>
                 </div>
                 
               		 <input type="text"  id="ranking-search" class="form-control " placeholder="Search"> 
			</div>
			
			<div id="ranking-myTabContent" class="tab-content">
				<div class="tab-pane fade active in" id="ranking-all">
					<div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>순위</th>
									<th>아이디</th>
									<th width="10%">아바타</th>
									<th>닉네임</th>
									<th>수익률</th>
									<th>총자산</th>
								</tr>
							</thead>
							<tbody id="ranking-buy-tbody">
							</tbody>
						</table>
						<div id="ranking-all-btn">
							<button type="button" class="btn btn-default"
								id="ranking-back-btn">이전</button>
							<button type="button" class="btn btn-default"
								id="ranking-next-btn">다음</button>
						</div>
					</div>
				</div>
				<div class="tab-pane fade" id="ranking-week">
					<div>
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>순위</th>
									<th>아이디</th>
									<th width="10%">아바타</th>
									<th>닉네임</th>
									<th>수익률</th>
									<th>총자산</th>
								</tr>
							</thead>
							<tbody id="ranking-sell-tbody">
							</tbody>
						</table>
						<div id="ranking-week-btn">
							<button type="button" class="btn btn-default"
								id="ranking-back-btn">이전</button>
							<button type="button" class="btn btn-default"
								id="ranking-next-btn">다음</button>
						</div>
					</div>
				</div>
				
			</div>
		</div>
	</div>



</body>

    
<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxcore.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxnotification.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxbuttons.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxwindow.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxtooltip.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxcheckbox.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxscrollbar.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxpanel.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxgrid.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxgrid.selection.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxdata.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxpanel.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxsortable.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxslider.js"></script>
<script type="text/javascript" src="resources/js/jqwidgets/jqxnumberinput.js"></script>
<script type="text/javascript" src="resources/js/jquery.bootpag.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui/jquery-ui.js"></script>
<script src="resources/js/set.js"></script>
<script src="resources/js/script2.js"></script>
<script type="text/javascript">
	 
</script>
</html>