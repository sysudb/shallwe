<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shall We</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" scope="session" class="pac.User" />
<%
	//向微信服务器拉取用户数据，并初始化user类
	String code = "";
	code = request.getParameter("code");
	if (user.init(code) == false) {
		%>
		<script type="text/javascript">
		alert("微信自动登录失败！");
		</script>
		<%
	}
	else {
		//###############
		//#在这里生成网页#
		//###############
		//下面的内容是可以删的%>
获取到的用户信息：<br>
<%
	out.print(user.openid + "</br>");
	out.print(user.nickname + "</br>");
	out.print(user.sex + "</br>");
	out.print(user.province + "</br>");
	out.print(user.city + "</br>");
	out.print(user.country + "</br>");
	out.print(user.headimgurl + "</br>");
%>
这是网站首页
		
	<%}
%>
</body>
</html>