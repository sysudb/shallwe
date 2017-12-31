<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布运动计划</title>
<style type="text/css">
	body{
		margin: auto;
		background: #efefef;
	}
	p{
		margin: auto;
		font-family: arial, "Microsoft Yahei";
		text-align: center;
		color: #303030;
	}
	.header{
		width: 100%;
		height: 145px;
		background:white;
		margin-bottom: 38px;
		box-shadow: 0px 4px 4px 0 rgba(0,0,0,0.05);
		-webkit-box-shadow: 0px 4px 4px 0 rgba(0,0,0,0.05);
	}
	.title_wrapper{
		float: left;
		width: 100%;
		padding-top: 75px;
		padding-bottom: 25px;	
	}
	.title{
		float: left;
		width: 100%;
    	height: 45px;
	}
	.release_sport{
		float: left;
		width:100%;
		font-size: 36px;
    	line-height: 45px;
	}
	.release_sport_button{
		position: absolute;
		right:44px;
		font-size: 30px;
    	line-height: 45px;
	}
	.release_sport p{
		float: left;
		padding-left: 0;
	}
	.choose_sport_type{
		width: 100%;
		height: 222px;
	}
	.add_sport{
		margin: 0 auto;
		margin-bottom: 15px;
		width: 128px;
		height: 128px;
		border-radius: 50%;
		background: white;
	}
	.sport_circle{
		margin: 0 auto;
		width: 100%;
		height: 100%;
		border-radius: 50%;
	}
	.sport_circle p{
		float: none;
		font-size: 100px;
		height: 128px;
		line-height: 128px;
		color: #08fe8d;
	}
	.choose_text{
		width: 100%;
    	font-size: 30px;
    	color: #9a9c9b;
	}
	.form1{
		width: 100%;
		height: 420px;
		background: white;
		box-shadow: 0px 4px 4px 0 rgba(0,0,0,0.05);
		-webkit-box-shadow: 0px 4px 4px 0 rgba(0,0,0,0.05);
	}
	.form2{
		width: 100%;
		height:314px;
		margin-top: 20px;
		background: white;
		box-shadow: 0px 4px 4px 0 rgba(0,0,0,0.05);
		-webkit-box-shadow: 0px 4px 4px 0 rgba(0,0,0,0.05);
	}
	.button{
		width: 100%;
		height:75px;
		margin-top: 30px;
		margin-bottom:70px;
		background: #86c5a4;
		box-shadow: 0px 4px 4px 0 rgba(134,197,164,0.05);
		-webkit-box-shadow: 0px 4px 4px 0 rgba(134,197,164,0.05);
	}
	form{
		width:100%;
		height:100px;
	}
	form p{
	    float: left;
	    font-size: 36px;
	    width: 80px;
	    height: 100px;
	    line-height: 100px;
	    margin-left: 40px;
	    color: #494949;
	}
	form:after{
		position: absolute;
		left: 40px;
		right: 0;
		content: "";
		border-bottom: solid #eeeded 1px;
	}
	form:before{
		position: absolute;
		left: 40px;
		right: 0;
		content: "";
		border-bottom: solid #dadada 1px;
	}
	.first_form:after, .first_form:before{
		display: none;'
	}
	input,.sport_time_placeholder{
		border: none;
    	height: 100px;
    	line-height: 100px;
    	margin-left: 100px;
    	font-size: 30px;
	}
	input:focus{
		outline: none;
		line-height: normal;
	}
	input:-ms-input-placeholder{
    	color: #ccc;opacity:1;
	}

	input::-webkit-input-placeholder{
    	color: #ccc;opacity:1;
	}
	.sport_time_placeholder{
		width: 210px;
		color: #ccc;
	}
	.choose_time{
		float: right;
    	margin-right: 20px;
    	font-size: 40px;
    	font-family: cursive;
    	color: #ccc;
	}
	.cost_item{
		width: 120px;
    	font-size: 30px;
    	color: #6e6f6f;
    	cursor: default;
	}
	.cost_item_first{
		margin-left:80px;
	}
	.cost_item input{
		width: 100px;
    	margin: 0 10px;
    	border: 1px solid #bfbfbf;
    	height: 50px;
	}
	.choose_stadium{
		width: 100%;
    	float: left;
    	margin: 0 auto;
    	font-size: 36px;
    	color: white;
    	line-height: 75px;
	}   
	.choose_stadium_arrow{
		position: absolute;
    	right: 60px;
    	line-height: 75px;
    	font-size: 40px;
    	font-family: cursive;
    	color: white;
	}
	/** =============隐藏层样式================= **/
	@keyframes hidden_bg{
		0%{
			-webkit-transform: scale(0);
			-moz-transform: scale(0);
			-ms-transform: scale(0);
			transform: scale(0);
		}
		25%{
			-webkit-transform: scale(0.25);
			-moz-transform: scale(0.25);
			-ms-transform: scale(0.25);
			transform: scale(0.25);
		}
		50%{
			-webkit-transform: scale(0.5);
			-moz-transform: scale(0.5);
			-ms-transform: scale(0.5);
			transform: scale(0.5);
		}
		75%{
			-webkit-transform: scale(0.75);
			-moz-transform: scale(0.75);
			-ms-transform: scale(0.75);
			transform: scale(0.75);
		}
		100%{
			-webkit-transform: scale(1);
			-moz-transform: scale(1);
			-ms-transform: scale(1);
			transform: scale(1);
		}
	}
	#hidden_bg{ 
	  	position:absolute;
	  	left:0px;
	  	top:0px;
      	background-color:#000;
      	width:100%;
      	height:100%;
      	filter:alpha(opacity=60);
      	background: rgba(0,0,0,.6); 
      	display:none; 
      	z-Index:2;
      	     	
      	animation:hidden_bg .2s;
		-moz-animation:hidden_bg .2s; /* Firefox */
		-webkit-animation:hidden_bg .2s; /* Safari and Chrome */
		-o-animation:hidden_bg .2s; /* Opera */
		-moz-animation-timing-function: ease-in-out;
		-webkit-animation-timing-function: ease-in-out;
		-o-animation-timing-function: ease-in-out;
     }
     .hidden_page{
     	margin: 0 auto;
     	margin-top: 176px; 
     	width:81.3%;
     	height:1016px;
     	z-Index:3;
     	background: white;
     	border-radius: 10px;
     	
     }
     .hidden_sport_circle{
     	position: absolute;
     	margin-left: 40%;
     	margin-top: 90px;
     	width: 176px;
     	height:176px;
     	z-Index:4;
     	border-radius: 50%;
     	background: #86c5a4;
     }
     .hidden_sport_circle img{
     	width: 84px;
    	height: 90px;
    	margin: 43px auto;
    	display: block;
     }
     .sport_container{
     	margin-left:35px;
     	height:100%;
     }
     .sport_container_row{
     	float:left;
     	margin-top:30px;
     }
     .sport_container_row1{
     	margin-top:100px;
     }
     .sport_item{
     	float: left;
     	margin-right:52px;
     }
     .item_name{
     	font-size:26px;
     	color: #525252;
     }
