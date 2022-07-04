package com.my.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.member.MemberVo;
import com.my.member.Page;

@Mapper
@Repository
@Qualifier("mMapper")
public interface MemberMapper {
	public int totSize(Page page);
	public List<MemberVo> select(Page page);
	public String findId(MemberVo vo);
	public String findPwd(MemberVo vo);
	public MemberVo login(MemberVo vo);
	public MemberVo selectOne(String id);
	public int insert(MemberVo vo);
	
	public int update(MemberVo vo);
	public int delete(MemberVo vo);
}


