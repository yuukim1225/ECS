<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-新規会員登録-</title>
		<link rel="stylesheet" type="text/css" href="/ECS/css/stylesheet.css" />
	</head>
	<body>
	<h1>ECサイト 新規会員登録</h1>
		<c:if test="${inputError != null}">
			<div style="color:red;">
				<c:forEach var="msg" items="${inputError}">
					<li>${msg}</li>
				</c:forEach>
			</div>
		</c:if>
	<h2>新規会員入力内容</h2>
		<form action="/ECS/registAccount" method="post">
			<table>
				<tr>
					<th>ユーザーID</th>
					<td><input type="text" name="userId" placeholder="4～8文字"></td>
				</tr>
				<tr>
					<th>名前</th>
					<td><input type="text" name="name" placeholder="2文字以上"></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="text" name="password" placeholder="4～8文字"></td>
				</tr>
				<tr>
					<th>使用目的</th>
					<td>
						<select name="accountType">
						<option value="1">商品の購入</option>
						<option value="2">商品の販売</option>
						<option value="3">商品の購入、販売どちらとも</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>送付先住所</th>
					<td><input type="text" name="buyAddress"></td>
				</tr>
				<tr>
					<th>配送元住所</th>
					<td><input type="text" name="saleAddress"></td>
				</tr>
			</table>
			<input type="submit" value="登録する">
		</form>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>