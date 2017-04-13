<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>会员详细资料</title>
<link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<style type="text/css">
    .laydate-icon {width: 325px !important; height: 32px !important;border-top:solid 1px #a7b5bc !important; border-left:solid 1px #a7b5bc !important; border-right:solid 1px #ced9df !important; border-bottom:solid 1px #ced9df !important;}
</style>
<style>
body{min-width:400px;}
#tab{position:relative;}
#tab .tabList ul li{
	float:left;
	background:#fefefe;
	background:-moz-linear-gradient(top, #fefefe, #ededed);	
	background:-o-linear-gradient(left top,left bottom, from(#fefefe), to(#ededed));
	background:-webkit-gradient(linear,left top,left bottom, from(#fefefe), to(#ededed));
	border:1px solid #ccc;
	padding:5px 0;
	width:180px;
	text-align:center;
	margin-left:-1px;
	position:relative;
	cursor:pointer;
}
#tab .tabCon{
	position:absolute;
	left:-1px;
	top:28px;
	border:1px solid #ccc;
	border-top:none;
	width:723px;
	min-height:280px;
}
#tab .tabCon div{
	padding:10px;
	position:absolute;
}
#tab .tabList li.cur{
	border-bottom:none;
	background:#fff;
}
	.address_css{border:1px solid #cccccc;font-size:12px;}
	.address_css tr th{height:30px;line-height:30px;text-align:left;padding-left:5px;}
	.address_css tr td{height:30px;line-height:30px;text-align:left;padding-left:5px;border-bottom:1px solid #eeeeee;}
</style>
</head>

<body>
    <div class="formbody">
		<div id="tab" style="margin-left:20px;margin-top:20px">
		  <div class="tabList">
			<ul>
				<li class="cur">帐号资料</li>
				<li>公司信息</li>
				<li>收货地址</li>
				<li>发票信息</li>
			</ul>
		  </div>
		  <div class="tabCon">
			<div class="cur" style="display:block; overflow-y:auto; overflow-x:hidden; width:703px; height:259px; margin-top:1px">
		        <ul class="forminfo">
		            <li><label style="width:110px">头像：</label>
		                <dl><img id="headimgurl" name="headimgurl" src="" height="40px" width="40px" style="border-radius:10px" /></dl>
		            </li>
		            <li><label style="width:110px">昵称：</label>
		                <dl><input id="nick_name" name="nick_name" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:110px">性别：</label>
		                <dl><input id="gender" name="gender" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:110px">邮箱：</label>
		                <dl><input id="e_mail" name="e_mail" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:110px">真实姓名：</label>
		                <dl><input id="xm" name="xm" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:110px">手机号码：</label>
		                <dl><input id="phone" name="phone" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
					<li><label style="width:110px">行业属性：</label>
		                <dl><input id="hysx" name="hysx" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:110px">所在部门：</label>
		                <dl><input id="owning_department" name="owning_department" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		        </ul>
			</div>
			<div style="display:none; overflow-y:auto; overflow-x:hidden; width:703px; height:259px; margin-top:1px">
		        <ul class="forminfo">
		            <li><label style="width:120px">公司名称：</label>
		                <dl><input id="gsmc" name="gsmc" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:120px">公司注册资金：</label>
		                <dl><input id="zczj" name="zczj" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:120px">营业执照注册号：</label>
		                <dl><input id="yyzzzch" name="yyzzzch" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:120px">法定代表人：</label>
		                <dl><input id="fddbr" name="fddbr" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:120px">经营模式：</label>
		                <dl><input id="jyms" name="jyms" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:120px">员工人数：</label>
		                <dl><input id="ygrs" name="ygrs" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:120px">办公地址：</label>
		                <dl><input id="bgdz" name="bgdz" type="text" class="dfinput" value="" style="border:0px;background:#efefef;" readonly /></dl>
		            </li>
					<li><label style="width:120px">资质上传：</label>
		                <dl><span id="companyImg"></span></dl>
		            </li>
		        </ul>
			</div>
			<div style="display:none; overflow-y:auto; overflow-x:hidden; width:703px; height:259px; margin-top:1px">
				<ul class="forminfo" id="address_div">
					
		    	</ul>
			</div>
			<div style="display:none; overflow-y:auto; overflow-x:hidden; width:703px; height:259px; margin-top:1px">
				<ul class="forminfo">
		            <li><label style="width:110px">发票类型：</label>
		                <dl><input id="type" name="type" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:110px">单位名称：</label>
		                <dl><input id="dwmc" name="dwmc" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:110px">纳税人识别号：</label>
		                <dl><input id="nsrsbh" name="nsrsbh" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:110px">注册地址：</label>
		                <dl><input id="zcdz" name="zcdz" type="text" class="dfinput" value="" style="border:0px;background:#efefef;" readonly /></dl>
		            </li>
		            <li><label style="width:110px">注册电话：</label>
		                <dl><input id="zcdh" name="zcdh" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:110px">开户行：</label>
		                <dl><input id="khh" name="khh" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		            <li><label style="width:110px">银行对公账号：</label>
		                <dl><input id="yhdgzh" name="yhdgzh" type="text" class="dfinput" value="" style="border:0px;background:#efefef;width:200px" readonly /></dl>
		            </li>
		        </ul>
			</div>
		  </div>
		</div>

    </div>
<script>
$(function() {
	var id = "${param.id}";
	var action = "${param.action}";
	var genderList = getStringmap('user_info','gender');
	var owning_departmentList = getStringmap('user_info','owning_department');
	if(action == "edit"){
		$.post("<%=basePath%>user/query.json",{"user_id":id},function(data){
			$("#headimgurl").attr("src","http://www.tulingbuy.com"+data.headimgurl_page);
			$("#nick_name").val(data.nick_name);
			$("#gender").val(genderList[data.gender]);
			$("#e_mail").val(data.e_mail);
			$("#xm").val(data.xm);
			$("#phone").val(data.phone);
			$("#hysx").val(data.hysx);
			$("#owning_department").val(owning_departmentList[data.owning_department]);
		});

		$.post("<%=basePath%>user/queryCompany.json",{"user_id":id},function(data){
			$("#gsmc").val(data.gsmc);
			$("#zczj").val(data.zczj);
			$("#yyzzzch").val(data.yyzzzch);
			$("#fddbr").val(data.fddbr);
			$("#jyms").val(data.jyms);
			$("#ygrs").val(data.ygrs);
			$("#bgdz").val(data.bgdz);
		});

		$.post("<%=basePath%>user/queryCompanyImgList.json",{"user_id":id},function(data){
			var str = "";
			$.each(data, function (n, value) {
				str += "<img src=\"http://www.tulingbuy.com"+value.imgurl+"\" height=\"100px\" />&nbsp;&nbsp;";
	        });
			$("#companyImg").html(str);
		});

		$.post("<%=basePath%>user/queryAddress.json",{"user_id":id},function(data){
			var str = "<table width=\"97%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"  class=\"address_css\">"+
					"<tr style=\"background-color:#eeeeee;\">"+
			       	"		<th width=\"60\">收货人</th>"+
			       	"		<th width=\"150\">所在地区</th>"+
			       	"		<th>详细地址</th>"+
			       	"		<th width=\"60\">邮编</th>"+
			       	"		<th width=\"90\">电话</th>"+
		       		"	</tr>";
		    if(data.length>0){
				$.each(data, function (n, value) {
					str += "<tr>";
					str += "<td>"+value.consignee_name+"</td>";
					str += "<td>"+value.ssx+"</td>";
					str += "<td>"+value.address+"</td>";
					str += "<td>"+value.post_code+"</td>";
					str += "<td>"+value.phone+"</td>";
					str += "</tr>";
		        });
	        }else{
	        	str+="<tr><td colspan=\"5\" style=\"color:#888888;\">客户没有添加收货地址!</td></tr>";
	        }
	        str+="</table>";
			$("#address_div").html(str);
		});

		$.post("<%=basePath%>user/queryInvoices.json",{"user_id":id},function(data){
			$("#type").val(data.type);
			$("#dwmc").val(data.dwmc);
			$("#nsrsbh").val(data.nsrsbh);
			$("#zcdz").val(data.zcdz);
			$("#zcdh").val(data.zcdh);
			$("#khh").val(data.khh);
			$("#yhdgzh").val(data.yhdgzh);
		});
	}
});

</script>
<script>
window.onload = function() {
    var oDiv = document.getElementById("tab");
    var oLi = oDiv.getElementsByTagName("div")[0].getElementsByTagName("li");
    var aCon = oDiv.getElementsByTagName("div")[1].getElementsByTagName("div");
    for (var i = 0; i < oLi.length; i++) {
        oLi[i].index = i;
        oLi[i].onmouseover = function() {
            show(this.index);
        }
    }
    function show(a) {
        index = a;
        for (var j = 0; j < oLi.length; j++) {
            oLi[j].className = "";
            aCon[j].style.display = "none";
        }
        oLi[index].className = "cur";
        aCon[index].style.display = "block";
    }
}
</script>
</body>
</html>