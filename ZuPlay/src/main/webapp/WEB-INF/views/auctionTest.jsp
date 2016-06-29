<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="jQuery Tabs, Tabs Widget, TabView, jqxTabs" />
<title>Home</title>

<link href="resources/css/jqwidgets/jqx.base.css" rel="stylesheet">
<link href="resources/css/zuplay-green.css" rel="stylesheet">
<link href="resources/css/jquery-ui.min.css" rel="stylesheet">
<link href="resources/css/zuplay.css" rel="stylesheet">


<style type="text/css">
	#searchBar {float: right}
	.itemImg {width:100%;}
</style>
</head>
	<body class='default'>
    <div id='jqxWidget'>
		<div id='jqxTabs'>
			<ul>
				<li style="margin-left: 30px;">구매</li>
				<li>판매목록</li>
			</ul>

			<!-- 구매 탭 내용 -->
			<div>
			 <table id="table" border="1">
        <thead>
            <tr>
                <th align="left">First Name</th>
                <th align="left">Last Name</th>
                <th align="left">Product</th>
                <th align="right">Price</th>
                <th align="right">Quantity</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Ian</td>
                <td>Devling</td>
                <td>Espresso Truffle</td>
                <td>$1.75</td>
                <td>8</td>
            </tr>
            <tr>
                <td>Nancy</td>
                <td>Wilson</td>
                <td>Cappuccino</td>
                <td>$5.00</td>
                <td>3</td>
            </tr>
            <tr>
                <td>Cheryl</td>
                <td>Nodier</td>
                <td>Caffe Americano</td>
                <td>$2.50</td>
                <td>4</td>
            </tr>
            <tr>
                <td>Martin</td>
                <td>Saavedra</td>
                <td>Caramel Latte</td>
                <td>$3.80</td>
                <td>11</td>
            </tr>
            <tr>
                <td>Guylene</td>
                <td>Bjorn</td>
                <td>Green Tea</td>
                <td>$1.50</td>
                <td>8</td>
            </tr>
            <tr>
                <td>Andrew</td>
                <td>Burke</td>
                <td>Caffe Espresso</td>
                <td>$3.00</td>
                <td>2</td>
            </tr>
            <tr>
                <td>Regina</td>
                <td>Murphy</td>
                <td>White Chocolate Mocha</td>
                <td>$3.60</td>
                <td>6</td>
            </tr>
            <tr>
                <td>Michael</td>
                <td>Murphy</td>
                <td>Caramel Latte</td>
                <td>$3.80</td>
                <td>2</td>
            </tr>
            <tr>
                <td>Petra</td>
                <td>Bein</td>
                <td>Caffe Americano</td>
                <td>$2.50</td>
                <td>7</td>
            </tr>
            <tr>
                <td>Nancy</td>
                <td>Nodier</td>
                <td>Caffe Latte</td>
                <td>$4.50</td>
                <td>10</td>
            </tr>
            <tr>
                <td>Peter</td>
                <td>Devling</td>
                <td>Green Tea</td>
                <td>$1.50</td>
                <td>1</td>
            </tr>
            <tr>
                <td>Beate</td>
                <td>Saylor</td>
                <td>Espresso con Panna</td>
                <td>$3.25</td>
                <td>3</td>
            </tr>
            <tr>
                <td>Shelley</td>
                <td>Nodier</td>
                <td>Peppermint Mocha Twist</td>
                <td>$4.00</td>
                <td>7</td>
            </tr>
            <tr>
                <td>Nancy</td>
                <td>Murphy</td>
                <td>Peppermint Mocha Twist</td>
                <td>$4.00</td>
                <td>1</td>
            </tr>
            <tr>
                <td>Lars</td>
                <td>Wilson</td>
                <td>Caffe Espresso</td>
                <td>$3.00</td>
                <td>11</td>
            </tr>
        </tbody>
    </table>
				<!-- <table>
					<thead>
						<tr>
							<th width="10%">아이템</th>
							<th>아이템 이름 <span class="caret"></span></th>
							<th>판매가 <span class="caret"></span></th>
							<th>남은시간 <span class="caret"></span></th>
							<th>판매자이름 <span class="caret"></span></th>
							<th>구매</th>
						</tr>
					</thead>
					<tbody id="buyTBody">
					</tbody>
				</table> -->
				<button type="button" class="btn btn-primary" id="backBtn">이전</button>
				<button type="button" class="btn btn-primary" id="nextBtn">다음</button>
			</div>

			<!-- 판매목록 탭 내용 -->
			<div>
				<<!-- table>
					<thead>
						<tr>
							<th width="10%">아이템</th>
							<th>아이템 이름 <span class="caret"></span></th>
							<th>판매가 <span class="caret"></span></th>
							<th>남은시간 <span class="caret"></span></th>
							<th>취소</th>
						</tr>
					</thead>
					<tbody id="sellTBody">
					</tbody>
				</table> -->
			</div>
		</div>

	</div>
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/zuplay.js"></script>

<script src="resources/js/jqwidgets/jqxcore.js"></script>
<script src="resources/js/jqwidgets/jqxdata.js"></script>
<script src="resources/js/jqwidgets/jqxbuttons.js"></script>
<script src="resources/js/jqwidgets/jqxscrollbar.js"></script>
<script src="resources/js/jqwidgets/jqxcheckbox.js"></script>
<script src="resources/js/jqwidgets/jqxtabs.js"></script>
<script src="resources/js/jqwidgets/jqxdatatable.js"></script>




<script type="text/javascript">

$(document).ready(function () {
    $('#jqxTabs').jqxTabs({width: '90%', height: 200, position: 'top',selectionTracker: "true",animationType: 'fade' });
    $('#settings div').css('margin-top', '10px');
    
    $("#table").jqxDataTable(
            {
                altRows: true,
                sortable: true,
                editable: true,
                selectionMode: 'singleRow',
                columns: [
                  { text: 'First Name', dataField: 'First Name', width: 200 },
                  { text: 'Last Name', dataField: 'Last Name', width: 200 },
                  { text: 'Product', dataField: 'Product', width: 250 },
                  { text: 'Unit Price', dataField: 'Price', width: 100, align: 'right', cellsAlign: 'right', cellsFormat: 'c2' },
                  { text: 'Quantity', dataField: 'Quantity', width: 100, align: 'right', cellsAlign: 'right', cellsFormat: 'n' }
                ]
            });
    
});
</script> 
</html> 








