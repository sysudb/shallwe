<%@page import="org.apache.catalina.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="pac.Stadium" %>
<%@ page import="pac.TimeSlot" %>
<%@ page import="pac.SportInvitation2" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" scope="session" class="pac.User" />
<%
	String sport_type = request.getParameter("hidden_sport_type");
	String slogan = request.getParameter("hidden_slogan");
	String time = request.getParameter("hidden_time");
	String location = request.getParameter("hidden_location");
	int cost = Integer.valueOf(request.getParameter("hidden_sport_cost"));
	int sporter_count = Integer.valueOf(request.getParameter("hidden_sporter_count"));
	int sporter_sex = Integer.valueOf(request.getParameter("hidden_sporter_sex"));
	String detail = request.getParameter("hidden_sport_detail");
	String openid = user.openid;
	
	List<String> ss = new ArrayList<String>();
	for(String sss:time.replaceAll("[^0-9]", ",").split(",")){
        if (sss.length()>0)
            ss.add(sss);
    }
	String date = ss.get(0) + "-" + ss.get(1) + "-" + ss.get(2);
	String start_clock = ss.get(3) + ":" + ss.get(4) + ":00";
	String end_clock = ss.get(5) + ":" + ss.get(6) + ":00";
	
	Timestamp start_time = new Timestamp(System.currentTimeMillis());  
	Timestamp end_time = new Timestamp(System.currentTimeMillis());
    String startStr = date + " " + start_clock; 
    String endStr = date + " " + end_clock;  
    try {   
    	start_time = Timestamp.valueOf(startStr);   
    	end_time = Timestamp.valueOf(endStr);
        System.out.println(start_time); 
        System.out.println(end_time); 
    } catch (Exception e) {   
        e.printStackTrace();   
    } 
	
	TimeSlot timeslot = new TimeSlot(start_time, end_time);
	String stat = SportInvitation2.makeInvitation2(1060, openid, sport_type, slogan, timeslot, location, cost, sporter_count, sporter_sex, detail);	
	
	if(stat == "success"){
%>
<meta http-equiv="refresh" content="3;url=sportInvitationList.jsp"> 
<title>活动创建成功</title>
</head>
<body style="margin:auto; background:#efefef">
	<h1 style="text-align: center;margin-top: 100px">活动创建成功！正在跳转……</h1>
</body>
<% }
else{ %>
	<meta http-equiv="refresh" content="3;url=promotSport.jsp"> 
	<title>活动创建失败</title>
	</head>
	<body style="margin:auto; background:#efefef">
		<h1 style="text-align: center; margin-top: 100px">活动创建失败，正在返回活动创建页面……</h1>
	</body>
	<% 
}%>
</html>