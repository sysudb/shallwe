<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>付款页面</title>

</head>
<body bgcolor="#f4f4f4">
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" scope="session" class="pac.User" />

<div align="center" style="margin:10%">
<img src="<%=user.headimgurl%>" alt="图像缺失" width="20%" style="border: rgb(128,128,128) solid 5px;border-radius:50%;"/>
</div>

<form action="sportInvitationList.jsp" method="post">
<div style="font-size:40px;background-color:white;padding-left:5%;padding-right:5%;padding-top:1%;padding-bottom:1%;margin-left:10%;margin-right:10%" >
<p>付款金额 ：
<% 
String cost = request.getParameter("totalPay"); 
if(cost == null){
	cost = "0";
}
%>

<input type="text" name="money" value="<%=cost%>" readonly="readonly" UNSELECTABLE="on" style="border:0;outline:0;font-weight:bold;font-size:40px;height:50px;width:55%;text-align:right;">
<span style="font-size:40px;float:right" > 元</span>
</div>

<div style="font-size:40px;background-color:white;padding-left:5%;padding-right:5%;padding-top:1%;padding-bottom:1%;margin-left:10%;margin-right:10%;margin-top:1%" >
<p>请选择支付方式</p>
</div>

<div style="font-size:40px;background-color:white;padding-left:10%;padding-right:5%;padding-top:5%;padding-bottom:5%;margin-left:10%;margin-right:10%;margin-top:1%" >
<input type="radio" name="method" value="wechat" checked="checked" style="width:40px;height:40px">
<img src="/shallwe/image/wechat.png" width="10%" style="vertical-align:bottom">
<span> 微信支付</span>
</div>

<div style="font-size:40px;background-color:white;padding-left:10%;padding-right:5%;padding-top:5%;padding-bottom:5%;margin-left:10%;margin-right:10%;margin-top:1%" >
<input type="radio" name="method" value="wechat" checked="checked" style="width:40px;height:40px">
<img src="/shallwe/image/paypal.png" width="10%" style="vertical-align:bottom">
<span> 支付宝</span>
</div>

<div align="center" style="font-size:40px;background-color:white;padding-left:5%;padding-right:5%;padding-top:5%;padding-bottom:5%;margin-left:10%;margin-right:10%;margin-top:1%" >
<input type="hidden" name="charge" value="yes">
<input type="submit" value="付款" onclick = "alert('支付成功！')" style="font-size:40px;height:100px;width:90%;background:rgb(255,128,0);color:white">
</div>
</form>

</body>
</html>