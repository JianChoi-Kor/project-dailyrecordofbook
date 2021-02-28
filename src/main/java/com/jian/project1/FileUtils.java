package com.jian.project1;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

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
	
	
	/* 유정 소스
	public void save(MultipartFile file, String savePath, String fileName, boolean createThumb) {

		File path = new File(savePath);
		
		if(!path.exists()) {
			path.mkdirs();
		}
		
		File newFile = new File(savePath, fileName);
		try {
			file.transferTo(newFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(createThumb) {
			try {
				saveThumb(newFile, savePath, 600);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	 */
	
	public void saveThumb(File origin_file, String savePath, int MAX) throws Exception {
		BufferedImage origin_image = ImageIO.read(origin_file);
		String extension = getExt(origin_file.getName());
		
		File thumb_file = new File(savePath, "t_" + origin_file.getName());
		
		double origin_w = origin_image.getWidth();
		double origin_h = origin_image.getHeight();
		
		double ratio = (MAX / origin_w);
		
		int thumb_w = (int)(origin_w * ratio);
		int thumb_h = (int)(origin_h * ratio);
		
		BufferedImage thumb_image = new BufferedImage(thumb_w, thumb_h, BufferedImage.TYPE_3BYTE_BGR);
		
		Graphics2D graphic = thumb_image.createGraphics();
		Image image = origin_image.getScaledInstance(thumb_w, thumb_h, Image.SCALE_SMOOTH);
		graphic.drawImage(image, 0, 0, thumb_w, thumb_h, null);
		graphic.dispose();
		
		ImageIO.write(thumb_image, extension, thumb_file);
	}
	

	
	public String transferTo(MultipartFile mf, boolean createThumb, String... target) throws Exception {
		String fileNm = null;
		String basePath = getBasePath(target);
		makeFolders(basePath);
		File file;
		
		try {
			fileNm = getRandonFileNm(mf.getOriginalFilename());
			file = new File(basePath, fileNm);
			mf.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		if(createThumb) {
			saveThumb(file, basePath, 600);
		}
		
		return fileNm;
		
		
	}	
	
	



}
