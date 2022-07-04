<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<link rel='stylesheet' href='./css/index.css'>
<script src='./js/index.js'></script>
</head>
<body>
<div id='index'>
	<h1>hi~ Spring boot !</h1>

	<c:choose>
		<c:when test="${empty sessionScope.id }">
			<a href='login'>로그인</a>
			 [ <a href='findId'>아이디찾기</a> | 
			   <a href='findPwd'>암호찾기</a>
			]
		</c:when>
		<c:otherwise>
		   	[ ${sessionScope.id } ] 님 방가. <a href='logout'>로그아웃</a>
		</c:otherwise>		
	</c:choose>
	
	<hr/>
	
	<a href='board_select'>게시판</a>
	<a href='member_select'>회원관리</a>
	
	
</div>
</body>
</html>