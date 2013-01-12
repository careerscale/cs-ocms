package in.careerscale.training.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		ApplicationContext context =new ClassPathXmlApplicationContext("spring.xml");
				
       Test test = (Test) context.getBean("test");
       test.sayHello();
	}

}
