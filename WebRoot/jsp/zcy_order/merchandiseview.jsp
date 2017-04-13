<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看商品</title>
<%-- <link href="<%=basePath%>css/style.css" rel="stylesheet" type="text/css" /> --%>
<style type="text/css">
       *, :after, :before {
    box-sizing: border-box;
}
.modal .modal-body {
    padding: 20px 20px 16px;
    min-height: 200px;
}
.clearfix {
    zoom: 1;
}
b, strong {
    font-weight: 700;
}
.req-title {
    display: inline-block;
    vertical-align: middle;
    font-size: 14px;
    margin-bottom: 10px;
    font-family: Microsoft YaHei,Hiragino Sans GB,WenQuanYi Micro Hei,sans-serif;
}
.table {
    width: 100%;
    border: 1px solid #e7e9ea;
}
table {
    border-collapse: collapse;
    border-spacing: 0;
}
.table thead {
    background-color: #f7f7f7;
    color: #666;
}
.table>thead>tr>th {
    line-height: 18px;
    font-family: Hiragino Sans GB,Microsoft YaHei,微软雅黑,SimSun,\5b8b\4f53,WenQuanYi Micro Hei,Helvetica Neue,Arial,Tahoma,sans-serif;
    -webkit-font-smoothing: antialiased;
    text-align: left;
    padding: 10px;
    font-weight: 400;
    font-size: xx-small;
}
.table th {
    border-left: 1px solid #f7f7f7;
    border-right: 1px solid #f7f7f7;
}
.table>thead>tr>th:last-child {
    padding-right: 10px;
    border-right: 1px solid #e7e9ea;
}
.table>tbody>tr {
    height: 40px;
    background-color: #fff;
    color: #666;
    border-bottom: 1px solid #e7e9ea;
}
.table>tbody>tr>td:first-child, .table>tbody>tr>td:first-of-type {
    padding-left: 10px;
    border-left: 1px solid #e7e9ea;
}
.table>tbody>tr>td {
    font-family: Hiragino Sans GB,Microsoft YaHei,微软雅黑,SimSun,\5b8b\4f53,WenQuanYi Micro Hei,Helvetica Neue,Arial,Tahoma,sans-serif;
    padding: 10px;
    font-size: xx-small;
}
.sku-attrs {
    color: #bbb;
}
tbody .item-tr .price {
    color: #fe7800;
    font-weight: 400;
}
.table>tbody>tr>td:last-child {
    padding-right: 10px;
    border-right: 1px solid #e7e9ea;
}
.pur-modal .selected-pagination {
    float: right;
    right: 10px;
    margin-top: 10px;
}
.clearfix:after {
    clear: both;
}
.clearfix:after, .clearfix:before {
    content: "";
    display: table;
}
.modal .modal-footer {
    padding: 16px 20px 20px;
    text-align: right;
    border-top: 1px solid #dfdfdf;
    border-bottom-left-radius: 2px;
    border-bottom-right-radius: 2px;
}
.pur-modal .modal-footer .modal-footer-option {
    display: inline;
}
.btn-medium {
    padding: 7px 14px;
}
.btn-info {
    color: #666;
    border-color: #bbb;
    background-color: #fff;
}
.btn {
    display: inline-block;
    vertical-align: baseline;
    margin-bottom: 0;
    font-weight: 400;
    text-align: center;
    cursor: pointer;
    border: 1px solid transparent;
    border-radius: 0;
    white-space: nowrap;
    line-height: 18px;
    -moz-user-select: -moz-none;
    -ms-user-select: none;
    -webkit-user-select: none;
    user-select: none;
    padding: 9px 16px;
    font-size: 12px;
    line-height: 12px;
}


</style>
	<script src="<%=basePath%>js/jquery.min.js"></script>
	<script src="<%=basePath%>js/layer/layer.js"></script>
	<script src="<%=basePath%>js/common.js"></script>
	<script src="<%=basePath%>js/getUrlParam.js"></script>
<script>
     window.onload=function(){
     
     var _itemName = getUrlParam("itemName");
     var _pz = getUrlParam("pz");
     var _skuPrice = getUrlParam("skuPrice");
     var _quantity = getUrlParam("quantity");
     $("._itemname").html(_itemName);
     $(".sku-attrs").html(_pz);
     $(".skuprice").html((_skuPrice/100).toFixed(2));
     $(".skucount").html(_quantity);
     $(".sku-sumprice").html((_skuPrice/100*_quantity).toFixed(2));
     };
</script>
</head>

<body>
    <div class="invoice-items-lists" data-req-lists-total="1" data-invoiceid="">
    <div class="invoice-items">
      <div class="modal-body clearfix">
        <div><strong><span class="req-title">商品清单</span></strong></div>
        <table class="table  center-tex invoice-item-table">
          <thead>
            <tr>
              <th class="left-text" colspan="1" width="350"> <span class="">商品信息</span> </th>
              <th width="200">单价(元)</th>
              <th width="100">数量</th>
              <th width="100" class="last">小计(元)</th>
            </tr>
          </thead>
          <tbody>
              <tr class="item-tr">
                <td><span class="_itemname"></span>&nbsp;&nbsp;&nbsp;&nbsp;<span class="sku-attrs">&nbsp;&nbsp;</span></td>
                <td><div><span class="price"></span><span class="price skuprice"></span></div></td>
                <td class="skucount">
                </td>
                <td class="last">
                  <span class="price"></span><span class="price sku-sumprice"></span>
                </td>
              </tr>
          </tbody>
        </table>
        <div class="selected-pagination pagination-danger clearfix"></div>
      </div>
    </div>
  </div>

</body>
</html>
