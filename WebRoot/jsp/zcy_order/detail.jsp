<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page isELIgnored="true" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
SimpleDateFormat sdf = new SimpleDateFormat("YYYYMM");
Calendar calendar = Calendar.getInstance();
calendar.setTime(new Date());
calendar.add(Calendar.MONTH, -2);
Date m3 = calendar.getTime();
String mon3 = sdf.format(m3);
calendar.add(Calendar.MONTH, -9);
Date m12 = calendar.getTime();
String mon12 = sdf.format(m12);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>政采云订单管理</title>
<%-- <link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/pagination.css" rel="stylesheet" type="text/css" /> --%>
<link rel="stylesheet" href="//at.alicdn.com/t/font_1456890595_0763497.css"  id="css-mewtwo-icons"/>
<link href="<%=basePath%>css/zcydetail.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/zcyVender.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/eevee_layout.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/jquery.min.js"></script>
<script src="<%=basePath%>js/layer/layer.js"></script>
<script src="<%=basePath%>js/jquery.pagination.js"></script>
<script src="<%=basePath%>js/jquery.tmpl.js"></script>
<script src="<%=basePath%>js/stringmap.js"></script>
<script src="<%=basePath%>js/common.js"></script>
<script src="<%=basePath%>js/getUrlParam.js"></script>

<style type="text/css">

</style>
<script>
//{0}:发货单号_shipmentid
//{1}:发货清单id_shipmentid
//{2}:商品名称_itemName
//{3}:配置_attribute
//{4}:单价_skuPrice
//{5}:sku数量_quantity
//{6}:已发货数量_deliverSkuQuantity
//{7}:状态_hasInstallation
//{8}:快递公司代码_expressCompanyCode
//{9}:运单号_expressCode
//{10}地址_orderAddres
//{11}收货人_receiver
//{12}电话_phoneExt+phone
//{12}地区代码_areaCode
var spxxtp="<table class='table table-striped' style='margin-bottom: 10px;'>"+
              "<thead>"+
                "<tr><th colspan='5' class='left-text'>"+
                  "<span style='margin-right:20px;'>发货单号：{0}</span>"+
                  "<span data-expresscode='{8}' data-shipmentno='{9}'>运单号：{9}</span>"+
                  "<span style='margin-left:20px;'>地址：</span>"+
                  "<span>{10}({11} 收) {12}&nbsp;&nbsp;222</span>"+
                "</th>"+
                "</tr>"+
              "</thead>"+
              "<tbody>"+              
                "<tr data-shipmentid='{1}'>"+
                  "<td class='left-text'><span><a href='#'>{2}</a>&nbsp;&nbsp;&nbsp;&nbsp;<span class='sku-attrs'>{3}&nbsp;&nbsp;</span></span></td>"+
                  "<td width='10%' style='color:#F79000;'>{4}</td>"+
                  "<td width='10%'>{5}</td>"+
                  "<td width='14%' style='text-align: left;'>"+
                    "{6}"+                   
                  "</td>"+                  
                  "<td width='15%' rowspan='1' style='border:1px solid #CCD0D6;text-align: center;'>"+                 
                    "<div>{7}</div>"+                  
                  "</td>"+                  
                "</tr>"+
              "</tbody>"+
            "</table>";
 
//{0}:订单发票id_orderInvoiceId
//{1}:订单id_orderId
//{2}:发票id_invoiceId
//{3}:省id_provinceId
//{4}:省份_province
//{5}:城市id_cityId
//{6}:城市_city
//{7}:区id_regionId
//{8}:区_region  
//{9}:街道id_streetId
//{10}:街道_detail
//{11}:收货人_receiver
//{12}:收货人手机_mobile
//{13}:发票id_invoiceId
//{14}:发票抬头_invoiceTitle         
   var fpxx="<tr class='item-tr' data-id='{0}' data-orderid='{1}' data-invoiceid='{2}'>"+
                  "<td>"+
                  "<label data-provinceid='{3}'>{4}</label><label data-cityid='{5}'>{6}</label><label data-regionid='{7}'>{8}</label><label data-streetid='{9}'></label>{10}&nbsp;&nbsp;&nbsp;&nbsp;{11}&nbsp;&nbsp;&nbsp;&nbsp;{12}"+
                  "</td>"+
                  "<td>"+                  
                  "否"+            
                  "</td>"+
                  "<td>"+      
                  "增值税普通发票"+
                  "</td>"+
                  "<td data-invoiceid='{13}'>{14}</td>"+
                  "<td class='td-operation'><a class='js-show-inv-item-list'>查看商品</a></td>"+
                "</tr>";

