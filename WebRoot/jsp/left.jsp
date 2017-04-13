<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css" />
<script language="JavaScript" src="<%=basePath%>js/jquery.min.js"></script>


<style type="text/css">

body{margin:0;padding:0;overflow-x:hidden}
html, body{height:100%;}
img{border:none;}
*{font-family:'微软雅黑';font-size:12px;color:#626262;}
dl,dt,dd{display:block;margin:0;}
a{text-decoration:none;}

#bg{background-image:url(<%=basePath%>images/content/dotted.png);}
.container{width:100%;height:100%;background-color:#233646;}

/*left*/
.leftsidebar_box{width:200px;height:auto !important;overflow:visible !important;position:fixed;height:100% !important;background-color:#233646;}
.line{height:2px;width:100%;background-image:url(<%=basePath%>images/left/line_bg.png);background-repeat:repeat-x;}
.leftsidebar_box dt{padding-left:20px;padding-right:10px;background-repeat:no-repeat;background-position:10px center;color:#f5f5f5;font-size:14px;position:relative;line-height:48px;cursor:pointer;}
.leftsidebar_box dd{font-size:15px;background-color:#233646;padding:5px 0 5px 50px;}
.select{background-image:url(<%=basePath%>images/left/line_bg.png);background-repeat:repeat-Y;}
.leftsidebar_box dd a{color:#f5f5f5;line-height:20px;}
.leftsidebar_box dt img{position:absolute;right:10px;top:20px;}
.leftsidebar_box i{ margin-right:10px;color:#F5F5F5;font-size:20px;display:inline-block;}
.copyright{position:absolute;bottom:5px;color:#eaeaea; margin:auto;}
</style>

</head>

<body id="bg" style="background:#f0f9fd;">
    <div class="container">

	<div class="leftsidebar_box">
		<dl>
		<a href="firstpage.jsp" target="rightFrame" style="display: block;">
			<dt><i class="iconfont icon-wangluo"></i>首页</dt>
			</a>
		</dl>
		<!-- 基础数据 -->
		<%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_basic_data")){%>
		<dl>
			<dt><i class="iconfont icon-weifenxiaopcjiemianzhuanhuan"></i>基础数据<img src="<%=basePath%>images/left/select_xl01.png"></dt>
			<%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_article")){%>
            <dd class="first_dd"><a href="<%=basePath%>jsp/article/list.jsp" target="rightFrame">单页管理</a></dd>
	        <%}%>
	        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_ad")){%>
	           <dd><a href="<%=basePath%>jsp/ad/list.jsp" target="rightFrame">横幅广告管理</a></dd>
	        <%}%>
	        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_user_client")){%>
	            <dd><a href="<%=basePath%>jsp/user_client/list.jsp" target="rightFrame">专属客服管理</a></dd>
	        <%}%>
	        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_salesman")){%>
	            <dd><a href="<%=basePath%>jsp/salesman/list.jsp" target="rightFrame">专属销售管理</a></dd>
	        <%}%>
	        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_maintenance_station")){%>
	            <dd><a href="<%=basePath%>jsp/maintenance_station/list.jsp" target="rightFrame">维修站管理</a></dd>
	        <%}%>
	        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_dmb")){%>
	            <dd><a href="<%=basePath%>jsp/dmb/list.jsp" target="rightFrame">字典数据管理</a></dd>
	        <%}%>
	        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_stringmap")){%>
	            <dd><a href="<%=basePath%>jsp/stringmap/list.jsp" target="rightFrame">生产字典管理</a></dd>
	        <%}%>
		</dl>
		<% }%>
    	<!-- 图灵买商品 -->
		<%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_tulingbuy_product")){%>
		<dl> 
            <dt><i class="iconfont icon-dianpu"></i>图灵买商品<img src="<%=basePath%>images/left/select_xl01.png"></dt>
    	
        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_product_type")){%>
            <dd><a href="<%=basePath%>jsp/product_type/list.jsp" target="rightFrame">分类管理</a></dd>
        <%}%>
        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_product_type")){%>
            <dd><a href="<%=basePath%>jsp/product_type/template_list.jsp" target="rightFrame">模板管理</a></dd>
        <%}%>
        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_product_brand")){%>
            <dd><a href="<%=basePath%>jsp/brand/list.jsp" target="rightFrame">品牌管理</a></dd>
        <%}%>
        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_product")){%>
        	  <dd><a href="<%=basePath%>jsp/product_tree/list.jsp" target="rightFrame">商品管理</a></dd>
           <%}%>
