<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shall We</title>
</head>

<style>
body{background-color: rgb(240,240,240);font-family:"Microsoft YaHei";margin:0;position:relative;width:100%;height:100%}

img{width:100%;}

.head{
	position:relative;
	background-color: white;
	width:100%;
	height:200px;
}

.back{
	position:absolute;
	width:8%;
	top:100%;
	margin-top:-12%;
	margin-left:20px;
}

.title{
	position:absolute;
	width:400px;
	height:100px;
	text-align:center;
	bottom:10px;
	left:50%;
	margin-left:-200px;
	font-size:300%;
}


.info{
	background-color: white;
	width:100%;
	margin-top:20px;
	height:47%;
}

.info_box{
	width:95%;
	height:100%;
	margin:0 0 0 5%;
}
.slogan,.time,.location,.paytype,.creator,.sextype,.discription div{
	height:100%;
}

.creator{
	width:100%;
	height:30%;
}

.creator_icon{
	float:left;
	width:15%;
	margin-right:20px;
	position:relative;
	top:50%;
	margin-top:-7.5%;
}

.creator_icon img{border-radius:100%;}

.creator_name{
	font-size:200%;
	line-height:500%;
}

.slogan,.time,.location,.paytype,.sextype,.discription{
	width:100%;
	height:9%;
	margin-bottom:15px;
	font-size:200%;
	line-height:300%;
	border-bottom:1px solid #efefef;
}

.discription{
	border-bottom:0;
}

.slogan_img,.location_img,.time_img,.paytype_img{
	float:left;
	width:5%;
	margin-right:20px;
	position:relative;
	top:50%;
	margin-top:-2.5%;
}

.participant{
	background-color: white;
	width:100%;
	margin-top:10px;
	height:auto;
}

.participant_box{
	width: 90%;
	height: 350px;
	margin: 0 5%;
}

.participant_count{
	width:100%;
	height:100px;
	line-height:200%;
	font-size:250%;
	clear:both;
}

.participant_mem{
	float:left;
	width:150px;
	height:200px;
	line-height:50px;
	font-size:150%;
	text-align:center;
}

.participant_icon{
	width:150px;
	height:150px;
}

.participant_icon img{border-radius:100%;}

.bottom_blank{
	background-color:rgb(100,200,180);
	margin-top:20px;
	width:100%;
	height:100px;
}

.join{
	color:white;
	width:80%;
	height:100%;
	margin:0 10%;
	line-height:200%;
	font-size:300%;
	text-align:center;
}

</style>


<body>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" scope="session" class="pac.User" />


<div class="head">
	<div class="back" onclick="location.href='sportInvitationList.jsp'"><img src="/shallwe/image/back.png"></div>
	<div class="title">运动计划详情</div>
</div>
<div class="info">
	<div class="info_box">
	<div class="creator">
		<div class="creator_icon"><img src="<%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].ownerIcon %>"></div>
		<div class="creator_name"><%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].ownerWechatName %></div>
	</div>
	<div class="slogan">
		<div class="slogan_img"><img src="/shallwe/image/slogan.png"/></div>
		<div><%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].slogan %></div>
	</div>
	<div class="time">
		<div class="time_img"><img src="/shallwe/image/time.png"/></div>
		<div><%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].timeslot.startTime.toString().split("\\.")[0]+" - "+user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].timeslot.endTime.toString().split("[. ]")[1] %></div>
	</div>
	<div class="location">
		<div class="location_img"><img src="/shallwe/image/location2.png"/></div>
		<div><%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].location%> </div>
	</div>
	<div class="paytype">
		<div class="paytype_img"><img src="/shallwe/image/paytype.png"/></div>
		<% if(user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].payType == 1){ %>
		<div>支付方式：免费</div>
		<%}else if(user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].payType == 2){ %>
		<div>支付方式：我请客</div>
		<%} else if(user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].payType == 3){ %>
		<div>支付方式：AA</div>
		<%} else{ %>
		<div>支付方式： </div>
		<%}%>
	</div>
	<div class="sextype">
		<div class="paytype_img"><img src="/shallwe/image/paytype.png"/></div>
		<% if(user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].sexneed == 1){ %>
		<div>性别要求：无</div>
		<%}else if(user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].sexneed == 2){ %>
		<div>性别要求：男</div>
		<%} else if(user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].sexneed == 3){ %>
		<div>性别要求：女</div>
		<%} else{ %>
		<div> </div>
		<%}%>
	</div>
	<div class="discription">
		<div class="paytype_img"><img src="/shallwe/image/paytype.png"/></div>
		<%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].discription %>
	</div>
	</div>
</div>
<div class="participant">
	<div class="participant_box">
	<div class="participant_count">已参加（<%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].joinPeople %> / <%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].totalPeople %>）</div>
		<%for(int j = 0; j < user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].joinPeople && j < 5;j++){%>
			<div class="participant_mem">
				<div class="participant_icon"><img src="<%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].participantIcon[j] %>" /></div>
				<div class="participant_name"><%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].participantWechatname[j] %></div>
			</div>
		<%}%>
	</div>
</div>
<div class="bottom_blank">
	<div class="join" onclick="alert('<%=user.sportInvitationList[Integer.valueOf(request.getParameter("id"))].joinInvitation(user) %>');location.href='me.jsp'">加 入</div>
</div>
</body>
</html>