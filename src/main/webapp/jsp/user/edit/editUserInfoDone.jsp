<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-アカウント更新完了</title>
	</head>
	<body>
		<h1>会員情報の更新が完了しました</h1>
		<h2>更新後の内容は以下の通りです</h2>
			<table>
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
					<td><input type = "hidden" name = "accountType" value ="${sessionScope.ui.getAccountType()}">
						<c:if test="${ui.getAccountType() == 1}">商品の購入</c:if>
						<c:if test="${ui.getAccountType() == 2}">商品の販売</c:if>
						<c:if test="${ui.getAccountType() == 3}">商品の購入、販売どちらとも</c:if>
					</td>				</tr>
				<tr>
					<th>送付先住所</th>
					<td><input type = "hidden" name = "buyAddress" value ="${sessionScope.ui.getBuyAddress()}">${sessionScope.ui.getBuyAddress()}</td>
				</tr>
				<tr>
					<th>配送元住所</th>
					<td><input type = "hidden" name = "saleAddress" value ="${sessionScope.ui.getSaleAddress()}">${sessionScope.ui.getSaleAddress()}</td>
				</tr>
			</table>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>