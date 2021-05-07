package AOP_Basic_Spring_05;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

public class LogPrintAfterAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object returnvalue, Method method, Object[] args, Object target) throws Throwable {
		Log log = LogFactory.getLog(this.getClass());
		log.info("[After Advice start]");
		log.info("[보조업무 : 주 함수가 실행되고 .. 그 다음 실행되는 보조 공통함수 : 결과를 받을 수 있다]");
		log.info("[return value ]" + returnvalue);
		log.info("[method]" + method.getName());
		log.info("[args]" + Arrays.toString(args));
		log.info("[target]" + target.toString());
	}

}
