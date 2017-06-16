<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'ShowMessage.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
<link rel="stylesheet" type="text/css" href="./GoodManage/Showmessage.css">
<script>
/* $(document).ready(function(){
$("#menu li").each(function (index) {
    $(this).click(function () {
        $("div#right div.tab_content").hide();
        $("div#right div.tab_content:eq(" + index + ")").show();
    })
})
}); */
</script>
</head>
<body>
<div id="content">
<div id="logo">
<img height="150px"  width="1200px" src ="F:\photo\图片\广告创意思维\12.jpg" alt="logo"></img>
</div>
<div id="left">
<ul id="menu">
<li onclick="a(1)">商品管理</li>
<li onclick="a(2)">员工管理</li>
<li onclick="a(3)">订单管理</li>
<li onclick="a(4)">销售情况</li></ul>
</div>
<div id="right">
<div class="tab_content" id="demo1" style="display:none;"><jsp include="GoodManage.jsp" /></div>
<div class="tab_content" id="demo2" style="display:none;"><jsp include="WorkerManage.jsp" /></div>
<div class="tab_content" id="demo3" style="display:none;"><jsp include="OrderManage.jsp" /></div>
<div class="tab_content" id="demo4" style="display:none;"><jsp include="SaleStatus.jsp" /></div>
</div>
<div id="footer">©淘书城版权所有</div>
</div>
</body>
</html>