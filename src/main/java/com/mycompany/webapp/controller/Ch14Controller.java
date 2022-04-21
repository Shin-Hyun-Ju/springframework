package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dao.mybatis.Ch14BoardDao;
import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.Ch14BoardService;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14MemberService.JoinResult;
import com.mycompany.webapp.service.Ch14MemberService.LoginResult;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/ch14")
public class Ch14Controller {
	@Resource // 필드로 객체를 주입받음!!
	private Ch14MemberService memberService; // 주입 받고 싶음. <<어노테이션 사용

	@Resource
	private Ch14BoardDao boardDao;

	@RequestMapping("/content")
	public String content() {
		/*for(int i=1; i<=10000; i++) {
			Ch14Board board = new Ch14Board();
			
			board.setBtitle("제목"+i);
			board.setBcontent("내용"+i);
			board.setMid("user");
			boardDao.insert(board);
		}*/
		return "ch14/content";
	}

	@GetMapping("/join")
	public String joinForm() {
		return "ch14/joinForm";
	}

	@PostMapping("/join")
	// Dto는 view로 전달된다.
	// 1차 목표는 데이터를 받는 것이지만, 2차 목표는 양식을 세팅하고 view로 전달하는데 있다.
	public String join(Ch14Member member, Model model) {
		member.setMenabled(true); // 자동으로 오라클에는 1로 들어간다.
		member.setMrole("ROLE_USER");// spring security에서 설정

		// join 성공하면 success, 실패하면 fail 이런식으로 열거값 사용하고 싶음.
		JoinResult jr = memberService.join(member);
		// JoinResult jr = JoinResult.DUPLICATED;
		if (jr == JoinResult.SUCCESS) {
			return "redirect:/ch14/content";
		} else if (jr == JoinResult.DUPLICATED) { // 회원가입 실패
			model.addAttribute("error", "중복된 아이디가 있습니다.");
			return "ch14/joinForm";
		} else {
			model.addAttribute("error", "회원 가입 실패, 다시 시도해 주세요.");
			return "ch14/joinForm";
		}
	}

	@GetMapping("/login")
	public String loginForm() {
		return "ch14/loginForm";
	}

	// 요청 접수받고 데이터 잘 들어왔는지 아닌지 검사하는 역활
	// 비지니스 로직 -> db 에서 자료 가져오고 비교 하고 이런건 서비스에서 해야한다!!
	@PostMapping("/login")
	public String login(Ch14Member member, HttpSession session, Model model) {
		LoginResult result = memberService.login(member);
		if (result == LoginResult.SUCCESS) {
			session.setAttribute("sessionMid", member.getMid());
			return "redirect:/ch14/content";
		} else if (result == LoginResult.FAIL_MID) {
			model.addAttribute("error", "아이디가 존재하지 않습니다.");
			return "ch14/loginForm";
		} else {
			model.addAttribute("error", "패스워드가 틀립니다.");
			return "ch14/loginForm";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionMid");
		return "redirect:/ch14/content";
	}

	@Resource
	private Ch14BoardService boardService; // 관리객체가 되어야 주입 받을 수 있다.

	@GetMapping("/boardList")
	public String boardList(@RequestParam(defaultValue = "1") int pageNo, Model model) { // 꼭 넘어와야 하는 값이기 때문에 디폴트값 설정!!
		int totalBoardNum = boardService.getTotalBoardNum(); // 전체 개수 가져오기
		Pager pager = new Pager(5, 5, totalBoardNum, pageNo);
		model.addAttribute("pager", pager);

		List<Ch14Board> boards = boardService.getBoards(pager);
		model.addAttribute("boards", boards);
		return "ch14/boardList";
	}

	@GetMapping("/boardWriteForm")
	public String boardWRiterForm() {
		return "ch14/boardWriteForm";
	}

	@PostMapping("/boardWrite")
	public String boardWrite(Ch14Board board) throws IllegalStateException, IOException {
		if (!board.getBattach().isEmpty()) {// null이 아니라 빈공간
			board.setBattachoname(board.getBattach().getOriginalFilename());
			board.setBattachtype(board.getBattach().getContentType());
			board.setBattachsname(new Date().getTime() + "-" + board.getBattachoname());
			File file = new File("c:/Temp/uploadfiles/" + board.getBattachsname());
			board.getBattach().transferTo(file);

		}
		boardService.writeBoard(board);
		return "redirect:/ch14/content";
	}

	@GetMapping("/boardDetail")
	public String boardDetail(int bno, Model model) {
		Ch14Board board = boardService.getBoard(bno);
		model.addAttribute("board", board);
		return "ch14/boardDetail";
	}

	@GetMapping("/filedownload")
	public void filedownload(int bno, HttpServletResponse response, @RequestHeader("User-Agent") String userAgent) throws FileNotFoundException, IOException {
		//DB에서 bno에 대한 게시물 가져오기
		Ch14Board board = boardService.getBoard(bno);

		// DB에서 가져올 정보
		String contentType = board.getBattachtype(); // 다운로드 하려는 파일의 타입
		String originalFilename = board.getBattachoname(); // 다운로드 되는 파일 이름
		String saveFilename = board.getBattachsname(); // 저장하려는 파일

		// 응답 내용의 데이터 타입을 응답 헤더에 추가
		response.setContentType(contentType); // 어떤 데이터 타입인지 브라우저에게 알려주어야 한다.
		// response.setHeader("Content-Type", contentType); 이렇게 사용해도 됨

		// 다운로드할 파일명을 헤더에 추가
		if (userAgent.contains("Trident") || userAgent.contains("MSIE")) { // 윈도우 10이하 msie Trident i11
			// IE 브라우저일 경우
			originalFilename = URLEncoder.encode(originalFilename, "UTF-8");
		} else {
			// 크롬, 엣지, 사파리일 경우
			originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");

		// 파일 데이터를 응답 본문에 싣기
		File file = new File("C:/Temp/uploadfiles" + saveFilename);
		if (file.exists()) {
			FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
		}
	}
	
	@GetMapping("/boardUpdateForm")
	public String boardUpdateForm(int bno, Model model) {
		Ch14Board board = boardService.getBoard(bno);
		model.addAttribute("board",board);
		return "ch14/boardUpdateForm";
	}
	
	@PostMapping("/boardUpdate")
	public String boardUpdate(Ch14Board board) {
		//db의 내용을 가져와서 수정할때.
		/*Ch14Board dbBoard = boardService.getBoard(board.getBno());
		dbBoard.setBtitle(board.getBtitle());
		dbBoard.setBcontent(board.getBcontent());
		boardService.updateBoard(dbBoard);*/
		boardService.updateBoard(board);
		return "redirect:/ch14/boardDetail?bno="+board.getBno();
	}
	
	@GetMapping("/boardDelete")
	public String boardDelete(int bno) {
		boardService.removeBoard(bno);
		return "redirect:/ch14/boardList";
	}
}
