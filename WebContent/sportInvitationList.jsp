<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shall We</title>
</head>

<style>
body{
	background-color: rgb(240,240,240);
	font-family:"Microsoft YaHei";
	margin:0;
}

img{
	border-radius:100%;
	width:100%;
}

a{
	text-decoration:none;
	color:black;
}

.head{
	background-color: white;
	display: flex;
	width:100%;
	height:15%;
}

.my_icon{
	width:20%;
	height:0;
	padding-bottom:20%;
	position:absolute;
	bottom:85%;
	left:0;
}

.title{
	width:100%;
	height:0%;
	padding-bottom:15%;
	text-align:center;
	position:absolute;
	bottom:85%;
}

.title div{
	width:20%;
	margin:0 40%;
	text-align:center;
	font-size:400%;
	font-weight:bold;
}

.create{
	position:absolute;
	width:20%;
	height:0%;
	padding-bottom:20%;
	left:80%;
	bottom:85%;
}

.list{
	display:flex;
	flex-flow:column;
	width:100%;
}

.invitation{
	background-color:white;
	width:100%;
	height:300px;
	margin:10 0 0 0;
}

.slogan{
	display:flex;
	flex-flow:row;
	width:95%;
	margin-left:5%;
	font-size:250%;
	height:30%;
}

.slogan_img{
	width:90px;
}

.location{
	display:flex;
	flex-flow:row;
	width:95%;
	margin-left:5%;
	font-size:200%;
	height:20%;
}

.location_img{
	width:60px;
}

.time{
	display:flex;
	flex-flow:row;
	width:95%;
	margin-left:5%;
	font-size:200%;
	height:20%;
}

.time_img{
	width:60px;
}

.participant{
	display:flex;
	flex-flow:row;
	width:95%;
	margin-left:5%;
	height:30%;
}

.participant div{
	width:8%;
}

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
			<div class="my_icon"><img src="<%=user.headimgurl %>" /></div>
			<div class="title"><div>活动</div></div>
			<div class="create"><img src="/shallwe/image/default_icon.jpg" /></div>
		</div>
		<div class="list">
			<%
				user.getSportInvitationList(false); 
				for(int i = 0 ; i < user.sportInvitationListLen; i++){
					%>
					<a  href="sportInvitationListDetail.jsp?id=<%= i %>">
					<div class="invitation">
						<div class="slogan">
							<div class="slogan_img"><img src="/shallwe/image/default_icon.jpg"></div>
							<div><% out.print(user.sportInvitationList[i].slogan); %></div>
						</div>
						<div class="time">
							<div class="time_img"><img src="/shallwe/image/default_icon.jpg"></div>
							<div><% out.print(user.sportInvitationList[i].timeslot.startTime.toString().split("\\.")[0]); %></div>
						</div>
						<div class="location">
							<div class="location_img"><img src="/shallwe/image/default_icon.jpg"></div>
							<div><% out.print(user.sportInvitationList[i].location + "(" + user.sportInvitationList[i].stadium.name + ")"); %></div>
						</div>
						<div class="participant">
							<%
								for(int j = 0; j < user.sportInvitationList[i].joinPeople && j < 5;j++){
									%>
									<div><img src="<% out.print(user.sportInvitationList[i].participantIcon[j]);%>" /></div>
									<%
								}
							%>
						</div>
					</div>
					</a>
					<% 
				}
			%>
		</div>
	<%}
%>
</body>
</html>