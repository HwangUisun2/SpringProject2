package com.my.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.my.member.MemberService;
import com.my.member.MemberVo;
import com.my.member.Page;

@RestController
public class MemberController {

	@Autowired
	MemberService dao;
	
	@RequestMapping("login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/form_login");
		return mv;
	}
	@RequestMapping("loginR")
	public ModelAndView loginR(MemberVo vo, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		MemberVo rVo = dao.login(vo, req);
		
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		dao.logout(req);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("findId")
	public ModelAndView findId(MemberVo vo) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/form_find_id");
		return mv;
	}
	
	@RequestMapping("findIdR")
	public ModelAndView findIdR(MemberVo vo) {
		ModelAndView mv = new ModelAndView();
		String id = dao.findId(vo);
		
		mv.addObject("msg", id);
		mv.setViewName("member/find_id_result");
		return mv;
	}

	@RequestMapping("findPwd")
	public ModelAndView findPwd(MemberVo vo) {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/form_find_pwd");
		return mv;
	}
	
	@RequestMapping("findPwdR")
	public ModelAndView findPwdR(MemberVo vo) {
		ModelAndView mv = new ModelAndView();
		String pwd = dao.findPwd(vo);
		
		mv.addObject("msg", pwd);
		mv.setViewName("member/find_pwd_result");
		return mv;
	}
	
	
	@RequestMapping("/member_select")
	public ModelAndView select(Page page) {
		ModelAndView mv = new ModelAndView();
		
		if(page==null) {
			page = new Page();
			page.setFindStr("");
			page.setNowPage(1);
		}
		
		List<MemberVo> list = dao.select(page);
		page = dao.getPage();
				
		mv.addObject("page", page);
		mv.addObject("list", list);
		mv.setViewName("member/member_list");
		return mv;
	}

	@RequestMapping("modify")
	public ModelAndView modify(MemberVo vo, Page page) {
		ModelAndView mv = new ModelAndView();
		MemberVo rVo = dao.selectOne(vo.getId());
		
		mv.addObject("page", page);
		mv.addObject("vo", rVo);
		mv.setViewName("member/member_modify_form");
		return mv;
	}
	
	@RequestMapping("modifyR")
	public ModelAndView modifyR(MemberVo vo, Page page) {
		ModelAndView mv = new ModelAndView();
		dao.update(vo);
		
		mv.addObject("page", page);
		mv.setViewName("member/member_list");
		return mv;
	}
	
	@RequestMapping("deleteR")
	public ModelAndView deleteR(MemberVo vo, Page page) {
		ModelAndView mv = new ModelAndView();
		dao.delete(vo);
		
		mv.addObject("page", page);
		mv.setViewName("member/member_list");
		return mv;
	}
	
	@RequestMapping("insert")
	public ModelAndView insert(Page page) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("page", page);
		mv.setViewName("member/member_input_form");
		return mv;
	}
	
	@RequestMapping("insertR")
	public ModelAndView insertR(MemberVo vo, Page page) {
		ModelAndView mv = new ModelAndView();
		dao.insert(vo);
		
		mv.addObject("page", page);
		mv.setViewName("member/member_list");
		return mv;
	}
	
	
	
}








