package com.mycompany.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.dto.Ch07Board;
import com.mycompany.webapp.dto.Ch07City;
import com.mycompany.webapp.dto.Ch07Cloth;
import com.mycompany.webapp.dto.Ch07Member;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch07")
@Log4j2
public class Ch07Controller {
	private int count=0;
	
	@RequestMapping("/content")
	public String content(HttpServletRequest request) {
		log.info("실행");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(date);
		
		request.setAttribute("strDate", strDate); //키이름, 값
		
		return "ch07/content";
	}
	
	@RequestMapping("/requestScopeSave")
	public String requestScopeSave(HttpServletRequest request) {
		log.info("실행");
		//request 범위에 객체 저장
		request.setAttribute("requestScopeValue", "홍길동");
		
		//멤버 객체 생성
		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");
		
		Ch07City city = new Ch07City();
		city.setName("서울");
		member.setCity(city);
		request.setAttribute("member", member);
		
		return "ch07/content";
	}
	
	@RequestMapping("/sessionScopeSave")
	public String sessionScopeSave(HttpSession session) {
		log.info("실행");
		//session 범위에 객체 저장
		session.setAttribute("sessionScopeValue", "감자바");
		
		//멤버 객체 생성
		Ch07Member member = new Ch07Member();
		member.setName("감자바");
		member.setAge(27);
		member.setJob("개발자");
		
		Ch07City city = new Ch07City();
		city.setName("제주");
		member.setCity(city);
		session.setAttribute("member2", member); //"member"라는 키로 member 객체 값을 저장
		
		return "ch07/content";
	}
	
	@RequestMapping("/applicationScopeSave")
	public String applicationScopeSave(HttpServletRequest request) {
		log.info("실행");
		//application 범위에 객체 저장
		ServletContext application = request.getServletContext();	//request를 통해 servletContext를 얻어내어 application 사용
		application.setAttribute("applicationScopeValue", "한여름");
		
		//객체 생성 후 application 범위에 객체 저장
		Integer counter = ++count;
		application.setAttribute("counter", counter);
		
		return "ch07/content";
	}
	
	@GetMapping("/useJstl1")
	public String useJstl1(HttpServletRequest request) {
		String[] languages = {"Java", "JavaScript", "SpringFramework", "Vue"};
		request.setAttribute("langs", languages); //배열을 request범위에 저장
		return "ch07/useJstl1";
	}
	
	@GetMapping("/useJstl2")
	public String useJstl2(HttpServletRequest request) {
		List<Ch07Board> list = new ArrayList<>();
		for(int i=0; i<=5; i++) {
			Ch07Board board = new Ch07Board(i, "제목"+i, "내용"+i, "글쓴이"+i, new Date());
			list.add(board);
		}
		request.setAttribute("boardlist", list);
		return "ch07/useJstl2";
	}
	
	@GetMapping("/modelAndViewReturn")
	public ModelAndView modelAndViewReturn() {
		Ch07Board board = new Ch07Board(1, "제목1", "내용1", "글쓴이1", new Date());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("board",  board);
		modelAndView.setViewName("ch07/boardDetail");
		return modelAndView;
	}
	
	@GetMapping("/modelArgument") //argument 매개변수
	public String modelArgument(Model model) {
		Ch07Board board = new Ch07Board(1, "제목1", "내용1", "글쓴이1", new Date());
		model.addAttribute("board", board); //request.setAttribute("boardlist", list);같은 방식
		return "ch07/boardDetail";
	}
	
	@GetMapping("/modelAttribute") //argument 매개변수
	public String modelAttribute(@ModelAttribute("kind") String kind, @ModelAttribute("sex") String sex) { //@ModelAttribute를 붙이지 않으면 아직 request범위에 저장되지 않아!
		//@ModelAttribute를 붙이면 자동으로 request범위에 저장된다. kind가 키값이 되고 매개변수로 실제 넘어오는 값이 value가 된다!!
		return "ch07/clothInfo";
	}
	
	/*	@GetMapping("/modelAttribute2") //argument 매개변수
		public String modelAttribute2(String kind, String sex, Model model) {
			model.addAttribute("kind", kind);
			model.addAttribute("sex", sex);
			return "ch07/clothInfo";
		}*/
	
	@GetMapping("/commandOject") //argument 매개변수
	public String commandObject(@ModelAttribute("cloth") Ch07Cloth cloth) {	//개별로 받은 것들은 return값으로 전달되지 않는데 dto, 즉 command로 받게 되면 자동으로 전달된다.
		//클래스의 첫자를 소문자로 한 이름으로 request 범위에 저장된다.
		
		return "ch07/clothInfo";
	}
}
