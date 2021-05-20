package ncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.MemberDao;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
	//의존
	private MemberDao memberdao;
	
	//주입받기
	@Autowired
	public void setMemberdao(MemberDao memberdao) {
		this.memberdao = memberdao;
	}
	
	//회원가입 페이지(GET)
	@RequestMapping(value="join.htm", method=RequestMethod.GET)
	public String join() {
		return "joinus/join";
	}
	
	//회원가입 처리(POST)
	@RequestMapping(value="join.htm", method=RequestMethod.POST)
	public String join(Member member) {
		System.out.println(member.toString());
		//Member [userid=1, pwd=2, name=3, gender=여성, birth=, isLunar=Solar, cphone=null, email=8, habit=on, regDate=null]
		
		try {
			memberdao.insert(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// '/'를 붙이면 root로 빠진다 
		
		// index.htm
		// ㄴ> http://localhost:8090/SpringMVC_Basic06_WebSite_Annotation_JdbcTemplete/joinus/index.htm 이 된다.
		
		// /index.htm
		// ㄴ> http://localhost:8090/SpringMVC_Basic06_WebSite_Annotation_JdbcTemplete/index.htm 이 된다
		// 즉, 앞에 원래 붙어있던 폴더들 무시!
		return "redirect:/index.htm";
	}
	
	//로그인 요청 (로그인 처리는 Spring이 한다)
	@RequestMapping(value="login.htm", method=RequestMethod.GET)
	public String login() {
		return "joinus/login";
	}
}
