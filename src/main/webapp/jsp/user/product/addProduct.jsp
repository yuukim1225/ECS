<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-商品登録-</title>
		<link rel="stylesheet" type="text/css" href="/ECS/css/stylesheet.css" />
	</head>
	<body>
	<h1>ECサイト 商品登録</h1>
		<c:if test="${inputError != null}">
			<div style="color:red;">
				<c:forEach var="msg" items="${inputError}">
					<li>${msg}</li>
				</c:forEach>
			</div>
		</c:if>
	<h2>登録する商品内容を入力してください</h2>
		<form action="/ECS/addProduct" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<th>商品名</th>
					<td><input type="text" name="productName" placeholder="30文字以内"></td>
				</tr>
				<tr>
					<th>価格[P]</th>
					<td><input type="number" name="productPrice" placeholder="数字を入力">[P]</td>
				</tr>
				<tr>
					<th>出品者</th>
					<td><input type="hidden" name="userId" value="${sessionScope.ui.userId}">${sessionScope.ui.name}</td>
				</tr>
				<tr>
					<th>商品カテゴリ</th>
					<td><input type="text" name="productCategory" placeholder="10文字以内"></td>
				</tr>
				<tr>
					<th>商品内容</th>
					<td>
						<textarea id="productText" name="productText" rows="5" cols="33" placeholder="商品内容を入力"></textarea>
					</td>
				</tr>
				<tr>
					<th>商品イメージ</th>
					<td><input type="file" name="productImg"></td>
				</tr>
			</table>
			<input type="submit" value="商品を登録する">
		</form>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>