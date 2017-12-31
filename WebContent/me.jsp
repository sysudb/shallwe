<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的信息</title>
</head>
<body style="margin:10%" >
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" scope="session" class="pac.User" />

<div align="center">
<img src="<%=user.headimgurl%>" alt="图像缺失" width="20%" style="border: rgb(128,128,128) solid 5px;border-radius:50%;"/>
<p style="font-size:20px"><%=user.province%>-<%=user.city %></p>
</div>

<div align="right" style="font-size:25px">
<p>余额：<%=user.money %>元
<a href="charge.jsp">充值</a>
</div>

<div>
<p style="font-size:30px">我的运动</p>
<hr/>
</div>
</body>
</html>