<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel='stylesheet' href='./css/board.css'>
<script defer src='./js/board.js'></script>

</head>
<body>
<div id='board'>
	<h1>상세보기</h1>
	<form name='frm_board' class='frm_board' method='post'>
		<label>작성일</label>
		<input type='date' name='nal' value="${vo.nal }"/><br/>
		
		<label>작성자</label>
		<input type='text' name='id' value='${vo.id }'/><br/>
		
		<label>제목</label>
		<input type='text' name='subject'  value='${vo.subject }'/><br/>
		
		<div class='doc'>${vo.doc }</div>
		
		<div class='attList'>
			<c:forEach var="attVo" items="${vo.attList }">
				<div class='att'>${attVo.oriFile }</div>
			</c:forEach>
		</div>
		
		<div class='btns'>
			<button type='button' onclick='board.modify(this.form)'>수정</button>
			<button type='button' onclick='board.deleteR(this.form)'>삭제</button>
			<button type='button' onclick='board.repl(this.form)'>답글</button>
			<button type='button' onclick='board.select(this.form)'>취소</button>
		</div>
		<input type='text' name='findStr' value='${page.findStr }'/>
		<input type='text' name='nowPage' value='${page.nowPage }'/>
		<input type='text' name='sno'     value='${vo.sno }' />
		<input type='text' name='grp'     value='${vo.grp }' />
		<input type='text' name='seq'     value='${vo.seq }' />
		<input type='text' name='deep'    value='${vo.deep }' />
		
	</form>
</div>

</body>
</html>