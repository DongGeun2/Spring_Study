package login.controller;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController implements ApplicationContextAware {
	private WebApplicationContext context = null;

	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.context = (WebApplicationContext)applicationContext;
	}

	
	@RequestMapping("file")
	public ModelAndView download() {
		String path = context.getServletContext().getRealPath("/WEB-INF/설명.txt");
		
		File downloadFile = new File(path);
		
		return new ModelAndView("download","downloadFile",downloadFile);
		
	}
	
	@RequestMapping("file2")
	public ModelAndView download2() {
		String path = context.getServletContext().getRealPath("/WEB-INF/서태희.txt");
		
		File downloadFile = new File(path);
		
		return new ModelAndView("download","downloadFile",downloadFile);
	}
	

}
