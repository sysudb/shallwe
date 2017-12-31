<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>余额充值</title>
</head>
<body style="margin:10%">
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" scope="session" class="pac.User" />

<div align="center">
<img src="<%=user.headimgurl%>" alt="图像缺失" width="20%" style="border: rgb(128,128,128) solid 5px;border-radius:50%;"/>
</div>
<br>
<form action="me.jsp" method="post">
<div style="font-size:25px">
<p style="font-size:30px">充值金额：
<input type="text" name="money" size="10" style="font-size:25px;height:30px"> 元
<hr>
<p style="font-size:30px">请选择支付方式</p>
<input type="radio" name="method" value="wechat" checked="checked" style="width:30px;height:30px">
<img src="/shallwe/image/wechat.png" width="30px"><span style="font-size:30px"> 微信支付</span>
<br>
<input type="radio" name="method" value="paypal" style="width:30px;height:30px">
<img src="/shallwe/image/paypal.png" width="30px"><span style="font-size:30px"> 支付宝</span>
<br>
<br>
<input type="hidden" name="charge" value="yes">
<input type="submit" value="充值" style="font-size:25px">
</div>
</form>


</body>
</html>