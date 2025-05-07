<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品取引画面</title>
	</head>
	<body>
		<h1>取引状況</h1>
		<c:if test="${msg != null}">
			<p>${msg}</p>
		</c:if>
		<c:if test="${sessionScope.ui.getUserId() == saleItemInfo.getUserId()}">
			<c:if test="${saleItemInfo.getItemSaleState() == 1 || saleItemInfo.getItemSaleState() == 2 }">
				<form action="dealProduct" method="post">
				<input type="hidden" name="saleItemId" value="${saleItemInfo.getItemSaleId()}">
				<table>
					<tr>
						<th>商品の発送状況 : </th>
						<td>
							<input type="radio" id=0 name="shippingStatus" value=0 />
							<label for=0>発送準備中</label>
							<input type="radio" id=1 name="shippingStatus" value=1 />
							<label for=1>発送完了</label>
						</td>
					</tr>
				</table>
				<input type = "submit" value = "取引の状況を更新する">
				</form>
			</c:if>
			<c:if test="${saleItemInfo.getItemSaleState() != 1 && saleItemInfo.getItemSaleState() != 2}">
				<table>
					<th>取引状態 : </th>
					<c:if test="${saleItemInfo.getItemSaleState() == 3}">
						<td>商品発送完了 / 購入者の商品受け取り待ちです</td>
					</c:if>
					<c:if test="${saleItemInfo.getItemSaleState() == 4}">
						<td>商品受け取り完了 / 購入者の取引評価待ちです</td>
					</c:if>
					<c:if test="${saleItemInfo.getItemSaleState() == 5}">
						<td>取引完了</td>
					</c:if>
				</table>
			</c:if>
		</c:if>
		<c:if test="${sessionScope.ui.getUserId() == saleItemInfo.getBuyUserId()}">
			<c:if test="${saleItemInfo.getItemSaleState() == 3 }">
				<form action="dealProduct" method="post">
				<input type="hidden" name="saleItemId" value="${saleItemInfo.getItemSaleId()}">
				<table>
					<tr>
						<th>商品の受け取り状況 : </th>
						<td>
							<input type="radio" id=0 name="receivingStatus" value=0 />
							<label for=0>未受け取り</label>
							<input type="radio" id=1 name="receivingStatus" value=1 />
							<label for=1>受け取り済み</label>
						</td>
					</tr>
				</table>
				<input type = "submit" value = "取引の状況を更新する">
				</form>
			</c:if>
			<c:if test="${saleItemInfo.getItemSaleState() == 4 }">
				<form action="dealProduct" method="post">
				<input type="hidden" name="saleItemId" value="${saleItemInfo.getItemSaleId()}">
				<table>
					<tr>
						<th>今回の出品者の取引評価 : </th>
						<td>
							<input type="radio" id=1 name="tradeEvaluation" value=1 />
							<label for=1>良い</label>
							<input type="radio" id=0 name="tradeEvaluation" value=0 />
							<label for=0>悪い</label>
						</td>
					</tr>
				</table>
				<input type = "submit" value = "取引の状況を更新する">
				</form>
			</c:if>
			<c:if test="${saleItemInfo.getItemSaleState() != 3 && saleItemInfo.getItemSaleState() != 4}">
				<table>
					<th>取引状態 : </th>
					<c:if test="${saleItemInfo.getItemSaleState() == 1}">
						<td>商品成約の確認待ち</td>
					</c:if>
					<c:if test="${saleItemInfo.getItemSaleState() == 2}">
						<td>商品発送の準備中です</td>
					</c:if>
					<c:if test="${saleItemInfo.getItemSaleState() == 5}">
						<td>取引完了</td>
					</c:if>
				</table>
			</c:if>
		</c:if>
		<p><a href="/ECS/productInfo?saleItemId=${saleItemInfo.getItemSaleId()}">商品内容画面に戻る</p>
		<p><a href="/ECS/home">ホーム画面はこちらから</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>