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
	<div class="nullDiv"></div>
	<div id="oriPro">
	<h1 align="center">프로필 이미지</h1>
		<div id="pBo">
			<img alt="profile" src="./profileImg/<%=dto.getProfileImg()%>"
				height="400" id="image">
		</div>
		<form action="profileUpdate.do" method="post"
			enctype="multipart/form-data">
			<input type="file" name="file" id="fileid"><br> <input
				type="submit" value="변경" class="ppBtn">
		</form>
	</div>
<script>
	var upload = document.getElementById('fileid');
	var holder = document.getElementById('pBo');

	upload.onchange = function(e) {
		e.preventDefault();
		var file = upload.files[0];
		var reader = new FileReader();
		reader.onload = function(event) {
			var img = new Image();
			img.src = event.target.result;
			img.height = 400;
			holder.innerHTML = '';
			holder.appendChild(img);
		};
		reader.readAsDataURL(file);
		return false;
	};
</script>
</body>
<%@ include file="../footer.jsp"%>
</html>