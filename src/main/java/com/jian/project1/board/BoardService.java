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
		return mapper.insBoard(p);
	}
	
	// list 받아오는 기능
	public List<BoardDomain> selBoardList(BoardEntity p) {
		return mapper.selBoardList(p);
	}
	
	
	
	
	
	public BoardDomain selBoard(BoardDomain p) {
		return mapper.selBoard(p);
	}
	
	
	
	
	
	
	
	
	
}
