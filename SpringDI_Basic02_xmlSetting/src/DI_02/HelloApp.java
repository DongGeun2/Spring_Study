package DI_02;

public class HelloApp {

	public static void main(String[] args) {
		
		/*
		// 영문
		MessageBean_en messageBean_en = new MessageBean_en();
		messageBean_en.sayHello("hong");
		
		// 한글
		
		MessageBean_kr messageBean_kr = new MessageBean_kr();
		messageBean_kr.sayHello("홍");
		*/
		
		
		// 영문
		MessageBean messageBean_en = new MessageBean_en();
		messageBean_en.sayHello("hong");
		
		// 한글
		
		MessageBean messageBean_kr = new MessageBean_kr();
		messageBean_kr.sayHello("홍");
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