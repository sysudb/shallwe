<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shall We</title>
</head>

<style>
body{
	margin:0;
}

.head{
	display: flex;
	width:100%;
	height:15%
}

.my_icon{
	height:100%;
	width:10%;
}

.my_icon img{
	width:100%;
}

.title{
	width:80%;
	text-align:center;
	font-size:200%;
}

.create{
	height:100%;
	width:10%;
}

.create img{
	width:100%;
}

.list{}

.invitation_block{}

.location{}

.time{}

.participant{}

</style>

<body>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" scope="session" class="pac.User" />
<%
	//向微信服务器拉取用户数据，并初始化user类
	String code = "";
	code = request.getParameter("code");
	user.init(code);
	if (user.initSuccess == false) {
		%>
		<script type="text/javascript">
		alert("微信自动登录失败！");
		</script>
		<%
	}
	else {
		//alert("微信自动登录成功！");
		//###############
		//#在这里生成网页#
		//###############
		//下面的内容是可以删的%>
		<div class="head">
			<div class="my_icon"><img src="<% out.print(user.headimgurl); %>" /></div>
			<div class="title"><div>活动</div></div>
			<div class="create"><img src="/shallwe/image/create.png" /></div>
		</div>
		<div class="list">
			<%
				user.getSportInvitationList(false); 
				for(int i = 0 ; i < user.sportInvitationListLen; i++){
					%>
					<div><img src="<% out.print(user.sportInvitationList[i].ownerIcon);%>"></div>
					<div class="invitationBlock">
						<div class="slogan">
							<img src="/shallwe/image/<% out.print(user.sportInvitationList[i].sportType);%>.jpg">
							<% out.print(user.sportInvitationList[i].slogan); %>
						</div>
						<div class="time">
							<img src="/shallwe/image/clock.jpg">
							<% out.print(user.sportInvitationList[i].timeslot.startTime.toString().split("\\.")[0]); %>
						</div>
						<div class="location">
							<img src="/shallwe/image/pin.jpg">
							<% out.print(user.sportInvitationList[i].location + "(" + user.sportInvitationList[i].stadium.name + ")"); %>
						</div>
						<div class="participant">
							<%
								for(int j = 0; j < user.sportInvitationList[i].joinPeople && j < 5;j++){
									%>
									<img src="<% out.print(user.sportInvitationList[i].participantIcon[j]); %>">
									<%
								}
							%>
						</div>
					</div>
					<% 
				}
			%>
		</div>
	<%}
%>
</body>
</html>