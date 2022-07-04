<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' type='text/css' href='./css/member.css'>

<title>form_login.jsp</title>
</head>
<body>
<form name='frm_login' id='frm_login' method='post'>
	<h2>로그인</h2>
	<label>아이디</label>
	<input type='text' name='id' size='14'/><br/>
	<label>암호</label>
	<input type='password' name='pwd' size='14'/><br/>
	<input type='button' id='btnLogin2' value='로그인'/>
	<br/>
	<label></label>
	<a href='findId'>아이디</a>│
	<a href='findPwd'>암호 찾기</a>
</form>
<script src='./js/member.js'></script>
</body>
</html>


