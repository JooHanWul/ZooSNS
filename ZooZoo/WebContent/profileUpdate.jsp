<%@page import="com.pinguin.dto.pinguinDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="./img/pinguinLogo.png" type="image/x-icon">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>PinguinHaus</title>
<link rel="stylesheet" type="text/css" href="./css/join.css?var=1">
<script type="text/javascript">
	$(document).ready(function() {
		$('#rpsw').focusout(function() {
			var pass = $.trim($("#psw").val());
			var pass2 = $.trim($("#rpsw").val());
			if (pass2 == pass) {
				$(this).css('background-color', 'white');
			} else {
				$(this).css('background-color', 'red');
			}
		});
	});

	$(document).ready(function() {
		$('#psw').focusout(function() {
			var pass = $.trim($("#psw").val());
			var pass2 = $.trim($("#rpsw").val());
			if (pass2 != "") {
				if (pass2 == pass) {
					$("#rpsw").css('background-color', 'white');
				} else {
					$("#rpsw").css('background-color', 'red');
				}
			}
		});
	});
</script>
</head>
<body>
	<%
		pinguinDto dto = (pinguinDto) session.getAttribute("whospinguin");
		System.out.println(dto.getName());
	%>
	<form action="update.do" method="post">
		<div class="container">
			<div id="profileImg">
				<img alt="profileImage" src="./profileImg/<%=dto.getProfileImg()%>"
					height="350">
			</div>
			
			<label><b>Email</b></label> <input type="text"
				value="<%=dto.getId()%>" name="id" disabled="disabled"> <label><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="Pw"
				id="oldpsw" required> <label><b>New Password</b></label> <input
				type="password" placeholder="Enter Password" name="newPw" id="psw"
				required> <label><b>Repeat Password</b></label> <input
				type="password" placeholder="Repeat Password" name="newPw-repeat"
				id="rpsw" required> <label><b>Name</b></label> <input
				type="text" placeholder="name" name="name" required>
			<div class="clearfix">
				<button type="submit" class="signupbtn">Sign Up</button>
				<button type="button" class="cancelbtn" onclick="back()">Cancel</button>
			</div>
		</div>
	</form>
</body>
</html>