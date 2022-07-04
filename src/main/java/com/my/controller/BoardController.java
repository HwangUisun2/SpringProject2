package com.my.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.my.board.BoardAtt;
import com.my.board.BoardService;
import com.my.board.BoardVo;
import com.my.board.Page;

@RestController
public class BoardController {

	@Autowired
	BoardService dao; //DI
	
	@Autowired
	FileUploadController fc;
	
	@Autowired
	SummernoteController sc;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping("board_select")
	public ModelAndView select(Page page){
		ModelAndView mv = new ModelAndView();
		
		if(page.getFindStr() == null) {
			page = new Page();
			page.setNowPage(1);
			page.setFindStr("");
		}		
		
		List<BoardVo> list = dao.select(page); 
		page = dao.getPage();
		
		mv.addObject("list", list);
		mv.addObject("page", page);
		
		mv.setViewName("board/board_list");
		
		return mv;
		
	}
	
	@RequestMapping("board_view")
	public ModelAndView view(Page page, int sno) {
		ModelAndView mv = new ModelAndView();
		BoardVo vo = dao.selectOne(sno);
		
		mv.addObject("vo", vo);
		mv.addObject("page", page);
		mv.setViewName("board/board_view");
		
		return mv;
	}
	
	@RequestMapping("board_input")
	public ModelAndView input(Page page){
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("page", page);
		mv.setViewName("board/board_input");
		
		return mv;
	}
	
	@RequestMapping("board_inputR")
	public int inputR(BoardVo vo, Page page, List<MultipartFile> mul) {
		int sno = dao.insert(vo);
		System.out.println("Controller sno : " + sno);

		return sno;
	}
	
	@RequestMapping("fileUpload")
	public void fileUpload(@RequestParam("attFile") List<MultipartFile> mul, 
			int sno, Page page) {
		
		ModelAndView mv = new ModelAndView();

		List<BoardAtt> attList = fc.upload(mul, sno);
		dao.insertAtt(attList);
		
		mv.addObject("sno", sno);
		mv.addObject("page", page);
		mv.setViewName("board/board_list");
	}
	
	@RequestMapping("summernote")
	public String summernote( @RequestParam("file") List<MultipartFile> mul) {
		String file = sc.upload(mul);
		return file;// ./upload/XXXX
	}
	
	@RequestMapping("summernoteDelete")
	public void summernoteDelete(@RequestParam("delFile") String delFile) {
		String[] temp = delFile.split("/");
		String file = temp[temp.length-1]; // 경로명을 제외한 삭제할 파일
		File f = new File(FileUploadController.uploadPath + file);
		if(f.exists()) f.delete();
	}
	
	
	@RequestMapping("board_modify")
	public ModelAndView modify(Page page, int sno){
		ModelAndView mv = new ModelAndView();
		BoardVo vo = null;
		vo = dao.selectOne(sno);
		mv.addObject("vo", vo);
		mv.addObject("page", page);
		mv.setViewName("board/board_modify");
		return mv;
	}

	@RequestMapping("board_modifyR")
	public void modifyR(Page page, BoardVo vo, 
			@RequestParam(name = "delAtt", required=false) List<String> delAtt){
		List<BoardAtt> delAttList = new ArrayList<BoardAtt>();
		
		if(delAtt !=null) {
			for(String delFile : delAtt) {
				BoardAtt att = new BoardAtt();
				att.setpSno(vo.getSno());
				att.setSysFile(delFile);
				delAttList.add(att);
			}
		}
		vo.setDelList(delAttList);
		boolean b = dao.update(vo);
		
	}
	

	
	@RequestMapping("board_deleteR")
	public void deleteR(Page page , int sno){
		dao.delete(sno);
		
	}
	
	@RequestMapping("board_repl")
	public ModelAndView repl(Page page, BoardVo vo){
		ModelAndView mv = new ModelAndView();
		mv.addObject("page", page);
		mv.addObject("vo", vo);
		mv.setViewName("board/board_repl");
		return mv;
	}
	
	@RequestMapping("board_replR")
	public int replR(Page page, BoardVo vo){
		ModelAndView mv = new ModelAndView();
		
		int sno = dao.repl(vo);
		return sno;
	
	}
	
	
}
