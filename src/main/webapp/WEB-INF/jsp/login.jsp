<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="controller" method="get">
		<input type="hidden" name="command" value="do_logination" /> Enter login:<br />
		<input type="text" name="login" value="" /><br /> Enter password:<br />
		<input type="password" name="password" value="" /><br /> <input
			type="submit" value="LogIn" /><br />
	</form>
</body>
</html>