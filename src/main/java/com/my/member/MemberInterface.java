package com.my.member;

import java.util.List;

public interface MemberInterface {
	public boolean insert(MemberVo vo);
	public List<MemberVo> select(Page page);
	public MemberVo selectOne(String id);
	public boolean update(MemberVo vo);
	public boolean delete(MemberVo vo);
}
