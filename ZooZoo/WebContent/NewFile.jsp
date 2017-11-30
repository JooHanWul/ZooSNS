<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<article>
	<p>
		<input type="file" id="fileid">
	</p>
	<p>Select an image from your machine to read the contents of the
		file without using a server</p>
	<div id="holder"
		style="width: 400px; height: 400px; border-radius: 200px; overflow: hidden;"
		align="center"></div>
	</article>
	<script>
		// var upload = document.getElementsByTagName('input')[0];
		var holder = document.getElementById('holder');
		var upload = document.getElementById('fileid');

		upload.onchange = function(e) {
			e.preventDefault();
			var file = upload.files[0], reader = new FileReader();
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
</html>