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
                    columns: columns
                });
        
		$("#rta-Window").jqxWindow({
            width:"300",
            height:"auto",
            resizable:true,
            showCollapseButton: true,
            autoOpen:false
          });
	}
	rtaInit();
	
});