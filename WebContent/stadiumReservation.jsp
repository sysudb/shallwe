<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>场馆预定</title>
</head>
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
		width:100%;
		height:150px;
		background:#86c5a4;
	}
	.title_container{
		float:left;
		width:100%;
		height:40px;
		margin-top:75px;
		margin-bottom:35px;
	}
	.turn_back{
		float: left;
    	margin: 2px 0 2px 70px;
	}
	.title{
		width:100%;
		font-size:36px;
		height:40px;
		line-height:40px;
		text-align:center;
		color:white;
	}
	.sharing_icon{
		float:right;
	}
	.sharing_icon img{
		margin-right:30px;
	}
	.week_days{
		width:100%;
		height:100px;
		background:white;
	}
	.date_item{
		float:left;
		width:17.3%;
		height:100px;
		margin: 0 5px;
		text-align:center;
	}
	.date_item p{
		float:left;
		width:100%;
		font-size:26px;
		height:40px;
		line-height:40px;
	}
	.date_item_1{
		margin-left:7.2%;
		color:#ed6f6f;
	}
	.chooseCourt{
		height:56px;
		width:100%;
		margin: 20px 0;
	}
	.court_num{
		float:left;
		width:17.3%;
		height:56px;
		margin: 0 5px;
		text-align:center;
	}
	.court_num p{
		float:left;
		width:100%;
		font-size:26px;
		height:56px;
		line-height:56px;
		color:#5c5c5c;
	}
	.sport_clock{
		float:left;
		width:6%;
		height:auto;
		margin-left:10px;
	}
	.clock_time{
		font-size:16px;
		height: 15px;
		line-height:15px;
		color:#5c5c5c;
		text-align:center;
		margin-bottom:67px;
	}
	.reservationTable{
		float:right;
		width: 92.8%;
		margin-top:5px;
	}
	table{
		width:100%;
		border: none;
		border-spacing: 4px;
	}
	td{
		height:75px;
		font-size:24px;
		line-height:75px;
		text-align:center;
		color: #a5a5a5;
		background: white;
		font-family: arial, "Microsoft Yahei";
	}
	.payment{
		position:fixed;
		width:100%;
		height:150px;
		bottom:0;
	}
	.totalPay{
		float:left;
		width:50%;
		height:150px;
		background:white;
	}
	.checkPay{
		float:left;
		width:50%;
		height:150px;
		background:#ed6f6f;
	}
	.totalPay p, .checkPay p{
		float:left;
		font-size:36px;
		height:150px;
		line-height:150px;
		text-align:center;
		color:#ed6f6f;
	}
	.totalPay p{
		padding-left:15%;
		width:36%;
	}
	.checkPay p{
		width: 100%;
		color:white;
	}
	#total{
		padding-left:0;
		text-align: left;
		font-size:50px;
	}
	.disable_mask{
		position:fixed;
	  	left:0px;
	  	top:0px;
      	background-color:#fff;
      	width:100%;
      	height:100%;
      	font-size: 40px;
      	color: #000;
      	text-align:center;
      	filter:alpha(opacity=60);
      	background: rgba(255,255,255,.6); 
      	z-Index:999;
	}
	.disable_mask span{
		float:left;
		width:100%;
		margin-top:50%;
	}
</style>

<div class="disable_mask"><span>此功能暂未开放，敬请期待！</span></div>

