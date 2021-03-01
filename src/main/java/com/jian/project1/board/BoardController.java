package com.jian.project1.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jian.project1.Const;
import com.jian.project1.SecurityUtils;
import com.jian.project1.model.BoardDomain;
import com.jian.project1.model.BoardEntity;

@Controller
@RequestMapping("/board")
public class BoardController {

		@Autowired
		private BoardService service;
		
		@Autowired
		private SecurityUtils sUtils;
	
		// CKEditor 이미지 업로드 부분
		@ResponseBody
		@PostMapping("/imgUpload")
		public Map<String, Object> imgUpload(@RequestParam("upload") MultipartFile img) {
			String ckImg = service.uploadImg(img);
			System.out.println("test img : " + ckImg);
			
			Map<String, Object> json = new HashMap<String, Object> ();
			json.put("uploaded", ckImg);

			return json;
		}
		
		
		// 글쓰기
		@GetMapping("/write")
		public void write() {
			
		}
		

		@PostMapping("/write")
		public String write(BoardEntity p, HttpSession hs) {
			
			// userPk 값 set
			p.setUserPk(sUtils.getLoginUserPk(hs));
			
			service.insBoard(p);
			return "redirect:/board/detail?boardPk=" + p.getBoardPk();
		}
		
		
		
		//리스트
		@GetMapping("/list")
		public void list(BoardEntity p, Model model) {
			model.addAttribute(Const.KEY_LIST, service.selBoardList(p));
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
