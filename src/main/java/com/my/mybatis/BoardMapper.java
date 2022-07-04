package com.my.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.my.board.BoardAtt;
import com.my.board.BoardVo;
import com.my.board.Page;

@Mapper
@Repository
@Qualifier("bMapper")
public interface BoardMapper {
	public int insert(BoardVo vo);
	public int insertAtt(List<BoardAtt> att);
	public int repl(BoardVo vo);
	
	public int totSize(Page page);
	
	
	public List<BoardVo> select(Page page);
	public int getSno();
	public BoardVo view(int sno);
	public List<BoardAtt> attList(int pSno);
	
	public void hitUp(int sno);
	public int  update(BoardVo vo);
	public int  seqUp(BoardVo vo);
	
	public int deleteAtt(List<BoardAtt> attList);
	public int delete(int sno);
	
}
