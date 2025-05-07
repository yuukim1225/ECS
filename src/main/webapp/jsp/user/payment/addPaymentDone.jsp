<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-入金完了</title>
	</head>
	<body>
		<h1>ポイント入金完了</h1>
		<table>
			<tr>
				<th>入金額 : </th>
				<td>${depositAmount} [P]</td>
			</tr>
			<tr>
				<th>残高 : </th>
				<td>${ui.getHoldPoint()} [P]</td>
			</tr>
		</table>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>