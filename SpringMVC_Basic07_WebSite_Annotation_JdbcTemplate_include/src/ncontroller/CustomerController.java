package ncontroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.NewNoticeDao;
import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {
	
	
	private NewNoticeDao noticedao;
	
	@Autowired
	public void setNoticedao(NewNoticeDao noticedao) {
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
	
	
	return "customer/notice";
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
		
		return "customer/noticeDetail";
	}
	
	// 5.x.x 버전에서
	//@Getmapping
	//@Postmapping
	
	
	
	// 글쓰기 화면 (get)
	@RequestMapping(value="noticeReg.htm",method=RequestMethod.GET)
	public String noticeReg() {
		return "customer/noticeReg";
	}
	
	// 글쓰기 처리 (POST)
	@RequestMapping(value="noticeReg.htm",method=RequestMethod.POST)
	public String noticeReg(Notice n, HttpServletRequest request) {
		
		// Notice >> DTO
		// private List<CommonsMultipartFile> files
		
		// files[0]  >> 1.jpg
		// files[1]  >> 2.jpg
		
		
		List<CommonsMultipartFile> files = n.getFiles();
		List<String> filenames = new ArrayList<String>(); // 파일명 관리
		
		if(files != null && files.size() > 0) { // 1개라도 업도드된 파일이 존재하면
			for(CommonsMultipartFile mutifile : files) {
				String filename = mutifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload"); // 배포된 서버 경로
				String fpath = path + "/" + filename;
				System.out.println(path);
				
				if(!filename.equals("")) { // 실 파일 업로드
					FileOutputStream fs = null;
					try {
						fs = new FileOutputStream(fpath);
						fs.write(mutifile.getBytes());
						
						filenames.add(filename); // db에 입력될 파일명
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						try {
							fs.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		
		n.setFileSrc(filenames.get(0));
		n.setFileSrc2(filenames.get(1));
		
		try {
			noticedao.insert(n); // DB insert
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// insert 나 update 하고 나면 ... ( f5 누르면 계속 글이 write )
		// 리스트 ( location.href or redirect )
		// 서버에게 새로운 요청 ... 목록보기
		// Spring : redirect:notice.htm
		// Servlet , jsp : location.href or response.sendRedirect
		
		
		return "redirect:notice.htm";
	}
	
	// 글 수정하기(화면)
	@RequestMapping(value="noticeEdit.htm",method=RequestMethod.GET)
	public String noticeEdit(String seq, Model model) throws ClassNotFoundException, SQLException {
		// noticedao.getNotice
		
		Notice notice;
		
		notice = noticedao.getNotice(seq);
		
		model.addAttribute("notice", notice);
		
		return "customer/noticeEdit";
	}
	
	// 글 수정(처리)
	@RequestMapping(value="noticeEdit.htm",method=RequestMethod.POST)
	public String noticeEdit(Notice n, HttpServletRequest request) {
		
		List<CommonsMultipartFile> files = n.getFiles();
		List<String> filenames = new ArrayList<String>(); // 파일명 관리
		
		if(files != null && files.size() > 0) { // 1개라도 업도드된 파일이 존재하면
			for(CommonsMultipartFile mutifile : files) {
				String filename = mutifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload"); // 배포된 서버 경로
				String fpath = path + "/" + filename;
				System.out.println(path);
				
				if(!filename.equals("")) { // 실 파일 업로드
					FileOutputStream fs = null;
					try {
						fs = new FileOutputStream(fpath);
						fs.write(mutifile.getBytes());
						
						filenames.add(filename); // db에 입력될 파일명
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						try {
							fs.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		
		n.setFileSrc(filenames.get(0));
		n.setFileSrc2(filenames.get(1));
		
		try {
			noticedao.update(n); // DB insert
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// insert 나 update 하고 나면 ... ( f5 누르면 계속 글이 write )
		// 리스트 ( location.href or redirect )
		// 서버에게 새로운 요청 ... 목록보기
		// Spring : redirect:notice.htm
		// Servlet , jsp : location.href or response.sendRedirect
		
		
		return "redirect:noticeDetail.htm?seq=" + n.getSeq();
	}
	
	@RequestMapping("noticeDel.htm")
	public String noticeDel(String seq) throws ClassNotFoundException, SQLException {
		noticedao.delete(seq);
		
		return "redirect:notice.htm";
	}
	
}
