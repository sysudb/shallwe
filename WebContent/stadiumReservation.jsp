<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		height:100px;
		bottom:0;
	}
	.totalPay{
		float:left;
		width:50%;
		height:100px;
		background:white;
	}
	.checkPay{
		float:left;
		width:50%;
		height:100px;
		background:#ed6f6f;
	}
	.totalPay p, .checkPay p{
		float:left;
		font-size:36px;
		height:100px;
		line-height:100px;
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
</style>
<body>
	<div class="header">
		<div class="title_container">
			<div class="title">
				<div class ="turn_back">
					<a href="http://localhost/shallwe/chooseStadium.jsp">
						<img src="/shallwe/image/arrow_left.png">
					</a>
				</div>
				<div class="sharing_icon">
					<img src="/shallwe/image/heart.png">
					<img src="/shallwe/image/sharing.png">
				</div>
				<p>北京科技大学体育馆</p>
			</div>
			
		</div>
	</div>

	
	<div class="week_days">
		<div class = "date_item date_item_1">
			<p style="margin-top:10px">今天</p>
			<p style="margin-bottom:10px">01月03日</p>
		</div>
		<div class = "date_item">
			<p style="margin-top:10px">周四</p>
			<p style="margin-bottom:10px">01月04日</p>
		</div>
		<div class = "date_item">
			<p style="margin-top:10px">周五</p>
			<p style="margin-bottom:10px">01月05日</p>
		</div>
		<div class = "date_item">
			<p style="margin-top:10px">周六</p>
			<p style="margin-bottom:10px">01月06日</p>
		</div>
		<div class = "date_item">
			<p style="margin-top:10px">周日</p>
			<p style="margin-bottom:10px">01月07日</p>
		</div>
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
				for(int i = 7; i< 23; i++){
					%>
					<tr>
					<%
						for(int j = 0; j < 5; j++){
							if(j==0){
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
		<div class="checkPay">
			<p>确认支付</p>
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
	</script>
</body>
</html>