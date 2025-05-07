<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-新規登録情報確認-</title>
		<link rel = "stylesheet" type = "text/css" href = "/miniApp2/css/stylesheet.css" />
	</head>
	<body>
		<h1>新規登録情報確認</h1>
		<c:if test="${registError != null}">
			<div style="color:red;">
				<p>${registError}</p>
			</div>
		</c:if>
		
		<p>下記の情報で登録しますか？</p>
		<form action = "/ECS/registerConfirm" method = "post">
			<table>
				<tr>
					<th>ユーザーID：</th>
					<td><input type = "hidden" name = "userId" value ="${ui.getUserId()}">${ui.getUserId()}</td>
				</tr>
				<tr>
					<th>お名前：</th>
					<td><input type = "hidden" name = "name" value ="${ui.getName()}">${ui.getName()}</td>
				</tr>
				<tr>
					<th>パスワード：</th>
					<td><input type = "hidden" name = "password" value ="${ui.getPassword()}">${ui.getPassword()}</td>
				</tr>
				<tr>
					<th>使用目的：</th>
					<td><input type = "hidden" name = "accountType" value ="${ui.getAccountType()}">
						<c:if test="${ui.getAccountType() == 1}">商品の購入</c:if>
						<c:if test="${ui.getAccountType() == 2}">商品の販売</c:if>
						<c:if test="${ui.getAccountType() == 3}">商品の購入、販売どちらとも</c:if>
					</td>
				</tr>
				<tr>
					<th>送付先住所</th>
					<td><input type = "hidden" name = "buyAddress" value ="${ui.getBuyAddress()}">${ui.getBuyAddress()}</td>
				</tr>
				<tr>
					<th>配送元住所</th>
					<td><input type = "hidden" name = "saleAddress" value ="${ui.getSaleAddress()}">${ui.getSaleAddress()}</td>
				</tr>
			</table>
			<input type = "submit" value = "登録">
		</form>
	</body>
</html>