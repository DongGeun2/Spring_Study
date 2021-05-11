package ncontroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.multi.MultiFileChooserUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {

	private NoticeDao noticedao;
	
	@Autowired
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}

	/*
	1.   method안에서 return type  [String] 리턴값이 뷰의 주소
	
	2.  public ModelAndView notices ...    >  ModelAndView 객체 생성  > 데이터 , 뷰 설정 > return 
	
	3. public String notices (Model model) { 함수가 실행시 내부적으로 Model객체의 주소가 들어온다  }
	
	*/
	
	//public List<Notice> getNotices(int page, String field, String query) 
	@RequestMapping("notice.htm")   //   /customer/notice.htm
	public String notices(String pg , String f , String q , Model model) {
		
		//default 값 설정
		int page = 1;
		String field="TITLE";
		String query = "%%";
		
		if(pg != null   && ! pg.equals("")) {
			page  = Integer.parseInt(pg);
		}
		
		if(f != null   && ! f.equals("")) {
			field = f;
		}

		if(q != null   && ! q.equals("")) {
			query = q;
		}
		
		//DAO 작업
		List<Notice> list = null;
		try {
						list = noticedao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
					e.printStackTrace();
		} catch (SQLException e) {
					e.printStackTrace();
		}
		
		
		//Spring  적용
		/*
		ModelAndView   mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("notice.jsp");
		return mv;
		*/
		model.addAttribute("list", list);  //자동으로 notice.jsp forward 
		/*
		  	<c:forEach items="${list}" var="n"> ...
		*/
		//return  "notice.jsp";
		return "customer/notice";
		
		//     /WEB-INF/views/   + customer/notice  +  .jsp
	}
	
	//public Notice getNotice(String seq)
	@RequestMapping("noticeDetail.htm")
	public String noticesDetail(String seq  , Model model) {
	
		Notice notice = null;
		try {
					notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
						e.printStackTrace();
		} catch (SQLException e) {
						e.printStackTrace();
		}
		
		/*
		ModelAndView  mv = new ModelAndView();
		
		mv.addObject("notice", notice);
		mv.setViewName("noticeDetail.jsp");
		*/
		model.addAttribute("notice", notice);
		//return "noticeDetail.jsp";
		return "customer/noticeDetail";
	}
	
	//5.x.x 버전에서
	//@GetMapping
	//@PostMapping
	
	
	//글쓰기 화면 (GET)
	//http://localhost:8090/SpringMVC_Basic04_WebSite_Annotation/customer/notice.htm
	@RequestMapping(value="noticeReg.htm",  method = RequestMethod.GET)
	public String noticeReg() {
			//return  "noticeReg.jsp";
		   return "customer/noticeReg";
	}
	//글쓰기 처리(POST)
	@RequestMapping(value="noticeReg.htm",  method = RequestMethod.POST)
	public String noticeReg(Notice n , HttpServletRequest request) {
		  // System.out.println(n.toString());
		  // Notice  >> DTO
		  // private List<CommonsMultipartFile>   files
		
		 //files[0]     >>1.jpg
		 //files[1]     >>2.jpg
		
		 List<CommonsMultipartFile> files = n.getFiles();
		 List<String> filenames = new ArrayList<String>();  //파일명 관리
		
		if(files != null  && files.size() > 0) {  //1개라 업로드된 파일이 존재하면
				for(CommonsMultipartFile  mutifile  : files) {
					String filename =  mutifile.getOriginalFilename();
					String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로 
					String fpath = path + "\\" + filename;
					System.out.println(fpath);
					
					if(!filename.equals("")) {  //실 파일 업로드 (웹서버)
						FileOutputStream fs =null;
						try {
							     fs = new FileOutputStream(fpath);
							     fs.write(mutifile.getBytes());
			     
							     filenames.add(filename);  //db에 입력될 파일명
							     
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
		 
		 
		 
		 
			//파일명 (DTO)
			n.setFileSrc(filenames.get(0));
			n.setFileSrc2(filenames.get(1));
			try {
						noticedao.insert(n);  //DB insert
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			//insert 나 update 하고 나면 ...(F5 누르면 계속 글이 ..Write)
			//리스트 (location.href    or   redirect )
			//서버에게 새로운 요청 ...목록보기
			//String :   redirect:notice.htm   
			//Servlet , jsp  :   location.href  or   response.sendRedirect 
			
			
		  return "redirect:notice.htm";
	}
	
	//글수정하기 (화면) GET
	@RequestMapping(value="noticeEdit.htm"  , method = RequestMethod.GET)
	public String noticeEdit(String seq , Model model) {
		Notice notice=null;
		try {
		   notice =  	noticedao.getNotice(seq);
		} catch( Exception e) {
				e.printStackTrace();
		} 
		
		model.addAttribute("notice", notice);
		
		//return "noticeEdit.jsp";
		return "customer/noticeEdit";
	}
	//글수정 (처리) POST
	@RequestMapping(value="noticeEdit.htm"  , method = RequestMethod.POST)
	public String noticeEdit(Notice n , HttpServletRequest request) {
		 
		 List<CommonsMultipartFile> files = n.getFiles();
		 List<String> filenames = new ArrayList<String>();  //파일명 관리
		
		if(files != null  && files.size() > 0) {  //1개라 업로드된 파일이 존재하면
				for(CommonsMultipartFile  mutifile  : files) {
					String filename =  mutifile.getOriginalFilename();
					String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로 
					String fpath = path + "\\" + filename;
					System.out.println(fpath);
					
					if(!filename.equals("")) {  //실 파일 업로드 (웹서버)
						FileOutputStream fs =null;
						try {
							     fs = new FileOutputStream(fpath);
							     fs.write(mutifile.getBytes());
			     
							     filenames.add(filename);  //db에 입력될 파일명
							     
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
		 
		 
			//파일업로드 2개 한다는 전제
			//파일명 (DTO)
			n.setFileSrc(filenames.get(0));
			n.setFileSrc2(filenames.get(1));
			try {
						noticedao.update(n);  //DB insert
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		//처리가 끝나면 상세 페이지로 : redirect  글번호를 가지고 ....
		return "redirect:noticeDetail.htm?seq="+n.getSeq();    //서버에게 새 요청 ....
	}
	
	@RequestMapping("noticeDel.htm") // /customer/noticeDel.htm
	public String noticeDel(String seq){
			try {
				noticedao.delete(seq);
			} catch (Exception e) {
			}
		return "redirect:notice.htm";
	}
	
}
