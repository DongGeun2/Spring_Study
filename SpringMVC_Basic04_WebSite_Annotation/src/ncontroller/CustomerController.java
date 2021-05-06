package ncontroller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	
	private NoticeDao noticedao;
	
	@Autowired
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
		
	}
	
	/*
	 1. method 안에서 return type [String] 리턴값이 뷰의 주소
	 
	 2. public ModelAndView notices ... > ModelAndView 객체 생성 > 데이터, 뷰 설정 > return 
	 
	 3. public String notices(Model model){ 함수가 실행시 내부적으로 Model객체의 주소가 들어옴  }
	 
	 */
	
	@RequestMapping("notice.htm")
	public String Notice(@RequestParam(value="pg",defaultValue = "1") String _page,
						@RequestParam(value="f", defaultValue = "TITLE") String _field,
						@RequestParam(value="p", defaultValue = "%%") String _query,
						Model model) throws NumberFormatException, ClassNotFoundException, SQLException {
		
	//DAO 작업
	List<Notice> list = noticedao.getNotices(Integer.parseInt(_page), _field, _query);
	
	/*
	ModelAndView mav = new ModelAndView();
	mav.addObject("list", list);
	mav.setViewName("notice.jsp");
	*/
	model.addAttribute("list", list); // 자동으로 notice.jsp  forward 
	
	// <c:forEach items="${list}" var="n"> 
	
	
	return "notice.jsp";
	}
	
	
	@RequestMapping("noticeDetail.htm")
	public String NoticeDetail(String seq, Model model) throws ClassNotFoundException, SQLException {
		
		Notice notice = noticedao.getNotice(seq);
		
		/*
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("notice", notice);
		mav.setViewName("noticeDetail.jsp");
		*/
		
		model.addAttribute("notice", notice);
		
		return "noticeDetail.jsp";
	}
	
	
	// 글쓰기 화면 (get)
	@RequestMapping(value="noticeReg.htm",method=RequestMethod.GET)
	public String noticeReg() {
		return "noticeReg.jsp";
	}
	
	// 글쓰기 처리 (POST)
	@RequestMapping(value="noticeReg.htm",method=RequestMethod.POST)
	public String noticeReg(Notice n) {
		System.out.println(n.toString());
		return null;
	}
	
	// 글 수정하기(화면)
	
	
	// 글 수정(처리)
	
}
