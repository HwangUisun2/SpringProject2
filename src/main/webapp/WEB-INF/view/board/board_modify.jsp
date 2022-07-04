<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<link rel='stylesheet' href='./css/board.css'>
<script src='./js/board.js'></script>

</head>
<body>
<div id='board'>
	<h1>게시판[수정]</h1>
	<form name='frm_board' class='frm_board' method='post'>
		<label>작성일</label>
		<input type='date' name='nal' value='${vo.nal }'/><br/>
		
		<label>작성자</label>
		<input type='text' name='id' value='${vo.id }' readonly/><br/>
		
		<label>제목</label>
		<input type='text' name='subject' value='${vo.subject }'/><br/>
		
		<textarea name='doc' id='summernote'>${vo.doc }</textarea><br/>
		
		<label>기첨부</label>
		<div class='appendList'>
			<c:forEach var='att' items='${vo.attList }'>
				<div>
					<span>${att.oriFile }</span>
					<button type='button' value ='${att.sysFile }'
					       onclick='board.appendRemoveAtt(this)'> X </button>
				</div>
			</c:forEach>
		</div>
		
		
		<label>첨부</label>
		<button type='button' onclick ='board.showFileTag()'>찾아보기</button>
		
		<div class='attList'></div>
		
		<div class='btns'>
			<button type='button' onclick='board.modifyR(this.form)'>수정저장</button>
			<button type='button' onclick='board.select(this.form)'>취소</button>
		</div>
		<input type='hidden' name='findStr' value='${param.findStr }'/>
		<input type='hidden' name='nowPage' value='${param.nowPage }'/>
		<input type='hidden' name='sno'     value='${vo.sno }' />
	</form>
	
	<form name='frm_att' class='frm_att' method='post' 
		enctype='multipart/form-data' action='fileUpload'>
		<input type='file' name='attFile' class='file_tag'
			onchange='board.fileView(this)' 
			multiple='multiple' style='display:none'/>
		<input type='hidden' name='sno'     value='${vo.sno }' />
	</form>
	
</div>
<script>
	board.init();
</script>
</body>
</html>