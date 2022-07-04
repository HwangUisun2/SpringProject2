/**
 * 학생관리와 관련된 스크립트
 */
/* 로그인/로그아웃/아이디찾기 --------- */
var btnLogin = document.getElementById('btnLogin');
var btnLogin2 = document.getElementById('btnLogin2');
var btnLogout = document.getElementById('btnLogout');
var btnInput = document.getElementById('btnInput');
var btnList = document.getElementById('btnList');

if(btnInput != null){
	btnInput.onclick = function(){
		let frm = document.frm_member;
		let url = 'insert';
		frm.action = url;
		frm.submit();
	}
}

/* 학생 정보 저장 ---------- */
var btnSave = document.getElementById('btnSave');
if(btnSave != null){
	btnSave.onclick = function(){
		let url = 'insertR';
		let frm = document.frm_member;
		frm.action = url;
		frm.submit();
	}
}


if(btnList != null){
	btnList.onclick = function(){
		let frm = document.frm_member;
		let url = 'member_select';
		frm.action = url;
		frm.submit();
	}
}

// index에서 로그인 버튼이 클릭된 경우
if(btnLogin != null){
	btnLogin.onclick = function(){
		console.log('login')
		let url = 'login';
		location.href=url;
	}
}

// 로그인폼(form_login)에서 로그인 버튼이 클릭된 경우
if(btnLogin2 != null){
	btnLogin2.onclick = function(){
		let url = 'loginR';
		let frm = document.frm_login;
		frm.action = url;
		frm.submit();
	}
}

// 로그아웃
if(btnLogout != null){
	btnLogout.onclick = function(){
		let url = 'logout';
		let frm = document.frm_login_out;
		frm.action = url;
		frm.submit();
	}
}

/* 아이디 / 암호 찾기 ------------------------- */
var btnFindId = document.getElementById('btnFindId');
if(btnFindId != null){
	btnFindId.onclick = function(){
		let frm = document.frm_find;
		let url = 'findIdR';
		frm.action = url;
		frm.submit();
	}
}

var btnFindPwd = document.getElementById('btnFindPwd');
if(btnFindPwd !=null){
	btnFindPwd.onclick = function(){
		let frm = document.frm_find;
		let url = 'findPwdR';
		frm.action = url;
		frm.submit();
	}
}


/* 학생 정보 조회 -------------------- */
var btnFind = document.getElementById('btnFind');
if(btnFind !=null){
	btnFind.onclick = function(){
		let url = 'member_select';
		let frm = document.frm_member;
		frm.nowPage.value=1;
		frm.action = url;
		frm.submit();
	}
}

/* 페이징 처리 --------------------------- */
function movePage(page){
		let url = 'member_select';
		let frm = document.frm_member;
		frm.nowPage.value = page;
		frm.action = url;
		frm.submit();
	
}

//수정할 데이터 가져오기
function modify(id){
	let url = 'modify';
	let frm = document.frm_member;
	frm.id.value = id;
	frm.action = url;
	frm.submit();
}

// 학생정보 수정
var btnUpdate = document.getElementById('btnUpdate');
if(btnUpdate !=null){
	btnUpdate.onclick = function(){
		let pwd = prompt("수정하려면 암호를 입력하세요");
		if(pwd == null){
			return;
		}
		
		let url = 'modifyR';
		let frm = document.frm_member;
		frm.pwd.value = pwd;
		frm.action = url;
		frm.submit();
	}
}

// 학생 정보 삭제
var btnDelete = document.getElementById("btnDelete");
if(btnDelete!=null){
	btnDelete.onclick = function(){
		let url = 'deleteR';
		let frm = document.frm_member;
		let pwd = prompt("정보를 삭제하려면 암호를 입력해 주세요");
		if(pwd==null) return;
		frm.pwd.value = pwd;
		frm.action = url;
		frm.submit(); 
	}
}

// 우편번호 검색
var btnZipFind = document.getElementById('btnZipFind');
if(btnZipFind !=null){
	btnZipFind.onclick = function(){
		new daum.Postcode({
			oncomplete : function(data){
				let frm = document.frm_member;
				frm.zipcode.value = data.zonecode;
				frm.address.value = data.address;
			}
		}).open();	
	}
}











