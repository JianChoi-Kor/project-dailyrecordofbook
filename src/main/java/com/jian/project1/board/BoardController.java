package com.jian.project1.board;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jian.project1.Const;
import com.jian.project1.SecurityUtils;
import com.jian.project1.model.BoardDomain;
import com.jian.project1.model.BoardEntity;
import com.jian.project1.model.BoardPagingVO;
import com.jian.project1.model.LikeEntity;

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
		return service.uploadImg(img);
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
		return "redirect:/board/detail?category=" + p.getCategory() + "&boardPk=" + p.getBoardPk();
	}


	
	// 디테일
	@GetMapping("/detail")
	public void detail(BoardDomain p, Model model) {
		model.addAttribute(Const.KEY_DATE, service.selBoard(p));
	}

	

	
	
	// 리스트
	@GetMapping("/list")
	public void list(BoardPagingVO vo, LikeEntity p, Model model) {
		System.out.println(vo.getCategory());
		System.out.println(vo.getCurPage());
		// 카테고리
		int category = vo.getCategory();
		// 총 게시글 갯수
		int totalCountOfItem = service.selTotalCountOfItem(vo);
		// 한 페이지당 게시글 갯수
		int itemCountPerPage = 6;
		// 블럭당 페이지 갯수
		int pageCountPerBlock = 5;
		// 현재 페이지
		int curPage = vo.getCurPage();

		if (curPage == 0) {
			curPage = 1;
		}

		BoardPagingVO calcVo = new BoardPagingVO(category, totalCountOfItem, curPage, itemCountPerPage,
				pageCountPerBlock);
		System.out.println(calcVo.toString());

		model.addAttribute("paging", calcVo);
		model.addAttribute(Const.KEY_LIST, service.selBoardList(calcVo));
		
		
		
	}

	
	
	
	
	// 독서 모임 리스트
	@GetMapping("/community")
	public void community(BoardPagingVO vo, Model model) {
		System.out.println(vo.getCategory());
		System.out.println(vo.getCurPage());
		// 카테고리
		int category = vo.getCategory();
		// 총 게시글 갯수
		int totalCountOfItem = service.selTotalCountOfItem(vo);
		// 한 페이지당 게시글 갯수
		int itemCountPerPage = 4;
		// 블럭당 페이지 갯수
		int pageCountPerBlock = 5;
		// 현재 페이지
		int curPage = vo.getCurPage();

		if (curPage == 0) {
			curPage = 1;
		}

		BoardPagingVO calcVo = new BoardPagingVO(category, totalCountOfItem, curPage, itemCountPerPage,
				pageCountPerBlock);
		System.out.println(calcVo.toString());

		model.addAttribute("paging", calcVo);
		model.addAttribute(Const.KEY_LIST, service.selBoardList(calcVo));
	}

	// 글 삭제
	@ResponseBody
	@DeleteMapping("/del/{boardPk}")
	public int del(BoardEntity p, HttpSession hs) {
		p.setUserPk(sUtils.getLoginUserPk(hs));

		System.out.println("delBoardPk : " + p.getBoardPk());
		return service.delBoard(p);
	}

	// 글 수정
	@GetMapping("/mod")
	public String mod(BoardDomain p, Model model) {
		model.addAttribute(Const.KEY_DATE, service.selBoard(p));
		return "board/write";
	}

	@PostMapping("/mod")
	public String mod(BoardEntity p, HttpSession hs) {
		p.setUserPk(sUtils.getLoginUserPk(hs));
		int result = service.updBoard(p);
		return "redirect:/board/detail?category=" + p.getCategory() + "&boardPk=" + p.getBoardPk();
	}

	// 진행중인 모임 마감으로 변경
	@ResponseBody
	@PutMapping("/close/{boardPk}")
	public int close(BoardEntity p, HttpSession hs) {
		p.setUserPk(sUtils.getLoginUserPk(hs));
		return service.closeBoard(p);
	}

	/*
	 * 
	 * // 파일 업로드를 실행해볼까?
	 * 
	 * @RequestMapping(value="/imgUpload", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public String boardImageUpload(HttpServletRequest request,
	 * HttpServletResponse response, @RequestParam MultipartFile upload) {
	 * 
	 * OutputStream out = null; response.setCharacterEncoding("utf-8");
	 * response.setContentType("text/html;charset=utf-8");
	 * 
	 * String callback = request.getParameter("CKEditorFuncNum"); String fileName =
	 * "";
	 * 
	 * try { fileName = upload.getOriginalFilename(); byte[] bytes =
	 * upload.getBytes();
	 * 
	 * String drv = request.getRealPath("");
	 * 
	 * drv = drv.substring(0, drv.length()) + "./resources" +
	 * request.getContextPath() + "/upload/notices/";
	 * 
	 * File desti = new File(drv); if(!desti.exists()) { desti.mkdirs(); }
	 * 
	 * String inDate = new java.text.SimpleDateFormat("yyyyMMdd").format(new
	 * java.util.Date()); String inTime = new
	 * java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
	 * 
	 * fileName = inDate + inTime + fileName;
	 * 
	 * out = new FileOutputStream(new File(drv+fileName)); out.write(bytes);
	 * 
	 * 
	 * } catch(IOException e) { e.printStackTrace(); } finally { return
	 * "{\"uploaded\":1, \"url\":\"" +
	 * "http://localhost:8080/base/resources"+request.getContextPath()+
	 * "/upload/notices/"+fileName + "\"}"; } }
	 * 
	 */
}
