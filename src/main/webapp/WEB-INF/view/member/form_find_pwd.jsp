<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='./css/member.css'>
<title>form_find_pwd</title>

</head>
<body>
<form name='frm_find' id='frm_find' method='post'>
	<h2>암호 찾기</h2>
	<label>아이디</label>
	<input type='text' name='id' size='20'/><br/>
	<label>이메일</label>
	<input type='text' name='email' size='30'/><br/>
	<label></label>
	<input type='button' id='btnFindPwd' value='암호 찾기'/>
	<br/>
	</form>
<script src='./js/member.js'></script>

</body>
</html>