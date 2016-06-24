<!doctype html>
<html lang="en">
      <head>
        <meta charset="utf-8">
        <title>jQuery UI Sortable - Display as grid</title>
         
<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/jquery-ui.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">

        <style>
         #sortable1, #sortable2 {
          border: 1px solid #eee;
            background-color: gray;
          width: 300px;
            height : auto;
          min-height: 100px;
          list-style-type: none;
          margin: 0;
          padding: 5px 0 0 0;
          float: left;
          margin-right: 10px;
        }
        #sortable1 div, #sortable2 div {
           width: 100px; height: 100px;
            border: 1px blue solid;
            display: inline-block;
          margin: 0 5px 5px 5px;
          padding: 5px;
          font-size: 1.2em;
          width: 120px;
        }
         #sortable1 div{
            background-color: blue;
         }
         #sortable2 div{
            background-color: yellow;
         }

        </style>
        <script>
        $(function() {
          $( "#sortable1, #sortable2" ).sortable({
            connectWith: ".connectedSortable",
            disabled: false,
          //  placeholder: "sortable-placeholder", //
            zIndex: 9999            
          }).disableSelection();
        });
        </script>
      </head>
<body>

	<div id="sortable1" class="connectedSortable">
		<div class="ui-state-default">Item 1</div>
		<div class="ui-state-default">Item 2</div>
		<div class="ui-state-default">Item 3</div>
		<div class="ui-state-default">Item 4</div>
		<div class="ui-state-default">Item 5</div>
		<div class="ui-state-default">Item 6</div>
		<div class="ui-state-default">Item 7</div>
		<div class="ui-state-default">Item 8</div>
	</div>
	<div>
		<div id="sortable2" class="connectedSortable"> </div>
		<div id="sortable2" class="connectedSortable"> </div>
		<div id="sortable2" class="connectedSortable"> </div>
		<div id="sortable2" class="connectedSortable"> </div>
		<div id="sortable2" class="connectedSortable"> </div>
		<div id="sortable2" class="connectedSortable"> </div>
	</div>
</body>
</html>