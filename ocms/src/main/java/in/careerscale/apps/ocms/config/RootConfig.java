package in.careerscale.apps.ocms.config;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan(basePackages = { "in.careerscale.apps.ocms" })
public class RootConfig {
	
	@Bean
	public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		//ppc.setLocation(new ClassPathResource("/persistence.properties"));
		
		 Resource[] resources = new ClassPathResource[ ]
				   { new ClassPathResource( "persistence.properties" ), new ClassPathResource( "application.properties" ) };
		  ppc.setLocations( resources );
				  ppc.setIgnoreUnresolvablePlaceholders( true );
		return ppc;
	}
	
}