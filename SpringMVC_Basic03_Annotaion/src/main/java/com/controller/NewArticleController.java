package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.NewArticleCommand;
import com.service.ArticleService;

/*
 -클라이언트 요청
 1. 화면요청 (글쓰기, 로그인하기) : write.do
 2. 처리하기 (글쓰기 입력처리, 로그인 완료처리 ) : writeok.do
 
 요청주소가 : write.do >> 화면
 요청주소가 : writeok.do >> 처리
 
 클라이언트 요청 주소 1개를 가지고 나누어서 
 요청 주소 하나로 (화면, 처리) 판단 >> 근거 >> GET , POST
 >> http://localhost:8090/SpringMVC/article/newArticle.do
 
 전송방식이 
 1. GET >> 화면 >> view 제공
 2. POST >> 처리 >> insert, update 처리
 
 */



@Controller
@RequestMapping("/article/newArticle.do")
public class NewArticleController {
	
	private ArticleService articleservice;
	
	@Autowired
	public void setArticleservice(ArticleService articleservice) {
		this.articleservice = articleservice;
	}

	@RequestMapping(method=RequestMethod.GET) // 화면 제공
	public String form() { // 함수의 return type String view 주소라고 하자.
		
		return "article/newArticleForm";
		//  /WEB-INF/views/ + article/newArticleForm + .jsp
	}
	
	// 데이터를 받는 가장 전통적인 방법 >>  submit(HttpServletRequest request) >> 코드량 >> Spring 에서는 선호하지 않음
	/*
	@RequestMapping(method=RequestMethod.POST) // insert 처리
	public ModelAndView submit(HttpServletRequest request) {
		
		NewArticleCommand article = new NewArticleCommand();
		article.setParentId(Integer.parseInt(request.getParameter("parentId")));
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		
		//NewArticleController 는 service가 필요해
		this.articleservice.writeArticle(article);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("newArticleCommand", article);
		mav.setViewName("article/newArticleSubmitted");
		
		return mav;
	}
	*/
	
	// 2. Spring 에서 parameter를 DTO 객체로 받기
	//  2.1 자동화 >> 전제조건 >> input name="" 값이 DTO 객체의 member field 명과 동일 해야한다.
	/*
	@RequestMapping(method=RequestMethod.POST) // insert 처리
	public ModelAndView submit(NewArticleCommand command) {
		// 1. 자동 DTO 객체가 생성 : NewArticleCommand article = new NewArticleCommand();
		// 2. 넘어온 parameter 값이 setter 통해서 자동 주입
		// 3. NewAticleComman 객체가 IOC 컨테이너 안에 자동 생성 >> id 값이 자동 >> id="newArtilceCommand"

		//NewArticleController 는 service가 필요해
		this.articleservice.writeArticle(command);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("newArticleCommand", command);
		mav.setViewName("article/newArticleSubmitted");
		
		return mav;
	}
	*/
	
	@RequestMapping(method=RequestMethod.POST) // insert 처리
	public String submit(@ModelAttribute("Ariticledata") NewArticleCommand command) {
		// 1. 자동 DTO 객체가 생성 : NewArticleCommand article = new NewArticleCommand();
		// 2. 넘어온 parameter 값이 setter 통해서 자동 주입
		// 3. NewAticleComman 객체가 IOC 컨테이너 안에 자동 생성 >> id 값이 자동 >> id="newArtilceCommand"

		//NewArticleController 는 service가 필요해
		this.articleservice.writeArticle(command);
		// 처리완료
		// view 페이지가 데이터를 어찌 받느냐 ...
		
		// 자동 forward >> view에서 >> 객체이름을 뭐로 받느냐  >> newArtilceCommand (key값) >> forward
		
		// forward 되는 key 이름을 내가 정의 >> mav.addObject("newArticleCommand", command);
		
		return "article/newArticleSubmitted";
	}
}














