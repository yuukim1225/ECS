<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-商品内容</title>
	</head>
	<body>
		<c:if test="${dealError != null}">
			<div style="color:red;">
				<p>${dealError}</p>
			</div>
		</c:if>
		<h1>${saleItemInfo.getItemSaleName()}</h1>
		<p>値段 : ${saleItemInfo.getItemSalePrice()}[P]</p>
		<p>出品者 : ${dp.referSaleUserData(saleItemInfo.getUserId()).getName()} </p>
		<c:if test="${saleItemInfo.getItemSaleCategory() != null}">
			<p>商品カテゴリ : ${saleItemInfo.getItemSaleCategory()}</p>
		</c:if>
		<p>出品日 : ${saleItemInfo.getItemSaleDate()}</p>
		<c:if test="${saleItemInfo.getItemSaleImg() != null}">
			<p>出品画像</p>
			<img src="${saleItemInfo.getItemSaleImg()}">
		</c:if>
		<p>商品内容</p>
		<blockquote>
			${saleItemInfo.getItemSaleText()}
		</blockquote>
		
		<c:if test="${sessionScope.ui.userId != null && saleItemInfo.getItemSaleState() == 0}" >
			<c:if test="${sessionScope.ui.userId != saleItemInfo.getUserId()}">
				<form action=buyProduct method="post">
					<input type="hidden" name="userId" value="${sessionScope.ui.userId}">
					<input type="hidden" name="itemSaleId" value="${saleItemInfo.getItemSaleId()}">
					<input type="submit" value="購入">
				</form>
			</c:if>
			<c:if test="${sessionScope.ui.userId == saleItemInfo.getUserId()}">
				<p>自身の出品商品は購入できません</p>
			</c:if>
		</c:if>
		
		<c:if test="${sessionScope.ui.userId != null && saleItemInfo.getItemSaleState() != 0}" >
			<c:if test="${saleItemInfo.getBuyUserId() == sessionScope.ui.userId || saleItemInfo.getUserId() == sessionScope.ui.userId}">
				<form action=dealProduct method="get">
					<input type="hidden" name="itemSaleId" value="${saleItemInfo.getItemSaleId()}">
					<input type="submit" value="商品取引画面に進む">
				</form>
			</c:if>
			<c:if test="${saleItemInfo.getBuyUserId() != sessionScope.ui.userId && saleItemInfo.getUserId() != sessionScope.ui.userId}">
				<p>この商品は成約済みです</p>
			</c:if>
		</c:if>
		
		<c:if test="${sessionScope.ui.userId == null }">
			<p>商品を購入する際はユーザーログイン、もしくは会員登録が必要になります。</p>
			<p><a href="/ECS/login">ユーザーログインはこちらから</a></p>
			<p><a href="/ECS/registAccount">新規会員登録はこちらから</a></p>
		</c:if>
		
		<p><a href="/ECS/home">ホーム画面はこちらから</a></p>
	</body>
</html>