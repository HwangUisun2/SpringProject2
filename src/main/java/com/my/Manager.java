package com.my;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Manager {
	
	@Autowired
	HttpServletRequest req;
	
	public Manager() {
		System.out.println("Manager.......");
	}
	
	public boolean loginCheck() {
		boolean b=false;
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		if(id != null) {
			b=true;
		}
		return b;
	}
	
	
}
