package DI_06_Spring;

public class ArticleService {
	
	// 클라이언트 요청에 따라서
	// DAO 객체 생성... 함수를 호출
	
	// 글쓰기, 목록보기 ...
	
	// ArticleService 는 ArticleDao 가 필요해 (의존 )
	private ArticleDao articledao;
	public ArticleService(ArticleDao articledao) {
		this.articledao = articledao;
		System.out.println("ArticleService 생성자 호출");
	}
	
	// 글쓰기 서비스
	public void write(Article article) {
		this.articledao.insert(article);
		
	}
}
