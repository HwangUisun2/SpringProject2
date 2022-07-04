/**
 * 게시판과 관련한 스크립트
 * date : 2022.06(pwg)
 */

let board={};
let loadInterval = [];

//input, modify, repl에서만 호출
board.init=function(){
	fonts =["맑은고딕", "돋음", "궁서", "굴림", "굴림체",
		"궁서체", "바탕", "바탕체", "HY엽서체M", "휴먼옛체"];
	fonts.sort();
	
	
	$('#summernote').summernote({
		fontNames : fonts,
		callbacks : {
			onImageUpload : function(files){
				loadInterval.length = files.length;
				$('#board').addClass('spinner');
				for(var i=files.length-1 ; i>=0 ; i--){
					sendFile(i, files[i]);
				}
			},
			
			onMediaDelete : function(target){
				var file = decodeURI(target[0].src);
				$.ajax({
					data : {delFile : file},
					type : 'POST',
					url  : 'summernoteDelete',
					cache:false,
					success : function(resp){
						console.log("delete OK.....");
					}
				});
			}
			
		}
	});
}

//board.sendFile = function(a,b,c ){} // hoisting 되지 않기 때문에 호출부분보다 상위에 정의해야 됨.
function sendFile(i, file){
	var frm = new FormData(); //enctype이 있는 form tag를 생성
	frm.append('file', file);
	$.ajax({
		data : frm,
		type : 'POST',
		url  : 'summernote',
		enctype : 'multipart/form-data',
		cache : false,
		contentType : false,
		processData : false,
		success : function(att){
			// interval된 객체를 loadInterval의 i번째에 저장
			loadInterval[i] = 
			    setInterval(loadCheck.bind(null, i, att), 1500)
		}
		
	});
}

function loadCheck(i, att){
	var img = new Image();
	img.onload = function(){
		$('#summernote').summernote('editor.insertImage', att);
		$('#board').removeClass('spinner')
		clearInterval(loadInterval[i]);
	}
	img.src = att;
}



board.find = function(frm){
	frm.nowPage.value = 1;
	board.select(frm);
}

board.select = function(frm){
	frm.action='board_select';
	frm.submit();
}

board.input = function(frm){
	frm.action = 'board_input';
	frm.submit();
}

board.view = function(sno){
	let frm = $('.frm_board_list')[0];
	frm.sno.value = sno;
	frm.action='board_view';
	frm.submit();
}


board.inputR = function(frm){
	let param = $(frm).serialize();
	$.post('board_inputR', param , function(sno){
		let tempFrm = $('.frm_att')[0];
		let frmAtt = new FormData(tempFrm);
		frmAtt.append('sno', sno);
		
		$.ajax({
			data : frmAtt,
			url  : 'fileUpload',
			type : 'POST',
			enctype : 'multipart/form-data',
			cache  : false,
			contentType : false,
			processData : false,
			success : function(resp){
				frm.action = 'board_select';
				frm.submit();
			}
		})
	})	
}

board.modify = function(frm){
	frm.action='board_modify';
	frm.submit();
}

board.modifyR = function(frm){
	let param = $(frm).serialize();
	$.post('board_modifyR', param, function(resp){
		let temp =$('.frm_att')[0];
		let frmAtt = new FormData(temp);
		
		$.ajax({
			data   : frmAtt,
			type   : 'POST',
			url    : 'fileUpload',
			enctype : 'multipart/form-data',
			cache  : false,
			contentType : false,
			processData : false,
			success : function(resp){
				frm.action='board_select';
				frm.submit();
			}
		})
	})

}


board.deleteR = function(frm){
	let yn = confirm('정말 삭제 ????');
	if( !yn ) return;
	
	let param = $(frm).serialize();
	$.post('board_deleteR', param, function(resp){
		frm.action='board_select';
		frm.submit();
	} )
}

board.repl = function(frm){
	frm.action='board_repl';
	frm.submit();
}


board.replR = function(frm){
	let param = $(frm).serialize();
	$.post('board_replR', param , function(sno){
		console.log('sno:', sno)
		
		let tempFrm = $('.frm_att')[0];
		let frmAtt = new FormData(tempFrm);
		frmAtt.append('sno', sno);
		
		$.ajax({
			data : frmAtt,
			url  : 'fileUpload',
			type : 'POST',
			enctype : 'multipart/form-data',
			cache  : false,
			contentType : false,
			processData : false,
			success : function(resp){
				frm.action = 'board_select';
				frm.submit();
			}
		})
	})	

}

board.movePage = function(page){
	let frm = $('.frm_board_list')[0];
	frm.nowPage.value = page;
	frm.action='board_select';
	frm.submit();
}

// ----------------------------------
// 첨부 파일이 변경된 경우 
// ----------------------------------
board.fileList = [];
board.showFileTag = function(){
	let fileTag = $('.file_tag');
	fileTag.click();
}

board.fileView = function(e){
	//이벤트가 발생한 file tag의 파일 목록
	let files = e.files;
	if(files.length <=0 ) return false;
	
	let fileArray = Array.prototype.slice.call(files);
	let str = '';
	for(f of fileArray){
		board.fileList.push(f);
		let size = parseInt(f.size/1000);
		str += `
			<div>
			   <span>${f.name}(${size}Kb) </span>
			   <button type='button' value='${f.name}' class='delAttFile'
                  onclick='board.removeAtt(this)'> X </button>
			</div>		
		`
	}
	$('.attList').prepend(str);
	
	let dt = new DataTransfer();
	for(f of board.fileList){
		dt.items.add(f);
	}
	$('.file_tag')[0].files = dt.files;
}
	
board.removeAtt = function(btn){
	// 표시된 목록에서 삭제
	let p = $(btn).parent();
	$(p).remove();
	
	// file tag의 목록에서 삭제
	let files = $('.file_tag')[0].files;
	
	let fileArray = Array.prototype.slice.call(files);
	for(var i=0 ; i<fileArray.length ; i++){
		var f=fileArray[i];
		if(f.name == btn.value){
			fileArray.splice(i, 1);
			break;
		}
	}
	
	board.fileList = fileArray;
	
	// 수정된 정보를 file tag에 적용
	let dt = new DataTransfer();
	
	for(f of fileArray){
		dt.items.add(f);
	}
	$('.file_tag')[0].files =dt.files;

}

board.appendRemoveAtt = function(btn){
	let frm = $('.frm_board')[0];
	
	let chkbox = document.createElement('input');
	chkbox.setAttribute('type', 'hidden');
	chkbox.setAttribute('name', 'delAtt');
	chkbox.setAttribute('value', btn.value);
	
	frm.appendChild(chkbox);
	
	let div = $(btn).parent();
	$(div).remove();
	
}












