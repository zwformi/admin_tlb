<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String wsbasePath = request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath%>js/jquery.min.js"></script>
<style>
.waittodo{
    display: inline-block;
    margin-right: 30px;
    line-height: 50px;
    background: #0b3a58;
    padding-left: 15px;
    padding-right: 15px;
}
#matters
{
user-select: none;
}

label.label.label-danger {
    color: #fff;
    background-color:#d13532;
}
label.label {
    border-radius: 6px;
    padding: 2px 4px;
    margin: -2px 0;
    font-size: 12px;
    line-height: 15px;
    margin-left:5px;
}
label {
    display: inline-block;
    vertical-align: baseline;
    margin-right: 2px;
    color: #999;
}

</style>
<script type="text/javascript">
      var stateTtext={
			   "1":'待发货',
               "2":'部分发货',
               "3":'全部发货',
               "4":'待验收',
               "5":'已验收',
               "6":'启动结算',
               "7":'已完成',
               "8":'订单待取消',
               "-2":'供应商拒单',
               "-5":'订单取消',
               "10":'退换货中',
               "0":'未知状态'
			};
      var socketdata;
      var websocket = null;
      var url="ws://<%=wsbasePath%>echo.ws";
      //判断当前浏览器是否支持WebSocket
      if('WebSocket' in window){
          websocket = new WebSocket(url);
      }
      else{
          alert('Not support websocket');
      }
       
      //连接发生错误的回调方法
      websocket.onerror = function(){
          alert("error:连接发生错误");
      };
       
      //连接成功建立的回调方法
      websocket.onopen = function(event){
           console.log("成功建立");
      }
       
      //接收到消息的回调方法
      websocket.onmessage = function(event){
          var redl=event.data.replace(/(id\":)([0-9]+?)(,)/ig,"$1\"$2\"$3");
          socketdata = JSON.parse(redl);
         /*  socketdata = eval("("+event.data+")"); */
	      $("#matters").find("label").html(socketdata.length);   
      }
      
       
      //连接关闭的回调方法
      websocket.onclose = function(){
          //console.log("close");
      }
       
      //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
      window.onbeforeunload = function(){
          websocket.close();
      }
       
      //关闭连接
      function closeWebSocket(){
          websocket.close();
      }


$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
	
	//加载待办事宜
	//待办事宜显示
	
	$("#matters").on('click',function () {
	     var dbsy=window.parent.frames["rightFrame"].document.getElementById("dbsy");
	     if($(dbsy).length==0)
	     {
	        if(!!socketdata)
	        {
	          creatwait(socketdata);
	        }	        
	     }
          else
          {
             removewait();
          }    
	});	
});

	function creatwait(Data){
	var orderdetailHtml="";
	if(Data.length!=0)
	{
	  $.each(Data,function(i,value){
	      var ho_time;
	      var _order=value.order;
	      var testD=_order.createdAt;
	      var tds=testD.split("+")[0];
	      var newd=tds.substring(0,4)+"-"+tds.substring(4,6)+"-"+tds.substring(6,8)+" "+tds.substring(8,10)+":"+tds.substring(10,12)+":"+tds.substring(12,14);
	      if(GetDateDiff(newd, CurentTime(), "day")==0)
	      {
	        if(GetDateDiff(newd, CurentTime(), "hour")==0)
	        {
	          if(GetDateDiff(newd, CurentTime(), "minute")==0)
	          {
	            ho_time=GetDateDiff(newd, CurentTime(), "second")+"秒";
	          }
	          else
	          {
	            ho_time=GetDateDiff(newd, CurentTime(), "minute")+"分钟";
	          }
	          
	        }
	        else
	        {
	          ho_time=GetDateDiff(newd, CurentTime(), "hour")+"小时";
	        }
	      }
	      else
	      {
	        ho_time=GetDateDiff(newd, CurentTime(), "day")+"天";
	      }	      
          orderdetailHtml+='<a href="<%=basePath%>jsp/zcy_order/detail.jsp?id='+_order.id+'"  style="padding: 12px 15px;border-left: 1px solid #f7f7f7;display: block;color: inherit;line-height: 14px; border-bottom: 1px solid #ccc;">'+
										    '<span class="text-link" style="color: #2687d0!important;display: inline-block;max-width: 200px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">【订单接单】订单(编号:'+_order.id+')'+stateTtext[_order.status]+'</span>'+
										    '<span class="time-info" data-time-millis="" style="color: #999;float: right;margin-right: 30px;">'+ho_time+'前</span>'+
										  '</a>';
      });	
	}    
	var main_body = window.parent.frames["rightFrame"].document.body;	                	
                    var oDiv = document.createElement('div');
                    $(main_body).scrollTop($(main_body).scrollTop()+1);//控制滚动条下移1px  
			        var barw=($(main_body).scrollTop()>0)?16:0; 
			        $(main_body).scrollTop($(main_body).scrollTop()-1);//还原
                    oDiv.setAttribute("id", "dbsy");
                    oDiv.style.position = "fixed";
                    oDiv.style.zIndex = "20";
                    oDiv.style.top = "0px";
                    oDiv.style.right = (380-barw)+"px";   
                    oDiv.innerHTML = ' <div class="dropdown-menu float-menu eve-component" style="position: absolute;border: 1px solid #d8d8d8;background-color: #fff;">'+
										'<div id="todo-container" class="float-menu-body" style="width: 400px;float: left;position: fixed;background: #fff;border: 1px solid #d8d8d8;border-top: 0;margin-left: -1px;font-size: 12px;">'+
										  orderdetailHtml		  
										'</div>'+
							        '</div>';
                   main_body.appendChild(oDiv);
                   }
