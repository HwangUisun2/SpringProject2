<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id='board'>
	<h1>select</h1>
	<a href='/'>HOME</a>

	<form name='frm' method='post' action='board_select'>
		<input type='text' name='findStr' value='${page.findStr }'/>
		<button>검색</button>
	</form>
	
	<div>
	totSize : ${totSize }
	</div>
</div>
</body>
</html>