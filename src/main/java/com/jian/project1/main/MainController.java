package com.jian.project1.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jian.project1.Const;
import com.jian.project1.model.BookSlideEntity;

@Controller
@RequestMapping("main")
public class MainController {

	@Autowired
	private MainService service;
	
	
	@GetMapping("/home")
	public void home(Model model) {
		model.addAttribute(Const.KEY_LIST, service.selBookList());
	}
	
	
	// BookSlide 도서 추가하기
	@GetMapping("/addBook")
	public void addBook(Model model) {
		
	}
	
	
	@PostMapping("/addBook")
	public String addBook(BookSlideEntity p) {
		
		System.out.println("bookpk : " + p.getBookPk());
		System.out.println("booktitle : " + p.getBookTitle());
		
		
		service.addBook(p);
		return "redirect:/main/home";
	}
	
	
	
	
}
