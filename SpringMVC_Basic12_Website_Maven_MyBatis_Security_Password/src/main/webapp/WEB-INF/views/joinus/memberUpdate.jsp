<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>index</title>
<link href="login.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<!-- 공통헤더 include -->
	<jsp:include page="../inc/header.jsp" />
	<!-- visual include -->
	<jsp:include page="./inc/visual.jsp" />

	<div id="main">
		<div class="top-wrapper clear">
			<div id="content">
				<form method="post">
					<table border="1">
						<tr>
						<th>아이디</th>
						<td><input type="text" value="${member.userid}" name="userid" readonly></td>
						</tr>
						<tr>
						<th>비밀번호</th>
						<td><input type="password" value="" name="pwd"></td>
						</tr>
						<tr>
						<th>이름</th>
						<td><input type="text" value="${member.name}" name="name"></td>
						</tr>
						<tr>
						<th>성별</th>
						<td><input type="text" value="${member.gender}" name="gender" readonly></td>
						</tr>
						<tr>
						<th>전화번호</th>
						<td><input type="text" value="${member.cphone}" name="cphone"></td>
						</tr>
						<tr>
						<th>email</th>
						<td><input type="text" value="${member.email}" name="email"></td>
						</tr>
						<tr>
							<td colspan="5"><input type="submit" value="수정"></td>
						</tr>
					</table>

				</form>
			</div>
			<!-- navi (aside.jsp) -->
			<jsp:include page="./inc/aside.jsp" />

		</div>
	</div>

	<!-- 공통 footer include -->
	<jsp:include page="../inc/footer.jsp" />
</body>
</html>

