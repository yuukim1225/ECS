<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ECサイト-入金額確認</title>
	</head>
	<body>
		<h1>ポイント入金額確認</h1>
		<form action="/ECS/addPaymentConfirm" method="post">
			<table>
				<tr>
					<th>入金額 : </th>
					<td><input type="hidden" name="depositAmount" value="${depositAmount}">${depositAmount} [P]</td>
				</tr>
			</table>
			<input type="submit" value="入金確定する">
		</form>
	</body>
</html>