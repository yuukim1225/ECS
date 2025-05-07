<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-商品購入確認</title>
	</head>
	<body>
	<h1>購入内容確認</h1>
	<form action="buyProductCofirm" method="post">
		<table>
			<tr>
				<th>商品名 : </th>
				<td><input type = "hidden" name = "saleItemId" value ="${saleItemInfo.getItemSaleId()}">${saleItemInfo.getItemSaleName()}</td>
			</tr>
			<tr>
				<th>値段 : </th>
				<td>${saleItemInfo.getItemSalePrice()}[P]</td>
			</tr>
			<tr>
				<th>出品者 : </th>
				<td><input type = "hidden" name = "saleUserId" value ="${saleUi.getUserId()}">${saleUi.getName()}</td>
			</tr>
			<tr>
				<th>送付元住所 : </th>
				<td>${saleUi.getSaleAddress()}</td>
			</tr>
			<tr>
				<th>購入者 : </th>
				<td><input type = "hidden" name = "buyUserId" value ="${buyUi.getUserId()}">${buyUi.getName()}</td>
			</tr>
			<tr>
				<c:if test="${buyUi.getHoldPoint() >= saleItemInfo.getItemSalePrice()}">
					<th>購入後の残高 : </th>
					<td>${buyUi.getHoldPoint() - saleItemInfo.getItemSalePrice()}[P]</td>
				</c:if>
				<c:if test="${buyUi.getHoldPoint() < saleItemInfo.getItemSalePrice()}">
					<p>残高不足のため購入できません！</p>
					<p><a href="/ECS/home">ホーム画面はこちらから</a></p>
				</c:if>
			</tr>
		</table>
		<c:if test="${buyUi.getHoldPoint() >= saleItemInfo.getItemSalePrice()}">
			<input type="submit" value="購入確定">
		</c:if>
	</form>
	</body>
</html>