package com.my.board;

import java.util.List;

public class BoardVo {
	int sno;
	int grp;
	int seq;
	int deep;
	int hit; // 조회수
	int attCnt; // 첨부파일의 갯수
	
	String nal;
	String id;
	String subject;
	String doc;
	
	List<BoardAtt> attList ;
	List<BoardAtt> delList;
	
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public int getGrp() {
		return grp;
	}
	public void setGrp(int grp) {
		this.grp = grp;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getDeep() {
		return deep;
	}
	public void setDeep(int deep) {
		this.deep = deep;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getAttCnt() {
		return attCnt;
	}
	public void setAttCnt(int attCnt) {
		this.attCnt = attCnt;
	}
	public String getNal() {
		return nal;
	}
	public void setNal(String nal) {
		this.nal = nal;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public List<BoardAtt> getAttList() {
		return attList;
	}
	public void setAttList(List<BoardAtt> attList) {
		this.attList = attList;
	}
	public List<BoardAtt> getDelList() {
		return delList;
	}
	public void setDelList(List<BoardAtt> delList) {
		this.delList = delList;
	} 
	
	
	
}
