<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ErrorPage</title>
</head>
<body>
	<%
		System.out.print("Das ist ErrorPage");
	%>
	<c:choose>
		
		<c:when test="${requestScope.errorCode == 'falseId' }">
			<script type="text/javascript">
				alert('아이디가 틀렸습니다.');
				location.replace("pinguinhaus.html");
			</script>
		</c:when>
		
		<c:when test="${requestScope.errorCode == 'falsePw' }">
			<script type="text/javascript">
				alert('비밀번호가 틀렸습니다.');
				location.replace("pinguinhaus.html");
			</script>
		</c:when>
	</c:choose>
</body>
</html>