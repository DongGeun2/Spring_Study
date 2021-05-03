package DI_04_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		/* java 코드
		MessageBeanImpl messagebean = new MessageBeanImpl("hong");
		messagebean.setGreeting("hello");
		*/
		
		
		// spring 코드
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_04_Spring/DI_04.xml");
		// Generic ... 형변환에 대한 이점이 발생항
		// 원칙 : (MessageBean)context.getBean("MessageBean")
		
		MessageBean bean = context.getBean("m1",MessageBean.class);
		bean.sayHello();
		
		
	}

}
