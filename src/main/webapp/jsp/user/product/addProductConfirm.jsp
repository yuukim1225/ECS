<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-商品登録確認-</title>
		<link rel = "stylesheet" type = "text/css" href = "/miniApp2/css/stylesheet.css" />
	</head>
	<body>
		<h1>商品登録確認</h1>
		<c:if test="${registError != null}">
			<div style="color:red;">
				<p>${registError}</p>
			</div>
		</c:if>
		
		<p>下記の情報で商品を登録しますか？</p>
		<form action="/ECS/addProductConfirm" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<th>商品名</th>
					<td><input type="hidden" name="productName" value="${sii.itemSaleName}">${sii.itemSaleName}</td>
				</tr>
				<tr>
					<th>価格[P]</th>
					<td><input type="hidden" name="productPrice" value="${sii.itemSalePrice}">${sii.itemSalePrice}[P]</td>
				</tr>
				<tr>
					<th>出品者</th>
					<td><input type="hidden" name="userName" value="${sii.userId}">${sui.name}</td>
				</tr>
				<tr>
					<th>商品カテゴリ</th>
					<td><input type="hidden" name="productCategory" value="${sii.itemSaleCategory}">${sii.itemSaleCategory}</td>
				</tr>
				<tr>
					<th>商品内容</th>
					<td><input type="hidden" name="productText" value="${sii.itemSaleText}">${sii.itemSaleText}</td>
				</tr>
<!-- 
				<tr>
					<th>商品イメージ</th>
					<td><input type="file" name="productImg"></td>
				</tr>
 -->				
				<tr>
					<th>商品イメージ</th>
					<td><input type="hidden" name="productImg" value="${imageData}">
						<img src="data:image/png;base64,${imageData}" alt="画像">
					</td>
				</tr>
			</table>
			<input type="submit" value="確定">
		</form>
	</body>
</html>