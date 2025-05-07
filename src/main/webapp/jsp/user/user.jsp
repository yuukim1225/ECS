<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-マイページ</title>
	</head>
	<body>
		<h1>マイページ</h1>
		<h2>登録内容</h2>
			<table>
				<tr>
					<th>ユーザーID：</th>
					<td>${sessionScope.ui.getUserId()}</td>
				</tr>
				<tr>
					<th>お名前：</th>
					<td>${sessionScope.ui.getName()}</td>
				</tr>
				<tr>
					<th>使用目的：</th>
					<td>
						<c:if test="${ui.getAccountType() == 1}">商品の購入</c:if>
						<c:if test="${ui.getAccountType() == 2}">商品の販売</c:if>
						<c:if test="${ui.getAccountType() == 3}">商品の購入、販売どちらとも</c:if>
					</td>
				</tr>
				<tr>
					<th>残高 : </th>
					<td>${sessionScope.ui.getHoldPoint()}[P]</td>
				</tr>
				<tr>
					<th>取引回数 : </th>
					<td>${sessionScope.ui.getTradeNum()}回</td>
				</tr>
				<tr>
					<th>取引高評価 : </th>
					<td>${sessionScope.ui.getGoodNum()}</td>
				</tr>
				<tr>
					<th>取引低評価 : </th>
					<td>${sessionScope.ui.getBadNum()}</td>
				</tr>
				<tr>
					<th>送付先住所 : </th>
					<td>${sessionScope.ui.getBuyAddress()}</td>
				</tr>
				<tr>
					<th>配送元住所 : </th>
					<td>${sessionScope.ui.getSaleAddress()}</td>
				</tr>
			</table>
		<c:if test="${ui.getAccountType()==2 ||ui.getAccountType()==3}">
			<p><a href="/ECS/salingProduct">出品中の商品/新しく商品を出品する</p>
		</c:if>
		<p><a href="/ECS/dealingProducts">取引中の商品の一覧を見る</p>
		<p><a href="/ECS/addPayment">ポイントを入金する</p>
		<p><a href="/ECS/editAccount">アカウント情報の編集 / 更新する</p>
		<p><a href="/ECS/deleteAccount">退会手続きはこちらから</a></p>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
	</body>
</html>