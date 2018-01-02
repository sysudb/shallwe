<%@page import="org.apache.catalina.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pac.SportInvitation2" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" scope="session" class="pac.User" />
<%
	String stat = user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].joinInvitation(user);
	if(stat == "加入成功"){
%>
<meta http-equiv="refresh" content="3;url=me.jsp"> 
<title>加入活动成功</title>
</head>
<body style="margin:auto; background:#efefef">
	<h1 style="text-align: center;margin-top: 100px">加入活动成功！正在跳转……</h1>
</body>
<% }
else{ %>
	<meta http-equiv="refresh" content="5;url=sportInvitationList.jsp"> 
	<title>加入活动失败</title>
	</head>
	<body style="margin:auto; background:#efefef">
		<h1 style="text-align: center; margin-top: 100px">
			加入活动失败！<br></br>
			<%=stat %><br></br>
			正在返回页面……</h1>
	</body>
	<% 
}%>
</html>