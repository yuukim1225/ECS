<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-アカウント編集</title>
	</head>
	<body>
		<h1>アカウント情報の編集</h1>
		<c:if test="${inputError != null}">
			<div style="color:red;">
				<c:forEach var="msg" items="${inputError}">
					<li>${msg}</li>
				</c:forEach>
			</div>
		</c:if>
		<h2>以下項目にて希望の箇所を変更後、「変更」ボタンを押下してください</h2>
		<form action="editAccount" method="post">
			<table>
				<tr>
					<th>ユーザーID：</th>
					<td><input type = "hidden" name = "userId" value ="${sessionScope.ui.getUserId()}">${sessionScope.ui.getUserId()}</td>
				</tr>
				<tr>
					<th>お名前：</th>
					<td><input type = "text" name = "name" value ="${sessionScope.ui.getName()}"></td>
				</tr>
				<tr>
					<th>パスワード：</th>
					<td><input type = "text" name = "password" value ="${sessionScope.ui.getPassword()}"></td>
				</tr>
				<tr>
					<th>使用目的：</th>
					<td>
						<select name="accountType">
							<c:forEach begin="1" end="3" step="1" var="i">
								<c:if test="${sessionScope.ui.getAccountType() == i}">
									<c:if test="${i == 1}">
										<option value="${i}" selected>
											商品の購入
										</option>
									</c:if>
									<c:if test="${i == 2}">
										<option value="${i}" selected>
											商品の販売
										</option>
									</c:if>
									<c:if test="${i == 3}">
										<option value="${i}" selected>
											商品の購入、販売どちらとも
										</option>
									</c:if>
								</c:if>
								<c:if test="${sessionScope.ui.getAccountType() != i}">
									<c:if test="${i == 1}">
										<option value="${i}">
											商品の購入
										</option>
									</c:if>
									<c:if test="${i == 2}">
										<option value="${i}">
											商品の販売
										</option>
									</c:if>
									<c:if test="${i == 3}">
										<option value="${i}">
											商品の購入、販売どちらとも
										</option>
									</c:if>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>送付先住所</th>
					<td><input type = "text" name = "buyAddress" value ="${sessionScope.ui.getBuyAddress()}"></td>
				</tr>
				<tr>
					<th>配送元住所</th>
					<td><input type = "text" name = "saleAddress" value ="${sessionScope.ui.getSaleAddress()}"></td>
				</tr>
			</table>
			<input type = "submit" value = "変更する">
		</form>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>