package AOP_Basic_Spring_03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Program {
	public static void main(String[] args) {
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:AOP_Basic_Spring_03/ApplicationContext.xml");
		
		Calc calc = context.getBean("proxy",Calc.class);
		
		int result = calc.ADD(555, 555);
		System.out.println("result : " + result);
		
		result = calc.MUL(555, 555);
		System.out.println("result : " + result);
		
		result = calc.SUB(655, 555);
		System.out.println("result : " + result);
		
	}
}
