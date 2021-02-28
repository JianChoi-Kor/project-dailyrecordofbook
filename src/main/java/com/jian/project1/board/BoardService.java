package com.jian.project1.board;

import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jian.project1.FileUtils;

@Service
public class BoardService {

	
	@Autowired
	private FileUtils fUtils;
	
	
	// 이미지 업로드
	public String uploadImg(MultipartFile img) {
		
		BufferedImage test = fUtils.resizeImg(img, 300, 300);
		System.out.println("test : " + test);
		
		System.out.println("img : " + img.getOriginalFilename());
		String folder = "/res/img/board/ctntImg";
		String ctntImg = fUtils.transferTo(img, folder);
		
		if(ctntImg == null) {
			return "";
		}
		return folder + "/" + ctntImg;
	}
	
}
