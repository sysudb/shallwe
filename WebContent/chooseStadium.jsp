<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pac.StadiumList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>体育馆选择</title>
<style type="text/css">
	body{
		margin: auto;
		width:100%;
		background: #efefef;
	}
	p{
		margin: auto;
		font-family: arial, "Microsoft Yahei";
	}
	.header{
		width：100%;
		height: 145px;
		background:#86c5a4;
	}
	.turn_back{
		float: left;
    	margin: 70px 30px 39px 50px;
	}
	.search_box{
		float:right;
		margin:60px 30px  25px 30px;
		height:60px;
		width: 80%;
    	border-radius: 30px;
    	background: rgba(255,255,255,0.6);
	}
	input{
		border: none;
		padding:0;
		width:92%;
    	height: 60px;
    	line-height: 60px;
    	text-align:center;
    	font-size: 30px;
    	background:none;
	}
	input:-ms-input-placeholder{
    	color: #fff;opacity:1;
	}

	input::-webkit-input-placeholder{
    	color: #fff;opacity:1;
	}
	input:focus{
		outline: none;
		line-height: normal;
	}
	.search_box img{
		position: absolute;
		top:75px;
		left:35%;
	}
	.advance_filter{
		width:100%;
		height:80px;
		background:white;
	}
	.advance_filter p{
		float:left;
		width:60%;
		height: 80px;
		line-height: 80px;
	}
	.filter{
		position：relative;
		float:left;
		height:80px;
		width: 25%;
    	text-align: center;
    	font-size: 26px;
	}
	.filter:after{
		display:black;
		position: absolute;
		top:20px;
		bottom:20px;
		right:0;
		content: "";
		border-bottom: solid #a0a0a0 2px;
	}
	.filter img{
		float:left;
		margin:34px 14%;
	}
	.date_filter img{
		width: 30px;
    	height: 30px;
    	margin: 26px 12%;
    	opacity: 0.6;
	}
	.stadium_lists{
		width:100%;
		height:auto;
		background:white;
	}
	.stadium{
		float:left;
		width:100%;
		height:190px;
	}
	.stadium:hover{
		background:#86c5a4;
	}
	.stadium:before{
		position: absolute;
		left: 0;
		right: 0;
		content: "";
		border-bottom: solid #dadada 1px;
	}
	.stadium img{
		float:left;
		padding:25px;
	}
	.stadium_info{
		float:left;
		height:190px;
		width:50%;
	}
	.stadium_name{
		height:65px;
		line-height:75px;
		font-size:32px;
	}
	.stadium_name p{
		float:left;
	}
	.stadium_evaliation{
		height:28px;
		margin-top:10px;
	}
	.stars{
		height:28px;
		float:left;
	}
	.stars img{
		float:left;
		padding:0;
		margin-right: 2px;
	}
	.stadium_evaliation p{
		font-size:20px;
		color:#727272;
		height:28px;
		line-height:28px;
		float:left;
		margin-left:20px;
	}
	.stadium_location{
		height:27px;
		margin:25px 0;
	}
	.stadium_location img{
		padding: 0;
		margin-right:10px;
	}
	.stadium_location p{
		font-size:24px;
		color:#504f4f;
		height:27px;
		line-height:27px;
	}
	.stadium_price{
		float: right;
	    margin-top: 100px;
	    margin-right: 60px;
	    font-size: 24px;
	    color: #ed6f6f;
	}
	.stadium_price p{
		float: left;
    	margin-right: 5px;
    	line-height: 42px;
	}
</style>
</head>
<body>
	<% request.setCharacterEncoding("UTF-8"); %>
	<jsp:useBean id="user" scope="session" class="pac.User" />
	
	<div class = "header">
		<div class ="turn_back">
					<a href="sportInvitationList.jsp">
						<img src="/shallwe/image/arrow_left.png">
					</a>
				</div>
		<div class = "search_box">
			<img src="/shallwe/image/search.png">
			<input name="stadium_search" placeholder = "输入场馆关键字搜索" onclick = "search_box_slide(this)" 
			       onblur = "search_box_reset(this)" onfocus="this.placeholder=''" type = "text">
		</div>
	</div>
	
	<div class="advance_filter">
		<div class="filter address_filter">
			<p>广州</p>
			<img src="/shallwe/image/arrow_down.png">
		</div>
		<div class="filter sport_filter">
			<p>网球</p>
			<img src="/shallwe/image/arrow_down.png">
		</div>
		<div class="filter ai_sort">
			<p>智能排序</p>
			<img src="/shallwe/image/arrow_down.png">
		</div>
		<div class="filter date_filter">
			<p>日期</p>
			<img src="/shallwe/image/filter-outline.png">
		</div>
	</div>
	
	<div class="stadium_lists">
		<%
			StadiumList list = new StadiumList();
			list.getAllStadiumList();
		for(int i = 0; i < list.listLen; i++){
			%>
			<div class="stadium" onclick="window.location = 'stadiumReservation.jsp?ToPage=<%=list.list[i].name%>'">
			<img src="/shallwe/image/stadium.png">
			<div class = "stadium_info">
				<div class="stadium_name">
					<p><%=list.list[i].name %></p>
					<%
						if(list.list[i].vip){
					%>
						<img style = "padding: 15px" src="/shallwe/image/stadium_vip.png">
					<%} %>
				</div>
				<div class="stadium_evaliation">
					<div class="stars">
						<%
							int s = list.list[i].grade;
						for(int j = 0; j < s; j++){
							%>
							<img src="/shallwe/image/star.png">
							<%
						}
						for(int t = 0; t < 5 - s; t++){
						%>
						<img src="/shallwe/image/star_gray.png">
						<%} %>
					</div>
					<p>(3人评论)</p>
				</div>
				<div class = "stadium_location">
					<img src="/shallwe/image/location.png">
					<p><%=list.list[i].address %>&nbsp; ~<%=list.list[i].distance %> km</p>
				</div>
			</div>
			<div class="stadium_price">
					<p>¥ <p style="font-size:36px"><%=list.list[i].price %></p><p>起</p>
			</div>
		</div>
		<%
		}
		
		%>
	
		<div class="clear" style="clear:both"></div>
	</div>
	<script>
		function search_box_slide(input){
			var search_icon = document.getElementsByClassName('search_box')[0].getElementsByTagName("img")[0];
			search_icon.style.left = "18%";	
			input.style.textAlign = "left";
			input.style.paddingLeft = "60px";
		}
		function search_box_reset(input){
			var search_icon = document.getElementsByClassName('search_box')[0].getElementsByTagName("img")[0];
			search_icon.style.left = "35%";	
			input.placeholder='输入场馆关键字搜索';
			input.style.textAlign = "center";
			input.style.paddingLeft = "0";
		}
	</script>
</body>
</html>