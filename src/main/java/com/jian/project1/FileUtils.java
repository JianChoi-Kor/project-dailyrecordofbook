package com.jian.project1;

import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.jpeg.JpegDirectory;

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
	 * 유정 소스 public void save(MultipartFile file, String savePath, String fileName,
	 * boolean createThumb) {
	 * 
	 * File path = new File(savePath);
	 * 
	 * if(!path.exists()) { path.mkdirs(); }
	 * 
	 * File newFile = new File(savePath, fileName); try { file.transferTo(newFile);
	 * } catch (IllegalStateException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); }
	 * 
	 * if(createThumb) { try { saveThumb(newFile, savePath, 600); } catch (Exception
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); } } }
	 * 
	 */

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

		if (createThumb) {
			// saveThumb(file, basePath, 600);
			makeThumbnail(file, basePath, 600);
		}
		return fileNm;
	}

	/*
	 * public void saveThumb(File origin_file, String savePath, int MAX) throws
	 * Exception { BufferedImage origin_image = ImageIO.read(origin_file); String
	 * extension = getExt(origin_file.getName());
	 * 
	 * File thumb_file = new File(savePath, "t_" + origin_file.getName());
	 * 
	 * 
	 * 
	 * 
	 * 
	 * double origin_w = origin_image.getWidth(); double origin_h =
	 * origin_image.getHeight(); System.out.println(origin_w);
	 * System.out.println(origin_h);
	 * 
	 * if (origin_w > MAX) {
	 * 
	 * double ratio = (MAX / origin_w); System.out.println(ratio);
	 * 
	 * int thumb_w = (int) (origin_w * ratio); int thumb_h = (int) (origin_h *
	 * ratio);
	 * 
	 * System.out.println(thumb_w); System.out.println(thumb_h);
	 * 
	 * BufferedImage thumb_image = new BufferedImage(thumb_w, thumb_h,
	 * BufferedImage.TYPE_3BYTE_BGR);
	 * 
	 * Graphics2D graphic = thumb_image.createGraphics(); Image image =
	 * origin_image.getScaledInstance(thumb_w, thumb_h, Image.SCALE_SMOOTH);
	 * graphic.drawImage(image, 0, 0, thumb_w, thumb_h, null); graphic.dispose();
	 * 
	 * ImageIO.write(thumb_image, extension, thumb_file);
	 * 
	 * } else { // image width 600px 이하인 경우 BufferedImage thumb_image = new
	 * BufferedImage((int) origin_w, (int) origin_h, BufferedImage.TYPE_3BYTE_BGR);
	 * 
	 * Graphics2D graphic = thumb_image.createGraphics(); Image image =
	 * origin_image.getScaledInstance((int) origin_w, (int) origin_h,
	 * Image.SCALE_SMOOTH); graphic.drawImage(image, 0, 0, (int) origin_w, (int)
	 * origin_h, null); graphic.dispose();
	 * 
	 * ImageIO.write(thumb_image, extension, thumb_file); }
	 * 
	 * }
	 * 
	 */

	private void makeThumbnail(File origin_file, String basePath, int MAX) throws Exception {

		String fileNm = origin_file.getName();
		String extension = getExt(origin_file.getName());

		File imageFile = new File(basePath + "/" + fileNm);

		int orientation = 1; // 회전정보 1 => 0도, 3 => 180도, 6 => 270도, 8 => 90도

		Metadata metadata; // 이미지 메타 데이터 객체
		Directory directory; // 이미지 Exif 데이터를 읽기 위한 객체
		JpegDirectory jpegDirectory; // JPG 이미지 정보를 읽기 위한 객체

		try {
			metadata = ImageMetadataReader.readMetadata(imageFile);
			directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
			jpegDirectory = metadata.getFirstDirectoryOfType(JpegDirectory.class);
			if (directory != null) {
				orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
			}

		} catch (Exception e) {
			orientation = 1;
		}

		BufferedImage srcImg = ImageIO.read(imageFile);
		AffineTransformOp[] xform = null;

		switch (orientation) {
		case 6:
			srcImg = Scalr.rotate(srcImg, Scalr.Rotation.CW_90, xform);
			break;
		case 1:

			break;
		case 3:
			srcImg = Scalr.rotate(srcImg, Scalr.Rotation.CW_180, xform);
			break;
		case 8:
			srcImg = Scalr.rotate(srcImg, Scalr.Rotation.CW_270, xform);
			break;

		default:
			orientation = 1;
			break;
		}

		double origin_w = srcImg.getWidth();
		double origin_h = srcImg.getHeight();

		if (origin_w > MAX) {

			double ratio = (MAX / origin_w);

			int thumb_w = (int) (origin_w * ratio);
			int thumb_h = (int) (origin_h * ratio);

			BufferedImage destImg = Scalr.resize(srcImg, thumb_w, thumb_h);

			String thumbFileNm = "t_" + fileNm;
			File thumbFile = new File(basePath + "/" + thumbFileNm);

			ImageIO.write(destImg, extension, thumbFile);

		} else {
			
			BufferedImage destImg = Scalr.resize(srcImg, (int)origin_w, (int)origin_h);

			String thumbFileNm = "t_" + fileNm;
			File thumbFile = new File(basePath + "/" + thumbFileNm);

			ImageIO.write(destImg, extension, thumbFile);
		}
	}

}
