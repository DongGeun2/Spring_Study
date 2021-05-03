package DI_06_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		// java 코드
		
		// Oracle 사용 insert 실행 하세요
		//OracleArticleDao articledao = new OracleArticleDao();
		/*
		MySqlArticleDao articledao = new MySqlArticleDao();
		ArticleService articleservice = new ArticleService(articledao);
		
		Article article = new Article();
		*/
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_06_Spring/DI_06.xml");
		
		ArticleService articleservice = context.getBean("articleservice",ArticleService.class);
		
		Article article = context.getBean("article",Article.class);
		
		
		articleservice.write(article);
		
	}

}

