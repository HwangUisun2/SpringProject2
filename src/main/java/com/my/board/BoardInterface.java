package com.my.board;

import java.util.List;

public interface BoardInterface {
	
	public List<BoardVo> select(Page page);
	
	public BoardVo selectOne(int sno);
	public int     insert(BoardVo vo);
	public boolean update(BoardVo vo);
	public boolean delete(int sno);
	public int     repl(BoardVo vo);
	
	public boolean insertAtt(List<BoardAtt> attList);
	public boolean deleteAtt(List<BoardAtt> attList);
	public List<BoardAtt> selectAtt(int sno);
	
}