<body>
	<div class="header">
		<div class="title_container">
			<div class="title">
				<div class ="turn_back">
					<a href="chooseStadium.jsp">
						<img src="/shallwe/image/arrow_left.png">
					</a>
				</div>
				<div class="sharing_icon">
					<img src="/shallwe/image/heart.png">
					<img src="/shallwe/image/sharing.png">
				</div>
				<%
				String stadiumName = request.getParameter("ToPage"); 
				%>
				<p>
				<%=stadiumName %>
				</p>
			</div>
			
		</div>
	</div>	
	<div class="week_days">
		<%
			String[] week = {"周一","周二","周三","周四","周五","周六","周日"};
			for(int i = 0; i < 5; i++){
				Calendar now = Calendar.getInstance();
				Date date=new Date();
				now.setTime(date);
				now.add(Calendar.DAY_OF_MONTH, i);
				int month = now.get(Calendar.MONTH) + 1;
				int day = now.get(Calendar.DAY_OF_MONTH);
				
				int weekDay = now.get(Calendar.DAY_OF_WEEK);
				boolean isFirstSunday = (now.getFirstDayOfWeek() == Calendar.SUNDAY); 
				if(isFirstSunday){  
				    weekDay = weekDay - 1;  
				    if(weekDay == 0){  
				        weekDay = 7;  
				    }  
				} 
				String m = String.valueOf(month);
				String d = String.valueOf(day);
				if (month < 10){
					m = "0" + String.valueOf(month);
				}
				if(day < 10){
					d = "0" + String.valueOf(day);
				}
				if (i==0){
					%>
					<div class = "date_item date_item_1">
					<p style="margin-top:10px">今天</p>
					<%
				}else{
					%>
					<div class = "date_item">
					<p style="margin-top:10px"><%=week[weekDay-1] %></p>
					<%
				}
				%>
				<p style="margin-bottom:10px"><%=m %>月<%=d %>日</p>
				</div>
				<%
			}
		%>
	</div>
	
	<div class="chooseCourt">
		<%
			for(int i = 1; i <= 5; i++){
				if(i==1){
					%><div class="court_num" style="margin-left:7.2%"><%
				}
				else{
					%><div class="court_num"><%
				}
				%><p><%=i %>号场地</p></div>
				<% } %>
	</div>
	
	<div class = "sport_clock">
		<%
			for(int i = 7; i < 23; i++){
				String time = "";
				if (i < 10){
					time += "0";
				}
				time = time + i + ":00";
				%>			
				<div class="clock_time">
					<p><%= time %></p>
				</div><% 
			}%>	
	</div>
	
	<div class = "reservationTable">
		<table>
			<%
				Calendar now = Calendar.getInstance();
				Date date=new Date();
				//SimpleDateFormat bjSdf = new SimpleDateFormat("yyyy-MM-dd HH24:mm:ss");     // 北京  
				//bjSdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));  // 设置北京时区  
				now.setTime(date);
				int hour = now.get(Calendar.HOUR_OF_DAY);
				for(int i = 7; i< 23; i++){
					%>
					<tr>
					<%
						for(int j = 0; j < 5; j++){
							
							if(hour + 8 > i){
								%>
								<td style="background:#ccc" onclick="book(this,<%=i%>,<%=j%>)">¥ 60</td>
								<% 
							}else{
								%>
								<td onclick="book(this,<%=i%>,<%=j%>)">¥ 60</td>
								<% 
							}
						}
					%>
					</tr>
					<%
				}
			%>
		</table>
	</div>
	
	<div class = "payment">
		<div class="totalPay">
			<p>合计：&nbsp;¥ </p>
			<p id="total">0</p>
		</div>
		<div class="checkPay" onclick="pay()">
			<p>确认预定</p>
		</div>
	</div>
	
	<script>
		var total = 0;
		function book(item,i,j){
			console.log(i+" "+j);
			if(item.style.background == "rgb(204, 204, 204)"){
				alert("此场地已被预订，请重新选择！")
				return;
			}
			if(item.style.background == "rgb(237, 111, 111)"){
				item.style.background = "white";
				item.style.color = "#a5a5a5";
				total --;
			}
			else{
				item.style.background = "#ed6f6f";
				item.style.color = "white";
				total ++;
			}
			var cost = total * 60;
			document.getElementById("total").innerHTML = cost;
		}
		
		function pay(){
			if(total == 0){
				alert("您没有选择任何场地！")
				return;
			}
			var totalPay = total * 60;
			var address = "pay.jsp?totalPay=" + totalPay; 
			window.location = address;
		}
	</script>
</body>
</html>