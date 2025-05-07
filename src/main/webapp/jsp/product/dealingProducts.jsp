<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-取引一覧</title>
		<link rel="stylesheet" type="text/css" href="/ECS/css/dealingProducts.css" />
	</head>
	<body>
		<h1>取引中の商品一覧</h1>
		<hr>
		<div class="wrapper">
		<c:forEach begin="1" end="${dsil.size()}" step="1" var="i" >
			<div>
				<a href="/ECS/productInfo?saleItemId=${dsil.get(i-1).getItemSaleId()}">
				<p class="txt-limit">商品名 : ${dsil.get(i-1).getItemSaleName()}</p>
				<p>値段 : ${dsil.get(i-1).getItemSalePrice()}[P]</p>
				<p class="txt-limit">出品者 : ${dp.referSaleUserData(dsil.get(i-1).getUserId()).getName()}</p>
				<p>取引ステータス : 
					<c:if test="${dsil.get(i-1).getItemSaleState()==1}">
						<c:if test="${dsil.get(i-1).getBuyUserId()==sessionScope.ui.getUserId()}">
							購入確定 / 出品者　商品発送準備　待ち
						</c:if>
						<c:if test="${dsil.get(i-1).getBuyUserId()!=sessionScope.ui.getUserId()}">
							売却確定 / 出品者　商品発送準備　待ち
						</c:if>
					</c:if>
					<c:if test="${dsil.get(i-1).getItemSaleState()==2}">
						商品準備中 / 出品者　発送完了　待ち
					</c:if>
					<c:if test="${dsil.get(i-1).getItemSaleState()==3}">
						商品発送中 / 購入者　商品受け取り　待ち
					</c:if>
					<c:if test="${dsil.get(i-1).getItemSaleState()==4}">
						商品受け取り完了 / 購入者　取引評価　待ち
					</c:if>
					<c:if test="${dsil.get(i-1).getItemSaleState()==5}">
						取引完了
					</c:if>
				</p>
				</a>
			</div>
		</c:forEach>
		</div>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>