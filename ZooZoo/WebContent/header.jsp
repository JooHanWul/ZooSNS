<%@page import="com.pinguin.dto.pinguinDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	pinguinDto dto = (pinguinDto) session.getAttribute("whospinguin");
	System.out.println(dto.getName());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="./img/pinguinLogo.png" type="image/x-icon">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="jquery/jquery-ui.js"></script>
<script type="text/javascript">
	$("#menuBox").click(function() {
		alert("ss");
		/* $("#logout").animate({
			top : 100
		}, 3000, "easeInOutBack");
		$("#update").animate({
			left : 100
		}, 3000, "easeInOutBack"); */
	})
</script>
<title>PinguinHaus</title>
<link rel="stylesheet" type="text/css"
	href="./css/pinguinMain.css?var=1">
</head>
<body>
	<div id="headerCon">
		<div id="header">
			<img alt="pinhuinhaus_logo" src="./img/flyingPinguin_3.png" id="logo"
				onClick="location.href='index.do'">
			<div id="profileImg">
				<img alt="profileImage" src="./profileImg/<%=dto.getProfileImg()%>"
					height="150">
			</div>
			<div id="info">
				<span id="infoS"><%=dto.getName()%></span> <br>
				<div id="menuBox">
					<input type="button" id="logout"
						onclick="location.href='logout.do'"> <input type="button"
						id="update" onclick="location.href='infoUpdate.do'"> <input
						type="button" id="profileUpdate"
						onclick="location.href='profile.do'"> <input type="button"
						id="freund" onclick="location.href='freund.do'"> <input
						type="button" id="menuBtn">
				</div>
			</div>
		</div>
	</div>
	<!-- https://www.w3schools.com/jquery/tryit.asp?filename=tryjquery_filters_table -->
</body>
</html>