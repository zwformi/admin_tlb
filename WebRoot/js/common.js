function loadTableStyle(){
	$('.tablelist tbody tr:even').css('background', '#fff');
    $('.tablelist tbody tr:odd').css('background', '#f5f8fa');
    
    $('.tablelist tbody tr').mouseover(function() {
        $(this).css('background', '#e5ebee');
    })
    $(".tablelist tbody tr:not('.a')").mouseout(function() {
        $('.tablelist tbody tr:even').css('background', '#fff');
        $('.tablelist tbody tr:odd').css('background', '#f5f8fa');
        $(".tablelist tbody tr.a").css('background', '#e5ebee');
    })
    $('.tablelist tbody tr').click(function(event) {
    	if(event.target.nodeName != "A" && event.target.nodeName != "INPUT"){
	        if($(this).find("input[type='checkbox']").prop("checked") == false){
	        	$(this).find("input[type='checkbox']").prop("checked", true);
	        }else if($(this).find("input[type='checkbox']").prop("checked")){
	        	$(this).find("input[type='checkbox']").prop("checked", false);
	        }
    	}
    	
        if ($(this).css('background') == '#e5ebee') {
            $(this).css('background', '#fff');
            $(this).removeClass('a');
        } else {
            $('.tablelist tbody tr:even').css('background', '#fff');
            $('.tablelist tbody tr:odd').css('background', '#f5f8fa');
            $('.tablelist tbody tr').removeClass('a');
            $(this).css('background', '#e5ebee');
            $(this).addClass('a');
        }
    })
 
       justifiedDivTable();
}

//兼容ie8的滚动条宽度调整办法
function justifiedDivTable(){
	  var width = $(".tablediv").width()-$(".tablediv").find('table:first').find('tr').width();
	$(".tabletop").css("padding-right",width);


}