String.prototype.format = function (args) {
var result = this;
if (arguments.length > 0) {
    if (arguments.length == 1 && typeof (args) == "object") {
        for (var key in args) {
            if (args[key] != undefined) {
                var reg = new RegExp("({" + key + "})", "g");
                result = result.replace(reg, args[key]);
            }
        }
    }
    else {
        for (var i = 0; i < arguments.length; i++) {
            if (arguments[i] != undefined) {
                //var reg = new RegExp("({[" + i + "]})", "g");//
                var reg = new RegExp("({)" + i + "(})", "g");
                result = result.replace(reg, arguments[i]);
            }
        }
    }
}
return result;
}
</script>
</head>
      
<body>  
<div class="order-form-detail trade-manage js-comp clearfix eve-component" data-comp-path="buyer/order_form_detail">
  <div class="float-hidden" hidden="true"></div>
  <div class="t-breadcrumb" style="padding-left: 15px;">
    <label>订单合同</label>
    <label> / </label>
    <a href="list.jsp">政采云订单</a>
    <label> / </label>
    <label class="active">订单详情</label>
  </div>
  <div class="order-form-detail-foot pull-right">
    <fieldset>
    </fieldset>
  </div>
  <div class="order-form-detail-body" data-orderid="1511823000000014237" data-order-id="1511823000000014237" data-order-type="0">
    <div class="trade-panel">
      <div class="trade-panel-top">
        <div class="trade-panel-title">订单概览</div>
      </div>
      <div class="trade-panel-body">
      
        <div class="order-form-detail-about" style="width:75%; display:inline-block;height: 160px">
        <span id="_orderStatus" style="display:none;">0</span>
          <table class="table table-striped">
            <tbody id="orderview"></tbody>
            <script id ="orderview_tmpl" type="text/x-jquery-tmpl" >
               <tr class="no-border">
                <td class="no-border right-text" width="11%"><label class="desc-text">订单号：</label></td>
                <td class="no-border" width="20%">${order.id}</td>
                <td class="no-border right-text" width="11%"><label class="desc-text">采购单号：</label></td>
                <td class="no-border" width="20%">${order.purchaserOrderId}</td>                
                <td class="no-border right-text" width="11%"><label class="desc-text">创建时间：</label></td>
                <td class="no-border left-text" width="20%">${order.createdAt}</td>
              </tr>
              <tr class="no-border">
                <td class="no-border right-text" width="11%"><label class="desc-text">采购单位：</label></td>
                <td class="no-border left-text" width="20%">${order.purchaserOrganizationName}</td>
                <td class="no-border right-text" width="11%"><label class="desc-text">提交人：</label></td>
                <td class="no-border left-text" width="20%">${order.purchaserName}</td>
                <td class="no-border right-text" width="11%"><label class="desc-text">联系电话：</label></td>
                <td class="no-border left-text" width="20%">${order.purchaserMobile}</td>
              </tr>
              <tr class="no-border">
                <td class="no-border right-text" width="11%"><label class="desc-text">供应商：</label></td>
                <td class="no-border left-text" width="20%">${order.shopName}</td>
                <td class="no-border right-text" width="11%"><label class="desc-text">联系人：</label></td>
                <td class="no-border left-text" width="20%">${order.supplierName}</td>
              </tr>
              <tr class="no-border">
                <td class="no-border right-text" width="11%"><label class="desc-text">验收单：</label></td>
                <td class="no-border left-text" width="20%">
                <a class="js-show-acceptance-file" data-files="[{&quot;id&quot;:441,&quot;orderId&quot;:&quot;1511823000000014237&quot;,&quot;type&quot;:2,&quot;userId&quot;:100058825,&quot;fileName&quot;:&quot;2017021017281128642955.png&quot;,&quot;filePath&quot;:&quot;http://demo-item.img-cn-hangzhou.aliyuncs.com/2017021017281128642955.png&quot;,&quot;createdAt&quot;:1486718938000,&quot;updatedAt&quot;:1486718938000,&quot;userName&quot;:&quot;葛晾&quot;,&quot;orgName&quot;:&quot;浙江浙大网新图灵信息科技有限公司&quot;}]">验收单</a>
                </td>
                <td class="no-border right-text" width="11%"><label class="desc-text">验收时间：</label></td>
                <td class="no-border left-text" width="20%">2017-01-24 10:13:43</td>
                <td class="no-border right-text" width="11%"><label class="desc-text">结算单号：</label></td>
                <td class="no-border left-text" width="20%">                
                <a href="/settlement/supplier-manage/detail?finalStatementId="></a>                
                </td>
              </tr>
            </script>
          </table>
        </div>
        <div class="order-form-detail-about detailStatus pull-right">
          <table class="table">
            <tbody><tr class="no-border"><td class="no-border"></td></tr>
            <tr class="no-border">
              <td class="no-border left-text" style="color:#bbb;">订单状态：</td>
            </tr>
            <tr class="no-border">
              <td style="font-size:16px;color:#F79000;" class="no-border left-text" id="orderStatus2">
                                                       交易状态  
              </td>
            </tr>
            <tr class="no-border"><td class="no-border"></td></tr>
            </tbody>
          </table>
        </div>
      
      </div>
    </div>
    
    
    <div class="trade-panel" id="Anchor">
      <div class="trade-panel-top">
        <div class="trade-panel-title">
        <span>商品信息</span>
        
        </div>
      </div>
      <div class="trade-panel-body">
        <div class="goodsListContent">
          <div class="trade-panel">
            <div class="trade-panel-top">
              <div class="trade-panel-title">
                <span class="tabletop" style="width:50%;">商品</span>
                <span class="tabletop" style="width:10%;">单价(元)</span>
                <span class="tabletop" style="width:10%;">数量</span>
                <span class="tabletop" style="width:12%;">已收货数量</span>
                <span class="tabletop" style="width:15%;text-align:right;">状态</span>
              </div>
            </div>
          </div>
          <div style="margin-bottom:10px;" id="spxx">
            
            <!-- <table class="table table-striped" style="margin-bottom: 10px;">
              <thead>
                <tr><th colspan="5" class="left-text">
                  <span style="margin-right:20px;">发货单号：1823000000000013909</span>
                  
                  <span style="margin-right:10px;">送货上门 </span>
                  <span>送货时间:2017-01-24 10:13:09</span>
                  
                </th>
                </tr>
              </thead>
              <tbody>              
                <tr data-shipmentid="1823000000000013909">
                  <td class="left-text"><span><a href="/items/2028073">test_20170119_smartphone</a>&nbsp;&nbsp;&nbsp;&nbsp;<span class="sku-attrs">128M&nbsp;&nbsp;</span></span></td>
                  <td width="10%" style="color:#F79000;">10.00</td>
                  <td width="10%">10</td>
                  <td width="14%" style="text-align: left;">
                    10                   
                  </td>                  
                  <td width="15%" rowspan="1" style="border:1px solid #CCD0D6;text-align: center;">                 
                    <div>全部收货</div>                  
                  </td>                  
                </tr>
              </tbody>
            </table> -->
          </div>
        </div>
        <div class="requireListContent" style="display:none;">
          <table class="table">
            <thead>
              <tr>
                <th>需求单号</th>
                <th>需求人</th>
                <th>商品</th>
                <th>单价(元)</th>
                <th>数量</th>
              </tr>
            </thead>
            <tbody>
              
              <tr>
                
                <td>1511000000000015032</td>
                
                <td>邢光明</td>
                <td><a href="/items/2028073">test_20170119_smartphone</a></td>
                <td style="color:#F79000;">10.00</td>
                <td>10</td>
              </tr>
              
            </tbody>
          </table>
        </div>
      </div>
    </div>
    
    
      <div class="order-invoices">
        <div class="trade-panel">
          <div class="trade-panel-top">
            <div class="trade-panel-title">发票信息</div>
          </div>
          <div class="trade-panel-body">
          
          
            <table class="table table-striped">
              <thead>
                <tr>
                  <th width="40%">发票寄送地址</th>
                  <th width="10%">货票同行</th>
                  <th width="15%">发票类型</th>
                  <th width="25%">发票抬头信息</th>
                  <th width="10%" class="td-operation">操作</th>
                </tr>
              </thead>
              <tbody id="fpxx">
