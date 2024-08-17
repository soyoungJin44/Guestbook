<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password"></td>
				<td><button type="submit">삭제</button></td>
				
				<input type="hidden" name="no" value=${param.no}>
				<input type="hidden" name="action" value="delete">
			</tr>
		</table>
	</form>
	
	<br><br>
	<a href="/phonebook3/gbc?action=list">메인으로 돌아가기</a>
</body>
</html>