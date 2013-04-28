package in.careerscale.apps.ocms.service;

import in.careerscale.apps.ocms.config.PersistenceConfig;
import in.careerscale.apps.ocms.config.RootConfig;
import in.careerscale.apps.ocms.service.exception.ApplicationException;
import in.careerscale.apps.ocms.web.cases.model.Case;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;


@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=RootConfig.class,loader=AnnotationConfigContextLoader.class)
@ContextConfiguration(classes={ RootConfig.class, PersistenceConfig.class})
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class CaseServiceTest {
	
	@Mock
	SecurityContext mockSecurityContext;   
	

	@Autowired
	private CaseService caseService;
	
	@Test
	public void registerCase() throws ApplicationException{
                
		ExtendedUser extendedUser = PowerMockito.mock(ExtendedUser.class);
		Authentication auth = PowerMockito.mock(Authentication.class);
		PowerMockito.mockStatic(SecurityContextHolder.class);
		PowerMockito.when(SecurityContextHolder.getContext()).thenReturn(mockSecurityContext);
		PowerMockito.when(mockSecurityContext.getAuthentication()).thenReturn(auth);
		PowerMockito.when(auth.getPrincipal()).thenReturn(extendedUser);
		
		Case caseBean = new Case("dummycase", "dummy description", "new paper", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(), "232323");
		caseService.registerCase(caseBean);
	
	}
	
	
	

}
