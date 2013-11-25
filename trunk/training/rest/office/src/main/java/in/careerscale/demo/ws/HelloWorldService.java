package in.careerscale.demo.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

//http://www.mkyong.com/webservices/jax-ws/deploy-jax-ws-web-services-on-tomcat/
//http://www.accordess.com/wpblog/working-with-jax-ws-web-services-part-1/
//https://jax-ws-commons.java.net/jaxws-maven-plugin/usage.html
//http://docs.oracle.com/javaee/6/tutorial/doc/bnayn.html
//http://docs.oracle.com/cd/E24329_01/web.1211/e24964/setenv.htm#i214460
//http://java.globinch.com/enterprise-java/web-services/soap-binding-document-rpc-style-web-services-difference/
//http://careerscale.in/2013/08/basics-of-web-services/
@WebService
public class HelloWorldService {
	
	@WebMethod
	public String sayHello(String name){
		return "Hello " + name;
	}

}
