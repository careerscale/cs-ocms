package in.careerscale.apps.ocms.service;

import java.util.Calendar;

import in.careerscale.apps.ocms.config.PersistenceConfig;
import in.careerscale.apps.ocms.config.RootConfig;
import in.careerscale.apps.ocms.dao.UserRepository;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.mail.EmailSender;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.registration.model.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.subethamail.wiser.Wiser;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=RootConfig.class,loader=AnnotationConfigContextLoader.class)
@ContextConfiguration(classes={ RootConfig.class, PersistenceConfig.class})
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class UserServiceTest {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailSender emailService;
	
	Wiser wiser = new Wiser();

	@Before
	public void setup(){
	
		wiser.setPort(2500); // Default is 25
		wiser.start();
		wiser.accept("unittest", "unittest");
		
		//emailService = new EmailSender("localhost", 2500, 2500, "unittest", "unittest", "unittest","false");
		//userService.setEmailService(emailService);
		
	}
	
	@After
	public void tearDown(){
		wiser.stop();
		
	}
	
	

	/*@Test
	public void shouldInitializeWithOneDemoUser() {
		// act
		//userService.initialize();
		// assert
		//verify(userRepositoryMock, times(2)).save(any(LoginMaster.class));
	}
	*/
	
	
	@Test
	public void testRegister_normal() throws ApplicationException{
		
		User user = new User("testemail", "testfirstname", "testLastName", Calendar.getInstance().getTime());
		userService.registerUser(user);
		LoginMaster loginMaster = userRepository.findByUsername("testemail");
		Assert.assertNotNull(loginMaster);
		
		
	}
	
	@Test
	public void testRegister_nullUserName() throws ApplicationException{
		
		User user = new User(null, "testfirstname", "testLastName", Calendar.getInstance().getTime());
		userService.registerUser(user);
		//TODO I think the userId should be must, no check is done so far. 
		
		LoginMaster loginMaster = userRepository.findByUsername("testemail");
		Assert.assertNull(loginMaster);
		
		
	}
	

	@Test(expected=ApplicationException.class)
	public void testRegister_nullFirstName() throws ApplicationException{		
		User user = new User("unittest", null, "testLastName", Calendar.getInstance().getTime());
		userService.registerUser(user);
	}
	
	@Test(expected=ApplicationException.class)
	public void testRegister_duplicateUserName() throws ApplicationException{		
		User user = new User("unittest", "testFirstName", "testLastName", Calendar.getInstance().getTime());
		userService.registerUser(user);
		userService.registerUser(user);
		//2nd registration with same user should fail causing ApplicationException
	}
	

	
	
}