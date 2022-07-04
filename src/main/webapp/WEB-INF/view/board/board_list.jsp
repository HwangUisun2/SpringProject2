<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board_list</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel='stylesheet' href='./css/board.css'>
<script src='./js/board.js'></script>
</head>
<body>
<div id='board_list'>
	<h1>게시판</h1>
	<form name='frm_board_list' class='frm_board_list' method='post'>
		<a href='/'>HOME</a>	
		<button type='button' onclick='board.input(this.form)'>입력</button>
		<input type='search' name='findStr' value='${page.findStr }' />
		<button type='button' onclick='board.find(this.form)'>조회</button>
		<input type='text' name='nowPage' value='${page.nowPage }'/>
		<input type='text' name='sno'/>
	</form>
	<div class='title'>
		<span class='sno'>SNO</span>
		<span class='subject'>제목</span>
		<span class='nal'>작성일</span>
		<span class='id'>작성자</span>
		<span class='hit'>조회수</span>
	</div>
	
	<div class='items'>
		<c:forEach var='vo' items="${list}">
			<div class='item' onclick='board.view(${vo.sno})'>
				<span class='sno'>${vo.sno }</span>
				<span class='subject'>${vo.subject }</span>
				<span class='nal'>${vo.nal }</span>
				<span class='id'>${vo.id }</span>
				<span class='hit'>${vo.hit }</span>
			</div>
		</c:forEach>
	</div>
	
	<div class='btns'>
		<c:if test="${page.startPage>1 }">
			<button type='button' onclick='board.movePage(1)'>맨첨</button>
			<button type='button' onclick='board.movePage(${page.startPage-1})'>이전</button>
		</c:if>	
	
		<c:forEach var='i' begin='${page.startPage }' end='${page.endPage }'>
			<button type='button'  onclick='board.movePage(${i})'>${i }</button>
		</c:forEach>
	
		<c:if test="${page.endPage<page.totPage }">
			<button type='button' onclick='board.movePage(${page.endPage+1})'>다음</button>
			<button type='button' onclick='board.movePage(${page.totPage})'>맨끝</button>
		</c:if>
	</div>
	
</div>
</body>
</html>

