</style>
</head>
<body>
	<% request.setCharacterEncoding("UTF-8"); %>
	<jsp:useBean id="user" scope="session" class="pac.User" />
	<div class = "header">
		<div class = "title_wrapper">
			<div class = "title">
				<p class = "release_sport">发布运动计划</p>
				<p class = "release_sport_button">发布</p>
			</div>		
		</div>
	</div>
	<div class = "choose_sport_type">
		<div class = "add_sport" onclick = "show()">
			<div class = "sport_circle"><p>+</p><img style ="padding: 14px; display:none" src=""></div>
		</div>
		<p class = "choose_text">点击可更改运动类型</p>
	</div>
	<!-- =============隐藏层开始================= -->
	<div id = "hidden_bg">
		<div class = "hidden_sport_circle"><img src="/shallwe/image/sport_type/running_white.png"/></div>
		<div class = "hidden_page">
			<div class = "sport_container">

				<%
					String[][] img_name = {
											{"exercise","basketball","soccer","cycling","boxing"},
											{"trekking","canoe","skateboarding","horseback-riding","fitness"},
											{"tennis","swimming","dragon-boat","ping-pong","badminton"},
											{"volleyball","baseball","windsurfing","wrestling","yoga"},
											{"archers-arrow","bungee-jumping","water-polo","surfing","skiing"},
										};
					String[][] sport_name = {
												{"跑步","篮球","足球","骑行","拳击"},
												{"远足","划船","滑板","骑马","健身"},
												{"网球","游泳","龙舟","乒乓球","羽毛球"},
												{"排球","棒球","帆船","散打","瑜伽"},
												{"射箭","蹦极","水球","冲浪","滑雪"},
										};
					
					for(int i = 0; i < 5; i++){
						if(i == 0){
							%><div class = "sport_container_row sport_container_row1"><%
						}else{
							%><div class = "sport_container_row "><%
						}
						for(int j = 0; j < 5; j++){
							String img = "/shallwe/image/sport_type/" + img_name[i][j] + ".png";
							String name = sport_name[i][j];
							%><div class = "sport_item"  onclick = "hide(this)">
								<img src="<%= img%>"/>
								<div class = "item_name"><p><%= name%></p></div>
							</div><%
						}
						
						%></div><%
					}
				%>
				</div>
			</div>
		</div>
	</div>
	<!-- =============隐藏层结束================= -->
	<div class = "form1">
		<form class = "sport_slogan first_form">
			<p>口号</p>
			<input type="text" name="sport_slogan" placeholder = "请输入一个响亮的口号" onfocus="this.placeholder=''" onblur="this.placeholder='请输入一个响亮的口号'" type = "text">
		</form>
		<form class = "sport_time">
			<p>时间</p>
			<p class = "sport_time_placeholder">请选择运动时间</p>
			<p class = "choose_time">></p>
		</form>
		<form class = "sport_addr">
			<p>地点</p>
			<input type="text" name="address" placeholder = "请输入运动地点" onfocus="this.placeholder=''" onblur="this.placeholder='请输入运动地点'" type = "text">
		</form>
		<form class = "sport_cost">
			<p>费用</p>
			<p class = "cost_item cost_item_first" onclick = "changeTextColor(this)">免费</p>
			<p class = "cost_item" onclick = "changeTextColor(this)">我请客</p>
			<p class = "cost_item" onclick = "changeTextColor(this)">AA</p>
		</form>
	</div>
	<div class = "form2">
		<form class = "sporter_count first_form">
			<p>人数</p>
			<p class = "cost_item cost_item_first" onclick = "changeTextColor(this)">不限</p>
			<p class = "cost_item" style = "width: 220px" onclick = "changeTextColor(this)">或<input type="text" name="sporter_count">人</p>
		</form>
		<form class = "sporter_sex">
			<p>性别</p>
			<p class = "cost_item cost_item_first" onclick = "changeTextColor(this)">不限</p>
			<p class = "cost_item" onclick = "changeTextColor(this)">只限男</p>
			<p class = "cost_item" onclick = "changeTextColor(this)">只限女</p>
		</form>
		<form class = "sport_disp">
			<p>介绍</p>
			<input name="sport_slogan" placeholder = "请详细描述计划内容" onfocus="this.placeholder=''" onblur="this.placeholder='请详细描述计划内容'" type = "text">
		</form>
	</div>
	<div class = "button" onclick = "stadiumChooseButton(this)">
		<p class = "choose_stadium">场馆预定</p>
		<p class = "choose_stadium_arrow">></p>
	</div>
<script>	
	var count = 0;
	function changeTextColor(item){
		parent = item.parentNode;
		var lists = parent.getElementsByClassName('cost_item');
		for(var i = 0; i < lists.length; i++){
			lists[i].style.color="#6e6f6f";
		}
		item.style.color='#08fe8d';
	}
	function stadiumChooseButton(button){
		button.style.background = "#ccc";
		window.location = "http://localhost/shallwe/chooseStadium.jsp";	
	}
	function show(){
		var hidden_bg = document.getElementById('hidden_bg');
		hidden_bg.style.display = "block";
		//hidden_bg.style.height = document.body.scrollHeight + "px";
	}
	function hide(item){
		var hidden_bg = document.getElementById('hidden_bg');
		hidden_bg.style.display = "none";
		var text = document.getElementsByClassName('sport_circle')[0].getElementsByTagName("p")[0];
		var img = document.getElementsByClassName('sport_circle')[0].getElementsByTagName("img")[0];
		text.style.display = "none";
		img.style.display = "block";
		img.src = item.getElementsByTagName("img")[0].src;	
	}
</script>
</body>
</html>