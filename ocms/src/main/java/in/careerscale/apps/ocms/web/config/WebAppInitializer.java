package in.careerscale.apps.ocms.web.config;

import java.util.EnumSet;
import java.util.Set;

import javax.servlet.*;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("in.careerscale.apps.ocms.config");
		
		FilterRegistration.Dynamic securityFilter = servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"));
		securityFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
		
		servletContext.addListener(new ContextLoaderListener(context));
		servletContext.setInitParameter("defaultHtmlEscape", "true");
		
		DispatcherServlet servlet = new DispatcherServlet();
		// no explicit configuration reference here: everything is configured in the root container for simplicity
		servlet.setContextConfigLocation("");
		
		ServletRegistration.Dynamic appServlet = servletContext.addServlet("appServlet", servlet);
		appServlet.setLoadOnStartup(1);
		appServlet.setAsyncSupported(true);
		
		Set<String> mappingConflicts = appServlet.addMapping("/");
		if (!mappingConflicts.isEmpty()) {
			throw new IllegalStateException("'appServlet' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
		}
	}
}