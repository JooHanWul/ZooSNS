<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="./img/pinguinLogo.png" type="image/x-icon">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>PinguinHaus</title>
<link rel="stylesheet" type="text/css" href="./css/join.css">
<script type="text/javascript">
	var Jahr = 0;
	var Februar = 0;
	function selectJahr(yy) {
		Jahr = parseInt(yy);
		if ((Jahr%4==0 && Jahr%100!=0)||Jahr%400==0) {
			Februar = 29;
		} else {
			Februar = 28;
		}
	}
	
	function selectMond(mm) {
		var optionsAsString ="";
		optionsAsString += "<option selected='selected' disabled='disabled'>day</option>";
		for(var i = 1; i <= ((mm==2||mm==4||mm==6||mm==9||mm==11)?((mm==2)?Februar:30):31); i++) {
		    optionsAsString += "<option value='" + i + "'>" + i + "</option>";
		}
		$("select[name='day']").find('option').remove().end().append($(optionsAsString));
	}
	
	$(document).ready(function(){
		$('#rpsw').focusout(function() {
			var pass = $.trim($("#psw").val());
			var pass2 = $.trim($("#rpsw").val());
	        if(pass2 == pass){
	        	$(this).css('background-color', 'white');
	        } else {
	        	$(this).css('background-color', 'red');
	        }
	    });
	});
	
	$(document).ready(function(){
		$('#psw').focusout(function() {
			var pass = $.trim($("#psw").val());
			var pass2 = $.trim($("#rpsw").val());
			if(pass2 != ""){
				if(pass2 == pass){
		        	$("#rpsw").css('background-color', 'white');
		        } else {
		        	$("#rpsw").css('background-color', 'red');
		        }
			}
	    });
	});
	
	function back() {
		location.replace("pinguinhaus.html");
	}
</script>
</head>
<body>
<%
	Date d = new Date(); 
	int Mond = 0;
	SimpleDateFormat yy = new SimpleDateFormat("yyyy");
	int Jahr = Integer.parseInt(yy.format(d));
%>
	<form action="join.do" method="post">
		<div class="container">
			<label><b>Email</b></label>
			<input type="text"
				placeholder="Enter Email" name="id"
				pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
				title="이메일 형식을 맞추세요" required>
			<label><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="psw" id="psw"
				required>
			<label><b>Repeat Password</b></label>
			<input type="password" placeholder="Repeat Password" name="psw-repeat"
				id="rpsw" required>
			<label><b>Name</b></label>
			<input type="text" placeholder="name" name="name"
				required>
			<label><b>Birth</b></label><br>
			<select name="year" onchange="selectJahr(this.value)">
			<option selected="selected" disabled="disabled">year</option>
			<% for(int i = Jahr;i>=1901;i--){ %>
				<option value="<%=i%>"><%=i %></option>
			<% } %>
			</select>
			<select name="month" onchange="selectMond(this.value)">
			<option selected="selected" disabled="disabled">month</option>
			<% for(int i = 1;i<=12;i++){ %>
				<option value="<%=i%>"><%=i %></option>
			<% } %>
			</select>
			<select name="day">
			<option selected="selected" disabled="disabled">day</option>

			</select>
			<br>
			<input type="radio" name="Geschlechtsunterschied" value="Mann" checked="checked">Male
			<input type="radio" name="Geschlechtsunterschied" value="Frau">Female
			<div class="clearfix">
				<button type="submit" class="signupbtn">Sign Up</button>
				<button type="button" class="cancelbtn" onclick="back()">Cancel</button>
			</div>
		</div>
	</form>
</body>
</html>