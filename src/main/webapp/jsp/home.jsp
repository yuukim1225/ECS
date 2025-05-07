<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-ホーム</title>
		<link rel="stylesheet" type="text/css" href="/ECS/css/home.css" />
		<script type="text/javascript" src="/ECS/js/SearchBehavior.js" ></script>
	</head>
	<body>
		<h1>ECサイト ホーム</h1>
		<c:if test="${sessionScope.ui.name != null }">
			<p>${sessionScope.ui.name}様　ログイン中</p>
			<p><a href="/ECS/user">マイページ</p>
			<p><a href="/ECS/logout">ログアウト</a></p>
		</c:if>
		<c:if test="${sessionScope.ui.name == null }">
			<p>ゲストとして参加中</p>
			<p><a href="/ECS/login">ユーザーログイン</a></p>
			<p><a href="/ECS/registAccount">新規会員登録はこちらから</a></p>
		</c:if>
		<h2>商品一覧</h2>
		<form action="/ECS/home" method="post">
			<th>商品検索</th>
			<td><select id="selectBox" name ="searchType" onchange="showContent()">
				<option value="">商品の検索方法を選択してください</option>
				<option value="productName">商品名から検索</option>
				<option value="productCategory">カテゴリーから検索</option>
				<option value="userName">出品ユーザー名から検索</option>
			</select></td>
			<div id="productName" class="content" style="display:none;">
				<th>商品名から検索</th>
				<td><input type="text" name="searchProductName"></td>
				<button>検索</button>
			</div>
			
			<div id="productCategory" class="content" style="display:none;">
				<th>カテゴリーから検索</th>
				<td><input type="text" name="searchProductCategory"></td>
				<button>検索</button>
			</div>
			
			<div id="userName" class="content" style="display:none;">
				<th>出品ユーザー名から検索</th>
				<td><input type="text" name="searchUserName"></td>
				<button>検索</button>
			</div>
			<input type="hidden" name="searchFlag" value = 1>
		</form>
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
		<c:if test="${sil.size()==0}">
			<p>該当する商品は見つかりませんでした</p>
		</c:if>
		</div>
	</body>
</html>