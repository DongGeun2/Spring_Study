package AOP_Basic_01;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

public class Calc {
/*
 	간단한 사칙 계산기 프로그램
	- 주관심 (업무) : 사칙연산 (ADD, MUL) -> 기능(함수)구현
	- 보고관심(공통관심) : 연산에 걸린 시간
	- log 출력 ( console 출력 red 한 글자로 )
	
	아래와 같은 함수가 100여 가지.. 시간이 지나서 .. 유지보수 .. 수정 .. 100개의 모두 수정
  
  */	
	
	public int Add(int x, int y) {
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머 시작]");
		
		// 주업무
		int result = x + y;
		
		sw.stop();
		log.info("[타이머 종료]");
		log.info("[Time log Method : Add]");
		log.info("[TIme log Method :" + sw.getTotalTimeMillis() + "]");
		
		return result;
	}
	
	
	public int Mul(int x, int y) {
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머 시작]");
		
		// 주업무
		int result = x * y;
		
		sw.stop();
		log.info("[타이머 종료]");
		log.info("[Time log Method : Mul]");
		log.info("[TIme log Method :" + sw.getTotalTimeMillis() + "]");
		
		
		
		return result;
	}
	
}
