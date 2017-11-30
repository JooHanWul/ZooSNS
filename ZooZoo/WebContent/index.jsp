<%@page import="java.util.ArrayList"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.Collections"%>
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
	<%
		String[] timelineRead = null;
		ArrayList<String[]> timelines = null;
		timelines = new ArrayList<>();
		int i = 0;
		StringTokenizer tokenizer = null;
		while (true) {
			String getTag = dto.getId() + "." + i;
			System.out.println(getTag);
			String str = (String) request.getServletContext().getAttribute(getTag);
			System.out.println("타임라인 내용: " + str);
			if (str != null) {
				tokenizer = new StringTokenizer(str);
				timelineRead = new String[3];
				if (tokenizer.nextToken().equals("txt")) {
					timelineRead[0] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // julian date
					timelineRead[1] = tokenizer.nextToken() + " " + tokenizer.nextToken(); // yyyy-MM-dd hh:mm:ss
					timelineRead[2] = ""; // context
					while (tokenizer.hasMoreTokens()) {
						timelineRead[2] += tokenizer.nextToken() + " ";
					}
					timelines.add(timelineRead);
				}
				i++;
			} else {
				break;
			}
		}

		if (timelines != null) {
			// 리스트 뒤집어 주기
			Collections.reverse(timelines);
		}
	%>
	<div class="nullDiv"></div>
	<div id="mainDiv">
		<div id="write">
			<form action="timeLineUpload.do" method="post">
				<textarea id="timeLine" name="text"
					placeholder="<%=dto.getName()%>님 오늘은 어떤일이 있었나요?" id="how"></textarea>
				<input type="hidden" name="id" value="<%=dto%>"> <input
					type="submit" value="게시" id="subApp">
			</form>
		</div>

		<div id="nullDiv2"></div>
		<%
			System.out.println("타임라인 출력 준비");
			System.out.println(timelines.toString());
			for (String[] s : timelines) {
		%>
		<div class="showtimeline">
			<div class="tlB">
				<p class="pdata"><%=s[1]%></p>
				<p class="tl"><%=s[2]%></p>
			</div>
		</div>
		<%
			}
		%>
	</div>
</body>
<%@ include file="../footer.jsp"%>
</html>