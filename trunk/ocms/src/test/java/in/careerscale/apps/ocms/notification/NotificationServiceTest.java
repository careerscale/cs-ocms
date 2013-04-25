package in.careerscale.apps.ocms.notification;

import in.careerscale.apps.ocms.config.PersistenceConfig;
import in.careerscale.apps.ocms.config.RootConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=RootConfig.class,loader=AnnotationConfigContextLoader.class)
@ContextConfiguration(classes={ RootConfig.class, PersistenceConfig.class})
@WebAppConfiguration
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class NotificationServiceTest {

	@Autowired
	private NotificationService notificationService;
  //  private CaseService caseService;
	@Test
	public void test() {
		notificationService.createNotification();
	}

}
