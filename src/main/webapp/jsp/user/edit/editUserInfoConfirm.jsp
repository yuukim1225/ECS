<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-アカウント確認</title>
	</head>
	<body>
		<h1>アカウント情報の編集</h1>
		<c:if test="${EditError != null}">
			<div style="color:red;">
				<p>${EditError}</p>
			</div>
		</c:if>
		<p>以下項目の変更後の内容を確認の上、「確定」ボタンを押下してください</p>
			<form action = "/ECS/editAccountConfirm" method = "post">
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
			<input type = "submit" value = "確定">
		</form>
	</body>
</html>