<!--                 <tr class="item-tr" data-id="2283" data-orderid="1511823000000014237" data-invoiceid="3184">
                  <td>
                  <label data-provinceid="330000">浙江省</label><label data-cityid="330100">杭州市</label><label data-regionid="330108">滨江区</label><label data-streetid="330108003"></label>滨文路1027号&nbsp;&nbsp;&nbsp;&nbsp;陈驰&nbsp;&nbsp;&nbsp;&nbsp;18787648363
                  </td>
                  <td>                  
                                                                   否            
                  </td>
                  <td>      
                                                                  增值税普通发票
                  </td>
                  <td data-invoiceid="3184">浙江职业技术学院</td>
                  <td class="td-operation"><a class="js-show-inv-item-list">查看商品(1)</a></td>
                </tr> -->
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <!-- <div class="order-payments">
        <div class="trade-panel">
          <div class="trade-panel-top">
            <div class="trade-panel-title">预算信息
              
            </div>
          </div>
          <div class="trade-panel-body">
            
            <input type="hidden" id="payments" value="null">
            <div class="receiver-mulit-payments">
              <div>
              
              
              
              </div>
            </div>
            <div class="pur-all-payments">
            本次总使用情况
              <table class="table table-striped">
                
                <tbody>
                  
                  <tr>
                    <td width="33%">已关联采购计划总额(元)：<span class="currency">0.00</span></td>
                    
                    <td width="33%">计划外资金总额(元)：
                    <input type="hidden" class="js-other-pay-item-id" data-id="2693" data-orderid="1511823000000014237" data-paidtype="2" data-quantity="10" value="10000">
                    
                    <span class="currency">100.00</span>&nbsp;&nbsp;&nbsp;&nbsp;<a class="js-show-other-pay-item-list">查看商品(1)</a></td>
                    
                    <td width="34%">采购单总额(元)：<span class="currency">100.00</span></td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    
    
      <div class="order-payments">
        <div class="trade-panel">
          <div class="trade-panel-top">
            <div class="trade-panel-title">
            退货信息
            </div>
          </div>
          <div class="trade-panel-body">
            <table class="table table-striped" style="margin-top:10px;">
              <thead>
                <tr>
                  <th width="20%"> 退换货单号</th>
                  <th width="20%">原因</th>
                  <th width="20%">退换货方式</th>
                  <th width="20%">状态</th>
                  <th width="20%" class="td-operation">操作</th>
                </tr>
              </thead>
              <tbody>
              
              </tbody>
            </table>
          </div>
        </div>
      </div>
    
    
    
    <div class="order-payments">
        <div class="trade-panel">
          <div class="trade-panel-top">
            <div class="trade-panel-title">流转日志
            </div>
          </div>
          <div class="trade-panel-body">
          <div class="flow-log js-comp eve-component" data-comp-path="common/flow_log">
