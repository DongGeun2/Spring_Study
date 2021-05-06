package kr.or.bit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class HelloController implements Controller{

	public HelloController() {
		System.out.println("HelloController 객체 생성");
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest requset, HttpServletResponse response) throws Exception {
		System.out.println("HelloController 요청 실행: handleRequest 함수 실행");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("name","bituser"); // requset.setAttribute("name","bituser")
		mav.setViewName("hello");
		// InternalResourceViewResolver 에 의해서 view 단의 주소가 조합
		// /WEB-INF/views/  + hello + .jsp   >>  /WEB-INF/views/hello.jsp
		// mav.setViewName("/WEB-INF/views/hello.jsp");
		
		return mav;
	}

}
