<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录界面</title>
<style>
div {
	/*width: 980px;*/
	height: 300px;
	text-align: center;
	/*background-color: red;*/
}

.blank {
	height: 50px;
}

input[type=text] {
	width: 200px;
	height: 20px;
}

input[type=password] {
	width: 200px;
	height: 20px;
}

input[type=submit] {
	width: 100px;
	height: 35px;
}

div {
	width: 300px;
	height: 200px;
	FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,
		startColorStr=#b8c4cb, endColorStr=red); /*IE 6 7 8*/
	background: -ms-linear-gradient(top, #fff, #0000ff); /* IE 10 */
	background: -moz-linear-gradient(top, #b8c4cb, #f6f6f8); /*火狐*/
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#b8c4cb),
		to(#f6f6f8)); /*谷歌*/
	background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#fff),
		to(#0000ff)); /* Safari 4-5, Chrome 1-9*/
	background: -webkit-linear-gradient(top, #fff, #0000ff);
	/*Safari5.1 Chrome 10+*/
	background: -o-linear-gradient(top, #fff, #0000ff); /*Opera 11.10+*/
}

#tb {
	text-align: center;
}
</style>
<style type="text/css">
body {
	background-image: url(../images/timg.jpg);
	background-size: cover;
	text-align: center;
}
</style>
</head>
<body>
	<center>
		<div>
			<h2 style="color: red">点餐系统登录</h2>
			<form id="indexform" name="indexForm" action="logincheck.jsp"
				method="post">
				<table border="0" id="tb">
					<tr>
						<td>账号：</td>
						<td><input type="text" name="username"></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input type="password" name="password"></td>
					</tr>
				</table>
				<br> <input type="submit" value="登录" style="color: #000000">
			</form>
			<!--        <form action="zhuce.jsp">
                <input type="submit" value="注册" style="color:#BC8F8F">
            </form> -->
		</div>
	</center>
</body>
</html>