package in.careerscale.apps.ocms.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Secured("ROLE_ANONYMOUS")
public class RegistrationController {
	
//	private Integer id;
	private String emailId;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	@RequestMapping(params="emailId")
	public String getEmailId() {
		return emailId;
	}

	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@RequestMapping(params="password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String index(Model model) {
		System.out.println("into the get method call");
		return "register/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model) {
		
		System.out.println(emailId);
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(dateOfBirth);
		//
		return "register/register"; //we need to return next page.
	}
	
}
