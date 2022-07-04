<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>student_modify_form</title>
<link rel='stylesheet' href='./css/member.css'>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>
<div id='member'>
	<h1>학생정보 수정</h1>
	
	<form name='frm_member' method='post' id='frm_member'>
		<label>아이디</label>
		<input type='text' name='id'  size='15'  readonly="readonly" 
		            value='${vo.id }'><br/>
		
		<label>성명</label>
		<input type='text' name='mName'  size='12' value='${vo.mName }'><br/>

		<label>성별</label>
		<span>
			<label>
				<input type='radio' name='gender'  value='m' 
										${ (vo.gender=='m')? 'checked' : ''  }
				>남자
				
			</label>
			<label>
				<input type='radio' name='gender'  value='f'
										${ (vo.gender=='f')? 'checked' : ''  }
				>여자
				
				</label>		
		</span>
		<br/>
		<label>연락처</label>
		<input type='text' name='phone'  size='12' value='${vo.phone }'><br/>

		<label>우편번호</label>
		<input type='text' name='zipcode'  readonly="readonly" size='7' value='${vo.zipcode }'>
		<button type='button' id='btnZipFind'>우편번호 검색</button>
		<br/>
	
		<label>주소</label>
		<input type='text' name='address' size='40'  value='${vo.address }'><br/>
		
		<label>이메일</label>
		<input type='text' name='email'  size='30' autocomplete="none" value='${vo.email }'><br/>
		<br/>
		<label></label>
		<button type='button' id='btnUpdate'>수정</button>
		<button type='button' id='btnDelete'>삭제</button>
		<button type='button' id='btnList'>취소</button>
		<input type='hidden' name='findStr' value='${page.findStr }' />
		<input type='hidden' name='nowPage' value='${page.nowPage }' />
		<input type='hidden' name='pwd'/>
	</form>

</div>
<script src='./js/member.js'></script>
</body>
</html>