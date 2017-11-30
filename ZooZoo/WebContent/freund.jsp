<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="./img/pinguinLogo.png" type="image/x-icon">
<title>PinguinHaus</title>
<link rel="stylesheet" type="text/css"
	href="./css/pinguinMain.css?var=1">
</head>
<body id="cover">
<div class="nullDiv"></div>
	<%
		ArrayList<String[]> list = (ArrayList<String[]>) request.getAttribute("freund");
		System.out.println(list);
	%>
	<table align="center">
		<tr>
			<th></th>
			<th>아이디</th>
			<th>친구</th>
			<th>추가</th>
		</tr>
		<%
			for (String[] f : list) {
				String uId = f[0];
				String uName = f[1];
				String uProfile = f[2];
				
				if(dto.getId().equals(uId)){
					continue;
				}
		%>
		<tr>
			<td><div style="width: 30px; height: 30px; border-radius: 15px; background-color: white; overflow: hidden;">
					<img alt="프사" src="./profileImg/<%=uProfile%>" width="30"
						height="30">
				</div></td>
			<td><%=uId %></td>
			<td><%=uName %></td>
			<td><button>추가</button></td>
		</tr>
		<%
			}
		%>

	</table>
</body>
<%@ include file="../footer.jsp"%>
</html>