function removewait(){
                    var removeDiv_body = window.parent.frames["rightFrame"].document.body;
                    removeDiv_body.removeChild(window.parent.frames["rightFrame"].document.getElementById("dbsy"));

}                   
function editshow()
{
    parent.document.getElementById("rightFrame").src="<%=basePath%>jsp/system_user/edit.jsp";  
}
function CurentTime()
    { 
        var now = new Date();
       
        var year = now.getFullYear();       //年
        var month = now.getMonth() + 1;     //月
        var day = now.getDate();            //日
       
        var hh = now.getHours();            //时
        var mm = now.getMinutes();          //分
       
        var clock = year + "-";
       
        if(month < 10)
            clock += "0";
       
        clock += month + "-";
       
        if(day < 10)
            clock += "0";
           
        clock += day + " ";
       
        if(hh < 10)
            clock += "0";
           
        clock += hh + ":";
        if (mm < 10) clock += '0'; 
        clock += mm; 
        return(clock); 
    } 
function GetDateDiff(startTime, endTime, diffType) {
    startTime = startTime.replace(/\-/g, "/");
    endTime = endTime.replace(/\-/g, "/");
    //将计算间隔类性字符转换为小写
    diffType = diffType.toLowerCase();
    var sTime = new Date(startTime);      //开始时间
    var eTime = new Date(endTime);  //结束时间
    //作为除数的数字
    var divNum = 1;
    switch (diffType) {
        case "second":
            divNum = 1000;
            break;
        case "minute":
            divNum = 1000 * 60;
            break;
        case "hour":
            divNum = 1000 * 3600;
            break;
        case "day":
            divNum = 1000 * 3600 * 24;
            break;
        default:
            break;
    }
    return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(divNum));
}    
</script>

</head>

<body style="background-color:#233646;">

    <div class="topleft">
        <img style="height:35px;" src="<%=basePath%>images/logo.png" title="系统首页" />
    </div>
    
    <div class="topright">
    <div style="display:inline-block" class="waittodo">
        <a style="color:#fff;display:inline-block;cursor:pointer" id="matters">待办事宜
        <label class="label label-danger">0</label>
        </a>
       
    </div>
    <ul>
    <li><a href="javascript:void(0);" ">欢迎登陆，${loginUser.user_name}</a></li>
    <li><a href="javascript:;" onclick="editshow()">修改密码  </a></li>
    <li><a href="<%=basePath %>login/out.html" target="_parent">退出</a></li>
    </ul>  
    </div>
</body>
</html>