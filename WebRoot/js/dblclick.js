/**
 * zgf
 */

 //双击进入详情事件
		function dblClick(host){ 
				var params = window.location.href.split('/');
				var url = '/jsp/'+params[params.length-2]+'/add.jsp';
				host = host==null||!host?'':host;
		       	var tr = $('.rightinfo').eq(0).find('tbody:first').find('tr');
		        	//双击事件
		        	tr.each(function(){
		        	$(this).dblclick(function(){
		        			var id = $(this).find('input:first').prop('value');
		        			if(!!id && id!=''&&!!url&&url!='')
		        			window.open(host+url+'?action=edit&id='+id);
		        	
		        	})
		        	
		        });
	        }