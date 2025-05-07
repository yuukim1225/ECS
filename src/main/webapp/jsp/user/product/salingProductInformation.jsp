<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-出品中の商品一覧</title>
		<link rel="stylesheet" type="text/css" href="/ECS/css/salingProductInformation.css" />
	</head>
	<body>
		<h1>出品中の商品一覧</h1>
		<hr>
		<div class="wrapper">
		<c:forEach begin="1" end="${sil.size()}" step="1" var="i" >
			<div>
				<a href="/ECS/productInfo?saleItemId=${sil.get(i-1).getItemSaleId()}">
				<p class="txt-limit">商品名 : ${sil.get(i-1).getItemSaleName()}</p>
				<p>値段 : ${sil.get(i-1).getItemSalePrice()}[P]</p>
				<p class="txt-limit">出品者 : ${dp.referSaleUserData(sil.get(i-1).getUserId()).getName()}</p>
				</a>
			</div>
		</c:forEach>
		</div>
		<c:if test="${sil.size()==0}">
			<p>現在出品している商品はありません</p>
		</c:if>
		<p><a href="/ECS/addProduct">新しく商品を出品する</p>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>