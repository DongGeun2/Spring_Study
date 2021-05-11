package ncontroller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.MemberDao;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {

	private MemberDao memberdao;
	
	@Autowired
	public void setMemberdao(MemberDao memberdao) {
		this.memberdao = memberdao;
	}
	
	// 회원가입 페이지 ( GET )
	@RequestMapping(value="join.htm", method = RequestMethod.GET)
	public String join() {
		
		return "joinus/join";
	}
	
	// 회원가입처리 ( POST )
	@RequestMapping(value="join.htm", method = RequestMethod.POST)
	public String join(Member member) {
		System.out.println(member.toString());
		// Member [userid=null, pwd=2, name=3, gender=남성, birth=, isLunar=Solar, cphone=null, email=8, habit=on, regDate=null]
		
		try {
			memberdao.insert(member);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return "redirect:/index.htm";    //     /  = ( root 경로 ) 
	}
	
}
