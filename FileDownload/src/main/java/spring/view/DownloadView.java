package spring.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component("download")
public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
        File file = (File) model.get("downloadFile");
        // 모델 데이터로 보낸 파일을 get
        // jsp에서는 EL로 꺼낼 수 있었지만 여기는 jsp페이지가아니므로 .get으로꺼내야함을 기억\
        System.out.println(file);
        
        response.setContentType(getContentType()); 
        response.setContentLength((int) file.length());
        
        String userAgent = request.getHeader("User-Agent");
        
		// MSIE라는 문자가있다면 1 , 없다면 -1이 뜬다. 
        // MSIE는 마이크로소프트 익스플로러에만 포함되어 있는 코드글자이다
        // 1이면 익스플로러 , 그외엔 익스플로러가 아님을 뜻함.
        
        boolean ie = userAgent.indexOf("MSIE") > -1;
        String fileName = null;
        
        if(ie) {
        	fileName = URLEncoder.encode(file.getName(),"UTF-8");
        }else {
        	fileName = new String(file.getName().getBytes("UTF-8"), "iso-8859-1");
        }
      
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        // 이 부분은 파일을 읽어들여서 서버에 저장하고 서버에 저장된걸 클라이언트가 읽기위한
        // 일련의 과정들을 FileCopyUtils 라는 추상메서드로 압축시켰다.
        OutputStream out = response.getOutputStream();
        FileInputStream fis = null;
        
        try {
        	fis = new FileInputStream(file);
            FileCopyUtils.copy(fis,out);
        } finally {
        	if(fis!=null) {
        		fis.close();
        	}
        }
        
        out.flush();  // 버퍼에 남은 데이터를 전부 출력한다.
	}

}
