package com.jian.project1.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jian.project1.model.BookSlideEntity;

@Service
public class MainService {
	
	@Autowired
	private MainMapper mapper;
	
	// 책 추가하는 메서드
	public int addBook(BookSlideEntity p) {
		String start = "/res/img/board/";
		String end = "</figure>";
		
		String ctnt = p.getBookImg();
		
		int check = ctnt.indexOf(start);
		
		if(check != -1) {
			int s = ctnt.indexOf(start);
			int e = ctnt.indexOf(end) - 2;
			
			String bookImg = ctnt.substring(s, e);
			p.setBookImg(bookImg);
		}
			
		return mapper.addBook(p);
	}

	
	// 책 리스트 가지고 오는 메서드
	public List<BookSlideEntity> selBookList() {
		return mapper.selBookList();
	}
	
	
}






