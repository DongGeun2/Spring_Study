package AOP_Basic_Spring_06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;




public class Program {
	public static void main(String[] args) {
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:AOP_Basic_Spring_06/ApplicationContext.xml");
		
		Calc calc = context.getBean("proxy",Calc.class);
		
		int result = calc.SUB(555, 555);
		System.out.println("result : " + result);

		
	}
}
