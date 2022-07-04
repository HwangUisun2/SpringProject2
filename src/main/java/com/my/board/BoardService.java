package com.my.board;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.my.controller.FileUploadController;
import com.my.mybatis.BoardMapper;

@Service
@Transactional
public class BoardService implements BoardInterface{
	
	@Autowired
	@Qualifier("bMapper")
	BoardMapper mapper;
	Page page;
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	public BoardService() {
		System.out.println("BoardService......");
	}
	
	
	@Override
	public List<BoardVo> select(Page page) {
		
		List<BoardVo> list = null;
		try {
			int totSize = mapper.totSize(page);
			
			page.setTotSize(totSize);
			page.compute();
			list = mapper.select(page);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		this.page = page;
		return list;
	}
	


	@Override
	public BoardVo selectOne(int sno) {
		
		BoardVo vo = null;
		List<BoardAtt> attList;
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			mapper.hitUp(sno);
			transaction.commit(status);
			
			vo = mapper.view(sno);
			attList = mapper.attList(sno);
			
			vo.setAttList(attList);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public int insert(BoardVo vo) {
		int sno =0;
		try {
			int cnt = mapper.insert(vo);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(cnt>0) {
				transaction.commit(status);
				sno = mapper.getSno();
			}else {
				transaction.rollback(status);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return sno;
	}

	@Override
	public boolean update(BoardVo vo) {
		boolean b=true;
		
		try {
			// 본문 내용을 수정
			int cnt = mapper.update(vo);
			
			// 삭제된 첨부파일 정보를 삭제
			if(cnt>0 && vo.getDelList().size()>0) {
				cnt = mapper.deleteAtt(vo.getDelList());
			}
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			if(cnt>0) {
				transaction.commit(status);

				// 실제 disk에서 파일 삭제
				for(BoardAtt att : vo.getDelList()) {
					
					File f = new File(FileUploadController.uploadPath + att.getSysFile());
					if(f.exists()) f.delete();
				}
			}else {
				transaction.rollback(status);
				b=false;
			}
		}catch(Exception ex) {
			b=false;
		}
		return b;
	}

	@Override
	public boolean delete(int sno) {
		boolean b = true;
		try {
			BoardVo vo = mapper.view(sno);
			String doc = vo.getDoc();
			
			// 삭제할 첨부 파일 목록
			List<BoardAtt> delAttList = mapper.attList(sno);
			// summernote(doc)에 있는 이미지 삭제
			String regexp = "(<img)(.+?)(src=\")(.+?)(\")";
			Pattern pattern = Pattern.compile(regexp);
			Matcher matcher = pattern.matcher(doc);
			
			// 본문삭제
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			int cnt = mapper.delete(sno);
			if(cnt>0) {
				// 첨부파일 정보 삭제
				if(delAttList.size()>0) {
					mapper.deleteAtt(delAttList);
				}
				transaction.commit(status);
				
				// 본문글에 있는 이미지를 디스크에서 삭제
				if(matcher != null) {
					while(matcher.find()) {
						String  delFile = matcher.group(4).split("/")[2];
						File f = new File(FileUploadController.uploadPath+delFile);
						if(f.exists()) f.delete();
					}
				}
				
				// 첨부파일을 디스크 장치에서 삭제
				for(BoardAtt att : delAttList) {
					File f = new File(FileUploadController.uploadPath + att.getSysFile());
					if(f.exists())f.delete();
				}
			}
			
		}catch(Exception ex) {
			b=false;
		}
		return b;
	}

	
	
	@Override
	public boolean insertAtt(List<BoardAtt> attList) {
		
		boolean b=true;
		if(attList.size()<=0) return b;
		
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			int cnt = mapper.insertAtt(attList);
			if(cnt>0) {
				transaction.commit(status);
			}else {
				transaction.rollback(status);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			b=false;
		}
		
		return b;
	}
	
	
	

	@Override
	public boolean deleteAtt(List<BoardAtt> attList) {
		return false;
	}

	@Override
	public List<BoardAtt> selectAtt(int sno) {
		return null;
	}

	@Override
	public int repl(BoardVo vo) {
		int sno = 0;
		try {
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			// 부모글 보다 큰 seq 증가
			mapper.seqUp(vo);
			int cnt = mapper.repl(vo);
			if(cnt>0) {
				transaction.commit(status);
				sno = mapper.getSno();
			}else {
				transaction.rollback(status);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return sno;
	}


	public Page getPage() {
		return this.page;
	}
	
}
