package com.my.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.my.mybatis.MemberMapper;

@Service
public class MemberService implements MemberInterface{
	Page page;
	AES aes; 
	
	@Autowired 
	@Qualifier("mMapper")
	MemberMapper mMapper;
	
	@Autowired
	DataSourceTransactionManager transaction;
	TransactionStatus status;
	
	public MemberService(){
		//this.conn = new DBConn().getConn();
		aes = new AES(); 
		System.out.println("MemberService......");

	}

	@Override
	public List<MemberVo> select(Page page) {
		List<MemberVo> list = null;
		int totSize = 0;
		try {
			
			totSize = mMapper.totSize(page);
			page.setTotSize(totSize);
			page.compute();
			this.page = page;
			list = mMapper.select(page);
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	
	
	public String findId(MemberVo vo) {
		String id = "";
		try {
			id = mMapper.findId(vo);

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	public String findPwd(MemberVo vo) {
		String pwd = "";
		
		try {
			//복호화된 암호 전달
			String temp = mMapper.findPwd(vo);
			pwd = aes.dec(temp );
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return pwd;
	}
		
	
	
	
	public MemberVo login( MemberVo vo, HttpServletRequest req) {
		MemberVo rVo=null;
		
		HttpSession session = req.getSession();
		
		try {
			String mPwd = aes.enc(vo.getPwd());
			vo.setPwd(mPwd);
			rVo = mMapper.login(vo);
			
			if(rVo != null) {
				session.setAttribute("id", rVo.getId());
			}else {
				session.setAttribute("id", null);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return rVo;
	}
	
	public String logout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String msg = "다음에 다시 만나요~";
		session.setAttribute("id", null);
		return msg;
	}
	
	public boolean insert(MemberVo vo) {
		boolean b=true;
		try {
			// 암호화.
			String pwd = aes.enc(vo.getPwd());
			vo.setPwd(pwd);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			mMapper.insert(vo);
			transaction.commit(status);
			
		}catch(Exception ex) {
			ex.printStackTrace();
			b=false;
		}
	
		return b;
	}
	

	@Override
	public MemberVo selectOne(String id) {
		MemberVo vo = null;
		try {
			vo = mMapper.selectOne(id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return vo;
	}

	public boolean update(MemberVo vo) {
		boolean b=true;
		String pwd="";
		try {
			// 암호화
			pwd = aes.enc(vo.getPwd());
			vo.setPwd(pwd);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			mMapper.update(vo);
			transaction.commit(status);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return b;
	}

	public boolean delete(MemberVo vo) {
		boolean b=true;
		String pwd="";
		try {
			pwd = aes.enc(vo.getPwd());
			vo.setPwd(pwd);
			status = transaction.getTransaction(new DefaultTransactionDefinition());
			mMapper.delete(vo);
			transaction.commit(status);
			

		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return b;
	}

	public Page getPage() {
		return page;
	}


	
}



















