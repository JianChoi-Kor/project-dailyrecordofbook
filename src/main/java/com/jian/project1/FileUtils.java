package com.jian.project1;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {

	@Autowired
	private ServletContext ctx;

	public void makeFolders(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}

	public String getBasePath(String... moreFolder) {
		String temp = "";
		for (String s : moreFolder) {
			temp += s;
		}
		String basePath = ctx.getRealPath(temp);
		return basePath;
	}

	// 확장자 얻어오기
	public String getExt(String fileNm) {
		return fileNm.substring(fileNm.lastIndexOf(".") + 1);
	}

	// 랜덤 파일명 리턴
	public String getRandonFileNm(String fileNm) {
		return UUID.randomUUID().toString() + "." + getExt(fileNm);
	}

	// 파일 저장 & 랜덤 파일명 구하기
	public String transferTo(MultipartFile mf, String... target) {
		String fileNm = null;
		String basePath = getBasePath(target);
		makeFolders(basePath);

		try {
			fileNm = getRandonFileNm(mf.getOriginalFilename());
			File file = new File(basePath, fileNm);
			mf.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return fileNm;
	}

	
	
	/*
	// imagscalr 라이브러리 사용 img resizing
	public BufferedImage resizeImg(MultipartFile mf, int resizeWidth, int resizeHeight) {

		BufferedImage rsImg = null;
		
		try {
			InputStream in;
			in = mf.getInputStream();
			BufferedImage originalImage = ImageIO.read(in);
			
			System.out.println("origianlImage width : "  + originalImage.getWidth());
			if(originalImage.getWidth() >= 500) {
				System.out.println("Start resizing");
				rsImg = Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, resizeWidth);
				in.close();
				return rsImg;
				
			} else {
				in.close();
				return originalImage;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*/
	

	
	
	/*
	
	
	public void resizeImg(MultipartFile mf, int resizeWidth, int resizeHeight) {
		BufferedImage rsImg = null;
		try {
			InputStream in;
			in = mf.getInputStream();
			
			BufferedImage originalImage = ImageIO.read(in);
			System.out.println("origianlImage width : "  + originalImage.getWidth());
			
			if(originalImage.getWidth() >= 500) {
				System.out.println("Start resizing");
				rsImg = Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, resizeWidth);
				in.close();

				System.out.println("rsImg width : " + rsImg.getWidth());

			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	*/

}
