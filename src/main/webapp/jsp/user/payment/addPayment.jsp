<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-入金額指定</title>
	</head>
	<body>
	<h1>ポイント入金額指定</h1>
	<c:if test="${inputError != null}">
		<div style="color:red;">
				<li>${inputError}</li>
		</div>
	</c:if>
	
		<form action="/ECS/addPayment" method="post">
			<table>
				<tr>
					<th>入金額 入力 : </th>
					<td>
						<select name="depositAmount">
						<option value=500>500</option>
						<option value=1000>1000</option>
						<option value=2000>2000</option>
						<option value=5000>5000</option>
						<option value=10000>10000</option>
						</select>[P]
					</td>
				</tr>
			</table>
			<input type="submit" value="入金する">
		</form>
		<p><a href="/ECS/home">ホームへ戻る</a></p>
		<p><a href="/ECS/user">マイページ</p>
	</body>
</html>