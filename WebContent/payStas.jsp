<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" scope="session" class="pac.User" />
<title>付款情况</title>
</head>
<%	
	String stat = "";
	if(stat == "success"){
%>
<meta http-equiv="refresh" content="3;url=sportInvitationList.jsp"> 
<title>场馆预定成功</title>
</head>
<body style="margin:auto; background:#efefef">
	<h1 style="text-align: center;margin-top: 100px">场馆预定成功！正在跳转……</h1>
</body>
<% }
else{ %>
	<meta http-equiv="refresh" content="3;url=charge.jsp"> 
	<title>场馆预定失败</title>
	</head>
	<body style="margin:auto; background:#efefef">
		<h1 style="text-align: center; margin-top: 100px">余额不足，请充值……</h1>
	</body>
	<% 
}%>
</html>