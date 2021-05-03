package DI_08_Spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		
		
		/*
		// java 코드
		JobExecute jobexecute = new JobExecute("hong", 100);
		
		// JobExecute jobexecute2 = new JobExecute("hong", 100L);
		
		// JobExecute jobexecute3 = new JobExecute("hong", "KIM");
		
		ArticleDao articledao = new ArticleDao();
		
		jobexecute.setArticledao(articledao);
		
		jobexecute.setData(100);
		*/
		
		ApplicationContext context = new GenericXmlApplicationContext("DI_08_Spring/DI_08.xml");
		
		
	}

}


