package AOP_Basic_Spring_07;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.MethodBeforeAdvice;


public class LogPrintBeforeAdvice implements MethodBeforeAdvice{

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		Log log = LogFactory.getLog(this.getClass());
		log.info("[Before Advice Start]");
		log.info("method : " + method.getName());
		log.info("args : " + args.toString());
		log.info("target : " + target.toString());
		
		
	}

}
