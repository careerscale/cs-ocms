package in.careerscale.apps.ocms.notification;

import in.careerscale.apps.ocms.dao.NotificationRepository;
import in.careerscale.apps.ocms.dao.model.CaseMaster;
import in.careerscale.apps.ocms.dao.model.LoginMaster;
import in.careerscale.apps.ocms.dao.model.Notification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("notificationService")
public class NotificationService {

		Log log = LogFactory.getLog(NotificationService.class);
		
		@Autowired
		private NotificationRepository notificationRepository;
		
		@SuppressWarnings({ "null", "null" })
		public void createNotification(){
			Notification notification = null ;
			
			 //notification = new Notification(Calendar.getInstance().getTime());
			LoginMaster loggedInUser = (LoginMaster) notificationRepository.getById(LoginMaster.class, 1);
			
			CaseMaster currentCase = (CaseMaster) notificationRepository.getById(CaseMaster.class, 1);
		// NotificationRecipient recipient = null;
		// NotificationStatus status = (NotificationStatus) notificationRepository.getById(NotificationStatus.class, 1);
		// NotificationTemplate template = (NotificationTemplate)
		// notificationRepository.getById(NotificationTemplate.class, 1);
		// notification = new Notification("this is recipient", Calendar.getInstance().getTime(),
		// Calendar.getInstance().getTime(), loggedInUser, loggedInUser, currentCase, recipient, status, template);
		//	 notification.setCreatedOn();
		// notificationRepository.save(notification);
		}
		
		
		
		
}
