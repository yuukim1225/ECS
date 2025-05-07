<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-退会確認</title>
	</head>
	<body>
		<c:if test="${deleteError != null}">
			<div style="color:red;">
				<p>${deleteError}</p>
			</div>
		</c:if>
	
		<h1>退会確認</h1>
		<h2>退会手続きを行い、以下の情報を消去します。よろしいでしょうか？</h2>
		<table>
		<form action="/ECS/deleteAccount" method="post">
			<tr>
				<th>ユーザーID：</th>
				<td><input type = "hidden" name = "userId" value ="${sessionScope.ui.getUserId()}">${sessionScope.ui.getUserId()}</td>
			</tr>
			<tr>
				<th>お名前：</th>
				<td><input type = "hidden" name = "name" value ="${sessionScope.ui.getName()}">${sessionScope.ui.getName()}</td>
			</tr>
			<tr>
				<th>パスワード：</th>
				<td><input type = "hidden" name = "password" value ="${sessionScope.ui.getPassword()}">${sessionScope.ui.getPassword()}</td>
			</tr>
			<tr>
				<th>使用目的：</th>
				<td><input type = "hidden" name = "accountType" value ="${sessionScope.ui.getAccountType()}">${sessionScope.ui.getAccountType()}</td>
			</tr>
			<tr>
				<th>送付先住所</th>
				<td><input type = "hidden" name = "buyAddress" value ="${sessionScope.ui.getBuyAddress()}">${sessionScope.ui.getBuyAddress()}</td>
			</tr>
			<tr>
				<th>配送元住所</th>
				<td><input type = "hidden" name = "saleAddress" value ="${sessionScope.ui.getSaleAddress()}">${sessionScope.ui.getSaleAddress()}</td>
			</tr>
		</table>
		<input type = "submit" value = "退会">
		</form>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>