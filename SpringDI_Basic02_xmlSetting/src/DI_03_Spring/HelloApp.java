package DI_03_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		
		/*
		// 영문
		MessageBean_en messageBean_en = new MessageBean_en();
		messageBean_en.sayHello("hong");
		
		// 한글
		
		MessageBean_kr messageBean_kr = new MessageBean_kr();
		messageBean_kr.sayHello("홍");
		
		
		
		// 영문
		MessageBean messageBean_en = new MessageBean_en();
		messageBean_en.sayHello("hong");
		
		// 한글
		
		MessageBean messageBean_kr = new MessageBean_kr();
		messageBean_kr.sayHello("홍");
		
		spring 컨테이너 생성 > 생성된 컨테이너 객세 생성 조립 ( xml )
		컨테이너 생성방법 : 다양한 방법 
		
		ApplicationContext context = new ClassPathXmlApplicationContext("DIConfig.xml");
		
		*/
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_03_Spring/DI_03.xml");
		// Generic ... 형변환에 대한 이점이 발생항
		// 원칙 : (MessageBean)context.getBean("MessageBean")
		
		MessageBean message = context.getBean("MessageBean",MessageBean.class);
		message.sayHello("hong");
	}

}



/*
 
 요구사항
 1. 한글버전 (hong) : 안녕 hong
 2. 영문버전 (hong) : hello hong 

MessageBean_kr > 안녕 hong
MessageBean_en > hello hong 

>> 인터페이스 : MessageBean 설계 ( 다형성 )  

 */