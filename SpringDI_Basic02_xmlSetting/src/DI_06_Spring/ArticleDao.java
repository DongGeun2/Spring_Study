package DI_06_Spring;


// mysql, oracle 이 사용하는 동일한 (공통)추상 함수...
public interface ArticleDao {
	void insert(Article article);
}
