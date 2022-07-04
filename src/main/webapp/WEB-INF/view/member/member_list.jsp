<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member_list</title>
<link rel='stylesheet' href='./css/member.css'>
<script src='./js/member.js'></script>
</head>
<body>

<div id='member_list'>
	<h1>학생정보 조회</h1>
	<form name='frm_member' method='post' id='frm_member'>
		<a href='/'>HOME</a>
		<button type='button' id='btnInput'>입력</button>
		<input type='text' name='findStr' 
								 value='${page.findStr }'	size='30'>
		 						
		<button type='button' id='btnFind'>조회</button>
		<input type='hidden' name='nowPage' value='${page.nowPage }'/>
		<input type='hidden' name='id'/>
	</form>
	<div id='list'>
		<div class='title'>
			<span class='no'>No</span>
			<span class='id'>아이디</span>
			<span class='mName'>성명</span>
			<span class='gender'>성별</span>
			<span class='phone'>연락처</span>
			<span class='address'>주소</span>
			<span class='email'>이메일</span>
			<span class='zipcode'>우편번호</span>
			<span class='pwd'>암호</span>
		</div>
		<div class='items'>
			<c:set var='num' value='${page.startNo }' />
			<c:forEach var='v' items='${list }'>
				<div class='item' onclick="modify('${v.id}')">
					<span class='no'     >${num }</span>
					<span class='id'     >${v.id }</span>
					<span class='mName'  >${v.mName}</span>
					<span class='gender' >${v.gender}</span>
					<span class='phone'  >${v.phone}</span>
					<span class='address'>${v.address}</span>
					<span class='email'  >${v.email}</span>
					<span class='zipcode'>${v.zipcode}</span>
					<span class='pwd'    >${v.pwd}</span>
				</div>
				<c:set var='num' value='${num=num+1 }'/>
			</c:forEach>
			
		</div>
		<div class='paging'>
		
			<c:if test="${page.startPage>1 }">
				<button type='button' class='btnFirst'  onclick='movePage(1)'  >맨첨</button>
				<button type='button' class='btnPrev'   onclick='movePage(${page.startPage-1})' >이전</button>
			</c:if>		
			
			
			<c:forEach var='i' begin='${page.startPage }' end='${page.endPage }'>
				<button type='button' class='first'  onclick = 'movePage(${i})' >${i }</button>
			</c:forEach>				


			<c:if test="${page.endPage < page.totPage }">
				<button type='button' class='btnNext'  onclick='movePage(${page.endPage+1})'>다음</button>
				<button type='button' class='btnLast'  onclick='movePage(${page.totPage})'>맨끝</button>
			</c:if>		
		</div>	
	</div>
	</div>
	<script src='js/member.js'></script>	
</body>
</html>





