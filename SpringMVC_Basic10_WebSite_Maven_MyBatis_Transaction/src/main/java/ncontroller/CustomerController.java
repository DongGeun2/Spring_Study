package ncontroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiFileChooserUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import service.CustomerService;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {

	// CustomerController 는 CustomerService 에 의존 합니다.

	private CustomerService customerService;

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 글 목록 조회
	@RequestMapping("notice.htm") // /customer/notice.htm
	public String notices(String pg, String f, String q, Model model) {

		List<Notice> list = customerService.notices(pg, f, q);

		model.addAttribute("list", list);

		return "customer/notice";
	}

	// 글 삭제 조회
	@RequestMapping("noticeDetail.htm")
	public String noticesDetail(String seq, Model model) {

		Notice notice = customerService.noticeDetail(seq);

		model.addAttribute("notice", notice);

		return "customer/noticeDetail";
	}

	// 글쓰기 화면 이동
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.GET)
	public String noticeReg() {

		return "customer/noticeReg";
	}

	// 글쓰기 처리(POST)
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.POST)
	public String noticeReg(Notice n, HttpServletRequest request) {

		String url = "redirect:notice.htm";
		
		
		try {

			url = customerService.noticeReg(n, request);

		} catch (Exception e) {

			e.printStackTrace();
		}
		
		// 예외가 발생하던 발생하지 않던 목록 페이지 새로고침 처
		return url;
	}

	// 글수정하기 (화면) GET
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.GET)
	public String noticeEdit(String seq, Model model) {
		Notice notice = null;

		try {

			notice = customerService.noticeEdit(seq);

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

		model.addAttribute("notice", notice);

		return "customer/noticeEdit";
	}

	// 글수정 (처리) POST
	@RequestMapping(value = "noticeEdit.htm", method = RequestMethod.POST)
	public String noticeEdit(Notice n, HttpServletRequest request) {

		String url = null;
		
		try {
			url = customerService.noticeEdit(n, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return url;
	}
	
	// 글 삭제하기
	@RequestMapping("noticeDel.htm") 
	public String noticeDel(String seq) {
		
		String url = null;
		try {
			url = customerService.noticeDel(seq);
			
		} catch (Exception e) {
			e.getMessage();
		}
		return url;
	}
	
	// 파일다운로드
	@RequestMapping("download.htm")
	public void download(String p, String f, HttpServletRequest request, HttpServletResponse response) throws IOException {

		customerService.download(p, f, request, response);
	}
	
}
