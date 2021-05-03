package AOP_Basic_02_JAVA;

import java.lang.reflect.Proxy;

public class Program {
	public static void main(String[] args) {
		// 1. 실 객체의 주소
		Calc calc = new NewCalc();
		
		// 2. 가짜를 생성
		Calc cal = (Calc)Proxy.newProxyInstance(calc.getClass().getClassLoader(),  // 실객체의 메타정보
							   calc.getClass().getInterfaces(),   // 실객체의 행위정보
							   new LogPrintHandler(calc));        // 보조객체의 정보
		int result = cal.ADD(555, 555);
		System.out.println("result : " + result);
		
		result = cal.MUL(555, 555);
		System.out.println("result : " + result);
		
		
		result = cal.SUB(655, 555);
		System.out.println("result : " + result);
	}
}
