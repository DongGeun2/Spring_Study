package DI_05_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		
		
//		Mybean mybean = new Mybean();
//		Mybean mybean2 = new Mybean("hong");
//		Mybean mybean3 = new Mybean();
//		System.out.println("mybean : " + mybean);
//		System.out.println("mybean2 : " + mybean2);
//		System.out.println("mybean3 : " + mybean3);
//		
//		
//		Singleton single = Singleton.getInstece();
//		Singleton single2 = Singleton.getInstece();
//		
//		System.out.println("single :" + single);
//		System.out.println("single2 :" + single2);

		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_05_Spring/DI_05.xml");
		// 스프링 컨테이너 (메모리) 가 구성되고 xml 파일을 read(파싱) 해서 객체 생성, 조립, 소멸을 담당
		// 컨테이너 안에서 필요한 객체를 얻어서 사용
		
		Mybean mybean = context.getBean("mybean",Mybean.class);
		Mybean mybean2 = context.getBean("mybean",Mybean.class);
		Mybean mybean3 = context.getBean("mybean",Mybean.class);
		Mybean mybean4 = context.getBean("mybean2",Mybean.class);
		// 질문
		// mybean  mybean2  mybean3 변수가 가지는 주소값을 같을 까요 다를까요 ???
		
		System.out.println(mybean);
		System.out.println(mybean2);
		System.out.println(mybean3);

		System.out.println("생성자 사용");
		System.out.println(mybean4);
		// getbean()
		// 1. return type Object (타입에 맞는 casting )
		// 2. getBean 호출시 새로운 객체를 만들지 않아요 ( new 를 하지 않는다 )
		// ** 스프링 컨테이너 안에 객체들의 타입은 : defalut singleton 
		// ** 예외적으로 getBean() 객체를 생성하게 할 수 있다 ( 거의 쓰지 않는다 )
		
		Singleton single = context.getBean("single",Singleton.class);
		Singleton single2 = context.getBean("single",Singleton.class);
		
		
		System.out.println(single + " / " + single2);
	}

}

