package DI_Annotation_04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration 자바파일은 >> DI_config.xml 과 동일한 효과
// xml 을 사용하지않고 자바파일을 xml 처럼 사용

@Configuration
public class ConfigContext {  // 객체를 생성하고 주입
	
	// xml >> <bean id="" class="DI_Annotation_04.User"> .... 
	// 함수를 통해서 객체를 리턴하도록 생성.
	
	@Bean
	public User user() {
		return new User();
	}
	
	@Bean
	public User2 user2() {
		return new User2();
	}
	
	
	
}
