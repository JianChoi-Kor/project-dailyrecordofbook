package com.jian.project1.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
public class BoardController {

		@Autowired
		private BoardService service;
	
	
		@GetMapping("/write")
		public void write() {
			
		}
		
		
		
		@ResponseBody
		@PostMapping("/imgUpload")
		public Map<String, Object> imgUpload(@RequestParam("upload") MultipartFile img) {
			
			System.out.println("img : " + img.getOriginalFilename());
			//return service.uploadImg(img);
			
			String testImg = service.uploadImg(img);
			
			System.out.println(testImg);
			
			Map<String, Object> json = new HashMap<String, Object> ();

			json.put("uploaded", testImg);

			return json;
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		
		// 파일 업로드를 실행해볼까?
		@RequestMapping(value="/imgUpload", method=RequestMethod.POST)
		@ResponseBody
		public String boardImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {
			
			OutputStream out = null;
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			String callback = request.getParameter("CKEditorFuncNum");
			String fileName = "";
			
			try {
				fileName = upload.getOriginalFilename();
				byte[] bytes = upload.getBytes();
				
				String drv = request.getRealPath("");
				
				drv = drv.substring(0, drv.length()) + "./resources" + request.getContextPath() + "/upload/notices/";
				
				File desti = new File(drv);
				if(!desti.exists()) {
					desti.mkdirs();
				}
				
				String inDate = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
				String inTime = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
				
				fileName = inDate + inTime + fileName;
				
				out = new FileOutputStream(new File(drv+fileName));
				out.write(bytes);
				
				
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				return "{\"uploaded\":1, \"url\":\"" + "http://localhost:8080/base/resources"+request.getContextPath()+"/upload/notices/"+fileName + "\"}";
			}
		}
		
		*/
}
