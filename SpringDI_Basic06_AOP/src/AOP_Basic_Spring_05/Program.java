package AOP_Basic_Spring_05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Program {
	public static void main(String[] args) {
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:AOP_Basic_Spring_05/ApplicationContext.xml");
		
		Calc calc = context.getBean("proxy",Calc.class);
		
		int result = calc.ADD(555, 555);
		System.out.println("result : " + result);

		
	}
}
