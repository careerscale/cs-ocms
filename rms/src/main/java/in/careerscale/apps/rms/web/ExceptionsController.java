package in.careerscale.apps.rms.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class ExceptionsController
{

	@ExceptionHandler(Exception.class)
	  public String handleIOException(IOException ex, HttpServletRequest request) {
		// return ClassUtils.getShortName(ex.getClass());
		return "error/error";
	  }

}
