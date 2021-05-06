package kr.or.bit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class IntroController implements Controller{

	public IntroController() {
		System.out.println("IntroController 객체 생성");
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest requset, HttpServletResponse response) throws Exception {
		System.out.println("HelloController 요청 실행: handleRequest 함수 실행");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("name","hong"); // requset.setAttribute("name","bituser")
		mav.setViewName("intro");
		
		
		return mav;
	}

}
