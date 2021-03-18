package com.jian.project1.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jian.project1.FileUtils;
import com.jian.project1.model.BoardDomain;
import com.jian.project1.model.BoardEntity;
import com.jian.project1.model.BoardPagingVO;

@Service
public class BoardService {

	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private FileUtils fUtils;
	
	
	/*
	// 이미지 업로드
	public String uploadImg(MultipartFile img) {
		
		System.out.println("img : " + img.getOriginalFilename());
		String folder = "/res/img/board/ctntImg";
		String ctntImg = fUtils.transferTo(img, folder);
		
		if(ctntImg == null) {
			return "";
		}
		return folder + "/" + ctntImg;
	}
	*/
	
	/* 유정 소스
	public String uploadImg(MultipartFile img) {
		
		String savePath = "/res/img/board/ctntImg";
		String fileName = img.getOriginalFilename();
		
		fUtils.save(img, savePath, fileName, true);
		
		return savePath + "/" + "t_" + fileName;
	}
	*/
	
	// write 내부 이미지 업로드 기능
	public Map<String, Object> uploadImg(MultipartFile img) {
		
		System.out.println("img : " + img.getOriginalFilename());
		String folder = "/res/img/board/ctntImg";
		String ctntImg = null;
		
		try {
			ctntImg = fUtils.transferTo(img, true, folder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(ctntImg == null) {
			return null;
		}
		
		Map<String, Object> json = new HashMap<String, Object> ();
		json.put("uploaded", 1);
		json.put("fileName", ctntImg);
		json.put("url", folder + "/" + "t_" + ctntImg);
		
		return json;
	}
	
	// 글 업로드 기능
	public int insBoard(BoardEntity p) {
		String start = "/res/img/board/";
		String end = "</figure>";
		
		String ctnt = p.getContent();
		
		
		int check = ctnt.indexOf(start);
		
		if(check != -1) {
			int s = ctnt.indexOf(start);
			int e = ctnt.indexOf(end) - 2;
			
			String boardMainImg = ctnt.substring(s, e);
			System.out.println(boardMainImg);
			p.setBoardMainImg(boardMainImg);
		}

		return mapper.insBoard(p);
	}
	
	
	
	
	public BoardDomain selBoard(BoardDomain p) {
		return mapper.selBoard(p);
	}
	
	
	
	public int selTotalCountOfItem(BoardPagingVO vo) {
		return mapper.selTotalCountOfItem(vo);
	}
	
	
	public List<BoardDomain> selBoardList(BoardPagingVO vo) {
		return mapper.selBoardList(vo);
	}
	
	public int delBoard(BoardEntity p) {
		p.setIsDel(1);
		return mapper.updBoard(p);
	}


	public int updBoard(BoardEntity p) {
		return mapper.updBoard(p);
	}
	
	
	public int closeBoard(BoardEntity p) {
		p.setCategory(12);
		return mapper.updBoard(p);
	}
	
	
}
