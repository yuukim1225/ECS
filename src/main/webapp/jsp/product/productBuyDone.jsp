<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-商品購入完了</title>
	</head>
	<body>
	<h1>商品購入完了</h1>
		<table>
			<tr>
				<th>商品名 : </th>
				<td>${saleItemInfo.getItemSaleName()}</td>
			</tr>
			<tr>
				<th>値段 : </th>
				<td>${saleItemInfo.getItemSalePrice()}[P]</td>
			</tr>
			<tr>
				<th>出品者 : </th>
				<td>${saleUi.getName()}</td>
			</tr>
			<tr>
				<th>送付元住所 : </th>
				<td>${saleUi.getSaleAddress()}</td>
			</tr>
			<tr>
				<th>購入者 : </th>
				<td>${buyUi.getName()}</td>
			</tr>
			<tr>
				<th>購入後の残高 : </th>
				<td>${buyUi.getHoldPoint()}[P]</td>
			</tr>
		</table>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>