package in.careerscale.apps.ocms.service;

import static org.mockito.Mockito.when;
import in.careerscale.apps.ocms.config.PersistenceConfig;
import in.careerscale.apps.ocms.config.RootConfig;
import in.careerscale.apps.ocms.dao.CaseRepository;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.cases.model.Case;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.agent.PowerMockAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is to test case services but we are mocking the spring security
 * context since we are going to run the tests under isolation and not on
 * container.
 * http://powermock.googlecode.com/svn/tags/powermock-1.5/examples/spring
 * -mockito
 * /src/test/java/org/powermock/examples/spring/mockito/SpringExampleTest.java
 * 
 * @author Harinath
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes=RootConfig.class,loader=AnnotationConfigContextLoader.class)
@ContextConfiguration(classes = { RootConfig.class, PersistenceConfig.class })
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@PowerMockIgnore({ "javax.management.*", "javax.xml.parsers.*",
		"com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*",
		"org.slf4j.*" })
@PrepareForTest({SecurityContextHolder.class})
public class CaseServiceTest {

	static {
		PowerMockAgent.initializeIfNeeded();
	}

//	@Rule
//	public PowerMockRule rule = new PowerMockRule();

	
	SecurityContext mockSecurityContext;

	@Autowired
	private CaseService caseService;

	@Mock
	CaseRepository caseRepository;

	@Test
	public void registerCase() throws ApplicationException {

		caseRepository = PowerMockito.mock(CaseRepository.class);
		caseService.setCaseRepository(caseRepository);
	
		System.out.println("\n\n\n before registerCase  1");
		PowerMockito.mockStatic(SecurityContextHolder.class);
		mockSecurityContext = PowerMockito.mock(SecurityContext.class);
		ExtendedUser extendedUser = PowerMockito.mock(ExtendedUser.class);
		Authentication auth = PowerMockito.mock(Authentication.class);
		when(SecurityContextHolder.getContext()).thenReturn(mockSecurityContext);
		Mockito.when(SecurityContextHolder.getContext()).thenReturn(mockSecurityContext);
		Mockito.when(mockSecurityContext.getAuthentication()).thenReturn(auth);
		Mockito.when(auth.getPrincipal()).thenReturn(extendedUser);
		Mockito.when(extendedUser.getUsername()).thenReturn("hari");
		Mockito.when(extendedUser.getId()).thenReturn(1);
		PowerMockito.when(auth.getPrincipal()).thenReturn(extendedUser);
		// Mockito.when(caseRepository.registerCase());
		Case caseBean = new Case("dummycase", "dummy description", "new paper",
				Calendar.getInstance().getTime(), Calendar.getInstance()
						.getTime(), "232323");
		System.out.println("\n\n\n before registerCase");
		caseService.registerCase(caseBean);

	}

}