<div class="log sr-flowLog">
  <table>
  
    <tbody><tr><td><div class="sr-dot dot-muted"></div><div class="sr-line"></div></td>
      <td>2017-01-24 10:12:19</td>
      <td>浙江职业技术学院</td>
      <td>邢光明</td>
      <td>订单生成</td>
      
      <td>成功</td>
      
    </tr>
  
    <tr><td><div class="sr-dot dot-muted"></div><div class="sr-line"></div></td>
      <td>2017-01-24 10:12:54</td>
      <td>浙江浙大网新图灵信息科技有限公司</td>
      <td>葛晾</td>
      <td>供应商接单</td>
      
      <td>成功</td>
      
    </tr>
  
    <tr><td><div class="sr-dot dot-muted"></div><div class="sr-line"></div></td>
      <td>2017-01-24 10:13:09</td>
      <td>浙江浙大网新图灵信息科技有限公司</td>
      <td>葛晾</td>
      <td>供应商发货</td>
      
      <td>全部发货</td>
      
    </tr>
  
    <tr><td><div class="sr-dot dot-muted"></div><div class="sr-line"></div></td>
      <td>2017-01-24 10:13:42</td>
      <td>浙江职业技术学院</td>
      <td>邢光明</td>
      <td>采购单位确认收货</td>
      
      <td>全部收货</td>
      
    </tr>
  
    <tr><td><div class="sr-dot dot-muted"></div><div class="sr-line"></div></td>
      <td>2017-01-24 10:13:42</td>
      <td>浙江职业技术学院</td>
      <td>邢光明</td>
      <td>采购单位验收</td>
      
      <td>完成验收</td>
      
    </tr>
  
    <tr><td><div class="sr-dot dot-primary"></div></td>
      <td>2017-02-07 10:45:45</td>
      <td>浙江职业技术学院</td>
      <td>邢光明</td>
      <td>采购单位申请退换货</td>
      
      <td>成功</td>
      
    </tr>
  
  </tbody></table>
