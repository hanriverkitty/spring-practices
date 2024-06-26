package com.poscodx.guestbook3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscodx.guestbook3.repository.GuestbookRepositoryWithJdbcContext;
import com.poscodx.guestbook3.service.GuestbookService;
// import com.poscodx.guestbook3.repository.GuestbookRepositoryWithJdbcTemplate;
import com.poscodx.guestbook3.vo.GuestbookVo;

@Controller
public class GuestbookController {	
	@Autowired
	private GuestbookService guestbookService;
	
	// findAll() 안됨
	@Autowired
	private GuestbookRepositoryWithJdbcContext guestbookRepository2;
	
//	@Autowired
//	private GuestbookRepositoryWithJdbcTemplate guestbookRepository3;
	
	@RequestMapping("/")
	public String index(Model model) {
		List<GuestbookVo> list = guestbookService.getContentsList();
		model.addAttribute("list",list);
		return "index";
		
	}
	
	@RequestMapping("/add")
	public String add(GuestbookVo vo) {
		guestbookService.addContents(vo);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete/{no}",method=RequestMethod.GET)
	public String delete(@PathVariable("no") Long no,Model model) {
		model.addAttribute("no",no);
		return "deleteform";
	}
	
	@RequestMapping(value="/delete/{no}",method=RequestMethod.POST)
	public String delete(@PathVariable("no")Long no,@RequestParam(value="password",required=true) String password) {
		int result = guestbookService.deleteContents(no, password);
		if(result==1) {
			return "redirect:/";
		}else {
			return "passwordfail";
		}
	}
	
	
}