<%--         <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_product")){%>
            <dd><a href="<%=basePath%>jsp/product/list.jsp" target="rightFrame">商品管理</a></dd>
        <%}%> --%>
        
        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_product")){%>
            <dd><a href="<%=basePath%>jsp/zcy_product/list.jsp" target="rightFrame">政采云商品管理</a></dd>
        <%}%>
              
		</dl>
    	<%}%>
    	

    		<!-- 需求单 -->
    	 <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_demand")){%>
		<dl>
			<a href="<%=basePath%>jsp/orderxuqiu/list.jsp" target="rightFrame" style="display: block;">
			   <dt><i class="iconfont icon-fabuxuqiu"></i>需求单</dt>
			</a>
		</dl>
    	<%}%>
    	    <!-- 订单合同  -->
    	 <% if(com.yunrer.common.AuthUtil.getAttribute(session, "m_order")){ %>
	    	 <dl>
				<dt><i class="iconfont icon-dingdan"></i>订单合同<img src="<%=basePath%>images/left/select_xl01.png"></dt>
	            <dd class="first_dd"><a href="<%=basePath%>jsp/order/list.jsp" target="rightFrame">订单合同</a></dd>
	            <dd class="first_dd"><a href="<%=basePath%>jsp/zcy_order/list.jsp" target="rightFrame">政采云订单</a></dd>
    	 
    	 
			</dl>

    	<%}%>
    		<!-- 服务单 -->
    	 <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_service")){%>
		<dl>
			<a href="<%=basePath%>jsp/service/list.jsp" target="rightFrame" style="display: block;">
			   <dt><i class="iconfont icon-fuwu"></i>服务单</dt>
			</a>
		</dl>
    	<%}%>
    	<!-- 图灵云商品 -->
	
		<%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_tulingyun")){%>
		<dl> 
            <dt><i class="iconfont icon-dianpu"></i>图灵云模块<img src="<%=basePath%>images/left/select_xl01.png"></dt> 	
        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_yun_type")){%>
            <dd><a href="<%=basePath%>jsp/yun_type/list_yuntype.jsp" target="rightFrame">类别管理</a></dd>
        <%}%>
        <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_yun_module")){%>
            <dd><a href="<%=basePath%>jsp/yun/list.jsp" target="rightFrame">模块管理</a></dd>
        <%}%>
        	</dl>
        <%}%>
		
    	
    		<!-- 网站会员 -->
    	 <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_user")){%>
		<dl >
			<a href="<%=basePath%>jsp/user/list.jsp" target="rightFrame" style="display: block;">
			   <dt><i class="iconfont icon-huiyuankehu"></i>网站会员</dt>
			</a>
		</dl>
    	<%}%>
    		<!-- 公司管理 -->
    	 <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_company")){%>
    	<dl >
			<a href="<%=basePath%>jsp/company/list.jsp" target="rightFrame" style="display: block;">
			   <dt><i class="iconfont icon-gongsi"></i>公司管理</dt>
			</a>
		</dl>
		<% }%>
    		<!-- 用户留言 -->
    	 <%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_user_feedback")){%>
		<dl >
			<a href="<%=basePath%>jsp/user_feedback/list.jsp" target="rightFrame" style="display: block;">
			   <dt><i class="iconfont icon-zixun"></i>用户留言</dt>
			</a>
		</dl>
    	<%}%>
		<!-- 系统管理 -->
		<%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_system")){%>
		<dl >
			<dt><i class="iconfont icon-weixiu"></i>系统管理<img src="<%=basePath%>images/left/select_xl01.png"></dt>
			<%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_system_user")){%>
            <dd class="first_dd"><a href="<%=basePath%>jsp/system_user/list.jsp" target="rightFrame">用户管理</a></dd>
	        <%}%>
			<%if(com.yunrer.common.AuthUtil.getAttribute(session, "m_system_role")){%>
            <dd ><a href="<%=basePath%>jsp/system_role/list.jsp" target="rightFrame">角色管理</a></dd>
	        <%}%>
		</dl>
		<% }%>
<!-- 		<div class="copyright">
		Copyright © tulingbuy.com
		</div>
	</div> -->

</div>

<script type="text/javascript">
$(".leftsidebar_box dt").css({"background-color":"#2F4050"});
$(".leftsidebar_box dt img").attr("src","<%=basePath%>images/left/select_xl01.png");
$(function(){
	$(".leftsidebar_box dd").hide();
	$(".leftsidebar_box dt").click(function(){
		$(".leftsidebar_box dt").css({"background-color":"#2F4050"})
		$(this).css({"background-color": "#233646"});
		$(this).parent().find('dd').removeClass("menu_chioce");
		$(".leftsidebar_box dt img").attr("src","<%=basePath%>images/left/select_xl01.png");
		$(this).parent().find('img').attr("src","<%=basePath%>images/left/select_xl.png");
		$(".menu_chioce").slideUp(); 
		$(this).parent().find('dd').slideToggle();
		$(this).parent().find('dd').addClass("menu_chioce");
		$(this).parent().find("dd").removeClass("select");
	});
	$(".leftsidebar_box dd a").click(function(){
		$(".leftsidebar_box").find("dd").removeClass("select");
		$(this).parent().addClass("select");
	
	})
	
})


</script>

    

</body>
</html>