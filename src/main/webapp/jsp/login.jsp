<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト -ログイン-</title>
		<link rel = "stylesheet" type="text/css" href = "/miniApp2/css/stylesheet.css" />
	</head>
	<body>
		<h1>ECサイト ユーザーログイン</h1>
		<c:if test="${loginError != null}">
			<div style="color:red;">
				<p>${loginError}</p>
			</div>
		</c:if>
		<form action="/ECS/login" method="post">
			<table>
				<tr>
					<th>ユーザーID</th>
					<td><input type="text" name="userId"></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="password"></td>
				</tr>
			</table>
			<input type="submit" value="ログイン">
		</form>
		<a href="/ECS/home">ホームへ戻る</a>
	</body>
</html>