</div>
</div>
          </div>
        </div>
      </div>
  </div> -->

</div>
    <script type="text/javascript">
		$(function(){
			var _id = getUrlParam("id");
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
               "10":'退换货中'
			};
			$.post('<%=basePath%>zcy/getorderdetail.json?id='+_id,function(data){
			
			console.log(JSON.stringify(data));
			var _data = JSON.parse(data.result);			
			var _deliveryItems = _data.deliveryItems;
			var _invoiceItems = _data.invoiceItems;
			var _shipmentItems = _data.shipmentItems;
			console.log(_shipmentItems);
			var _orderItems = _data.orderItems;
			var _order = _data.order;
			var _text = stateTtext[_order.status];
			$("#_orderStatus").text(_order.status);
			$("#orderStatus2").text(_text);
			$("#orderview").html($('#orderview_tmpl').tmpl(_data));
			
			// 订单列表 
			var pz="";
			var phone="";
			   var pzarr=JSON.stringify(_orderItems[0]["attribute"]).split(",");
			   for(var j=0;j<pzarr.length;j++)
			      {
			       pz+=pzarr[j].split(":")[1]+" ";
			       }
			       pz=pz.replace(/\\/g,"").replace(/"/g,"").replace("}","");
			if(_deliveryItems[0]["orderAddress"]["phoneExt"]!=""&&_deliveryItems[0]["orderAddress"]["phone"]!=""){
			  phone=_deliveryItems[0]["orderAddress"]["phoneExt"]+"-"+_deliveryItems[0]["orderAddress"]["phone"];
			}       
			if(_shipmentItems.length!=0)
			{
			    $.each(_shipmentItems,function(i,value){
			   var spxxtemplate= spxxtp.format(value["shipmentId"],value["shipmentId"],_orderItems[0]["itemName"],pz,(_orderItems[0]["skuPrice"]/100).toFixed(2),_orderItems[0]["quantity"],_orderItems[0]["deliverSkuQuantity"],_orderItems[0]["hasInstallation"]?stateTtext[_orderItems[0]["hasInstallation"]]:"无",value["expressCompanyCode"],value["expressCode"],_deliveryItems[0]["orderAddress"]["province"]+_deliveryItems[0]["orderAddress"]["city"]+_deliveryItems[0]["orderAddress"]["region"]+_deliveryItems[0]["orderAddress"]["street"]+_deliveryItems[0]["orderAddress"]["detail"],_deliveryItems[0]["orderAddress"]["receiver"],phone,_deliveryItems[0]["orderAddress"]["areaCode"]);
		       $("#spxx").append(spxxtemplate); 
		       });	
			}       
			else
			{
			    if(_orderItems.length!=0)
			    {
			       var spxxtemplate= spxxtp.format('','',_orderItems[0]["itemName"],pz,(_orderItems[0]["skuPrice"]/100).toFixed(2),_orderItems[0]["quantity"],_orderItems[0]["deliverSkuQuantity"],_orderItems[0]["hasInstallation"]?stateTtext[_orderItems[0]["hasInstallation"]]:"无",'','',_deliveryItems[0]["orderAddress"]["province"]+_deliveryItems[0]["orderAddress"]["city"]+_deliveryItems[0]["orderAddress"]["region"]+_deliveryItems[0]["orderAddress"]["street"]+_deliveryItems[0]["orderAddress"]["detail"],_deliveryItems[0]["orderAddress"]["receiver"],phone,_deliveryItems[0]["orderAddress"]["areaCode"]);
		           $("#spxx").append(spxxtemplate);
			    }
			}
//{0}:发货单号_shipmentid
//{1}:发货清单id_shipmentid
//{2}:商品名称_itemName
//{3}:配置_attribute
//{4}:单价_skuPrice
//{5}:sku数量_quantity
//{6}:已发货数量_deliverSkuQuantity
//{7}:状态_hasInstallation		

//{8}:快递公司代码_expressCompanyCode   ,value["expressCompanyCode"],value["expressCode"],_deliveryItems["orderAddress"]["province"]+_deliveryItems["orderAddress"]["city"]+_deliveryItems["orderAddress"]["region"]+_deliveryItems["orderAddress"]["street"]+_deliveryItems["orderAddress"]["detail"],_deliveryItems["orderAddress"]["receiver"],_deliveryItems["orderAddress"]["phoneExt"]+"-"+_deliveryItems["orderAddress"]["phone"],_deliveryItems["orderAddress"]["areaCode"]
//{9}:运单号_expressCode              
//{10}地址_orderAddres     
//{11}收货人_receiver	     
//{12}电话_phoneExt+phone  
//{12}地区代码_areaCode     
			
	//发票列表		
	$.each(_invoiceItems,function(i,value){
	 var fpxxtemplate= fpxx.format(value["skus"][0]["orderInvoiceId"],value["skus"][0]["orderId"],value["invoiceInfo"]["invoiceId"],value["invoiceAddress"]["provinceId"],value["invoiceAddress"]["province"],value["invoiceAddress"]["cityId"],value["invoiceAddress"]["city"],value["invoiceAddress"]["regionId"],value["invoiceAddress"]["region"],value["invoiceAddress"]["streetId"],value["invoiceAddress"]["detail"],value["invoiceAddress"]["receiver"],value["invoiceAddress"]["mobile"],value["invoiceInfo"]["invoiceId"],value["invoiceInfo"]["invoiceTitle"]);
	 $("#fpxx").append(fpxxtemplate);	
	});		
//{0}:订单发票id_orderInvoiceId
//{1}:订单id_orderId
//{2}:发票id_invoiceId
//{3}:省id_provinceId
//{4}:省份_province    
//{5}:城市id_cityId    
//{6}:城市_city        
//{7}:区id_regionId   
//{8}:区_region       
//{9}:街道id_streetId  
//{10}:街道_detail     
//{11}:收货人_receiver  
//{12}:收货人手机_mobile  
//{13}:发票id_invoiceId  
//{14}:发票抬头_invoiceTitle  
	
	
	//查看商品
	$(".js-show-inv-item-list").on("click",function(){
	  var itemName=_orderItems[0]["itemName"];
	  var skuPrice=_orderItems[0]["skuPrice"];
	  var quantity=_orderItems[0]["quantity"];
	  var url="merchandiseview.jsp?itemName="+itemName+"&pz="+pz+"&skuPrice="+skuPrice+"&quantity="+quantity;
	  layer.open({
					    type: 2,
					    area: '1000px',
					    shade:[0.8, '#393D49'],
					    offset:'90px',
					    scrollbar:false,
					    fix: true, //不固定
					    maxmin: false,//关闭最大最小化
					    content:encodeURI(url),
					    title:'已关联发票的商品'
			     });		    
	});
		});
		});

    </script>
</body>
</html>