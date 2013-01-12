package in.careerscale.training.spring.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "in.careerscale.training.spring.annotation","in.careerscale.training.spring.annotation.manager","in.careerscale.training.spring.annotation.dao" })
public class SpringConfiguration {

}
