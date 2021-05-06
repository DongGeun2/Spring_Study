package com.service;

import com.model.NewArticleCommand;

public class ArticleService {

   /*
    @Service
    public calss ArticleService
    
    servlet.xml상단에
   	<context:component-scan base-package="com.service">
    */
	
	
	
	public ArticleService() {
		System.out.println("ArticleService 생성자 호출");
		
	}
	
	
	public void writeArticle(NewArticleCommand command) {
		// DAO 있다고 가정
		// insert 실행 됐다고 가정
		System.out.println("글쓰기 작업 완료"  + command.toString());
		
	}
}
