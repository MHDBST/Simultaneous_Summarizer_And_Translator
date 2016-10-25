<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style.css" rel="stylesheet" type="text/css" />
<title>Register</title>
</head>
<body>

<form method="POST" action="RegisterUser">
<div id = "reglog">
<table id = "log">

	<tr>
		<td>نام کاربری</td>
		<td><input type="text" name="user"></td>
	</tr>
	<tr>
		<td>گذرواژه</td>
		<td><input type="password" name="pass"></td>
	</tr>

	<tr>
		<td colspan="2"><input type="submit" value="Register"></td>
	</tr>
	
</table>
</div>

</form>

</body>
</html>