<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shall We</title>
<style>
body{background-color: rgb(240,240,240);font-family:"Microsoft YaHei";margin:0;}
img{width:100%;}
.my_icon,.create,.participant,.creator img{border-radius:100%;}

.head{
	background-color:white;
	position:fixed;
	top:0;
	left:0;
	width:100%;
	height:200px;
	z-index:99999;
}

.my_icon{
	position:absolute;
	width:15%;
	height:auto;
	bottom:0;
	margin: 26px;
	z-index:1000;
}
.shallwe{
	position:absolute;
	width:100%;
	text-align:center;
	line-height:200px;
	font-size:350%;
	color:rgb(100,200,180);
}


.title{
	position:absolute;
	width:400px;
	height:100px;
	text-align:center;
	bottom:20px;
	left:50%;
	margin-left:-200px;
}

.title .activity,.news{
	position:absolute;
	width:200px;
	height:100px;
	font-size:300%;
	line-height:200%;
}
.title .activity{
	left:0;
	border-radius:40px 0 0 40px;
	background-color:rgb(100,200,180);
	color:white;
	}
.title .news{
	right:0;
	border-radius:0 40px 40px 0;
	background-color:rgb(240,240,240);
	color:black;
}

.list{
	display:flex;
	flex-flow:column;
	width:100%;
}

.empty{
	height:200px;
}

.invitation{
	border-radius:10px;
	width:100%;
	height:300px;
	margin:40 0 0 0;
}

.creator_box{
	float:left;
	width:15%;
	height:100%;
}

.creator{
	position:relative;
	top:50%;
	width:90%;
	margin-top:-45%;
	margin-left:10%;
}

.creator_name{font-size:200%;text-align:center;}

.triangle_box{
	width:5%;
	height:100%;
	float:left;
}

.triangle{
	width: 0;  
    height: 0;  
    border-top: 15px solid transparent;  
    border-right: 30px solid white;  
    border-bottom: 15px solid transparent;
	float:right;
	position:relative;
	top:50%;
	margin-top:-15px;
}

.info_box{
	background-color:white;
	float:right;
	width:80%;
	height:100%;
}

.info{
	float:right;
	width:95%;
	height:260;
	margin: 20px 0 20px 5%;
}
 
.slogan{
	width:100%;
	font-size:250%;
	height:30%;
	line-height:200%;
}

.slogan_img{
	float:left;
	width:90px;
	margin-right:20px;
}

.location,.time{
	float:left;
	width:100%;
	font-size:200%;
	height:20%;
	line-height:200%;
}

.location_img,.time_img{
	float:left;
	width:5%;
	position:relative;
	top:50%;
	margin-top:-2.5%;
	margin-right:20px;
}

.participant{
	float:left;
	width:100%;
	height:30%;
}

.participant_men{
	float:left;
	position:relative;
	width:8%;
	top: 50%;
	margin-top:-4%;
	margin-right:20px;
}

.participant_men img{
	border-radius:100%;
}

.participant_count{
	width:15%;
	height:80%;
	float:right;
	margin-right:70px;
	font-size:200%;
	text-align:center;
	line-height:200%;
	background-color:rgb(240,240,240);
	border-radius:20px 20px 20px 20px;
}

.head{
	box-shadow:0px 5px 14px 6px rgba(200,200,200,0.2);
} 
.info_box{
	box-shadow:-2px 7px 6px rgba(200,200,200,0.3);
} 

/**==============wheelmenu begin===========**/
.wheel{
	position:fixed;
	width:150px;
	height:150px;
	bottom:100px;;
	right:50px;
	font-size:100px;
	line-height:150px;
	text-align:center;
	color: white;
	border-radius:50%;
	background: rgb(100,200,180);
}

.wheel_option{
	display: none;
	width:200px;
	height:100px;
	font-size: 36px;
	color: white;
	margin-top: -260px;
    line-height: 100px;
    margin-bottom: 30px;
	text-align: center;
	margin-left: -25px;
    border-radius: 10px;
	background: rgb(100,200,180);
}

.wheel_option:active{
	background: rgb(204, 204, 204);
}
/**==============wheelmenu end===========**/
    
</style>

<input type="hidden" id="refreshed" value="no">
<script type="text/javascript">
onload=function(){
	var e=document.getElementById("refreshed");
		if(e.value=="no") e.value="yes";
		else{
			e.value="no";location.reload();
		}
}

function showOptions(wheel){
	wheel_options = document.getElementsByClassName("wheel_option");
	if(wheel.style.background == "rgb(204, 204, 204)"){
		wheel.style.background = "rgb(100,200,180)";
		wheel.getElementsByTagName("span").innerHTML = "+";
		for(var i = 0; i < wheel_options.length; i++){
			wheel_options[i].style.display = "none";
		}
	}else{
		wheel.style.background = "rgb(204, 204, 204)";
		wheel.getElementsByTagName("span").innerHTML = "-";
		for(var i = 0; i < wheel_options.length; i++){
			wheel_options[i].style.display = "block";
		}
	}
}

</script >

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
	else {%>
		<div class="head">
			<div class="my_icon" onclick="window.location.href='me.jsp'"><img src="<%=user.headimgurl %>" /></div>
			<div class="shallwe">SHALL WE</div>
		</div>
		<div class="wheel" onclick="showOptions(this)">
			<span>+</span>
			<div  class = "wheel_option" onclick="window.location.href='promotSport.jsp'">发布预约</div>
			<div class = "wheel_option" onclick="window.location.href='chooseStadium.jsp'">场馆预定</div>
		</div>
		
		<div class="empty"></div>
		<div class="list">
			<%user.getSportInvitationList(false); 
			for(int i = user.sportInvitationListLen-1 ; i >= 0; i--){%>
			<div class="invitation" >
				<div class="creator_box">
					<div class="creator">
						<div class="creator_icon"><img src="<%=user.sportInvitationList[i].ownerIcon %>"></div>
						<div class="creator_name"><%=user.sportInvitationList[i].ownerWechatName %></div>
					</div>
				</div>
				<div class="triangle_box">
					<div class="triangle"></div>
				</div>
				<div class="info_box" onclick="window.location.href='sportInvitationListDetail.jsp?id=<%=i%>'">
					<div class="info">
						<div class="slogan">
							<div class="slogan_img"><img src="/shallwe/image/sport_type/<%=user.sportInvitationList[i].sportType %>.png"></div>
							<div><% out.print(user.sportInvitationList[i].slogan); %></div>
						</div>
						<div class="time">
							<div class="time_img"><img src="/shallwe/image/time.png"></div>
							<div><% out.print(user.sportInvitationList[i].timeslot.startTime.toString().split("\\.")[0]); %></div>
						</div>
						<div class="location">
							<div class="location_img"><img src="/shallwe/image/location2.png"></div>
							<div><% out.print(user.sportInvitationList[i].location); %></div>
						</div>
						<div class="participant">
							<%
								for(int j = 0; j < user.sportInvitationList[i].joinPeople && j < 5;j++){
									%>
									<div class="participant_men"><img src="<% out.print(user.sportInvitationList[i].participantIcon[j]);%>" /></div>
									<%
								}
							%>
							<% if(user.sportInvitationList[i].totalPeople != -1){ %>
							<div class="participant_count">
								<% out.print( "" + user.sportInvitationList[i].joinPeople + "/" + user.sportInvitationList[i].totalPeople); %>
							</div>
							<%}else{ %><div class="participant_count">不限</div><%} %>
						</div>
					</div>
				</div>
			</div>
			<%}%>
		</div>
	<%}%>
</body>
</html>