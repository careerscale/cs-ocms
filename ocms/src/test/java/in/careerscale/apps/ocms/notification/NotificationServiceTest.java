package in.careerscale.apps.ocms.notification;

import in.careerscale.apps.ocms.config.PersistenceConfig;
import in.careerscale.apps.ocms.config.RootConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=RootConfig.class,loader=AnnotationConfigContextLoader.class)
@ContextConfiguration(classes={ RootConfig.class, PersistenceConfig.class})
@WebAppConfiguration
public class NotificationServiceTest {

	@Autowired
	private NotificationService notificationService;
  //  private CaseService caseService;
	@Test
	public void test() {
		notificationService.createNotification();
	}

}
