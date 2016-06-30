$(function(){
	
	var rtaInit = function(){

		var localData = {
			data : [
				{d1:"삼성전자",d2:"+10000",d3:"+3%",d4:"3000"},
				{d1:"삼성전자",d2:"+10000",d3:"+3%",d4:"3000"},
				{d1:"삼성전자",d2:"+10000",d3:"+3%",d4:"3000"},
				{d1:"삼성전자",d2:"+10000",d3:"+3%",d4:"3000"},
				{d1:"삼성전자",d2:"+10000",d3:"+3%",d4:"3000"}
			]
			};
		
        var source =
        {
			localdata : localData.data,
            datafields:
            [
                { name: 'd1', type: 'string'},
                { name: 'd2', type: 'string' },
                { name: 'd3', type: 'string' },
                { name: 'd4', type: 'string' }
            ],
            datatype: "array"
        };
		
		var columns = [
		                  { text: '종목명', datafield: 'd1', width: "30%", cellsalign: 'center', align: 'center' },
		                  { text: '전일비', datafield: 'd2', width: "30%", cellsalign: 'center', align: 'center' },
		                  { text: '등락률', datafield: 'd3', width: "15%", cellsalign: 'center', align: 'center' },
		                  { text: '거래량', datafield: 'd4', width: "25%", cellsalign: 'center', align: 'center' }
		                ];
		
		var dataAdapter = new $.jqx.dataAdapter(source);
        $("#rta-data").jqxGrid(
                {
             		width:"100%",
                    source: dataAdapter,
                    selectionmode: 'multiplecellsextended',
                    autoheight: true,
                    columns: columns,
                    theme:"kokomo"
                });
        
		$("#rta-Window").jqxWindow({
            width:"300",
            height:"auto",
            resizable:true,
            showCollapseButton: true,
            autoOpen:false,
            theme:"kokomo"
          });
	}
	rtaInit();
	
	var companyInfo = function(){
		$(".company-window").jqxWindow({
	          theme:"kokomo",
	          minWidth:500,
	          width:1000,
	          height:400,
	          showCollapseButton: true,
	          closeButtonAction: 'close'
	        });

	    $(".company-chart").jqxTabs({
	      scrollable:false,
	       width: '100%',
	       autoHeight: false,
	       height: 150,
	       theme:"kokomo"
	    });

	    $(".company-sell-slider, .company-buy-slider").jqxSlider({
	        width:"100%",
	        showTickLabels: true,
	        tooltip: true,
	        mode: "fixed",
	        min: 0,
	        max: 100,
	        ticksFrequency: 10,
	        value: 50,
	        step: 1,
	        theme : "kokomo",
	        tooltipPosition: "far"
	    });

	    $(".company-sell-input, .company-buy-input").jqxNumberInput({
	      width: "100%",
	      spinButtons: true,
	      inputMode: 'simple',
	      min:0,
	      max:100,
	      textAlign:"center",
	      decimalDigits: 0
	    });
	}
	
	companyInfo();
});